package karpiuk.ivan.retrofit.model

import karpiuk.ivan.repository.model.network.*

fun RestFeed.asExternalModel(): NetworkFeed = NetworkFeed(
    author = this.author.asExternalModel(),
    copyright = this.copyright,
    country = this.country,
    icon = this.icon,
    id = this.id,
    links = this.links.map { it.asExternalModel() },
    results = this.results.map { it.asExternalModel() },
    title = this.title,
    updated = this.updated
)

fun RestAuthor.asExternalModel() = NetworkAuthor(name = this.name, url = this.url)

fun RestLink.asExternalModel() = NetworkLink(self = this.self)

fun RestResult.asExternalModel() = NetworkResult(
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

fun RestGenre.asExternalModel() = NetworkGenre(
    genreId = this.genreId,
    name = this.name,
    url = this.url
)
