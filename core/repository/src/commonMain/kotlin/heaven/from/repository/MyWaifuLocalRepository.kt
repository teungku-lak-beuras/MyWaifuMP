package heaven.from.repository

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.flow.Flow

expect class MyWaifuLocalRepository(
    myWaifuLocalDataSource: MyWaifuLocalDataSourceContract
) {
    fun getAllWaifu(): Flow<ApiState<List<WaifuModelV1>>>
    fun insertWaifu(waifu: WaifuModelV1)
}
