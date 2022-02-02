package com.geno1024.util.networking

import kotlinx.cinterop.*
import platform.windows.*

actual object HTTP
{
    actual val proxy: String
        get() = memScoped {
            with (allocArray<USHORTVar>(2048)) {
                GetEnvironmentVariableW("http_proxy", this, 2048)
                toKString()
            }
        }

    actual val UA = "Geno-YAPT/0.1 Windows"

    actual fun GET(url: String, targetLocation: String)
    {
//        val internet = InternetOpenW(
//            UA,
//            (if (proxy.isEmpty()) INTERNET_OPEN_TYPE_DIRECT else INTERNET_OPEN_TYPE_PROXY).toUInt(),
//            if (proxy.isEmpty()) null else proxy,
//
//        )
    }
}
