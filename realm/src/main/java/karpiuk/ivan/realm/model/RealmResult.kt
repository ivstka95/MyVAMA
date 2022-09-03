package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject

class RealmResult() : RealmObject {
    var artistId: String? = null
    var artistName: String = ""
    var artistUrl: String? = null
    var artworkUrl100: String = ""
    var contentAdvisoryRating: String? = null
    var genres: List<RealmGenre> = emptyList()
    var id: String = ""
    var kind: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var url: String = ""
}