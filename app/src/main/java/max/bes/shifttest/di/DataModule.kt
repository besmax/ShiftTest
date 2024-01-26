package max.bes.shifttest.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    companion object {
        private const val baseUrl = "https://randomuser.me/api"
    }
}