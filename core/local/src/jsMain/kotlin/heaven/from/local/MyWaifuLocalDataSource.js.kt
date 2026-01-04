package heaven.from.local

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.local.entity.MyWaifuEntity

actual class MyWaifuLocalDataSource : MyWaifuLocalDataSourceContract {
    override suspend fun getAllWaifu(): List<MyWaifuEntity> {
        return emptyList()
    }

    override suspend fun insertWaifu(waifu: MyWaifuEntity) {
    }
}
