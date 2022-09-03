package karpiuk.ivan.realm.model

import io.realm.kotlin.types.RealmObject

class RealmLink() : RealmObject {
    var self: String = ""

    constructor(self: String) : this() {
        this.self = self
    }
}