package dev.spozap.replanter.listeners

import dev.spozap.replanter.Replanter
import dev.spozap.replanter.managers.CropManager
import dev.spozap.replanter.utils.isFullyGrown
import org.bukkit.Material
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class SeedBreakEvent(private val plugin: Replanter) : Listener {

    private val cropManager = plugin.cropManager
    private val playerManager = plugin.playerManager

    companion object {
        private val ALLOWED_SEEDS = setOf(Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.MELON_STEM, Material.PUMPKIN_STEM, Material.BEETROOTS)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onBreak(event: BlockBreakEvent) {

        val brokenBlock = event.block

        if (!playerManager.isReplantEnabled(event.player.uniqueId)) return

        if (!ALLOWED_SEEDS.contains(brokenBlock.type)) return

        val seedData = brokenBlock.blockData as Ageable

        if (!seedData.isFullyGrown()) return

        event.isCancelled = true
        cropManager.replant(brokenBlock, seedData)

    }

}