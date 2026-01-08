package heaven.from.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NekosiaCatApiResponse(
    @SerialName("success") val success: Boolean,
    @SerialName("status") val status: Int,
    @SerialName("count") val count: Int,
    @SerialName("images") val images: List<NekosiaImages>
)

@Serializable
data class NekosiaImages(
    @SerialName("id") val id: String,
    @SerialName("colors") val colors: NekosiaColors,
    @SerialName("image") val image: NekosiaImage,
    @SerialName("metadata") val metadata: NekosiaMetadata,
    @SerialName("category") val category: String,
    @SerialName("tags") val tags: List<String>,
    @SerialName("rating") val rating: String,
    @SerialName("anime") val anime: NekosiaAnime,
    @SerialName("source") val source: NekosiaSource,
    @SerialName("attribution") val attribution: NekosiaAttribution
)

@Serializable
data class NekosiaColors(
    @SerialName("main") val main: String,
    @SerialName("palette") val palette: List<String>
)

@Serializable
data class NekosiaImage(
    @SerialName("original") val original: NekosiaImageType,
    @SerialName("compressed") val compressed: NekosiaImageType
)

@Serializable
data class NekosiaImageType(
    @SerialName("url") val url: String,
    @SerialName("extension") val extension: String
)

@Serializable
data class NekosiaMetadata(
    @SerialName("original") val original: NekosiaMetadataType,
    @SerialName("compressed") val compressed: NekosiaMetadataType
)

@Serializable
data class NekosiaMetadataType(
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("size") val size: Int,
    @SerialName("extension") val extension: String
)

@Serializable
data class NekosiaAnime(
    @SerialName("title") val title: String = "Unspecified",
    @SerialName("character") val character: String = "Unspecified"
)

@Serializable
data class NekosiaSource(
    @SerialName("url") val url: String,
    @SerialName("direct") val direct: String
)

@Serializable
data class NekosiaAttribution(
    @SerialName("artist") val artist: NekosiaArtist,
    @SerialName("copyright") val copyright: String = "Unspecified"
)

@Serializable
data class NekosiaArtist(
    @SerialName("username") val username: String = "Unspecified",
    @SerialName("profile") val profile: String = "Unspecified"
)
