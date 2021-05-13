package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.Health;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.*;
import com.codecool.dungeoncrawl.logic.items.Sword;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Player extends Actor {
    ArrayList<Item> inventory;
    //ListView<String> inventoryList;
    public Player(Cell cell) {
        super(cell);
        setAttack(22);
        setHealth(15);
        inventory=new ArrayList<>();
        //inventoryList= new ListView();
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);

        if (nextCell.getType() != CellType.WALL) {
            if(nextCell.getType() == CellType.DOOR) {
                GameMap map2 = MapLoader.loadMap("/map2.txt");
                Main.setMap(map2);
            }

            if(nextCell.getActor() != null) {
                if (fight(this, nextCell.getActor())) {
                 nextCell.setActor(null);

                }
            }else if(nextCell.getItem()!=null){
                inventory.add(nextCell.getItem());
                //inventoryList.getItems().add(nextCell.getItem().getTileName());
                if(nextCell.getItem() instanceof Sword){
                    this.setAttack(getAttack()+((Sword) nextCell.getItem()).getDamage());
                }else if(nextCell.getItem() instanceof Health){
                    this.setHealth(getHealth()+((Health) nextCell.getItem()).getHealthPoint());
                }
                nextCell.setItem(null);
            }
            super.move(dx, dy);
        }
    }

    public ArrayList<Item> getInventory() {

        return inventory;
    }

    /*public ListView<String> getInventoryList() {
        return inventoryList;
    }*/
}
