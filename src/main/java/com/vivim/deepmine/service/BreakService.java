package com.vivim.deepmine.service;

import com.vivim.deepmine.items.BrushItem;
import com.vivim.deepmine.items.CustomItem;
import com.vivim.deepmine.items.ItemManager;
import com.vivim.deepmine.items.ItemType;
import com.vivim.deepmine.mine.MineGenerator;
import com.vivim.deepmine.mine.MineInstance;
import com.vivim.deepmine.mine.MinesManager;
import com.vivim.deepmine.mine.customblock.BlockRep;
import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.ActiveLayer;
import com.vivim.deepmine.mine.layer.MineLayerManager;
import com.vivim.deepmine.player.PlayerProfile;
import com.vivim.deepmine.player.PlayerProfileManager;
import com.vivim.deepmine.util.BlockPos;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BreakService {
    private final MinesManager minesMng;
    private final PlayerProfileManager profileManager;
    private final ItemManager itemMng;
    private final MineLayerManager layerManager;
    private final CustomBlockManager blockManager;
    private final JavaPlugin plugin;

    public BreakService(MinesManager mng, PlayerProfileManager profileManager,
                        ItemManager itemMng, MineLayerManager layerManager,
                        CustomBlockManager blockManager, JavaPlugin plugin) {
        this.minesMng = mng;
        this.profileManager = profileManager;
        this.itemMng = itemMng;
        this.layerManager = layerManager;
        this.blockManager = blockManager;
        this.plugin = plugin;
    }

    public void breakService(BlockBreakEvent e) {
        e.setDropItems(false);

        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        MineInstance mine = minesMng.getOrCreateMine(uuid);
        PlayerProfile profile = profileManager.getOrCreateDefault(uuid);

        if (mine == null || !mine.isBlockInRegion(e.getBlock().getX(),e.getBlock().getZ()) || mine.isGenerating())
        { e.setCancelled(true); return; }

        ActiveLayer activeLayer = mine.getActiveLayer();

        ItemStack itemM = e.getPlayer().getInventory().getItemInMainHand();
        CustomItem item = itemMng.getByStack(itemM);
        Block b = e.getBlock();
        BlockPos pos = new BlockPos(b.getX(),b.getY(), b.getZ());
        CUSTOM_BLOCK cb = mine.getFossil(pos);

        if (item == null) { e.setCancelled(true); return; }
        if (item.getType()==ItemType.BRUSH && cb==null) { e.setCancelled(true); return; }
        if (!item.tryBreak(b, p, cb)) { e.setCancelled(true); return; }
        if (!activeLayer.onBlockBrokenAtY(b.getY())) { e.setCancelled(true); return; }

        if (!e.isCancelled()) {
            validateMining(activeLayer, b, profile, mine, p);
            p.sendActionBar(profile.getDepth()+" depth");
        }
    }

    private void validateMining(ActiveLayer activeLayer, Block block, PlayerProfile profile, MineInstance mine, Player p) {
        profile.setCurrentThickness(activeLayer.getRemainingThickness());

        if (activeLayer.getRemainingThickness() <= 0) {
            layerEnded(profile, block, mine, p);
        }

        if (block.getLocation().getY() <= 11) {
            mineEnded(p, block, mine);
        }

        validateFossil(block, mine);

        ifBrokenFossil(p, block, mine);
    }

    private void layerEnded(PlayerProfile profile, Block block, MineInstance mine, Player p) {
        profile.incDepth();
        ActiveLayer newLayer = layerManager.generateLayer(profile.getDepth(), block.getY()-1,
                profile.getCurrentThickness());
        profile.setCurrentThickness(newLayer.getRemainingThickness());
        mine.newLayer(newLayer);

        MineGenerator generator = new MineGenerator(blockManager, plugin);
        generator.generateLayer(p, mine);
    }

    private void mineEnded(Player p, Block block, MineInstance mine) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,30,0));
        Bukkit.getScheduler().runTaskLater(plugin,() -> {
            p.teleport(block.getLocation().add(0,50,0));
        },1);

        mine.newLayer(mine.getActiveLayer().getCopyLayer());

        MineGenerator generator = new MineGenerator(blockManager, plugin);
        generator.generateLayer(p, mine);
    }

    @SuppressWarnings("deprecation")
    private void validateFossil(Block b, MineInstance mine) {
        for (BlockPos pos : neighbours(b)) {
            CUSTOM_BLOCK fossilType = mine.getFossil(pos);
            if (fossilType == null) continue;

            BlockRep rep = blockManager.getBlockByEnum(fossilType);
            if (rep == null) continue;

            Block b1 = b.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
            b1.setType(rep.getMaterial(), false);
            b1.setData(rep.getData(), false);
        }
    }

    private List<BlockPos> neighbours(Block b) {
        BlockPos pos = new BlockPos(b.getX(), b.getY(), b.getZ());
        return Arrays.asList(pos.add(1,0,0), pos.add(-1,0,0), pos.add(0,1,0),
                pos.add(0,-1,0), pos.add(0,0,1), pos.add(0,0,-1));
    }

    private void ifBrokenFossil(Player p, Block b, MineInstance mine) {
        BlockPos pos = new BlockPos(b.getX(),b.getY(), b.getZ());
        CUSTOM_BLOCK cb = mine.getFossil(pos);
        if (cb == null) return;

        BlockRep rep = blockManager.getBlockByEnum(cb);
        if (rep == null) { mine.rmFossil(pos); return; }

        ItemStack itemM = p.getInventory().getItemInMainHand();
        ItemType itemType = itemMng.getByStack(itemM).getType();
        ItemStack drop = rep.createDrop(itemType);
        if (drop == null) { mine.rmFossil(pos); return; }

        p.getInventory().addItem(drop);
        mine.rmFossil(pos);
    }
}