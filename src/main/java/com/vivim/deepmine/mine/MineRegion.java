package com.vivim.deepmine.mine;

public class MineRegion {
    private final int minX;
    private final int minZ;
    private final int maxX;
    private final int maxZ;

    public MineRegion(int x1, int x2, int z1, int z2) {
        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minZ = Math.min(z1, z2);
        this.maxZ = Math.max(z1, z2);
    }

    public boolean blockInRegion(int locX, int locZ) {
        return locX >= minX && locX <= maxX
                && locZ >= minZ && locZ <= maxZ;
    }

    public int[] getFromToX() { return new int[]{minX, maxX}; }
    public int[] getFromToZ() { return new int[]{minZ, maxZ}; }
}