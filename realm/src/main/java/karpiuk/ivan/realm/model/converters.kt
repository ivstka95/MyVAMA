package karpiuk.ivan.realm.model

import io.realm.kotlin.ext.toRealmList
import karpiuk.ivan.repository.model.local.*

internal fun LocalFeed.asRealmObject() = RealmFeed(
    author = this.author?.asRealmObject(),
    copyright = this.copyright,
    country = this.country,
    icon = this.icon,
    id = this.id,
    links = this.links.map { it.asRealmObject() }.toRealmList(),
    results = this.results.map { it.asRealmObject() }.toRealmList(),
    title = this.title,
    updated = this.updated
)

internal fun LocalAuthor.asRealmObject() = RealmAuthor(name = this.name, url = this.url)

internal fun LocalLink.asRealmObject() = RealmLink(self = this.self)

internal fun LocalResult.asRealmObject() = RealmResult(
    artistId = this.artistId,
    artistName = this.artistName,
    artistUrl = this.artistUrl,
    artworkUrl100 = this.artworkUrl100,
    contentAdvisoryRating = this.contentAdvisoryRating,
    genres = this.genres.map { it.asRealmObject() }.toRealmList(),
    id = this.id,
    kind = this.kind,
    name = this.name,
    releaseDate = this.releaseDate,
    url = this.url
)

internal fun LocalGenre.asRealmObject() = RealmGenre(
    genreId = this.genreId,
    name = this.name,
    url = this.url
)

internal fun RealmFeed.asExternalModel() = LocalFeed(
    author = this.author?.asExternalModel(),
    copyright = this.copyright,
    country = this.country,
    icon = this.icon,
    id = this.id,
    links = this.links.map { it.asExternalModel() },
    results = this.results.map { it.asExternalModel() },
    title = this.title,
    updated = this.updated
)

internal fun RealmAuthor.asExternalModel() = LocalAuthor(name = this.name, url = this.url)

internal fun RealmLink.asExternalModel() = LocalLink(self = this.self)

internal fun RealmResult.asExternalModel() = LocalResult(
    artistId = this.artistId,
    artistName = this.artistName,
    artistUrl = this.artistUrl,
    artworkUrl100 = this.artworkUrl100,
    contentAdvisoryRating = this.contentAdvisoryRating,
    genres = this.genres.map { it.asExternalModel() },
    id = this.id,
    kind = this.kind,
    name = this.name,
    releaseDate = this.releaseDate,
    url = this.url
)

internal fun RealmGenre.asExternalModel() = LocalGenre(
    genreId = this.genreId,
    name = this.name,
    url = this.url
)
