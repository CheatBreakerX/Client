pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net/")
        maven("https://repo.spongepowered.org/maven/")
        maven("https://repo.sk1er.club/repository/maven-releases/")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "gg.essential.loom" -> useModule("gg.essential:architectury-loom:${requested.version}")
            }
        }
    }
}

rootProject.name = "CheatBreakerX"

val supportedForgeVersions = listOf(
        "1.8.9",
        "1.9.4",
//        "1.10.2",
 //       "1.11.2"
)

include(":core")
project(":core").apply {
    projectDir = file("core")
    buildFileName = "build.gradle.kts"
}

supportedForgeVersions.forEach { version ->
    include(":versions:$version")
    project(":versions:$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "build.gradle.kts"
    }
}