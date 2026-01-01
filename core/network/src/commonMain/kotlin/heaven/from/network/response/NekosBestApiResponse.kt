package heaven.from.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NekosBestApiResponse(
    @SerialName("results") val results: List<NekosBestApiWaifu>
)

@Serializable
data class NekosBestApiWaifu(
    @SerialName("artist_href") val artistHref: String,
    @SerialName("artist_name") val artistName: String,
    @SerialName("source_url") val sourceUrl: String,
    @SerialName("url") val url: String
)
