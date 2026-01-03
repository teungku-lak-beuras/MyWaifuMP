package heaven.from.repository

import heaven.from.model.MyWaifuState
import heaven.from.model.WaifuModelV1
import heaven.from.network.NekosBestApiDataSource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException

class NekosBestApiRepository(
    private val nekosBestApiDataSource: NekosBestApiDataSource
) {
    fun getWaifu(amount: Int): Flow<MyWaifuState<List<WaifuModelV1>>> = flow {
        emit(MyWaifuState.Loading)

        try {
            val waifus = mutableListOf<WaifuModelV1>()
            val response = nekosBestApiDataSource.getWaifu(amount)

            for (i in response.results) {
                waifus.add(
                    WaifuModelV1(
                        artistHref = i.artistHref,
                        artistName = i.artistName,
                        sourceUrl = i.sourceUrl,
                        url = i.url
                    )
                )
            }
            emit(MyWaifuState.Success(data = waifus))
        }
        // 4xx HTTP error.
        catch (exception: ClientRequestException) {
            emit(MyWaifuState.Error(message = "HTTP 400 Error: ${exception.message.toString()}"))
        }
        // 5xx HTTP error.
        catch (exception: ServerResponseException) {
            emit(MyWaifuState.Error(message = "HTTP 500 Error: ${exception.message.toString()}"))
        }
        // Serialisation
        catch (exception: SerializationException) {
            emit(MyWaifuState.Error(message = "Serialisation Error: ${exception.message.toString()}"))
        }
        // IO
        catch (exception: IOException) {
            emit(MyWaifuState.Error(message = "IO Error: ${exception.message.toString()}"))
        }
        // Bruh
        catch (exception: Exception) {
            emit(MyWaifuState.Error(message = "Unexpected Error: ${exception.message.toString()}"))
        }
    }
}
