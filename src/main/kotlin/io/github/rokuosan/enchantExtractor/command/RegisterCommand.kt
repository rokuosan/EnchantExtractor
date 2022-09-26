package io.github.rokuosan.enchantExtractor.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RegisterCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // Not a command
        if(!command.name.equals("register", true)) return false

        // Invalid arguments amount
        if(args.isNotEmpty()) return false

        // プレイヤーが登録済みの場合



        return true
    }

}