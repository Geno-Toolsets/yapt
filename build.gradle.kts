plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "com.geno1024.toolsets"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }
    nativeTarget.apply {
        compilations.getByName("main") {
            cinterops {
                val libcurl by creating
            }
            copy {
                from("C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libnghttp2-14.dll", "C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libssh2-1.dll")
                into(layout.buildDirectory)
            }
        }
        binaries {
            executable {
                entryPoint = "com.geno1024.toolsets.yapt.main"
                runTask?.args("init")
            }
        }
    }

    sourceSets {
        val nativeMain by getting
    }

/*
    linuxX64()
    mingwX64() {
        compilations.getByName("main") {
            copy {
                from("C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libnghttp2-14.dll", "C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libssh2-1.dll")
                into(layout.buildDirectory)
            }
        }
    }

    targets.filterIsInstance<KotlinNativeTargetWithHostTests>().forEach {
        it.compilations.getByName("main") {
            cinterops {
                val libcurl by creating
            }
//            copy {
//                from("C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libnghttp2-14.dll", "C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libssh2-1.dll")
//                into(layout.buildDirectory)
//            }
        }
        it.binaries {
            executable {
                entryPoint = "com.geno1024.toolsets.yapt.main"
                runTask?.args("init")
            }
        }
    }

    sourceSets {
        val linuxX64Main by getting
        val mingwX64Main by getting
        val nativeMain by creating {
            linuxX64Main.dependsOn(this)
            mingwX64Main.dependsOn(this)
        }
    }*/
}
