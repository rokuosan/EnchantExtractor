package io.github.rokuosan.enchantExtractor.util

class ResourceLoader {
    fun load(name: String) = this.javaClass
            .classLoader
            .getResourceAsStream(name)
            ?.bufferedReader()?.use{
                it.readLines()
            }
}