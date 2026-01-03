package heaven.from.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import heaven.from.local.dao.MyWaifuRoomDao
import heaven.from.local.entity.MyWaifuRoomEntity

@Database(entities = [MyWaifuRoomEntity::class], version = 1, exportSchema = false)
abstract class MyWaifuRoomDatabase : RoomDatabase() {
    abstract fun getDao(): MyWaifuRoomDao
}
