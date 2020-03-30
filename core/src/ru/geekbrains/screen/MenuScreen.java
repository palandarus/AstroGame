package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ru.geekbrains.base.BaseScreen;

    public class MenuScreen extends BaseScreen {

        private Texture ship;
        private ArrayList<Texture> shot=new ArrayList<>();
        private Texture background;
        private Vector2 posShip;
        private Vector2 posShot;
        private Vector2 vShot;
        private Vector2 vShip;
        private Vector2 touch;
        private Vector2 touchCalcTMP;
        private final Float VLEN=4.5f;

        @Override
        public void show() {
            super.show();
            ship = new Texture("ship.png");
            shot.add(new Texture("shot.png"));
            background = new Texture("hw1background.jpg");
            posShip = new Vector2();
            posShot = new Vector2();
            touch = new Vector2();
            touchCalcTMP = new Vector2();
            vShot = new Vector2(0.0f, 0.0f);
            vShip = new Vector2(0.0f, 5.0f);
//        rotate = 0;
        }

        @Override
        public void render(float delta) {
            update(delta);
            draw();
        }

        @Override
        public void dispose() {
            batch.dispose();
            background.dispose();
            ship.dispose();
            for (int i = 0; i < shot.size(); i++) {
                shot.get(i).dispose();
            }
            super.dispose();
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            touch.set(screenX, Gdx.graphics.getHeight() - screenY);
            vShip.set(touch.cpy().sub(posShip)).setLength(VLEN);
            return false;
        }

        private void update(float delta) {
            
//        rotate += 1;
        }

        private void draw() {
            Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(background, 0, 0);
            batch.draw(ship, posShip.x, posShip.y,100,100);
            for (int i = 0; i < shot.size(); i++) {
                batch.draw(shot.get(i), posShot.x, posShot.y,20,20);
            }

//        batch.draw(new TextureRegion(img), pos.x, pos.y, pos.x, pos.y, 250, 250, 1, 1, rotate);
            batch.end();
        }

    }
