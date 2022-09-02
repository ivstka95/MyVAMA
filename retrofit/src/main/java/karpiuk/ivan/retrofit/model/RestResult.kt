package karpiuk.ivan.retrofit.model

data class RestResult(
    val artistId: String?,
    val artistName: String,
    val artistUrl: String?,
    val artworkUrl100: String,
    val contentAdvisoryRating: String?,
    val genres: List<RestGenre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)