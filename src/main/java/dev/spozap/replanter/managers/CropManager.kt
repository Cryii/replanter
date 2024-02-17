package dev.spozap.replanter.managers

import dev.spozap.replanter.utils.isFullyGrown
import dev.spozap.replanter.utils.resetAge
import org.bukkit.block.Block
import org.bukkit.block.data.Ageable

class CropManager {


    fun replant(block: Block, seedData: Ageable) {

        val world = block.world
        val location = block.location

        val newCrop = world.getBlockAt(location)

        block.breakNaturally()

        seedData.resetAge()

        newCrop.type = block.type
        newCrop.blockData = seedData


    }

}