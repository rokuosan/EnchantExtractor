package io.github.rokuosan.enchantExtractor.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class ExtractCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!command.name.equals("extract", true)) return false
        if(args.isNotEmpty()) return false




        return true
    }

}

class ExtractTabCompletion: TabCompleter{
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {

        return when(args.size){
            1 -> {
                mutableListOf()
            }

            else -> mutableListOf()
        }
    }
}