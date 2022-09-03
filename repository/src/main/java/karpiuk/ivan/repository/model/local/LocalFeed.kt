package karpiuk.ivan.repository.model.local

data class LocalFeed(
    val author: LocalAuthor?,
    val copyright: String,
    val country: String,
    val icon: String,
    val id: String,
    val links: List<LocalLink>,
    val results: List<LocalResult>,
    val title: String,
    val updated: String
)