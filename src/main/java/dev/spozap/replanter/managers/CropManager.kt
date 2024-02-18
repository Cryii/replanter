package dev.spozap.replanter.managers

import dev.spozap.replanter.utils.isFullyGrown
import dev.spozap.replanter.utils.resetAge
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.Ageable
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CropManager {

    companion object {
        const val SEEDS_TO_RETRIEVE = 1

        val SEED_REPLACEMENT = mapOf(
                Material.WHEAT to Material.WHEAT_SEEDS,
                Material.POTATOES to Material.POTATO,
                Material.CARROTS to Material.CARROT,
                Material.BEETROOTS to Material.BEETROOT_SEEDS,
                Material.MELON_STEM to Material.MELON_SEEDS,
                Material.PUMPKIN_STEM to Material.PUMPKIN_SEEDS)
    }

    fun replant(block: Block, seedData: Ageable) {

        val world = block.world
        val location = block.location

        val newCrop = world.getBlockAt(location)

        block.breakNaturally()

        seedData.resetAge()

        newCrop.type = block.type
        newCrop.blockData = seedData


    }

    fun canRetrieveSeed(player: Player, seed: Material): Boolean {
        val inventory = player.inventory
        return inventory.contains(findReplantableSeed(seed))
    }

    fun retrieveSeed(player: Player, seed: Material) {
        val inventory = player.inventory
        val seedToRetrieve = findReplantableSeed(seed)

        inventory.storageContents.map { item ->
            if (item != null) {
                if (item.type == seedToRetrieve) {
                    item.amount -= SEEDS_TO_RETRIEVE
                    return
                }
            }

        }

    }

    private fun findReplantableSeed(seed: Material): Material {
        return SEED_REPLACEMENT[seed] ?: seed
    }

}