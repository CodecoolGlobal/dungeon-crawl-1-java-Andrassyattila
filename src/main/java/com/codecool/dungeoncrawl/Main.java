package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.Item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    static GameMap map = MapLoader.loadMap("/map.txt");

    Button pickUpButton = new Button("Pick up");
    Canvas canvas = new Canvas(
            20 * Tiles.TILE_WIDTH,
            20 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label attackLabel = new Label();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.add(new Label("Health: "), 0,0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Attack: "),0,1);
        ui.add(attackLabel, 1, 1);
        ui.add(new Label("Inventory:"),0,2);
        //ui.add(pickUpButton,0,10);
        //pickUpButton.setDisable(true);
        //ui.add(map.getPlayer().getInventoryList(), 0,3,2,10);
        //map.getPlayer().getInventoryList().setDisable(true);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < 21; x++) {
            for (int y = 0; y < 21; y++) {
                Player player =map.getPlayer();
                if(map.getCell(x,y).getActor() instanceof Skeleton){
                    Skeleton skeleton = (Skeleton) map.getCell(x,y).getActor();
                    skeleton.move();
                }else if(map.getCell(x,y).getActor() instanceof Ghost){
                    Ghost ghost= (Ghost) map.getCell(x,y).getActor();
                    ghost.move();
                }
                int playerPositionX= player.getX();
                int playerPositionY= player.getY();
                int cameraX = playerPositionX + x - 10;
                int cameraY = playerPositionY + y -10;
                if(cameraX < 0 || cameraX >= map.getWidth() || cameraY < 0 ||cameraY >= map.getHeight()){
                    Tiles.drawTile(context, () -> "empty", x,y);
                }else {
                    Cell cell = map.getCell(cameraX, cameraY);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        attackLabel.setText(""+map.getPlayer().getAttack());

    }
    public void setButton(){
        pickUpButton.setDisable(!pickUpButton.isDisable());
    }

    public static void setMap(GameMap map){
        Main.map = map;
    }
}
