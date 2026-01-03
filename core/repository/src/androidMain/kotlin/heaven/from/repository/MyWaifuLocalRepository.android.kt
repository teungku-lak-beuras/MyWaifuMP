package heaven.from.repository

import heaven.from.local.contract.MyWaifuLocalDataSourceContract
import heaven.from.model.MyWaifuState
import heaven.from.model.WaifuModelV1
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException

actual class MyWaifuLocalRepository actual constructor(
    private val myWaifuLocalDataSource: MyWaifuLocalDataSourceContract
) {
    actual fun getAllWaifu() = flow<MyWaifuState<List<WaifuModelV1>>> {
        emit(MyWaifuState.Loading)

        try {
            val roomWaifu = myWaifuLocalDataSource.getAllWaifu()
            val waifu = mutableListOf<WaifuModelV1>()

            for (i in roomWaifu) {
                waifu.add(
                    WaifuModelV1(
                        artistHref = i.artistHref,
                        artistName = i.artistName,
                        sourceUrl = i.sourceUrl,
                        url = i.url
                    )
                )
            }
            emit(MyWaifuState.Success(data = waifu))
        }
        catch (exception: IOException) {
            emit(MyWaifuState.Error(message = exception.message.toString()))
        }
    }

    actual fun insertWaifu(waifu: WaifuModelV1) {
    }
}
