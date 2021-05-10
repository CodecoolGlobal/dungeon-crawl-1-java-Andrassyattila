package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor {
    public Ghost(Cell cell) {
        super(cell);
        setAttack(3);
        setHealth(5);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
