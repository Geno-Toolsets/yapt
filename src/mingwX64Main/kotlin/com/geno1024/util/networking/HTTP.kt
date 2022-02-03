package com.geno1024.util.networking

import kotlinx.cinterop.*
import platform.posix.free
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

    actual val no_proxy: String
        get() = memScoped {
            with (allocArray<USHORTVar>(2048)) {
                GetEnvironmentVariableW("no_proxy", this, 2048)
                toKString()
            }
        }

    actual val UA = "Geno-YAPT/0.1 Windows"

    actual fun GET(url: String, targetLocation: String)
    {
        var internet = InternetOpenW(
            lpszAgent = UA,
            dwAccessType = (if (proxy.isEmpty()) INTERNET_OPEN_TYPE_DIRECT else INTERNET_OPEN_TYPE_PROXY).toUInt(),
            lpszProxy = proxy.ifEmpty { null },
            lpszProxyBypass = no_proxy.ifEmpty { null },
            dwFlags = 0
        )
        memScoped {
            val component = alloc<LPURL_COMPONENTSWVar>()
            println(component)
            urlComponents(url, component.value)
            println(component.pointed?.lpszHostName)
            free(component.ptr)
        }
//        internet = InternetConnectW(
//            hInternet = internet,
//            lpszServerName = ,
//            nServerPort = ,
//            lpszUserName = ,
//            lpszPassword = ,
//            dwService = ,
//            dwFlags = ,
//            dwContent =
//        )
    }

    fun urlComponents(url: String, component: LPURL_COMPONENTSW?)
    {
        InternetCrackUrlW(
            lpszUrl = url,
            dwUrlLength = url.length.toUInt(),
            dwFlags = 0,
            lpUrlComponents = component
        )
    }
}
