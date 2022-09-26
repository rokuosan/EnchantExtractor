package io.github.rokuosan.enchantExtractor

import io.github.rokuosan.enchantExtractor.command.EnchantExtractorCommand
import io.github.rokuosan.enchantExtractor.command.EnchantExtractorTabCompletion
import io.github.rokuosan.enchantExtractor.command.ExtractCommand
import io.github.rokuosan.enchantExtractor.command.ExtractTabCompletion
import org.bukkit.plugin.java.JavaPlugin

class App: JavaPlugin() {
    companion object{
        fun log(text: String){
            Store.plugin?.logger?.info(text)
        }
    }

    override fun onEnable() {
        // Generate Config file
        saveDefaultConfig()
        val list = mutableMapOf<String, String>()
        for(i in Store.configKeys){
            config.getString(i)?.let { list+= i to it }
            config.set(i, config.getString(i))
        }
        Store.config = list

        // Initialize Store
        Store.server = this.server
        Store.plugin = this

        // Register commands
        getCommand("enchantextractor")?.setExecutor(EnchantExtractorCommand())
        getCommand("enchantextractor")?.tabCompleter = EnchantExtractorTabCompletion()

        getCommand("extract")?.setExecutor(ExtractCommand())
        getCommand("extract")?.tabCompleter = ExtractTabCompletion()

        // Register events

    }

    override fun onDisable() {
        logger.info("Shutting down Ktor")
    }
}