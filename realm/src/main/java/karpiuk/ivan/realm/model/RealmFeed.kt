package karpiuk.ivan.realm.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmFeed() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var author: RealmAuthor? = RealmAuthor()
    var copyright: String = ""
    var country: String = ""
    var icon: String = ""
    var links: RealmList<RealmLink> = realmListOf()
    var results: RealmList<RealmResult> = realmListOf()
    var title: String = ""
    var updated: String = ""

    constructor(
        id: String,
        author: RealmAuthor?,
        copyright: String,
        country: String,
        icon: String,
        links: RealmList<RealmLink>,
        results: RealmList<RealmResult>,
        title: String,
        updated: String
    ) : this() {
        this.id = id
        this.author = author
        this.copyright = copyright
        this.country = country
        this.icon = icon
        this.links = links
        this.results = results
        this.title = title
        this.updated = updated
    }
}