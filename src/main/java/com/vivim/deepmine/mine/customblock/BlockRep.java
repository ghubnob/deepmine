package com.vivim.deepmine.mine.customblock;

import com.vivim.deepmine.items.ItemType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class BlockRep {
    private final Material blockBase;
    private final byte data;

    private final Material itemBase;
    private final short itemDamage;
    private final boolean hasDrop;

    public BlockRep(Material blockBase, byte data, Material itemBase, short itemDamage) {
        this.blockBase = blockBase;
        this.data = data;
        this.itemBase = itemBase;
        this.itemDamage = itemDamage;
        this.hasDrop = true;
    }
    public BlockRep(Material blockBase, byte data) {
        this.blockBase = blockBase;
        this.data = data;
        this.itemBase = null;
        this.itemDamage = 0;
        this.hasDrop = false;
    }

    public Material getMaterial() { return blockBase; }
    public byte getData() { return data; }
    public boolean hasDrop() { return hasDrop; }
    public ItemStack createDrop(ItemType type) {
        if (!hasDrop) return null;
        //default block
        if (type == ItemType.PICKAXE) {
            String name = blockBase.name() + data + " fossil";
            List<String> lore = Collections.singletonList(blockBase.name() + data + " lore!");
            ItemStack is = new ItemStack(blockBase,1,data);
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            is.setItemMeta(meta);
            return is;
        }
        //fossil
        else if (type == ItemType.BRUSH) {
            String name = itemBase.name() + itemDamage + " item";
            List<String> lore = Collections.singletonList(itemBase.name() + itemDamage + " lore!");
            ItemStack is = new ItemStack(itemBase,1,itemDamage);
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);
            is.setItemMeta(meta);
            return is;
        }
        else return null;
    }
}