package com.vivim.deepmine;

import com.vivim.deepmine.bkt.listeners.PlayerBreakBlock;
import com.vivim.deepmine.bkt.listeners.PlayerJoin;
import com.vivim.deepmine.bkt.listeners.PlayerQuit;
import com.vivim.deepmine.items.ItemManager;
import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.persistence.*;
import com.vivim.deepmine.player.PlayerProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class DeepMine extends JavaPlugin {
    private MinesManager minesMng;
    private PlayerProfileManager profileManager;
    private ProfileRepository repository;

    @Override
    public void onEnable() {
        //init managers
        CustomBlockManager blockManager = new CustomBlockManager();
        StorageExecutor executor = new StorageExecutor();
        MineLayerManager layerManager = new MineLayerManager(blockManager);
        repository = new MongoRepository();
        repository.init();
        profileManager = new PlayerProfileManager(repository, executor, this);
        minesMng = new MinesManager(profileManager, layerManager);
        ItemManager itemManager = new ItemManager(blockManager);

        Bukkit.getPluginManager().registerEvents(new PlayerJoin(minesMng, itemManager, repository,
                blockManager,profileManager,this),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(minesMng, profileManager),this);
        Bukkit.getPluginManager().registerEvents(new PlayerBreakBlock(minesMng, profileManager,
                itemManager, layerManager, blockManager,this ),this);

        Bukkit.getScheduler().runTaskTimer(this, () -> Bukkit.getOnlinePlayers().forEach(p ->
                        profileManager.saveAsync(p.getUniqueId())), 20 * 300, 20 * 120);
    }

    @Override
    public void onDisable() {
        //shutdown managers
        //save data
        minesMng.shutdown();
        profileManager.shutdown(45, TimeUnit.SECONDS);
        repository.close();

        Bukkit.getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        if (label.equalsIgnoreCase("")) return true;
        return false;
    }
}