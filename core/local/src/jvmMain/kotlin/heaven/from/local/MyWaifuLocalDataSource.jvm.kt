package heaven.from.local

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.local.dao.MyWaifuRoomDao
import heaven.from.local.entity.MyWaifuEntity
import heaven.from.local.entity.MyWaifuRoomEntity

actual class MyWaifuLocalDataSource(
    private val dao: MyWaifuRoomDao
) : MyWaifuLocalDataSourceContract {
    override suspend fun getAllWaifu(): List<MyWaifuEntity> {
        val roomWaifu = dao.getAllWaifu()
        val waifu = mutableListOf<MyWaifuEntity>()

        for (i in roomWaifu) {
            waifu.add(
                MyWaifuEntity(
                    artistHref = i.artistHref,
                    artistName = i.artistName,
                    sourceUrl = i.sourceUrl,
                    url = i.url
                )
            )
        }
        return waifu
    }

    override suspend fun insertWaifu(waifu: MyWaifuEntity) {
        dao.insertWaifu(
            MyWaifuRoomEntity(
                id = 0L,
                artistHref = waifu.artistHref,
                artistName = waifu.artistName,
                sourceUrl = waifu.sourceUrl,
                url = waifu.url
            )
        )
    }
}
