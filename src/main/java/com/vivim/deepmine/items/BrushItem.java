package com.vivim.deepmine.items;

import com.vivim.deepmine.mine.customblock.BlockRep;
import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class BrushItem implements CustomItem {
    private final Map<CUSTOM_BLOCK, BlockRep> fossilBLocks;

    public BrushItem(CustomBlockManager blockManager) {
        this.fossilBLocks = blockManager.getEnumBlocks();
    }

    @Override
    public ItemType getType() { return ItemType.BRUSH; }

    @Override
    public Material getMaterial() { return Material.WOOD_PICKAXE; }

    @Override
    public ItemStack create() {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY+"Кисточка");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean canBreak(CUSTOM_BLOCK cb) {
        return fossilBLocks.get(cb).hasDrop();
    }

    @Override
    public void onBreak(Block b, Player p) {}
}