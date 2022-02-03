package com.geno1024.util.networking

actual object HTTP
{
    actual fun GET(url: String, targetLocation: String)
    {
    }

    actual val proxy: String
        get() = TODO("Not yet implemented")
    actual val UA: String
        get() = TODO("Not yet implemented")
    actual val no_proxy: String
        get() = TODO("Not yet implemented")
}
