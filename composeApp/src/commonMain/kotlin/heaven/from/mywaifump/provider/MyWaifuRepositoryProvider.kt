package heaven.from.mywaifump.provider

import heaven.from.model.MyWaifuState
import heaven.from.repository.MyWaifuRepository
import kotlinx.coroutines.flow.Flow

expect object MyWaifuRepositoryProvider {
    fun provideRepository(parameter: Any? = null): Flow<MyWaifuState<MyWaifuRepository>>
    fun provideCachedRepository(): MyWaifuRepository
}
