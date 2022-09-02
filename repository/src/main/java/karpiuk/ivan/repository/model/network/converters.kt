package karpiuk.ivan.repository.model.network

import karpiuk.ivan.model.Author
import karpiuk.ivan.model.Feed
import karpiuk.ivan.model.Genre
import karpiuk.ivan.model.Link
import karpiuk.ivan.model.Result

internal fun NetworkFeed.asDomainModel(): Feed = Feed(
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
