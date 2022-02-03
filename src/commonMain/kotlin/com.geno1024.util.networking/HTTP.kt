package com.geno1024.util.networking

expect object HTTP
{
    val proxy: String

    val no_proxy: String

    val UA: String

    fun GET(url: String, targetLocation: String = "-")
}
