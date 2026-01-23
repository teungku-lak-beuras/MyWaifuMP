package heaven.from.mywaifump.provider

import heaven.from.local.MyWaifuLocalDataSource
import heaven.from.local.provider.MyWaifuRoomProvider
import heaven.from.model.MyWaifuState
import heaven.from.network.NekosBestApiDataSource
import heaven.from.network.NekosiaCatApiDataSource
import heaven.from.network.provider.NekosBestApiProvider
import heaven.from.network.provider.NekosiaCatApiProvider
import heaven.from.repository.MyWaifuLocalRepository
import heaven.from.repository.MyWaifuRepository
import heaven.from.repository.NekosBestApiRepository
import heaven.from.repository.NekosiaCatApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.io.IOException

actual object MyWaifuRepositoryProvider {
    private val _repository = MutableStateFlow<MyWaifuState<MyWaifuRepository>>(
        MyWaifuState.Loading
    )
    actual val repository = _repository.asStateFlow()

    actual fun provideRepository(parameter: Any?) {
        // --- Multiple times reinstantiating prevention ---
        if (_repository.value is MyWaifuState.Success) {
            return
        }

        // --- Emission loading ---
        _repository.value = MyWaifuState.Loading

        try {
            // --- Network ---
            val engine = NekosBestApiProvider.provideEngine()
            val nekosBestApiDataSource = NekosBestApiDataSource(
                client = NekosBestApiProvider.provideHttpClient(
                    engine = engine
                )
            )
            val nekosBestApiRepository = NekosBestApiRepository(
                nekosBestApiDataSource = nekosBestApiDataSource
            )

            val nekosiaCatApiEngine = NekosiaCatApiProvider.provideEngine()
            val nekosiaCatApiDataSource = NekosiaCatApiDataSource(
                client = NekosiaCatApiProvider.provideHttpClient(
                    engine = nekosiaCatApiEngine
                )
            )
            val nekosiaCatApiRepository = NekosiaCatApiRepository(
                nekosiaCatApiDataSource = nekosiaCatApiDataSource
            )

            // --- Local ---
            val myWaifuRoomDatabaseBuilder = MyWaifuRoomProvider.getRoomDatabaseBuilder()
            val myWaifuRoomDatabase = MyWaifuRoomProvider.getRoomDatabase(
                builder = myWaifuRoomDatabaseBuilder
            )
            val myWaifuLocalDataSource = MyWaifuLocalDataSource(
                dao = myWaifuRoomDatabase.getDao()
            )
            val myWaifuLocalRepository = MyWaifuLocalRepository(
                myWaifuLocalDataSource = myWaifuLocalDataSource
            )

            // --- Emission success ---
            _repository.value = MyWaifuState.Success(
                data = MyWaifuRepository(
                    myWaifuLocalRepository = myWaifuLocalRepository,
                    nekosBestApiRepository = nekosBestApiRepository,
                    nekosiaCatApiRepository = nekosiaCatApiRepository
                )
            )
        }
        catch (exception: IOException) {
            _repository.value = MyWaifuState.Error(
                message = """
                    !!! ALERT !!!

                    Undefined exception caught: ${exception.message.toString()}.

                    This is a fatal strange error that should not happen at all. Tell the developer
                    to investigate this.
                """.trimIndent()
            )
        }
    }
}
