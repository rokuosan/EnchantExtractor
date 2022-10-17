package io.github.rokuosan.enchantExtractor.command

import io.github.rokuosan.enchantExtractor.App
import io.github.rokuosan.enchantExtractor.Store
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.EnchantmentStorageMeta
import kotlin.math.abs
import kotlin.math.min

class ExtractCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!command.name.equals("extract", true)) return false
        if(args.isEmpty()) return true
        if(sender !is Player) return false

        // Get item
        val item =sender.equipment?.itemInMainHand?: return true

        // Enchant check
        if(item.enchantments.isEmpty()) {
            sender.sendMessage("手に持っているアイテムにエンチャントはありません。")
            return true
        }

        // Enchant name check
        val query = args[0]
        var enchantment: Enchantment? = null
        var level = 1
        var isHit = false
        for(e in item.enchantments.keys){
            if(e.key.key == query){
                isHit=true
                enchantment = e
                level = item.getEnchantmentLevel(e)
                break
            }
        }
        if(!isHit){
            sender.sendMessage("そのようなエンチャントはありません")
            return true
        }

        // Cost Item Check
        val costItem = ItemStack(Material.getMaterial(Store.config["cost.item"]?:"LAPIS_LAZULI")!!)
        var costAmount = Store.config["cost.amount"]?.toInt()?: 0
        var itemAmount = 0
        var bookAmount = 0
        for(i in sender.inventory){
            if(i == null) continue
            if(i.type.name == Material.BOOK.name) bookAmount++
            if(i.type.name == costItem.type.name) itemAmount+= i.amount
        }
        if(itemAmount < costAmount){
            sender.sendMessage("コストアイテムが不足しています: ${costItem.type.name} x ${abs(itemAmount-costAmount)}")
            return true
        }

        // Level check
        val costLevel = Store.config["cost.level"]?.toInt()?: 10
        if(sender.level < costLevel){
            sender.sendMessage("レベルが不足しています: ${abs(costLevel-sender.level)}レベル")
            return true
        }

        // Book check
        var costBookAmount = Store.config["cost.book"]?.toInt()?:1
        if(bookAmount < costBookAmount){
            sender.sendMessage("本が不足しています: ${abs(costAmount-bookAmount)}冊")
            return true
        }

        // Remove Cost
        sender.level -= costLevel
        for(i in sender.inventory){
            if(i == null) continue
            if(i.type.name == costItem.type.name){
                val a = min(i.amount, costAmount)
                i.amount -= a
                costAmount -= a
                sender.updateInventory()
            }
            if(i.type.name == Material.BOOK.name){
                val a = min(i.amount, costBookAmount)
                i.amount -= a
                costBookAmount -= a
                sender.updateInventory()
            }
        }
        sender.updateInventory()
        item.removeEnchantment(enchantment!!)

        // Give Book
        val book = ItemStack(Material.ENCHANTED_BOOK)
        val esm: EnchantmentStorageMeta = book.itemMeta as EnchantmentStorageMeta
        esm.addStoredEnchant(enchantment, level, true)
        book.itemMeta = esm
        sender.inventory.addItem(book)

        sender.sendMessage("エンチャントを抽出しました")
        App.log("Enchant extracted by ${sender.name}. [${enchantment.key.key} / $level]")

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
                if(sender !is Player) mutableListOf<String>()
                val p = Store.server?.getPlayer(sender.name)!!
                val e = p.equipment?.itemInMainHand?.enchantments?.keys?: return mutableListOf()
                val list = mutableListOf<String>()
                for(i in e){
                    list.add(i.key.key)
                }

                list
            }

            else -> mutableListOf()
        }
    }
}