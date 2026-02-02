package com.vivim.deepmine.bkt.listeners;

import com.vivim.deepmine.items.ItemManager;
import com.vivim.deepmine.mine.MineGenerator;
import com.vivim.deepmine.mine.MineInstance;
import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.persistence.ProfileRepository;
import com.vivim.deepmine.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class PlayerJoin implements Listener {
    private final MinesManager minesMng;
    private final ItemManager itemMng;
    private final ProfileRepository repository;
    private final CustomBlockManager blockManager;
    private final PlayerProfileManager profileManager;
    private final JavaPlugin plugin;
    public PlayerJoin(MinesManager minesMng, ItemManager itemMng, ProfileRepository repository,
                      CustomBlockManager blockManager, PlayerProfileManager profileManager, JavaPlugin plugin) {
        this.minesMng = minesMng;
        this.itemMng = itemMng;
        this.repository = repository;
        this.blockManager = blockManager;
        this.profileManager = profileManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        //from 4 60 2 to -5 60 11
        //spawn (tp) 0* 61 0
        //all place from -9 to 8 (x)
        profileManager.hasProfile(uuid).thenAcceptAsync(joined -> Bukkit.getScheduler().runTask(plugin,() -> {
            if (!joined) {
                itemMng.giveTutorialItems(p);
                p.sendMessage("first!");
            } else p.sendMessage("hello again");
        }));

        MineInstance mine = minesMng.getOrCreateMine(uuid);
        if (mine == null) { p.kickPlayer("Зайдите немного позже"); return; }

        MineGenerator generator = new MineGenerator(blockManager, plugin);
        generator.generateLayer(p, mine);

        p.teleport(new Location(p.getWorld(), mine.getId(),61,0));

        String rpUrl = plugin.getConfig().getString("resource-pack.url");
        String rpHash = plugin.getConfig().getString("resource-pack.hash");
        p.setResourcePack(rpUrl, rpHash);
    }
}