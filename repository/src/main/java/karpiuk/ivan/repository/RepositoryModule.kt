package karpiuk.ivan.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karpiuk.ivan.domain.FeedRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFeedRepository(
        defaultFeedRepository: DefaultFeedRepository
    ): FeedRepository
}