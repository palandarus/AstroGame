package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class NewGame extends Sprite {

    public NewGame(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("button_new_game"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.07f);
        setTop(-0.1f);
    }
}