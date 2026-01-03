package heaven.from.repository

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.model.MyWaifuState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.flow.Flow

expect class MyWaifuLocalRepository(
    myWaifuLocalDataSource: MyWaifuLocalDataSourceContract
) {
    fun getAllWaifu(): Flow<MyWaifuState<List<WaifuModelV1>>>
    fun insertWaifu(waifu: WaifuModelV1)
}
