package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject

class RealmGenre() : RealmObject {
    var genreId: String = ""
    var name: String = ""
    var url: String = ""

    constructor(genreId: String, name: String, url: String) : this() {
        this.genreId = genreId
        this.name = name
        this.url = url
    }
}