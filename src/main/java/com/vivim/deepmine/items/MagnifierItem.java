package com.vivim.deepmine.items;

import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagnifierItem implements CustomItem {

    @Override
    public ItemType getType() {
        return ItemType.MAGNIFIER;
    }

    @Override
    public Material getMaterial() { return Material.WOOD_HOE; }

    @Override
    public ItemStack create() {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+"Лупа");
        meta.setUnbreakable(false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean canBreak(CUSTOM_BLOCK cb) {
        return false;
    }

    @Override
    public void onBreak(Block b, Player p) {}
}