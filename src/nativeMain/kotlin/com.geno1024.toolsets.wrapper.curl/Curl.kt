package com.geno1024.toolsets.wrapper.curl

import kotlinx.cinterop.*
import libcurl.*
import platform.posix.*
import platform.zlib.voidp
import platform.zlib.voidpVar

class Curl
{
    val curl = curl_easy_init()

    fun GET(url: String): String
    {
        val targetP = "".cstr.placeTo(MemScope())
        curl_easy_setopt(curl, CURLOPT_URL, url)
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1)
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, staticCFunction<voidpVar, size_t, size_t, voidpVar, size_t> { contents, size, nmemb, userp ->
            val realsize = size * nmemb
            userp.value = contents.reinterpret<ByteVarOf<Byte>>().ptr
            realsize
        })
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, targetP)
        curl_easy_perform(curl)
        return targetP.toKString()
    }

    fun GET(url: String, targetFile: String)
    {
        val targetP = targetFile.cstr.placeTo(MemScope())
        curl_easy_setopt(curl, CURLOPT_URL, url)
        curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1)
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, staticCFunction<voidpVar, size_t, size_t, voidpVar, size_t> { contents, size, nmemb, userp ->
            val realsize = size * nmemb
            val fp = fopen(userp.reinterpret<ByteVarOf<Byte>>().ptr.toKStringFromUtf8(), "wb")
            fwrite(contents.reinterpret<ByteVarOf<Byte>>().ptr, realsize, 1, fp)
            fclose(fp)
            realsize
        })
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, targetP)
        curl_easy_perform(curl)
    }
}
