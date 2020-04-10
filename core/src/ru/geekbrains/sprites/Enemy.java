package ru.geekbrains.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class Enemy extends Ship {
    private boolean startflag = true;
    private Vector2 startpos = new Vector2(1f, 0f);
    private Vector2 startSpeed = new Vector2(0f, -1.5f);

    public Enemy(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        v = new Vector2();
        v0 = new Vector2();
        bulletV = new Vector2();
    }

    @Override
    public void update(float delta) {
        if (this.getTop() > worldBounds.getTop()) {
            pos.mulAdd(startSpeed, delta);
            if(this.getTop()<worldBounds.getTop()) {
                this.setTop(worldBounds.getTop());
                reloadTimer=reloadInterval;
            }
        } else {
            pos.mulAdd(v, delta);

            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0f;
                if (this.getTop() <= worldBounds.getTop()) {
                    shoot();
                    if(this.getHeight()==0.1f)System.out.println(" smallship Piu at toptop");
                    else if(this.getHeight()==0.15f)System.out.println(" mediumship Piu at toptop");
                    else if(this.getHeight()==0.2f)System.out.println(" bigship Piu at toptop");
                }

            }

        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            Sound shootSound,
            Sound explosionSound,
            int hp,
            float height
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = reloadInterval;
        this.shootSound = shootSound;
        this.explosionSound = explosionSound;
        this.hp = hp;
        this.v.set(v0);
        setHeightProportion(height);
    }

    @Override
    public void destroy() {
        explosionSound.play();
        super.destroy();
    }


    public void destroy(boolean b) {
        super.destroy();
    }
}
