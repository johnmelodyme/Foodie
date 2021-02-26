package com.johnmelodyme.foodie.Database

open class SqlQuery
{

    companion object
    {
        val userQuery: String =
            "CREATE TABLE IF NOT EXISTS FOODIE (Id INTEGER PRIMARY KEY AUTOINCREMENT ,  posts VARCHAR, image BLOG)"
    }
}
