package max.bes.shifttest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import max.bes.shifttest.core.data.db.AppDatabase
import max.bes.shifttest.core.data.network.NetworkClient
import max.bes.shifttest.core.data.network.RandomUserApiService
import max.bes.shifttest.core.data.network.RetrofitNetworkClient
import max.bes.shifttest.users.data.db.dao.UserDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "shift_test_database.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao
    }

    @Singleton
    @Provides
    fun provideNetworkClient(
        @ApplicationContext context: Context,
        userApi: RandomUserApiService
    ): NetworkClient {
        return RetrofitNetworkClient(context, userApi)
    }

    @Singleton
    @Provides
    fun provideRandomUserApiService(): RandomUserApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApiService::class.java)
    }

    companion object {
        private const val baseUrl = "https://randomuser.me/api"
    }
}