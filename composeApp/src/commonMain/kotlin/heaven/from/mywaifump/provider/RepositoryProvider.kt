package heaven.from.mywaifump.provider

import heaven.from.repository.Repository

expect object RepositoryProvider {
    fun provideRepository(): Repository
}
