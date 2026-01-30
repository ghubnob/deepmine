package com.vivim.deepmine.bkt.listeners;

import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.player.PlayerProfileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuit implements Listener {

    private MinesManager minesMng;
    private PlayerProfileManager profileManager;
    public PlayerQuit(MinesManager minesMng, PlayerProfileManager profileManager) {
        this.minesMng = minesMng;
        this.profileManager = profileManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        minesMng.delMine(uuid);
        profileManager.unloadAsync(uuid);
    }
}
