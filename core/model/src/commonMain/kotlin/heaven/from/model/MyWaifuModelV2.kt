package heaven.from.model

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class MyWaifuModelV2
@OptIn(ExperimentalUuidApi::class)
constructor(
    val id: String = Uuid.generateV7().toString(),
    val cdnImageUrl: String,
    val cdnCompressedImageUrl: String,
    val imageSourceUrl: String,
    val directImageSourceUrl: String,
    val category: String,
    val rating: String,
    val tags: List<String>,
    val artistName: String,
    val artistUrl: String,
    val copyright: String
)
