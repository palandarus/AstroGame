package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.base.BaseScreen;

    public class MenuScreen extends BaseScreen {

        private Texture ship;
        private Texture shot;
        private Texture background;
        private Vector2 posShip;
        private Vector2 posShot;
        private Vector2 vShot;
        private Vector2 vShip;
//    private float rotate;

        @Override
        public void show() {
            super.show();
            ship = new Texture("ship.png");
            shot = new Texture("shot.png");
            background = new Texture("hw1background.jpg");
            posShip = new Vector2();
            posShot = new Vector2();
            vShot = new Vector2(0.0f, 0.3f);
            vShip = new Vector2(1.0f, 0.0f);
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
            shot.dispose();
            super.dispose();
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            posShip.set(screenX, Gdx.graphics.getHeight() - screenY);
            return false;
        }

        private void update(float delta) {
            posShip.add(vShip);
            posShot.add(vShot);
//        rotate += 1;
        }

        private void draw() {
            Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
           
            batch.end();
        }

    }
