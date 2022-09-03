package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmFeed() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var author: RealmAuthor = RealmAuthor()
    var copyright: String = ""
    var country: String = ""
    var icon: String = ""
    var links: List<RealmLink> = emptyList()
    var results: List<RealmResult> = emptyList()
    var title: String = ""
    var updated: String = ""
}