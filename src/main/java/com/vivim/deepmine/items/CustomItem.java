package com.vivim.deepmine.items;

import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CustomItem {
    ItemType getType();
    Material getMaterial();
    ItemStack create();
    boolean canBreak(CUSTOM_BLOCK cb);
    void onBreak(Block b, Player p);

    default boolean tryBreak(Block block, Player player, CUSTOM_BLOCK cb) {
        if (getType() == ItemType.BRUSH && cb == null) return false;
        if (!canBreak(cb)) return false;
        onBreak(block, player);
        return true;
    }
}