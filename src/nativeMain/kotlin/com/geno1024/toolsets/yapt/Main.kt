package com.geno1024.toolsets.yapt

import com.geno1024.toolsets.yapt.distro.Ubuntu

object Main
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
        when (args.getOrElse(0) { "" })
        {
            "init" -> init()
            else -> help()
        }
    }
}

fun main(args: Array<String>) = Main.main(args)
