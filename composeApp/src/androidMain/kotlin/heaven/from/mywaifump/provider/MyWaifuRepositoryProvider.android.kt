package heaven.from.mywaifump.provider

import android.content.Context
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
    // Defined with MyLazyWaifu for caching so that there is no need to check for multiple
    // reinitialisation.
    private var myWaifuRepository = MyLazyWaifu<Context, MyWaifuRepository> { context ->
        // --- Network ---
        val engine = NekosBestApiProvider.provideEngine()
        val nekosBestApiDataSource = NekosBestApiDataSource(
            client = NekosBestApiProvider.provideHttpClient(engine = engine)
        )
        val nekosBestApiRepository = NekosBestApiRepository(
            nekosBestApiDataSource = nekosBestApiDataSource
        )

        // --- Local ---
        val myWaifuRoomDatabaseBuilder = MyWaifuRoomProvider.getRoomDatabaseBuilder(context)
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
    val a get() = myWaifuRepository

    actual fun provideRepository(parameter: Any?): Flow<MyWaifuState<MyWaifuRepository>> {
        return flow {
            // --- Emission loading ---
            emit(MyWaifuState.Loading)

            try {
                // `parameter` could be type a of Context? and its value is null.
                if (parameter != null && parameter is Context) {
                    // -- Emission success ---
                    emit(
                        MyWaifuState.Success(
                            data = myWaifuRepository.get(transmitter = parameter)
                        )
                    )
                }
                else {
                    // --- Emission error ---
                    emit(
                        MyWaifuState.Error(
                            message = """
                            !!! STOP !!!
                            
                            You provided `$parameter` with type of `${
                                parameter?.javaClass?.simpleName ?: null.toString()
                            } but the required parameter type is `android.content.Context`. Please
                            provide the value with correct data type.
                            
                            This is a development fatal error and you need to recompile the project
                            with the given suggestion.
                        """.trimIndent()
                        )
                    )
                }
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
