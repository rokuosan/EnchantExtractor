import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


// Package Information
// -> Please edit gradle.properties
val javaVersion = findProperty("kotlinVersion")?:"17"
val mainClassPath: String by project
val minecraftVersion: String by project
val pluginName = findProperty("name")?:"Template Plugin"
val author: String by project
val apiVersion: String by project
val prefix: String by project
group = findProperty("group").toString()
version = findProperty("version").toString()
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)


// Plugins
plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    id("org.jetbrains.dokka") version "1.7.10"
    id ("com.github.johnrengelman.shadow") version "7.1.2"
    application
}


// Repositories
repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}


// Dependencies
dependencies {
    // Kotlin Serialization (For Parsing JSON)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // Minecraft Spigot API
    compileOnly("org.spigotmc:spigot-api:${minecraftVersion}-R0.1-SNAPSHOT")

}

// Configurations
application {
    mainClass.set(mainClassPath)
}
tasks.withType<KotlinCompile>{
    kotlinOptions{
        jvmTarget = "17"
    }
}
@Suppress("UnstableApiUsage")
tasks.withType<ProcessResources>{
    mapOf(
        "version" to version,
        "mainClass" to mainClassPath,
        "name" to pluginName,
        "author" to author,
        "apiVersion" to apiVersion,
        "prefix" to prefix
    ).let{ props ->
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml"){
            expand(props)
        }
        filesMatching("version"){
            expand(props)
        }
    }
}