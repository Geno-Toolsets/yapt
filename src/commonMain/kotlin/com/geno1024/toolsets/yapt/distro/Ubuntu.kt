package com.geno1024.toolsets.yapt.distro

import com.geno1024.toolsets.yapt.Distribution
import com.geno1024.util.networking.HTTP

object Ubuntu : Distribution()
{
    override fun init()
    {
        HTTP.GET("https://mirrors.tuna.tsinghua.edu.cn")
    }
}
