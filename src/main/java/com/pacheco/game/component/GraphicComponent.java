package com.pacheco.game.component;

import javafx.scene.canvas.GraphicsContext;

public interface GraphicComponent extends Component {
    public void render(GraphicsContext gc);
}
