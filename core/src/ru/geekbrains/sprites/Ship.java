package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {
    private Vector2 touchCalcTMP;
    private Vector2 vShip;
    private Vector2 touch;
    private float VLEN = 0.001f;

    public Ship(Texture region) throws GameException {
        super(new TextureRegion(region));
        setScale(0.1f);
        touchCalcTMP=new Vector2();
        vShip=new Vector2(0f,0f);
        touch=new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(1f);
//        pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        touchCalcTMP.set(touch);
        if(touchCalcTMP.sub(pos).len()>VLEN) {
            pos.add(vShip);
        }
        else{
            vShip.setZero();
            pos.set(touch);
        }
    }

    public void touchDown(Vector2 touch) {
        this.touch=touch;
        touchCalcTMP.set(touch);
        vShip.set(touchCalcTMP.sub(pos)).setLength(VLEN);
    }

    public void touchUp(Vector2 touch) {
        this.touch.set(touch);
    }

    public void touchDragged(Vector2 touch) {
        this.touch.set(touch);
    }
}
