package heaven.from.mywaifump.provider

import heaven.from.model.MyWaifuState
import heaven.from.repository.MyWaifuRepository
import kotlinx.coroutines.flow.StateFlow

expect object MyWaifuRepositoryProvider {
    val repository: StateFlow<MyWaifuState<MyWaifuRepository>>
    fun provideRepository(parameter: Any? = null)
}
