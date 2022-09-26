# Spigot Kotlin Template

This repository is a template for creating Spigot plugin in Kotlin.

It was created for my own use, so there may be many parts that are difficult to use.

If you find it difficult to use, please submit a PR or Issue.

## Usage

### 1. Change package name.

Change the package name in the following directory:

``/src/main/kotlin/``

> **Note**
> 
> Default: ``io.github.rokuosan.enchantExtractor``

### 2. Edit ``gradle.properties``

Rewrite the value in gradle.properties to the value of the plugin you are creating.

| Property Name | Setting Value |
| :-: | :-: |
| mainClassName | Use in Application Block |
| group | Your domain |
| name | Plugin Name |
| version | Plugin version |
| author | Your name |
| apiVersion | Require Spigot API version |
| prefix | Plugin's prefix |
| javaVersion | Target JVM version |
| minecraftVersion | Target MC version |


### 3. Just build!

Use following command:

```shell
./gradlew shadowJar
```

The results are outputs to ``/build/libs``.

## Default versions

| Name | Version |
| :-: | :-: |
| Java | 17 |
| Kotlin | 1.6.21 |
| Minecraft | 1.18.2 |
| Package | 0.1.0 |
| ShadowJar | 7.1.2 |

