package com.vivim.deepmine.items;

import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;

public class ItemManager {
    private final Map<ItemType,CustomItem> items = new EnumMap<>(ItemType.class);
    private final Map<Material,ItemType> typeMaterialMap = new EnumMap<>(Material.class);

    public ItemManager(CustomBlockManager blockManager) {
        items.put(ItemType.PICKAXE,new PickaxeItem());
        items.put(ItemType.BRUSH,new BrushItem(blockManager));
        items.put(ItemType.MAGNIFIER,new MagnifierItem());

        typeMaterialMap.put(items.get(ItemType.PICKAXE).getMaterial(), ItemType.PICKAXE);
        typeMaterialMap.put(items.get(ItemType.BRUSH).getMaterial(), ItemType.BRUSH);
        typeMaterialMap.put(items.get(ItemType.MAGNIFIER).getMaterial(), ItemType.MAGNIFIER);
    }

    public CustomItem getByStack(ItemStack stack) { return items.get(typeMaterialMap.get(stack.getType())); }

    public void giveTutorialItems(Player p) {
        giveItem(ItemType.PICKAXE,p);
        giveItem(ItemType.BRUSH,p);
        giveItem(ItemType.MAGNIFIER,p);
    }

    public void giveItem(ItemType type, Player p) {
        ItemStack item = items.get(type).create();
        p.getInventory().addItem(item);
    }
}