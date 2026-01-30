package com.vivim.deepmine.bkt.listeners;

import com.vivim.deepmine.items.ItemManager;
import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.player.PlayerProfileManager;
import com.vivim.deepmine.service.BreakService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerBreakBlock implements Listener {
    private final MinesManager minesMng;
    private final PlayerProfileManager profileManager;
    private final ItemManager itemMng;
    private final MineLayerManager layerManager;
    private final CustomBlockManager blockManager;
    private final JavaPlugin plugin;
    public PlayerBreakBlock(MinesManager mng, PlayerProfileManager profileManager,
                            ItemManager itemMng, MineLayerManager layerManager,
                            CustomBlockManager blockManager, JavaPlugin plugin) {
        this.minesMng = mng;
        this.profileManager = profileManager;
        this.itemMng = itemMng;
        this.layerManager = layerManager;
        this.blockManager = blockManager;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        BreakService breakService = new BreakService(minesMng,profileManager,itemMng,layerManager,blockManager,plugin);
        breakService.breakService(e);
    }
}
