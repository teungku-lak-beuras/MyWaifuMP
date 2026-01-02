package heaven.from.mywaifump.provider

import android.content.Context
import heaven.from.local.MyWaifuLocalDataSource
import heaven.from.local.dao.MyWaifuRoomDao
import heaven.from.local.provider.MyWaifuRoomProvider
import heaven.from.local.utility.LazyWaifu
import heaven.from.repository.MyWaifuLocalRepository
import heaven.from.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

actual object RepositoryProvider {
    private lateinit var myWaifuRoomDao: MyWaifuRoomDao

    fun initialiseMyWaifuRoomDao(context: Context): Flow<Boolean> = flow {
        emit(true)
        myWaifuRoomDao = LazyWaifu<Context, MyWaifuRoomDao> { context ->
            val myWaifuRoomDatabaseBuilder = MyWaifuRoomProvider
                .getRoomDatabaseBuilder(context)
            val myWaifuRoomDatabase = MyWaifuRoomProvider
                .getRoomDatabase(myWaifuRoomDatabaseBuilder)
            myWaifuRoomDatabase.getDao()
        }.get(context)
        emit(false)
    }

    fun provideLocalDataSource(): MyWaifuLocalDataSource {
        return MyWaifuLocalDataSource(this.myWaifuRoomDao)
    }

    fun provideLocalRepository(): MyWaifuLocalRepository {
        return MyWaifuLocalRepository(provideLocalDataSource())
    }

    actual fun provideRepository(): Repository {
        return Repository(provideLocalRepository())
    }
}
