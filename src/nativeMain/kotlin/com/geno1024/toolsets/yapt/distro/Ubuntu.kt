package com.geno1024.toolsets.yapt.distro

import com.geno1024.toolsets.wrapper.curl.Curl
import com.geno1024.toolsets.yapt.Distribution
import com.geno1024.toolsets.yapt.PackageManager
import libcurl.curl_easy_init

object Ubuntu : Distribution(PackageManager.APT)
{
    val curl = Curl()

    override fun init()
    {
        println(curl.GET("https://mirrors.tuna.tsinghua.edu.cn/ubuntu", "ubuntu"))
    }
}
