package dev.spozap.replanter

import dev.spozap.replanter.listeners.SeedBreakEvent
import org.bukkit.plugin.java.JavaPlugin

class Replanter : JavaPlugin() {

    private lateinit var plugin: Replanter

    override fun onEnable() {
        plugin = this

        registerListeners()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun registerListeners() {
        plugin.server.pluginManager.registerEvents(SeedBreakEvent(), this)
    }
}
