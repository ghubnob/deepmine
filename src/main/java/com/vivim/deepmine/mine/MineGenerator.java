package com.vivim.deepmine.mine;

import com.vivim.deepmine.fossils.FossilDefinition;
import com.vivim.deepmine.fossils.FossilService;
import com.vivim.deepmine.mine.customblock.BlockRep;
import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import com.vivim.deepmine.mine.layer.ActiveLayer;
import com.vivim.deepmine.util.BlockPos;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class MineGenerator {
    private final CustomBlockManager blockManager;
    private final JavaPlugin plugin;

    public MineGenerator(CustomBlockManager blockManager, JavaPlugin plugin) {
        this.blockManager = blockManager;
        this.plugin = plugin;
    }

    public void generateLayer(Player player, MineInstance mine) {
        if (mine.isGenerating()) return;
        mine.setGenerating(true);
        FossilService fossilService = new FossilService(blockManager);

        World world = player.getWorld();
        MineRegion region = mine.getRegion();
        ActiveLayer layer = mine.getActiveLayer();
        BlockRep baseRep = layer.getTemplate().getBase();

        final int MIN_Y = mine.getMinY();
        final double CHANCE = mine.getFossilChance();

        int[] xs = region.getFromToX();
        int[] zs = region.getFromToZ();

        int fromX = xs[0];
        int toX   = xs[1];
        int fromZ = zs[0];
        int toZ   = zs[1];

        int startY = layer.getCurrentY() - 1;
        int endY = Math.max(startY - layer.getRemainingThickness() + 1, MIN_Y);


        BukkitRunnable task = new BukkitRunnable() {
            int y = startY;
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                //end generation
                if (y < endY) {
                    for (int x = fromX; x <= toX; x++) {
                        for (int z = fromZ; z <= toZ; z++) {
                            Block thisBlock = world.getBlockAt(x, y, z);
                            thisBlock.setType(Material.BEDROCK, false);
                        }
                    }
                    this.cancel();
                    mine.setGenerating(false);
                    return;
                }

                for (int x = fromX; x <= toX; x++) {
                    for (int z = fromZ; z <= toZ; z++) {
                        Block thisBlock = world.getBlockAt(x, y, z);
                        thisBlock.setType(baseRep.getMaterial(), false);
                        thisBlock.setData(baseRep.getData(), false);

                        if (y == 60 && baseRep.getMaterial() == Material.DIRT) {
                            thisBlock.setType(Material.GRASS, false);
                        }

                        Optional<FossilDefinition> fossilRolled = fossilService.roll(baseRep.getMaterial(), CHANCE);
                        if (fossilRolled.isPresent()) {
                            mine.addFossil(new BlockPos(x,y,z), fossilRolled.get().getId());
                        }
                    }
                }

                y--;
            }
        };

        task.runTaskTimer(plugin, 0L, 1L);
    }
}