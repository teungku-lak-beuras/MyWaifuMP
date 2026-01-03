package heaven.from.mywaifump.provider

import heaven.from.local.MyWaifuLocalDataSource
import heaven.from.local.provider.MyWaifuRoomProvider
import heaven.from.model.MyWaifuState
import heaven.from.mywaifump.utility.MyLazyWaifu
import heaven.from.network.NekosBestApiDataSource
import heaven.from.network.provider.NekosBestApiProvider
import heaven.from.repository.MyWaifuLocalRepository
import heaven.from.repository.MyWaifuRepository
import heaven.from.repository.NekosBestApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException

actual object MyWaifuRepositoryProvider {
    private var myWaifuRepository = MyLazyWaifu<Boolean, MyWaifuRepository> { transmitter ->
        val engine = NekosBestApiProvider.provideEngine()
        val nekosBestApiDataSource = NekosBestApiDataSource(
            client = NekosBestApiProvider.provideHttpClient(engine = engine)
        )
        val nekosBestApiRepository = NekosBestApiRepository(
            nekosBestApiDataSource = nekosBestApiDataSource
        )

        val myWaifuRoomDatabaseBuilder = MyWaifuRoomProvider.getRoomDatabaseBuilder()
        val myWaifuRoomDatabase = MyWaifuRoomProvider.getRoomDatabase(myWaifuRoomDatabaseBuilder)
        val myWaifuLocalDataSource = MyWaifuLocalDataSource(dao = myWaifuRoomDatabase.getDao())
        val myWaifuLocalRepository = MyWaifuLocalRepository(
            myWaifuLocalDataSource = myWaifuLocalDataSource
        )

        // --- Result ---
        MyWaifuRepository(
            myWaifuLocalRepository = myWaifuLocalRepository,
            nekosBestApiRepository = nekosBestApiRepository
        )
    }

    actual fun provideRepository(parameter: Any?): Flow<MyWaifuState<MyWaifuRepository>> {
        return flow {
            // --- Emission loading ---
            emit(MyWaifuState.Loading)

            try {
                // -- Emission success ---
                // Transmitter is trash value since JVM target does not need context injection like
                // in Android counterpart.
                emit(
                    MyWaifuState.Success(
                        data = myWaifuRepository.get(transmitter = true)
                    )
                )
            }
            catch (exception: IOException) {
                // --- Emission undefined behaviour ---
                emit(
                    MyWaifuState.Error(
                        message = """
                            !!! ALERT !!!
                            
                            Undefined exception caught: ${exception.message.toString()}.
                            
                            This is a fatal strange error that should not happen at all. Tell
                            the developer to investigate this.
                        """.trimIndent()
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    actual fun provideCachedRepository(): MyWaifuRepository {
        return myWaifuRepository.getCached() ?: throw NullPointerException(
            """
                ${'$'}{MyWaifuRepository::class.java.simpleName} is not yet initialised, let alone
                cached. Fix your code and call this after it is initialised.
            """.trimIndent()
        )
    }
}
