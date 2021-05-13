package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

import java.util.Random;

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

    public void move() {
        int dx = 0;
        int dy = 0;
        switch (getRandomNumberForMovement()) {
            case 1:
                dx = 0;
                dy = -2;
                break;
            case 2:
                dx = 0;
                dy = 2;
                break;
            case 3:
                dx = -2;
                dy = 0;
                break;
            case 4:
                dx = 2;
                dy = 0;
                break;
        }
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.EMPTY && nextCell.getItem() == null && nextCell.getActor() == null) {
            super.move(dx, dy);
            }


    }
    public int getRandomNumberForMovement(){
        Random r = new Random();
        int low = 1;
        int high = 5;
        System.out.println(r.nextInt(high-low) + low);
        return r.nextInt(high-low) + low;
    }
}
