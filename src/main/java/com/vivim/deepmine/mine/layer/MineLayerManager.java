package com.vivim.deepmine.mine.layer;

import com.vivim.deepmine.mine.customblock.BlockRep;
import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MineLayerManager {
    private final List<MineLayer> layers = new ArrayList<>();

    public MineLayerManager(CustomBlockManager blockMng) {
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_DIRT), 4));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_STONE), 10));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_COBBLESTONE), 12));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_SANDSTONE), 10));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_NETHERRACK), 10));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_ENDER_STONE), 8));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_OBSIDIAN), 1));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_ADAMANTIUM), 8));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_NETHERIUM), 10));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_TITAN), 10));
        layers.add(new MineLayer(blockMng.getBlockByEnum(CUSTOM_BLOCK.DEFAULT_SIENIT), 10));
    }

    public ActiveLayer generateLayer(int depth, int startY, int thicknessOverride) {
        int index = depth % layers.size();
        MineLayer template = layers.get(index);
        int thickness = thicknessOverride > 0 ? thicknessOverride : template.getThickness();
        return new ActiveLayer(template, startY, thickness);
    }

    public BlockRep getBlockRepForDepth(int depth) {
        int index = depth % layers.size();
        return layers.get(index).getBase();
    }
}