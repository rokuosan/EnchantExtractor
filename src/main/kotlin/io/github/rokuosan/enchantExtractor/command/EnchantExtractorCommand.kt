package io.github.rokuosan.enchantExtractor.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EnchantExtractorCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!command.name.equals("enchantExtractor", true)) return false

        val first = if(args.isNotEmpty()){
            args[0]
        }else{
            "help"
        }

        when(first){
            "help" -> {

            }
            "reload" -> {

            }
        }

        return true
    }

    fun sendHelpMessage(){

    }
}