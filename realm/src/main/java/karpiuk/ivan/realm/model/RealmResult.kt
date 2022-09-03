package karpiuk.ivan.realm.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class RealmResult() : RealmObject {
    var artistId: String? = null
    var artistName: String = ""
    var artistUrl: String? = null
    var artworkUrl100: String = ""
    var contentAdvisoryRating: String? = null
    var genres: RealmList<RealmGenre> = realmListOf()
    var id: String = ""
    var kind: String = ""
    var name: String = ""
    var releaseDate: String = ""
    var url: String = ""

    constructor(
        artistId: String?,
        artistName: String,
        artistUrl: String?,
        artworkUrl100: String,
        contentAdvisoryRating: String?,
        genres: RealmList<RealmGenre>,
        id: String,
        kind: String,
        name: String,
        releaseDate: String,
        url: String
    ) : this() {
        this.artistId = artistId
        this.artistName = artistName
        this.artistUrl = artistUrl
        this.artworkUrl100 = artworkUrl100
        this.contentAdvisoryRating = contentAdvisoryRating
        this.genres = genres
        this.id = id
        this.kind = kind
        this.name = name
        this.releaseDate = releaseDate
        this.url = url
    }
}