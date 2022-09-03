package karpiuk.ivan.realm.model

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject

class RealmAuthor() : RealmObject {
    var name: String = ""
    var url: String = ""

    constructor(name: String, url: String) : this() {
        this.name = name
        this.url = url
    }
}