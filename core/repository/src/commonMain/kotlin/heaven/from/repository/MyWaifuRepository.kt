package heaven.from.repository

import heaven.from.model.MyWaifuModelV2
import heaven.from.model.MyWaifuState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MyWaifuRepository(
    private val myWaifuLocalRepository: MyWaifuLocalRepository,
    private val nekosBestApiRepository: NekosBestApiRepository,
    private val nekosiaCatApiRepository: NekosiaCatApiRepository
) {
    fun getNetworkWaifu(amount: Int): Flow<MyWaifuState<List<WaifuModelV1>>> {
        return nekosBestApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }

    fun getLocalWaifu(): Flow<MyWaifuState<List<WaifuModelV1>>> {
        return myWaifuLocalRepository
            .getAllWaifu()
            .flowOn(Dispatchers.Default)
    }

    fun getNekosiaWaifu(amount: Int): Flow<MyWaifuState<List<MyWaifuModelV2>>> {
        return nekosiaCatApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }
}
