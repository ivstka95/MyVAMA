package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject

class RealmAuthor() : RealmObject {
    var name: String = ""
    var url: String = ""
}