package com.pacheco.game.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoxGraphicComponent implements GraphicComponent {
    private Color color;
    private Integer width;
    private Integer height;

    public BoxGraphicComponent(Color color, Integer width, Integer height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(0, 0, width, height);
    }
}
