package heaven.from.mywaifump.provider

import heaven.from.model.MyWaifuState
import heaven.from.repository.MyWaifuRepository
import kotlinx.coroutines.flow.Flow

actual object MyWaifuRepositoryProvider {
    actual fun provideRepository(): Flow<MyWaifuState<MyWaifuRepository>> {
        TODO("Not yet implemented")
    }
}
