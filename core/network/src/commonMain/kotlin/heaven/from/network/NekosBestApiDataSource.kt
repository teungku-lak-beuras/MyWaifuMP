package heaven.from.network

import heaven.from.network.response.NekosBestApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
class NekosBestApiDataSource(
    private val client: HttpClient
) {
    suspend fun getWaifu(amount: Int): NekosBestApiResponse {
        return client.get(
            urlString = "waifu"
        ) {
            url {
                parameters.append("amount", amount.toString())
            }
        }.body()
    }
}
