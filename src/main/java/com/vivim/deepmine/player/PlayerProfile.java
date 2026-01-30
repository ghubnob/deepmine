package com.vivim.deepmine.player;

import java.util.UUID;

public class PlayerProfile {
    public final UUID uuid;
    private int depth;
    private int money;
    private int thick;

    public PlayerProfile(UUID uuid, int depth, int money, int thickness) {
        this.uuid = uuid;
        this.depth = depth;
        this.money = money;
        this.thick = thickness;
    }

    public int getMoney() { return money; }

    public int getDepth() { return depth; }

    public int getCurrentThickness() { return thick; }

    public void addMoney(int value) {
        money+=value;
    }

    public void incDepth() {
        depth++;
    }

    public void setCurrentThickness(int thickness) { thick = thickness; }
}