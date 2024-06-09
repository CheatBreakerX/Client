import groovy.lang.Closure

plugins {
    id("fabric-loom") version "1.6-SNAPSHOT"
}

apply(from="$rootDir/project.gradle")
val getShortCommitVersion: Closure<String> by ext
val getBranch: Closure<String> by ext
val projGroup: String by ext
val projMixinGroup = "$projGroup.mixin"
val projMixinTweaker: String by ext
val projMixinConfigs: String by ext
val projMixinRefmap: String by ext
val projDepsLocation: String by ext
val projModID: String by ext
val mcVersion: String by project
val fabricLoaderVersion: String by project

base {
    archivesName = "CheatBreaker-" + getShortCommitVersion()
}

repositories {
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org/")
    }
}

loom {

}

dependencies {
    minecraft("com.mojang:minecraft:${mcVersion}")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${mcVersion}:2023.06.26@zip")
    })
    modImplementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")
}

tasks.processResources {
    inputs.property("version", mcVersion + "-" + getShortCommitVersion() + "/" + getBranch())
    inputs.property("mcversion", mcVersion)
    inputs.property("modid", projModID)
    inputs.property("mixinGroup", projMixinGroup)

    filesMatching(listOf("fabric.mod.json", projMixinConfigs)) {
        expand(inputs.properties)
    }
}

tasks.withType(JavaCompile::class) {
    options.release = 17
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType(Jar::class) {
    archiveBaseName.set("CheatBreaker")
    archiveVersion.set(getShortCommitVersion())
}
