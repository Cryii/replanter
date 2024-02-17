package dev.spozap.replanter.utils

import org.bukkit.block.data.Ageable


fun Ageable.isFullyGrown() : Boolean = this.age == this.maximumAge

fun Ageable.resetAge() { this.age = 0 }