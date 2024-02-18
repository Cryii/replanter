package dev.spozap.replanter

import dev.spozap.replanter.commands.ReplantCommand
import dev.spozap.replanter.listeners.SeedBreakEvent
import dev.spozap.replanter.managers.CropManager
import dev.spozap.replanter.managers.PlayerManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Replanter : JavaPlugin() {

    private lateinit var plugin: Replanter

    val playerManager: PlayerManager = PlayerManager()
    val cropManager: CropManager = CropManager()

    override fun onEnable() {
        plugin = this

        registerListeners()
        registerCommands()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun registerListeners() {
        plugin.server.pluginManager.registerEvents(SeedBreakEvent(plugin), this)
    }

    private fun registerCommands() {
        Bukkit.getPluginCommand("replant")!!.setExecutor(ReplantCommand(playerManager))
    }
}
