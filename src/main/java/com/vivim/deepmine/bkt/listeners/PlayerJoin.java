package com.vivim.deepmine.bkt.listeners;

import com.vivim.deepmine.items.ItemManager;
import com.vivim.deepmine.mine.MineGenerator;
import com.vivim.deepmine.mine.MineInstance;
import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.persistence.ProfileRepository;
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
    private final JavaPlugin plugin;
    public PlayerJoin(MinesManager minesMng, ItemManager itemMng, ProfileRepository repository,
                      CustomBlockManager blockManager, JavaPlugin plugin) {
        this.minesMng = minesMng;
        this.itemMng = itemMng;
        this.repository = repository;
        this.blockManager = blockManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        //from 4 60 2 to -5 60 11
        //spawn (tp) 0* 61 0
        //all place from -9 to 8 (x)
        MineInstance mine = minesMng.getOrCreateMine(uuid);
        if (mine == null) { p.kickPlayer("Зайдите немного позже"); return; }

        MineGenerator generator = new MineGenerator(blockManager, plugin);
        generator.generateLayer(p, mine);

        p.teleport(new Location(p.getWorld(), mine.getId(),61,0));
        if (!repository.isPlayerJoined(uuid)) itemMng.giveTutorialItems(p);
        else p.sendMessage("hello again");

        String rpUrl = plugin.getConfig().getString("resource-pack.url");
        String rpHash = plugin.getConfig().getString("resource-pack.hash");
        p.setResourcePack(rpUrl, rpHash);
    }
}