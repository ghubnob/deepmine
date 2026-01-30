package com.vivim.deepmine.items;

import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PickaxeItem implements CustomItem {

    @Override
    public ItemType getType() {
        return ItemType.PICKAXE;
    }

    @Override
    public Material getMaterial() { return Material.STONE_PICKAXE; }

    @Override
    public ItemStack create() {
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Кирка для раскопок");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean canBreak(CUSTOM_BLOCK cb) {
        return true;
    }

    @Override
    public void onBreak(Block b, Player p) {}
}