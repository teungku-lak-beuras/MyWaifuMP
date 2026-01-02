package heaven.from.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import heaven.from.local.entity.MyWaifuRoomEntity

@Dao
interface MyWaifuRoomDao {
    @Query("SELECT * from MyWaifuMP")
    suspend fun getAllWaifu(): List<MyWaifuRoomEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWaifu(waifuEntity: MyWaifuRoomEntity)
}
