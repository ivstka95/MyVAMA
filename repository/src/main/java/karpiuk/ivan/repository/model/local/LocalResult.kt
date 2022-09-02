package karpiuk.ivan.repository.model.local

data class LocalResult(
    val artistId: String?,
    val artistName: String,
    val artistUrl: String?,
    val artworkUrl100: String,
    val contentAdvisoryRating: String?,
    val genres: List<LocalGenre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)