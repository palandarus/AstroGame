package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Ship extends Sprite {
    private Vector2 touchCalcTMP;
    private Vector2 vShip;
    private Vector2 touch;
    private float VLEN = 0.005f;
    private float STARTX = 0f;
    private float STARTY = -0.45f;
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("main_ship"));
        setScale(0.1f);
        pos.set(STARTX, STARTY);
        touchCalcTMP = new Vector2();
        touch = new Vector2(pos);
        vShip = new Vector2(0.08f, 0f);
        regions[0] = new TextureRegion(regions[0], 0, 0, regions[0].getRegionWidth() / 2, regions[0].getRegionHeight());

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.5f);
        this.worldBounds = worldBounds;

    }

    @Override
    public void update(float delta) {
        touchCalcTMP.set(touch);
        if (Math.abs(touchCalcTMP.sub(pos.x, 0).x) > VLEN) {
            pos.add(vShip.x, 0);
        } else {
            vShip.setZero();
            pos.set(touch.x, STARTY);
        }
    }

    public void touchDown(Vector2 touch) {
        this.touch = touch;
        touchCalcTMP.set(touch);
        vShip.set(touchCalcTMP.x, 0).setLength(VLEN);
    }

    public void touchUp(Vector2 touch) {
        this.touch.set(touch);
    }

    public void touchDragged(Vector2 touch) {
        this.touch.set(touch);
    }
}
