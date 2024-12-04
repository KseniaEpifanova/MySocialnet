package com.example.mysocialnet.models.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserDB : RealmObject {
    @PrimaryKey
    var id: String = ""
    var username: String = ""
    var password: String = ""
}
