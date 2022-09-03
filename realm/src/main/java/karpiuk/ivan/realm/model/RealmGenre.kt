package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject

class RealmGenre() : RealmObject {
    var genreId: String = ""
    var name: String = ""
    var url: String = ""
}