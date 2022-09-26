package io.github.rokuosan.enchantExtractor.command

import io.github.rokuosan.enchantExtractor.Store
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class EnchantExtractorCommand: CommandExecutor {
    private lateinit var sender: CommandSender

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        this.sender = sender
        if(!command.name.equals("enchantExtractor", true)) return false
        if(args.isEmpty()) sendHelpMessage().let { return true }

        when(args[0]){
            "help" -> sendHelpMessage()
            "reload" -> reload()
            "config.yml" -> showConfig()
            else -> sender.sendMessage("Invalid argument")
        }

        return true
    }

    private fun showConfig(){
        sender.sendMessage("config.yml:")
        sender.sendMessage(Store.config.toString())
    }

    private fun sendHelpMessage(){
        this.sender.sendMessage("""
            Allowed Arguments:
            - help
            - reload
            - config.yml
        """.trimIndent())
    }

    private fun reload(){
        val plugin = Store.plugin?:run{
            this.sender.sendMessage("Fatal error: Please report this error code '1000' to administrator")
            return
        }
        plugin.reloadConfig()
        val list = mutableMapOf<String, String>()
        for(i in Store.configKeys){
            plugin.config.getString(i)?.let { list+=i to it }
        }
        Store.config = list
        this.sender.sendMessage("Successfully reloaded!")
    }
}

class EnchantExtractorTabCompletion: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        return when(args.size){
            1 -> {
                mutableListOf(
                    "help",
                    "reload",
                    "config.yml"
                )
            }

            else -> mutableListOf("")
        }
    }
}