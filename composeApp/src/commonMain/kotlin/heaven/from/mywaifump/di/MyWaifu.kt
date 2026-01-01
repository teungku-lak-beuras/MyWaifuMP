package heaven.from.mywaifump.di

import heaven.from.mywaifump.viewmodel.ViewModelModule
import heaven.from.repository.RepositoryModule
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module

@Configuration
@Module(
    includes = [
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
class MyWaifuModule

@KoinApplication
object MyWaifuApp

