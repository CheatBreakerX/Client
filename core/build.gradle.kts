plugins {
    idea
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net/")
}

val shadowImpl: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    shadowImpl("org.java-websocket:Java-WebSocket:1.4.1")
    shadowImpl("org.slf4j:slf4j-api:1.7.26")
    shadowImpl("javazoom:jlayer:1.0.1")
    shadowImpl("java3d:vecmath:1.3.1")
    shadowImpl("com.github.f4b6a3:uuid-creator:5.2.0")

    compileOnly("com.paulscode:codecjorbis:20101023")
    compileOnly("com.paulscode:codecwav:20101023")
    compileOnly("com.paulscode:libraryjavasound:20101123")
    compileOnly("com.paulscode:librarylwjglopenal:20100824")
    compileOnly("com.paulscode:soundsystem:20120107")
    compileOnly("com.google.code.gson:gson:2.2.4")
    compileOnly("com.google.guava:guava:17.0")
    compileOnly("com.mojang:netty:1.8.8")
    compileOnly("com.mojang:authlib:1.5.16")
    compileOnly("io.netty:netty-all:4.0.10.Final")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
}