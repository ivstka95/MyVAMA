package karpiuk.ivan.retrofit

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karpiuk.ivan.repository.NetworkFeedSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitModule {

    @Binds
    fun bindsNiaNetwork(
        retrofitFeedSource: RetrofitFeedSource
    ): NetworkFeedSource

    companion object {
        @Provides
        @Singleton
        fun providesFeedApiService(): FeedApiService {
            return Retrofit.Builder()
                .baseUrl(FEED_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeedApiService::class.java)
        }
    }
}