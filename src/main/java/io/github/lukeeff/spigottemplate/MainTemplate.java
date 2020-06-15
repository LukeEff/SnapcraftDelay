package io.github.lukeeff.spigottemplate;

import io.github.lukeeff.spigottemplate.cooldown.Cooldown;
import io.github.lukeeff.spigottemplate.runtime.Break;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MainTemplate extends JavaPlugin {

    @Getter private Cooldown cooldown;


    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Template successfully loaded!");
        cooldown = new Cooldown();
        getServer().getPluginManager().registerEvents(new Break(this), this);
    }

    @Override
    public void onDisable() {

    }

}
