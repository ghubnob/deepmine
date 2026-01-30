package com.vivim.deepmine.util;

import java.util.Objects;

public class BlockPos {
    private final int x; private final int y; private final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x; this.y = y; this.z = z;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

    public BlockPos add(int x1, int y1, int z1) {
        return new BlockPos(this.x + x1, this.y + y1, this.z + z1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPos blockPos = (BlockPos) o;
        return x == blockPos.x && y == blockPos.y && z == blockPos.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}