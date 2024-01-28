package max.bes.shifttest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import max.bes.shifttest.core.data.ExternalNavigatorImpl
import max.bes.shifttest.core.data.network.NetworkClient
import max.bes.shifttest.core.domain.ExternalNavigator
import max.bes.shifttest.users.data.UserRepositoryImpl
import max.bes.shifttest.users.data.db.dao.UserDao
import max.bes.shifttest.users.domain.repositories.UserRepository
import max.bes.shifttest.users.domain.usecases.GetUsersUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideUserRepository(networkClient: NetworkClient, dao: UserDao): UserRepository {
        return UserRepositoryImpl(networkClient, dao)
    }

    @Singleton
    @Provides
    fun provideGetUsersUseCase(repository: UserRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideExternalNavigator(@ApplicationContext appContext: Context): ExternalNavigator {
        return ExternalNavigatorImpl(appContext)
    }

}