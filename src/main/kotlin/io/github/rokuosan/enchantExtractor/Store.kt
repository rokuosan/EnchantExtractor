package io.github.rokuosan.enchantExtractor

import org.bukkit.Server
import org.bukkit.plugin.java.JavaPlugin

class Store{
    companion object{

        var server: Server? = null
        var plugin: JavaPlugin? = null

        var config = listOf<String>()

        val configKeys = listOf(
            "cost.item",
            "cost.amount",
            "cost.level",
            "cost.book",
        )
    }
}