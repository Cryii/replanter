package dev.spozap.replanter.managers

import java.util.UUID

class PlayerManager(private val replantEnabled: MutableMap<UUID, Boolean> = mutableMapOf()) {

        fun toggleReplant(uuid: UUID): Boolean {
            if (replantEnabled[uuid] == null) {
                replantEnabled[uuid] = true
                return true
            }

            val enabled = !replantEnabled[uuid]!!

            enabled.let {
               replantEnabled[uuid] = enabled
            }

            return enabled
        }

        fun isReplantEnabled(uuid: UUID): Boolean {
            return replantEnabled[uuid] == true
        }


}