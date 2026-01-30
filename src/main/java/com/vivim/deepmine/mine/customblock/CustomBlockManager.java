package com.vivim.deepmine.mine.customblock;

import com.vivim.deepmine.fossils.FossilDefinition;
import com.vivim.deepmine.fossils.FossilService;
import com.vivim.deepmine.fossils.FossilType;
import org.bukkit.Material;

import java.util.*;

public class CustomBlockManager {
    private final Map<CUSTOM_BLOCK, BlockRep> enumBlocks = new HashMap<>();
    private final Map<Material, List<CUSTOM_BLOCK>> defaultBlocks = new HashMap<>();
    private final Map<CUSTOM_BLOCK, FossilDefinition> fossilDefs = new HashMap<>();

    public CustomBlockManager() {
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_DIRT, new BlockRep(Material.DIRT, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_STONE, new BlockRep(Material.STONE, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_COBBLESTONE, new BlockRep(Material.COBBLESTONE, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_SANDSTONE, new BlockRep(Material.SANDSTONE, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_NETHERRACK, new BlockRep(Material.NETHERRACK, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_ENDER_STONE, new BlockRep(Material.ENDER_STONE, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_OBSIDIAN, new BlockRep(Material.OBSIDIAN, (byte) 0));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_ADAMANTIUM, new BlockRep(Material.STONE, (byte) 1));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_NETHERIUM, new BlockRep(Material.STONE, (byte) 2));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_TITAN, new BlockRep(Material.STONE, (byte) 3));
        enumBlocks.put(CUSTOM_BLOCK.DEFAULT_SIENIT, new BlockRep(Material.STONE, (byte) 4));

        enumBlocks.put(CUSTOM_BLOCK.DIRT_COMMON, new BlockRep(Material.CONCRETE, (byte) 1, Material.WOOD_AXE, (short) 101));
        enumBlocks.put(CUSTOM_BLOCK.DIRT_RARE, new BlockRep(Material.CONCRETE, (byte) 2, Material.WOOD_AXE, (short) 102));
        enumBlocks.put(CUSTOM_BLOCK.DIRT_EPIC, new BlockRep(Material.CONCRETE, (byte) 3, Material.WOOD_AXE, (short) 103));
        enumBlocks.put(CUSTOM_BLOCK.DIRT_LEGENDARY, new BlockRep(Material.CONCRETE, (byte) 4, Material.WOOD_AXE, (short) 104));

        enumBlocks.put(CUSTOM_BLOCK.STONE_COMMON, new BlockRep(Material.CONCRETE, (byte) 5, Material.PAPER, (short) 105));
        enumBlocks.put(CUSTOM_BLOCK.STONE_RARE, new BlockRep(Material.CONCRETE, (byte) 6, Material.PAPER, (short) 106));
        enumBlocks.put(CUSTOM_BLOCK.STONE_EPIC, new BlockRep(Material.CONCRETE, (byte) 7, Material.PAPER, (short) 107));
        enumBlocks.put(CUSTOM_BLOCK.STONE_LEGENDARY, new BlockRep(Material.CONCRETE, (byte) 8, Material.PAPER, (short) 108));

        enumBlocks.put(CUSTOM_BLOCK.COBBLESTONE_COMMON, new BlockRep(Material.CONCRETE, (byte) 9, Material.PAPER, (short) 109));
        enumBlocks.put(CUSTOM_BLOCK.COBBLESTONE_RARE, new BlockRep(Material.CONCRETE, (byte) 10, Material.PAPER, (short) 110));
        enumBlocks.put(CUSTOM_BLOCK.COBBLESTONE_EPIC, new BlockRep(Material.CONCRETE, (byte) 11, Material.PAPER, (short) 111));
        enumBlocks.put(CUSTOM_BLOCK.COBBLESTONE_LEGENDARY, new BlockRep(Material.CONCRETE, (byte) 12, Material.PAPER, (short) 112));

        enumBlocks.put(CUSTOM_BLOCK.SANDSTONE_COMMON, new BlockRep(Material.CONCRETE, (byte) 13, Material.PAPER, (short) 113));
        enumBlocks.put(CUSTOM_BLOCK.SANDSTONE_RARE, new BlockRep(Material.CONCRETE, (byte) 14, Material.PAPER, (short) 114));
        enumBlocks.put(CUSTOM_BLOCK.SANDSTONE_EPIC, new BlockRep(Material.CONCRETE, (byte) 15, Material.PAPER, (short) 115));
        enumBlocks.put(CUSTOM_BLOCK.SANDSTONE_LEGENDARY, new BlockRep(Material.WOOL, (byte) 6, Material.PAPER, (short) 116));

        enumBlocks.put(CUSTOM_BLOCK.NETHERRACK_COMMON, new BlockRep(Material.STONE, (byte) 6, Material.PAPER, (short) 117));
        enumBlocks.put(CUSTOM_BLOCK.NETHERRACK_RARE, new BlockRep(Material.SANDSTONE, (byte) 1, Material.PAPER, (short) 118));
        enumBlocks.put(CUSTOM_BLOCK.NETHERRACK_EPIC, new BlockRep(Material.SANDSTONE, (byte) 2, Material.PAPER, (short) 119));
        enumBlocks.put(CUSTOM_BLOCK.NETHERRACK_LEGENDARY, new BlockRep(Material.WOOL, (byte) 1, Material.PAPER, (short) 120));

        enumBlocks.put(CUSTOM_BLOCK.ENDER_STONE_COMMON, new BlockRep(Material.WOOL, (byte) 2, Material.PAPER, (short) 121));
        enumBlocks.put(CUSTOM_BLOCK.ENDER_STONE_RARE, new BlockRep(Material.WOOL, (byte) 3, Material.PAPER, (short) 122));
        enumBlocks.put(CUSTOM_BLOCK.ENDER_STONE_EPIC, new BlockRep(Material.WOOL, (byte) 4, Material.PAPER, (short) 123));
        enumBlocks.put(CUSTOM_BLOCK.ENDER_STONE_LEGENDARY, new BlockRep(Material.WOOL, (byte) 5, Material.PAPER, (short) 124));

        enumBlocks.put(CUSTOM_BLOCK.ADAMANTIUM_COMMON, new BlockRep(Material.WOOL, (byte) 10, Material.PAPER, (short) 125));
        enumBlocks.put(CUSTOM_BLOCK.ADAMANTIUM_RARE, new BlockRep(Material.WOOL, (byte) 11, Material.PAPER, (short) 126));
        enumBlocks.put(CUSTOM_BLOCK.ADAMANTIUM_EPIC, new BlockRep(Material.WOOL, (byte) 12, Material.PAPER, (short) 127));
        enumBlocks.put(CUSTOM_BLOCK.ADAMANTIUM_LEGENDARY, new BlockRep(Material.WOOL, (byte) 13, Material.PAPER, (short) 128));

        enumBlocks.put(CUSTOM_BLOCK.NETHERIUM_COMMON, new BlockRep(Material.WOOL, (byte) 14, Material.PAPER, (short) 129));
        enumBlocks.put(CUSTOM_BLOCK.NETHERIUM_RARE, new BlockRep(Material.WOOL, (byte) 15, Material.PAPER, (short) 130));
        enumBlocks.put(CUSTOM_BLOCK.NETHERIUM_EPIC, new BlockRep(Material.BLACK_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 131));
        enumBlocks.put(CUSTOM_BLOCK.NETHERIUM_LEGENDARY, new BlockRep(Material.BLUE_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 132));

        enumBlocks.put(CUSTOM_BLOCK.TITAN_COMMON, new BlockRep(Material.BROWN_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 133));
        enumBlocks.put(CUSTOM_BLOCK.TITAN_RARE, new BlockRep(Material.CYAN_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 134));
        enumBlocks.put(CUSTOM_BLOCK.TITAN_EPIC, new BlockRep(Material.GRAY_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 135));
        enumBlocks.put(CUSTOM_BLOCK.TITAN_LEGENDARY, new BlockRep(Material.LIME_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 136));

        enumBlocks.put(CUSTOM_BLOCK.SIENIT_COMMON, new BlockRep(Material.MAGENTA_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 137));
        enumBlocks.put(CUSTOM_BLOCK.SIENIT_RARE, new BlockRep(Material.ORANGE_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 138));
        enumBlocks.put(CUSTOM_BLOCK.SIENIT_EPIC, new BlockRep(Material.PINK_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 139));
        enumBlocks.put(CUSTOM_BLOCK.SIENIT_LEGENDARY, new BlockRep(Material.PURPLE_GLAZED_TERRACOTTA, (byte) 0, Material.PAPER, (short) 140));

        //FossilDefinitions
        fossilDefs.put(CUSTOM_BLOCK.DIRT_COMMON, new FossilDefinition(CUSTOM_BLOCK.DIRT_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.DIRT_COMMON), "Dirt Common"));
        fossilDefs.put(CUSTOM_BLOCK.DIRT_RARE, new FossilDefinition(CUSTOM_BLOCK.DIRT_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.DIRT_RARE), "Dirt Rare"));
        fossilDefs.put(CUSTOM_BLOCK.DIRT_EPIC, new FossilDefinition(CUSTOM_BLOCK.DIRT_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.DIRT_EPIC), "Dirt Epic"));
        fossilDefs.put(CUSTOM_BLOCK.DIRT_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.DIRT_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.DIRT_LEGENDARY), "Dirt Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.STONE_COMMON, new FossilDefinition(CUSTOM_BLOCK.STONE_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.STONE_COMMON), "Stone Common"));
        fossilDefs.put(CUSTOM_BLOCK.STONE_RARE, new FossilDefinition(CUSTOM_BLOCK.STONE_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.STONE_RARE), "Stone Rare"));
        fossilDefs.put(CUSTOM_BLOCK.STONE_EPIC, new FossilDefinition(CUSTOM_BLOCK.STONE_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.STONE_EPIC), "Stone Epic"));
        fossilDefs.put(CUSTOM_BLOCK.STONE_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.STONE_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.STONE_LEGENDARY), "Stone Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.COBBLESTONE_COMMON, new FossilDefinition(CUSTOM_BLOCK.COBBLESTONE_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.COBBLESTONE_COMMON), "Cobblestone Common"));
        fossilDefs.put(CUSTOM_BLOCK.COBBLESTONE_RARE, new FossilDefinition(CUSTOM_BLOCK.COBBLESTONE_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.COBBLESTONE_RARE), "Cobblestone Rare"));
        fossilDefs.put(CUSTOM_BLOCK.COBBLESTONE_EPIC, new FossilDefinition(CUSTOM_BLOCK.COBBLESTONE_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.COBBLESTONE_EPIC), "Cobblestone Epic"));
        fossilDefs.put(CUSTOM_BLOCK.COBBLESTONE_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.COBBLESTONE_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.COBBLESTONE_LEGENDARY), "Cobblestone Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.SANDSTONE_COMMON, new FossilDefinition(CUSTOM_BLOCK.SANDSTONE_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.SANDSTONE_COMMON), "Sandstone Common"));
        fossilDefs.put(CUSTOM_BLOCK.SANDSTONE_RARE, new FossilDefinition(CUSTOM_BLOCK.SANDSTONE_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.SANDSTONE_RARE), "Sandstone Rare"));
        fossilDefs.put(CUSTOM_BLOCK.SANDSTONE_EPIC, new FossilDefinition(CUSTOM_BLOCK.SANDSTONE_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.SANDSTONE_EPIC), "Sandstone Epic"));
        fossilDefs.put(CUSTOM_BLOCK.SANDSTONE_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.SANDSTONE_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.SANDSTONE_LEGENDARY), "Sandstone Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.NETHERRACK_COMMON, new FossilDefinition(CUSTOM_BLOCK.NETHERRACK_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.NETHERRACK_COMMON), "Netherrack Common"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERRACK_RARE, new FossilDefinition(CUSTOM_BLOCK.NETHERRACK_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.NETHERRACK_RARE), "Netherrack Rare"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERRACK_EPIC, new FossilDefinition(CUSTOM_BLOCK.NETHERRACK_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.NETHERRACK_EPIC), "Netherrack Epic"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERRACK_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.NETHERRACK_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.NETHERRACK_LEGENDARY), "Netherrack Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.ENDER_STONE_COMMON, new FossilDefinition(CUSTOM_BLOCK.ENDER_STONE_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.ENDER_STONE_COMMON), "End Stone Common"));
        fossilDefs.put(CUSTOM_BLOCK.ENDER_STONE_RARE, new FossilDefinition(CUSTOM_BLOCK.ENDER_STONE_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.ENDER_STONE_RARE), "End Stone Rare"));
        fossilDefs.put(CUSTOM_BLOCK.ENDER_STONE_EPIC, new FossilDefinition(CUSTOM_BLOCK.ENDER_STONE_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.ENDER_STONE_EPIC), "End Stone Epic"));
        fossilDefs.put(CUSTOM_BLOCK.ENDER_STONE_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.ENDER_STONE_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.ENDER_STONE_LEGENDARY), "End Stone Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.ADAMANTIUM_COMMON, new FossilDefinition(CUSTOM_BLOCK.ADAMANTIUM_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.ADAMANTIUM_COMMON), "Adamantium Common"));
        fossilDefs.put(CUSTOM_BLOCK.ADAMANTIUM_RARE, new FossilDefinition(CUSTOM_BLOCK.ADAMANTIUM_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.ADAMANTIUM_RARE), "Adamantium Rare"));
        fossilDefs.put(CUSTOM_BLOCK.ADAMANTIUM_EPIC, new FossilDefinition(CUSTOM_BLOCK.ADAMANTIUM_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.ADAMANTIUM_EPIC), "Adamantium Epic"));
        fossilDefs.put(CUSTOM_BLOCK.ADAMANTIUM_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.ADAMANTIUM_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.ADAMANTIUM_LEGENDARY), "Adamantium Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.NETHERIUM_COMMON, new FossilDefinition(CUSTOM_BLOCK.NETHERIUM_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.NETHERIUM_COMMON), "Netherium Common"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERIUM_RARE, new FossilDefinition(CUSTOM_BLOCK.NETHERIUM_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.NETHERIUM_RARE), "Netherium Rare"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERIUM_EPIC, new FossilDefinition(CUSTOM_BLOCK.NETHERIUM_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.NETHERIUM_EPIC), "Netherium Epic"));
        fossilDefs.put(CUSTOM_BLOCK.NETHERIUM_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.NETHERIUM_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.NETHERIUM_LEGENDARY), "Netherium Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.TITAN_COMMON, new FossilDefinition(CUSTOM_BLOCK.TITAN_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.TITAN_COMMON), "Titan Common"));
        fossilDefs.put(CUSTOM_BLOCK.TITAN_RARE, new FossilDefinition(CUSTOM_BLOCK.TITAN_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.TITAN_RARE), "Titan Rare"));
        fossilDefs.put(CUSTOM_BLOCK.TITAN_EPIC, new FossilDefinition(CUSTOM_BLOCK.TITAN_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.TITAN_EPIC), "Titan Epic"));
        fossilDefs.put(CUSTOM_BLOCK.TITAN_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.TITAN_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.TITAN_LEGENDARY), "Titan Legendary"));

        fossilDefs.put(CUSTOM_BLOCK.SIENIT_COMMON, new FossilDefinition(CUSTOM_BLOCK.SIENIT_COMMON,
                FossilType.COMMON, 0.65, enumBlocks.get(CUSTOM_BLOCK.SIENIT_COMMON), "Sienit Common"));
        fossilDefs.put(CUSTOM_BLOCK.SIENIT_RARE, new FossilDefinition(CUSTOM_BLOCK.SIENIT_RARE,
                FossilType.RARE, 0.25, enumBlocks.get(CUSTOM_BLOCK.SIENIT_RARE), "Sienit Rare"));
        fossilDefs.put(CUSTOM_BLOCK.SIENIT_EPIC, new FossilDefinition(CUSTOM_BLOCK.SIENIT_EPIC,
                FossilType.EPIC, 0.08, enumBlocks.get(CUSTOM_BLOCK.SIENIT_EPIC), "Sienit Epic"));
        fossilDefs.put(CUSTOM_BLOCK.SIENIT_LEGENDARY, new FossilDefinition(CUSTOM_BLOCK.SIENIT_LEGENDARY,
                FossilType.LEGENDARY, 0.02, enumBlocks.get(CUSTOM_BLOCK.SIENIT_LEGENDARY), "Sienit Legendary"));

        // Default mine blocks : mine's fossil blocks
        defaultBlocks.put(Material.DIRT, Arrays.asList(CUSTOM_BLOCK.DIRT_COMMON, CUSTOM_BLOCK.DIRT_RARE,
                CUSTOM_BLOCK.DIRT_EPIC, CUSTOM_BLOCK.DIRT_LEGENDARY));
        defaultBlocks.put(Material.STONE, Arrays.asList(CUSTOM_BLOCK.STONE_COMMON, CUSTOM_BLOCK.STONE_RARE,
                CUSTOM_BLOCK.STONE_EPIC, CUSTOM_BLOCK.STONE_LEGENDARY));
        defaultBlocks.put(Material.COBBLESTONE, Arrays.asList(CUSTOM_BLOCK.COBBLESTONE_COMMON,
                CUSTOM_BLOCK.COBBLESTONE_RARE, CUSTOM_BLOCK.COBBLESTONE_EPIC, CUSTOM_BLOCK.COBBLESTONE_LEGENDARY));
        defaultBlocks.put(Material.SANDSTONE, Arrays.asList(CUSTOM_BLOCK.SANDSTONE_COMMON, CUSTOM_BLOCK.SANDSTONE_RARE,
                CUSTOM_BLOCK.SANDSTONE_EPIC, CUSTOM_BLOCK.SANDSTONE_LEGENDARY));
        defaultBlocks.put(Material.NETHERRACK, Arrays.asList(CUSTOM_BLOCK.NETHERRACK_COMMON, CUSTOM_BLOCK.NETHERRACK_RARE,
                CUSTOM_BLOCK.NETHERRACK_EPIC, CUSTOM_BLOCK.NETHERRACK_LEGENDARY));
        defaultBlocks.put(Material.ENDER_STONE, Arrays.asList(CUSTOM_BLOCK.ENDER_STONE_COMMON, CUSTOM_BLOCK.ENDER_STONE_RARE,
                CUSTOM_BLOCK.ENDER_STONE_EPIC, CUSTOM_BLOCK.ENDER_STONE_LEGENDARY));
    }
    
    public BlockRep getBlockByEnum(CUSTOM_BLOCK enumBlock) { return enumBlocks.get(enumBlock); }

    public List<CUSTOM_BLOCK> getByMaterial(Material m) {
        List<CUSTOM_BLOCK> list = defaultBlocks.get(m);
        return list==null ? Collections.emptyList() : Collections.unmodifiableList(defaultBlocks.get(m));
    }

    public FossilDefinition getFossilDefByEnum(CUSTOM_BLOCK cb) {
        return fossilDefs.get(cb);
    }

    public List<FossilDefinition> getFossilDefinitionsForBase(Material base) {
        List<CUSTOM_BLOCK> list = defaultBlocks.get(base);
        if (list == null || list.isEmpty()) return Collections.emptyList();
        List<FossilDefinition> out = new ArrayList<>();
        for (CUSTOM_BLOCK cb : list) {
            FossilDefinition def = fossilDefs.get(cb);
            if (def != null) out.add(def);
        }
        return out;
    }

    public Map<CUSTOM_BLOCK, BlockRep> getEnumBlocks() {
        return enumBlocks;
    }
}

/*
    DEFAULT_DIRT	dirt.png
    DEFAULT_STONE	stone.png
    DEFAULT_COBBLESTONE	cobblestone.png
    DEFAULT_SANDSTONE	sandstone_normal.png
    DEFAULT_NETHERRACK	netherrack.png
    DEFAULT_ENDER_STONE	end_stone.png
    DEFAULT_OBSIDIAN	obsidian.png
    DEFAULT_ADAMANTIUM	stone_andesite.png
    DEFAULT_NETHERIUM	stone_diorite.png
    DEFAULT_TITAN	stone_granite.png
    DEFAULT_SIENIT	stone_granite_smooth.png

    DIRT_COMMON		concrete_orange.png
    DIRT_RARE		concrete_magenta.png
    DIRT_EPIC		concrete_light_blue.png
    DIRT_LEGENDARY	concrete_yellow.png

    STONE_COMMON	concrete_lime.png
    STONE_RARE		concrete_pink.png
    STONE_EPIC		concrete_gray.png
    STONE_LEGENDARY	concrete_silver.png

    COBBLESTONE_COMMON	concrete_cyan.png
    COBBLESTONE_RARE	concrete_purple.png
    COBBLESTONE_EPIC	concrete_blue.png
    COBBLESTONE_LEGENDARY concrete_brown.png

    SANDSTONE_COMMON 	concrete_green.png
    SANDSTONE_RARE	concrete_red.png
    SANDSTONE_EPIC	concrete_black.png
    SANDSTONE_LEGENDARY	wool_colored_pink.png

    NETHERRACK_COMMON	stone_diorite_smooth.png
    NETHERRACK_RARE	sandstone_carved.png
    NETHERRACK_EPIC	sandstone_smooth.png
    NETHERRACK_LEGENDARY wool_colored_orange.png

    ENDER_STONE_COMMON	wool_colored_magenta.png
    ENDER_STONE_RARE	wool_colored_light_blue.png
    ENDER_STONE_EPIC	wool_colored_yellow.png
    ENDER_STONE_LEGENDARY wool_colored_lime.png

    ADAMANTIUM_COMMON	wool_colored_purple.png
    ADAMANTIUM_RARE	wool_colored_blue.png
    ADAMANTIUM_EPIC	wool_colored_brown.png
    ADAMANTIUM_LEGENDARY wool_colored_green.png

    NETHERIUM_COMMON	wool_colored_red.png
    NETHERIUM_RARE	wool_colored_black.png
    NETHERIUM_EPIC	glazed_terracotta_black.png
    NETHERIUM_LEGENDARY	glazed_terracotta_blue.png

    TITAN_COMMON	glazed_terracotta_brown.png
    TITAN_RARE		glazed_terracotta_cyan.png
    TITAN_EPIC		glazed_terracotta_gray.png
    TITAN_LEGENDARY	glazed_terracotta_lime.png

    SIENIT_COMMON	glazed_terracotta_magenta.png
    SIENIT_RARE		glazed_terracotta_orange.png
    SIENIT_EPIC		glazed_terracotta_pink.png
    SIENIT_LEGENDARY	glazed_terracotta_purple.png
 */