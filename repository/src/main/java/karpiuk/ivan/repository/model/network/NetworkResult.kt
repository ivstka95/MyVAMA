package karpiuk.ivan.repository.model.network

data class NetworkResult(
    val artistId: String?,
    val artistName: String,
    val artistUrl: String?,
    val artworkUrl100: String,
    val contentAdvisoryRating: String?,
    val genres: List<NetworkGenre>,
    val id: String,
    val kind: String,
    val name: String,
    val releaseDate: String,
    val url: String
)