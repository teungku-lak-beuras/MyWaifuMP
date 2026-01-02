package heaven.from.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyWaifuMP")
data class MyWaifuRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val artistHref: String,
    val artistName: String,
    val sourceUrl: String,
    val url: String
)
