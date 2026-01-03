package heaven.from.local.provider

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import heaven.from.local.database.MyWaifuRoomDatabase
import kotlinx.coroutines.Dispatchers
import java.io.File

object MyWaifuRoomProvider {
    fun getRoomDatabaseBuilder(): RoomDatabase.Builder<MyWaifuRoomDatabase> {
        return Room.databaseBuilder<MyWaifuRoomDatabase>(
            name = File(
                System.getProperty("java.io.tmpdir"),
                "mywaifu.db"
            ).absolutePath
        )
    }

    fun getRoomDatabase(
        builder: RoomDatabase.Builder<MyWaifuRoomDatabase>
    ): MyWaifuRoomDatabase {
        return builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
