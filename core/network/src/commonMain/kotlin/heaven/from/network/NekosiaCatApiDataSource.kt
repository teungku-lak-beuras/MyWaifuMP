package heaven.from.network

import heaven.from.network.response.NekosiaCatApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NekosiaCatApiDataSource(
    private val client: HttpClient
) {
    suspend fun getWaifu(amount: Int): NekosiaCatApiResponse {
        return client.get("/api/v1/images/random") {
            url {
                parameters.append("count", amount.toString())
            }
        }.body()
    }
}
