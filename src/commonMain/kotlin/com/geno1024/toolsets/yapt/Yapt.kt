package com.geno1024.toolsets.yapt

import com.geno1024.toolsets.yapt.distro.Ubuntu

object Yapt
{
    fun init()
    {
        Ubuntu.init()
    }

    fun help()
    {

    }

    fun main(args: Array<String>)
    {
        if (args.size == 0)
        {
            help()
            return
        }
        when (args[0])
        {
            "init" -> init()
            else -> help()
        }
    }
}

fun main(args: Array<String>) = Yapt.main(args)
