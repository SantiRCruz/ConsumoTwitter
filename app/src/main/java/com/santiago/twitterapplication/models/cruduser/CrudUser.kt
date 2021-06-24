package com.santiago.twitterapplication.models.cruduser

class CrudUser {
     var name = ""
    var job =  ""


    constructor()

    constructor(name: String, job: String) {
        this.name = name
        this.job = job
    }

    override fun toString(): String {
        return "CrudUser(name='$name', job='$job')"
    }

}