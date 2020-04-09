package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {
    private Vector2 touchCalcTMP;
    private Vector2 vShip;
    private Vector2 touch;
    private float VLEN = 0.005f;
    private float STARTX = 0f;
    private float STARTY = -0.45f;
    private float osXSpeed = 0.001f;
    private Rect worldBounds;


    public Ship(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("main_ship"));
        setScale(0.1f);
        pos.set(STARTX, STARTY);
        touchCalcTMP = new Vector2();
        touch = new Vector2(pos);
        vShip = new Vector2(osXSpeed, 0f);
        regions[0] = new TextureRegion(regions[0], 0, 0, regions[0].getRegionWidth() / 2, regions[0].getRegionHeight());

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.5f);
        this.worldBounds = worldBounds;

    }

    @Override
    public void update(float delta) {
        touchCalcTMP.set(pos);
        touchCalcTMP.add(vShip);
        if (Math.abs(touchCalcTMP.x) > (worldBounds.getHalfWidth() - this.getHalfWidth() / 10))
            vShip.setZero();

        touchCalcTMP.set(touch);
        if (touchCalcTMP.sub(pos).len() > VLEN) {
            pos.add(vShip);
        } else {
            vShip.setZero();
            pos.set(touch);
        }

    }

    public void touchDown(Vector2 touch) {
        move(touch);
    }

    public void touchUp(Vector2 touch) {
        move(touch);
    }

    public void touchDragged(Vector2 touch) {
        move(touch);
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case 21:
                moveFromKey(-worldBounds.getHalfWidth());
                break;
            case 29:
                moveFromKey(-worldBounds.getHalfWidth());
                break;

            case 22:
                moveFromKey(worldBounds.getHalfWidth());
                break;

            case 32:
                moveFromKey(worldBounds.getHalfWidth());
                break;

        }
    }



    public void keyUp(int keycode) {
        touch.set(pos.x, pos.y);
        vShip.set(0, 0);
    }

    private void move(Vector2 touch) {
        this.touch.set(touch.x, STARTY);
        touchCalcTMP.set(this.touch);
        vShip.set(touchCalcTMP.sub(pos)).setLength(VLEN);
    }

    private void moveFromKey(float v) {
        touch.set(v, 0f);
        vShip.set(touch.x, 0).setLength(VLEN);
    }
}
