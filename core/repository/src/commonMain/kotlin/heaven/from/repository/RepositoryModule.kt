package heaven.from.repository

import heaven.from.network.NekosBestApiModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        NekosBestApiModule::class
    ]
)
@ComponentScan
class RepositoryModule
