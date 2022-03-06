package com.pacheco.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pacheco.game.animation.Animation;
import com.pacheco.game.animation.AnimationLoader;
import com.pacheco.game.map.MapModel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class AppTest 
{
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        Animation animation = AnimationLoader.loadFromFile(
                "src/main/resources/animations/knight.png", "src/main/resources/animations/knight.anim");
        System.out.println(animation);
    }
}
