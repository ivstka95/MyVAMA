package karpiuk.ivan.retrofit.model

data class RestFeed(
    val author: RestAuthor,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<RestLink>,
    val results: List<RestResult>,
    val title: String,
    val updated: String
)