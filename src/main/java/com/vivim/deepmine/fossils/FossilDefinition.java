package com.vivim.deepmine.fossils;

import com.vivim.deepmine.mine.customblock.BlockRep;
import com.vivim.deepmine.mine.customblock.CUSTOM_BLOCK;

public class FossilDefinition {
    private final CUSTOM_BLOCK id;
    private final FossilType type;
    private final double weight;
    private final BlockRep representation;
    private final String displayName;

    public FossilDefinition(CUSTOM_BLOCK id, FossilType type, double weight, BlockRep rep, String displayName) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.representation = rep;
        this.displayName = displayName;
    }
    public CUSTOM_BLOCK getId() { return id; }
    public double getWeight() { return weight; }
    public FossilType getType() { return type; }
    public BlockRep getRepresentation() { return representation; }
    public String getDisplayName() { return displayName; }
}