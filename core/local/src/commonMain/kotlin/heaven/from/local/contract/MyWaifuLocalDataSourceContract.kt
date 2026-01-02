package heaven.from.local.contract

import heaven.from.local.entity.MyWaifuEntity

interface MyWaifuLocalDataSourceContract {
    suspend fun getAllWaifu(): List<MyWaifuEntity>
    suspend fun insertWaifu(waifu: MyWaifuEntity)
}
