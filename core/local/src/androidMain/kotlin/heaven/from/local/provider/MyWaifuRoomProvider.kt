package heaven.from.local.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import heaven.from.local.database.MyWaifuRoomDatabase
import kotlinx.coroutines.Dispatchers

object MyWaifuRoomProvider {
    /**
     * Perhatikan!
     *
     * Tanda tangan dari fungsi statis ini patutlah serasi di antara sasaran Android, JVM
     * (desktop), pula dengan sasaran barang rakitan Apple (bila sungguh disasarkan).
     *
     * Pula demikian, pengamalan dari fungsi statis ini patutlah diamalkan dengan pen di antara
     * sasaran Android, JVM (desktop), pula dengan sasaran barang rakitan Apple (bila sungguh
     * disasarkan). Manakala tanda tangan pun pula pengalaman berselisih di antara ketiga
     * sasaran yang dipaparkan di muka, boleh lah mengakibatkan kesukaran tatkala perburuan
     *    hambatan di masa nanti.
     */
    fun getRoomDatabaseBuilder(context: Context): RoomDatabase.Builder<MyWaifuRoomDatabase> {
        return Room.databaseBuilder<MyWaifuRoomDatabase>(
            context = context.applicationContext,
            name = context.applicationContext.getDatabasePath("mywaifu.db").absolutePath
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
