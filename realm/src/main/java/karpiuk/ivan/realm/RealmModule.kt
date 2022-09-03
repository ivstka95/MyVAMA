package karpiuk.ivan.realm

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import karpiuk.ivan.realm.model.*
import karpiuk.ivan.repository.LocalFeedSource
import karpiuk.ivan.repository.NetworkFeedSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RealmModule {

    @Binds
    fun bindsRealmDatabase(
        realmDatabase: RealmDatabase
    ): LocalFeedSource

    companion object {
        @Provides
        @Singleton
        fun providesRealm(): Realm = Realm.open(
            RealmConfiguration.Builder(
                schema = setOf(
                    RealmFeed::class,
                    RealmGenre::class,
                    RealmLink::class,
                    RealmResult::class,
                    RealmAuthor::class,
                )
            )
                .build()
        )
    }
}