package com.vivim.deepmine.mine.layer;

import com.vivim.deepmine.mine.customblock.BlockRep;

public class MineLayer {
    private final BlockRep base;
    private final int thickness;
    public MineLayer(BlockRep base, int thickness) {
        this.base = base;
        this.thickness = thickness;
    }
    public BlockRep getBase() { return base; }
    public int getThickness() { return thickness; }
}