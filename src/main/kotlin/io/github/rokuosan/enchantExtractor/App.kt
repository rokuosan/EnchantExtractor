package io.github.rokuosan.enchantExtractor

import io.github.rokuosan.enchantExtractor.util.Initializer
import org.bukkit.plugin.java.JavaPlugin

class App: JavaPlugin() {
    override fun onEnable() {
        // Initialize
        Store.server = this.server
        Store.plugin = this


//        Initializer.run()
    }

    override fun onDisable() {
        logger.info("Shutting down Ktor")
    }
}
