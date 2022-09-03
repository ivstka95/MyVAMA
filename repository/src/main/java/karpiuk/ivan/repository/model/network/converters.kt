package karpiuk.ivan.repository.model.network

import karpiuk.ivan.model.Author
import karpiuk.ivan.model.Feed
import karpiuk.ivan.model.Genre
import karpiuk.ivan.model.Link
import karpiuk.ivan.model.Result
import karpiuk.ivan.repository.model.local.*
import karpiuk.ivan.repository.model.network.asDomainModel

internal fun NetworkFeed.asDomainModel() = Feed(
    author = this.author.asDomainModel(),
    copyright = this.copyright,
    country = this.country,
    icon = this.icon,
    id = this.id,
    links = this.links.map { it.asDomainModel() },
    results = this.results.map { it.asDomainModel() },
    title = this.title,
    updated = this.updated
)

internal fun NetworkAuthor.asDomainModel() = Author(name = this.name, url = this.url)

internal fun NetworkLink.asDomainModel() = Link(self = this.self)

internal fun NetworkResult.asDomainModel() = Result(
    artistId = this.artistId,
    artistName = this.artistName,
    artistUrl = this.artistUrl,
    artworkUrl100 = this.artworkUrl100,
    contentAdvisoryRating = this.contentAdvisoryRating,
    genres = this.genres.map { it.asDomainModel() },
    id = this.id,
    kind = this.kind,
    name = this.name,
    releaseDate = this.releaseDate,
    url = this.url
)

internal fun NetworkGenre.asDomainModel() = Genre(
    genreId = this.genreId,
    name = this.name,
    url = this.url
)


internal fun NetworkFeed.asLocalModel() = LocalFeed(
    author = this.author.asLocalModel(),
    copyright = this.copyright,
    country = this.country,
    icon = this.icon,
    id = this.id,
    links = this.links.map { it.asLocalModel() },
    results = this.results.map { it.asLocalModel() },
    title = this.title,
    updated = this.updated
)

internal fun NetworkAuthor.asLocalModel() = LocalAuthor(name = this.name, url = this.url)

internal fun NetworkLink.asLocalModel() = LocalLink(self = this.self)

internal fun NetworkResult.asLocalModel() = LocalResult(
    artistId = this.artistId,
    artistName = this.artistName,
    artistUrl = this.artistUrl,
    artworkUrl100 = this.artworkUrl100,
    contentAdvisoryRating = this.contentAdvisoryRating,
    genres = this.genres.map { it.asLocalModel() },
    id = this.id,
    kind = this.kind,
    name = this.name,
    releaseDate = this.releaseDate,
    url = this.url
)

internal fun NetworkGenre.asLocalModel() = LocalGenre(
    genreId = this.genreId,
    name = this.name,
    url = this.url
)
