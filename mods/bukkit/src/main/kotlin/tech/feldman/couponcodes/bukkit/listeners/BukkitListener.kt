/**
 * The MIT License
 * Copyright (c) 2015 Nicholas Feldman (AngrySoundTech)
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tech.feldman.couponcodes.bukkit.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.ServerCommandEvent
import tech.feldman.couponcodes.api.CouponCodes
import tech.feldman.couponcodes.bukkit.BukkitPlugin
import tech.feldman.couponcodes.bukkit.entity.BukkitServerSender

class BukkitListener(private val plugin: BukkitPlugin) : Listener {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        val sender = plugin.wrapPlayer(event.player)
        val command = event.message

        if (CouponCodes.getCommandHandler().handleCommandEvent(sender, command)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onServerCommand(event: ServerCommandEvent) {
        val command = event.command
        if (CouponCodes.getCommandHandler().handleCommandEvent(BukkitServerSender(event.sender), command)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        CouponCodes.getModTransformer().removePlayer(CouponCodes.getModTransformer().getPlayer(event.player.uniqueId.toString()))
    }
}