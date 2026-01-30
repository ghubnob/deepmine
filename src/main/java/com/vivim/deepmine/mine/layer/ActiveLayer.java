package com.vivim.deepmine.mine.layer;

public class ActiveLayer {
    private final MineLayer layerTemplate;
    private int remainingThickness;
    private int currentY;

    public ActiveLayer(MineLayer layer, int y, int thickness) {
        this.layerTemplate = layer;
        if (thickness<=0) this.remainingThickness = layer.getThickness();
        else this.remainingThickness = thickness;
        this.currentY = y+1;
    }

    public boolean onBlockBrokenAtY(int y) {
        if (y == currentY-1) {
            currentY = y;
            remainingThickness--;
            return true;
        } else return y > currentY - 1;
    }

    public int getRemainingThickness() { return remainingThickness; }

    public int getCurrentY() { return currentY; }

    public ActiveLayer getCopyLayer() {
        return new ActiveLayer(layerTemplate, 60, remainingThickness);
    }

    public MineLayer getTemplate() { return layerTemplate; }
}