package com.pacheco.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class App extends Application
{
    private static final int WND_WIDTH = 1200;
    private static final int WND_HEIGHT = 960;
    private static final String GAME_TITLE = "RPG";

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(WND_WIDTH, WND_HEIGHT);
        final Game game = new Game(canvas);
        game.start();

        new AnimationTimer() {
            private long lastTime;

            @Override
            public void start() {
                lastTime = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                long elapsedNanoSecs = now - lastTime;
                lastTime = now;
                double elapsedSeconds = elapsedNanoSecs / 1000000000.0;

                game.update(elapsedSeconds);
            }
        }.start();

        Scene scene = new Scene(new Group(canvas));

        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> game.keyboardInput(key));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, key -> game.keyboardInput(key));

        stage.setScene(scene);
        stage.setTitle(GAME_TITLE);
        stage.setResizable(false);
        stage.show();
    }

    public static void main( String[] args ) throws Exception {
        launch(args);
    }
}
