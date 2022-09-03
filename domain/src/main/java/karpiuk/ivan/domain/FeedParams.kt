package karpiuk.ivan.domain

data class FeedParams(
    val mediaType: String,
    val storefront: String,
    val type: String,
    val feed: String,
    val resultLimit: Int = 100,
    val format: String = Format.JSON
)