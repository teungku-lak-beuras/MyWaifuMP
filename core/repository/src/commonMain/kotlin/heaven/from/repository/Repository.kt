package heaven.from.repository

import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class Repository(
    private val nekosBestApiRepository: NekosBestApiRepository
) {
    fun getNetworkWaifu(amount: Int): Flow<ApiState<List<WaifuModelV1>>> {
        return nekosBestApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }
}
