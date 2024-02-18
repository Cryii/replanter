package dev.spozap.replanter.commands

import dev.spozap.replanter.managers.PlayerManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReplantCommand(val playerManager: PlayerManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return true

        if (args.isNotEmpty()) {
            sender.sendMessage("Too many args")
            return true
        }

        val playerId = sender.uniqueId

        val toggled = playerManager.toggleReplant(playerId)

        if (toggled) sender.sendMessage("Has activado auto-replant") else sender.sendMessage("Has desactivado auto-replant")

        return true

    }

}