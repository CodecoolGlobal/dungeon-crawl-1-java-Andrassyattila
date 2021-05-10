package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Actor {
    public Spider(Cell cell) {
        super(cell);
        setAttack(2);
        setHealth(5);
    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
