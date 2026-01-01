package heaven.from.repository

import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import heaven.from.network.NekosBestApiDataSource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import org.koin.core.annotation.Single

@Single
class NekosBestApiRepository(
    private val nekosBestApiDataSource: NekosBestApiDataSource
) {
    fun getWaifu(amount: Int): Flow<ApiState<List<WaifuModelV1>>> = flow {
        emit(ApiState.Loading)

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
            emit(ApiState.Success(data = waifus))
        }
        // 4xx HTTP error.
        catch (exception: ClientRequestException) {
            emit(ApiState.Error(error = "HTTP 400 Error: ${exception.message.toString()}"))
        }
        // 5xx HTTP error.
        catch (exception: ServerResponseException) {
            emit(ApiState.Error(error = "HTTP 500 Error: ${exception.message.toString()}"))
        }
        // Serialisation
        catch (exception: SerializationException) {
            emit(ApiState.Error(error = "Serialisation Error: ${exception.message.toString()}"))
        }
        // IO
        catch (exception: IOException) {
            emit(ApiState.Error(error = "IO Error: ${exception.message.toString()}"))
        }
        // Bruh
        catch (exception: Exception) {
            emit(ApiState.Error(error = "Unexpected Error: ${exception.message.toString()}"))
        }
    }
}
