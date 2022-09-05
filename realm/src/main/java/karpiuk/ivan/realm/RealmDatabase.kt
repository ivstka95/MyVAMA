package karpiuk.ivan.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.notifications.SingleQueryChange
import karpiuk.ivan.realm.model.RealmFeed
import karpiuk.ivan.realm.model.asExternalModel
import karpiuk.ivan.realm.model.asRealmObject
import karpiuk.ivan.repository.LocalFeedSource
import karpiuk.ivan.repository.model.local.LocalFeed
import karpiuk.ivan.utils.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RealmDatabase @Inject constructor(
    private val realm: Realm,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : LocalFeedSource {
    override fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Flow<LocalFeed?> =
        realm.query(
            RealmFeed::class,
            "id CONTAINS '$mediaType' AND id CONTAINS '$storefront' AND id CONTAINS '$type' AND id CONTAINS '$feed' AND id CONTAINS '$resultLimit' AND id CONTAINS '$format'"
        )
            .first()
            .asFlow()
            .flowOn(ioDispatcher)
            .map { it.obj?.asExternalModel() }

    override suspend fun saveFeedToCache(localFeed: LocalFeed) {
        withContext(ioDispatcher) {
            realm.write {
                val realmFeed: RealmFeed? =
                    this.query<RealmFeed>("id = '${localFeed.id}'").first().find()
                if (realmFeed != null) {
                    realmFeed.author = localFeed.author?.asRealmObject()
                    realmFeed.copyright = localFeed.copyright
                    realmFeed.country = localFeed.country
                    realmFeed.icon = localFeed.icon
                    realmFeed.links = localFeed.links.map { it.asRealmObject() }.toRealmList()
                    realmFeed.results = localFeed.results.map { it.asRealmObject() }.toRealmList()
                    realmFeed.title = localFeed.title
                    realmFeed.updated = localFeed.updated
                } else {
                    copyToRealm(localFeed.asRealmObject())
                }
            }
        }
    }
}