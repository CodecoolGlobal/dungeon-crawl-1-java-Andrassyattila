package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
        setAttack(5);
        setHealth(5);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        if (nextCell.getType() != CellType.WALL) {
            /*if(nextCell.getItem() != null) {
                .setOnAction(actionEvent ->  {
                    //... do something in here.
                });*/
            super.move(dx, dy);
        }else if(nextCell.getActor() instanceof Skeleton){
            
        }
    }
}
