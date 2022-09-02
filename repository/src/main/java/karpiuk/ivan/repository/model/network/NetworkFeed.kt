package karpiuk.ivan.repository.model.network

data class NetworkFeed(
    val author: NetworkAuthor,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<NetworkLink>,
    val results: List<NetworkResult>,
    val title: String,
    val updated: String
)