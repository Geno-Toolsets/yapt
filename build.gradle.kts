plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "com.geno1024.toolsets"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    /*
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
        }
        binaries {
            executable {
                entryPoint = "com.geno1024.toolsets.yapt.main"
                copy {
                    from("C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libnghttp2-14.dll", "C:\\cygwin64\\usr\\x86_64-w64-mingw32\\sys-root\\mingw\\bin\\libssh2-1.dll")
                    into(outputDirectory)
                }
                runTask?.args("init")
            }
        }
    }

    sourceSets {
        val nativeMain by getting
    }

*/
    linuxX64()
    mingwX64()

    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithHostTests>().forEach {
        it.binaries {
            executable {
                entryPoint = "com.geno1024.toolsets.yapt.main"
                runTask?.args("init")
            }
        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "7.3"
    distributionType = Wrapper.DistributionType.ALL
}
