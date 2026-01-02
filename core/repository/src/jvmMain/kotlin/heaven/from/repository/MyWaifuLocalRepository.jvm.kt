package heaven.from.repository

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.flow.Flow

actual class MyWaifuLocalRepository actual constructor(
    private val myWaifuLocalDataSource: MyWaifuLocalDataSourceContract
) {
    actual fun getAllWaifu(): Flow<ApiState<List<WaifuModelV1>>> {
        TODO("Not yet implemented")
    }

    actual fun insertWaifu(waifu: WaifuModelV1) {
    }
}
