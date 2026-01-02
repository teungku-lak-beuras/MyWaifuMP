package heaven.from.repository

import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class Repository(
    private val myWaifuLocalRepository: MyWaifuLocalRepository
) {
    private val nekosBestApiRepository: NekosBestApiRepository by lazy {
        NekosBestApiRepository()
    }

    fun getNetworkWaifu(amount: Int): Flow<ApiState<List<WaifuModelV1>>> {
        return nekosBestApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }
    fun getLocalWaifu(): Flow<ApiState<List<WaifuModelV1>>> {
        return myWaifuLocalRepository
            .getAllWaifu()
            .flowOn(Dispatchers.Default)
    }
}
