package com.vivim.deepmine.mine;

import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.ActiveLayer;
import com.vivim.deepmine.player.PlayerProfile;
import com.vivim.deepmine.util.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MineInstance {
    private final UUID owner;
    private final MineRegion region;
    private final int xId;
    private final PlayerProfile profileOwner;
    private ActiveLayer activeLayer;
    private boolean isGenerating;

    private final Map<BlockPos, CUSTOM_BLOCK> fossils = new HashMap<>();

    private static final int MIN_Y = 11;
    private static final double FOSSIL_CHANCE = 0.02;

    public MineInstance(UUID owner, PlayerProfile profileOwner, int xId, ActiveLayer layer) {
        this.owner = owner;
        this.xId = xId;
        this.profileOwner = profileOwner;
        this.activeLayer = layer;

        this.region = new MineRegion(xId+4, xId-5, 2, 11);
    }

    public UUID getOwner() {
        return owner;
    }

    public PlayerProfile getProfileOwner() { return profileOwner; }

    public int getId() {
        return xId;
    }

    public boolean isBlockInRegion(int locX, int locZ) {
        return region.blockInRegion(locX, locZ);
    }

    public void newLayer(ActiveLayer layer) {
        this.activeLayer = layer;
    }

    public ActiveLayer getActiveLayer() { return activeLayer; }

    public MineRegion getRegion() { return region; }

    public boolean isGenerating() {
        return isGenerating;
    }

    public void setGenerating(boolean is) { isGenerating=is; }

    public int getMinY() { return MIN_Y; }
    public double getFossilChance() { return FOSSIL_CHANCE; }

    public void addFossil(BlockPos pos, CUSTOM_BLOCK fossilId) { fossils.put(pos,fossilId); }
    public CUSTOM_BLOCK getFossil(BlockPos pos) { return fossils.get(pos); }
    public void rmFossil(BlockPos pos) { fossils.remove(pos); }
}