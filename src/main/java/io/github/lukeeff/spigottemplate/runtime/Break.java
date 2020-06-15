package io.github.lukeeff.spigottemplate.runtime;

import io.github.lukeeff.spigottemplate.MainTemplate;
import io.github.lukeeff.spigottemplate.cooldown.Cooldown;
import net.sf.cglib.asm.$ByteVector;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

/**
 * Runtime testing...
 *
 * @author lukeeff
 * @since 1.0
 */
public class Break implements Listener {

    private final MainTemplate plugin;
    private final Cooldown cooldown;

    public Break(MainTemplate plugin) {
        this.plugin = plugin;
        this.cooldown = plugin.getCooldown();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Bukkit.broadcastMessage("Event called");
        final Material brokenMaterial = e.getBlock().getType();
        final UUID playerId = e.getPlayer().getUniqueId();
        if(brokenMaterial.equals(Material.DIAMOND_BLOCK)) {
            if(!cooldown.isOnCoolDown(playerId)) {
                cooldown.registerPlayer(playerId);
                e.getPlayer().sendMessage(ChatColor.YELLOW + "Cooldown begin.");
            } else {
                e.getPlayer().sendMessage(ChatColor.RED + "Not yet...");
                e.setCancelled(true);
            }

        }
    }

}
