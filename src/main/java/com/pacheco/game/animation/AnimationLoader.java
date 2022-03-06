package com.pacheco.game.animation;

import com.pacheco.game.Constants;
import com.pacheco.game.core.Box;
import com.pacheco.game.core.Vector2d;
import com.pacheco.game.util.ResourceUtil;
import com.pacheco.game.util.SpriteUtil;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AnimationLoader {

    public static Animation load(String animationName) {
        String spriteFile = Constants.ANIMATIONS_PATH + animationName + ".png";
        String animFile = Constants.ANIMATIONS_PATH + animationName + ".anim";

        return loadFromFile(spriteFile, animFile);
    }

    public static Animation loadFromFile(String spriteFile, String animFile) {
        Image sprite = SpriteUtil.loadSprite(spriteFile);
        HashMap<String, Animation.Action> animationDefinition = new HashMap<>();

        List<String> animContent = List.of(ResourceUtil.getContentFromResource(animFile).split("\n"))
                .stream().filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        Iterator<String> animContentIterator = animContent.iterator();

        while (animContentIterator.hasNext()) {
            String line = animContentIterator.next();

            String name = line;
            Animation.Action action = new Animation.Action();

            line = animContentIterator.next();
            while(!line.equals("***")) {
                if (line.trim().equals("definition")) action.definition = getDefinition(animContentIterator);
                if (line.trim().equals("dimensions")) action.dimensions = getDimensions(animContentIterator);
                line = animContentIterator.next();
            }

            animationDefinition.put(name, action);
        }

        return new Animation(sprite, animationDefinition);
    }

    private static Vector2d getDimensions(Iterator<String> animContentIterator) {
        String line = animContentIterator.next();
        String[] numbers = line.split(" ");
        Vector2d dimensions = new Vector2d();
        dimensions.x = Double.parseDouble(numbers[0]);
        dimensions.y = Double.parseDouble(numbers[1]);

        return dimensions;
    }

    private static List<Box> getDefinition(Iterator<String> animContentIterator) {
        List<Box> definition = new ArrayList<>();
        String line = animContentIterator.next();

        while (!line.equals("---")) {
            String[] numbers = line.split(" ");
            Box box = new Box();
            box.left = Double.parseDouble(numbers[0]);
            box.top = Double.parseDouble(numbers[1]);
            box.right = Double.parseDouble(numbers[2]);
            box.bottom = Double.parseDouble(numbers[3]);
            definition.add(box);
            line = animContentIterator.next();
        }

        return definition;
    }

}
