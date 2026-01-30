package com.vivim.deepmine.mine;

import com.vivim.deepmine.mine.layer.ActiveLayer;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.player.PlayerProfile;
import com.vivim.deepmine.player.PlayerProfileManager;

import java.util.*;

public class MinesManager {
    private final Map<UUID,MineInstance> mines = new HashMap<>();
    private final Deque<Integer> freeMinesX = new ArrayDeque<>();
    private final PlayerProfileManager profileManager;
    private final MineLayerManager layerManager;

    public MinesManager(PlayerProfileManager profileManager, MineLayerManager layerManager) {
        this.profileManager = profileManager;
        this.layerManager = layerManager;

        freeMinesX.addAll(Arrays.asList(0, -23, -46, -69, -92, -115, -138, -161, -184, -207, -230, -253,
                -276, -299, -322, -345, -368, -391, -414, -437, -460, -483, -506, -529));
    }

    public MineInstance getOrCreateMine(UUID uuid) {
        return mines.computeIfAbsent(uuid, id -> {
            Integer x = freeMinesX.poll();
            if (x==null) return null;

            PlayerProfile profile = profileManager.loadAsync(uuid);
            ActiveLayer newLayer = layerManager.generateLayer(profile.getDepth(),60,
                    profile.getCurrentThickness());

            return new MineInstance(uuid, profileManager.getOrCreateDefault(uuid), x, newLayer);
        });
    }

    public void delMine(UUID uuid) {
        MineInstance mine = mines.remove(uuid);
        if (mine != null) freeMinesX.offer(mine.getId());
    }

    public void shutdown() { mines.clear(); }
}