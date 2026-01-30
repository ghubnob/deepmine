package com.vivim.deepmine.fossils;

import com.vivim.deepmine.mine.customblock.CustomBlockManager;
import org.bukkit.Material;

import java.util.*;

public class FossilService {

    private final CustomBlockManager blockManager;
    private final Random rnd = new Random();

    public FossilService(CustomBlockManager blockManager) {
        this.blockManager = blockManager;
    }

    public Optional<FossilDefinition> roll(Material baseMaterial, double layerChance) {
        if (layerChance <= 0.0) return Optional.empty();
        if (rnd.nextDouble() > layerChance) return Optional.empty();

        List<FossilDefinition> defs = blockManager.getFossilDefinitionsForBase(baseMaterial);
        if (defs == null || defs.isEmpty()) return Optional.empty();

        return Optional.of(weightedRoll(defs));
    }

    private FossilDefinition weightedRoll(List<FossilDefinition> list) {
        double total = 0.;
        for (FossilDefinition d : list) total += d.getWeight();

        double r = rnd.nextDouble() * total;
        for (FossilDefinition d : list) {
            r -= d.getWeight();
            if (r <= 0) return d;
        }
        return list.get(list.size() - 1);
    }
}