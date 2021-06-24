package com.santiago.twitterapplication.models.listuser

class ListUser {
    var page = 0
    var per_page = 6
    var total = 12
    var total_pages = 2
    var data : ArrayList<Data> = ArrayList()

    constructor(page: Int, per_page: Int, total: Int, total_pages: Int, data: ArrayList<Data>) {
        this.page = page
        this.per_page = per_page
        this.total = total
        this.total_pages = total_pages
        this.data = data
    }

    override fun toString(): String {
        return "ListUser(page=$page, per_page=$per_page, total=$total, total_pages=$total_pages, data=$data)"
    }

}