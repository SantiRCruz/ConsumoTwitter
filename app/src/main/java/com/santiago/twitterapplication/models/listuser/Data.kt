package com.santiago.twitterapplication.models.listuser

class Data {
    var id= 0
    var email= ""
    var first_name = ""
    var last_name = ""
    var avatar = ""

    constructor(id: Int, email: String, first_name: String, last_name: String, avatar: String) {
        this.id = id
        this.email = email
        this.first_name = first_name
        this.last_name = last_name
        this.avatar = avatar
    }

    override fun toString(): String {
        return "Data(id=$id, email='$email', first_name='$first_name', last_name='$last_name', avatar='$avatar')"
    }

}