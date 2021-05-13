package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Health extends Item{
    Random random = new Random();
    private final int healthPoint = random.nextInt(6-2+1)+2;
    public Health(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "health";
    }

    public int getHealthPoint() {
        return healthPoint;
    }
}
