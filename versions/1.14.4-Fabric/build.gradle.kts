import groovy.lang.Closure

plugins {
    idea
    java
    id("gg.essential.loom") version "0.10.0.+"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

apply(from="$rootDir/project.gradle")
val getShortCommitVersion: Closure<String> by ext
val projGroup: String by ext
val projMixinGroup = "$projGroup.mixin"
val projMixinTweaker: String by ext
val projMixinConfigs: String by ext
val projMixinRefmap: String by ext
val projDepsLocation: String by ext
val projModID: String by ext
val mcVersion: String by project
val fabricLoaderVersion: String by project

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

loom {
    log4jConfigs.from(file("log4j2.xml"))
    launchConfigs {
        "client" {
            //property("mixin.debug", "true")
            property("asmhelper.verbose", "true")
            //arg("--tweakClass", projMixinTweaker)
            arg("--mixin", projMixinConfigs)
        }
    }
    mixin {
        defaultRefmapName.set(projMixinRefmap)
    }
}

sourceSets.main {
    output.setResourcesDir(file("$buildDir/classes/java/main"))
}

repositories {
    mavenCentral()
    //maven("https://repo.spongepowered.org/maven/")
}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    minecraft("com.mojang:minecraft:$mcVersion")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")

    //shadowImpl("org.spongepowered:mixin:0.7.11-SNAPSHOT") {
    //    isTransitive = false
    //}
    //annotationProcessor("org.spongepowered:mixin:0.8.4-SNAPSHOT")

    shadowImpl(project(path=":core", configuration="default"))
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}

tasks.withType(Jar::class) {
    archiveBaseName.set("CheatBreaker")
    archiveVersion.set(getShortCommitVersion())

    manifest.attributes.run {
        this["Specification-Title"] = "cheatbreaker"
        this["Specification-Vendor"] = "cbx"
        this["Specification-Version"] = "1"
        this["Implementation-Title"] = "CheatBreakerX"
        this["Implementation-Version"] = project.version
        this["Implementation-Vendor"] = "cbx"
//        this["Implementation-Timestamp"] = Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
//        this["TweakClass"] = projMixinTweaker
//        this["TweakOrder"] = 0
//        this["MixinConfigs"] = projMixinConfigs
    }
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("mcversion", mcVersion)
    inputs.property("modid", projModID)
    inputs.property("mixinGroup", projMixinGroup)

    filesMatching(listOf("mcmod.info", projMixinConfigs)) {
        expand(inputs.properties)
    }

    rename("(.+_at.cfg)", "META-INF/$1")
}


val remapJar by tasks.named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
    archiveBaseName.set("CheatBreaker")
    archiveVersion.set(getShortCommitVersion())
    archiveClassifier.set("")
    from(tasks.shadowJar)
    input.set(tasks.shadowJar.get().archiveFile)
}

tasks.jar {
    archiveClassifier.set("without-deps")
    destinationDirectory.set(layout.buildDirectory.dir("badjars"))
}

tasks.shadowJar {
    destinationDirectory.set(layout.buildDirectory.dir("badjars"))
    archiveClassifier.set("all-dev")
    configurations = listOf(shadowImpl)
    doLast {
        configurations.forEach {
            println("Copying jars into mod: ${it.files}")
        }
    }

    fun relocate(name: String) = relocate(name, "$projDepsLocation.$name")
}

tasks.assemble.get().dependsOn(tasks.remapJar)
