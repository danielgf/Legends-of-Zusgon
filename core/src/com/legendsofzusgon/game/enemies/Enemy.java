package com.legendsofzusgon.game.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 6/10/16.
 */
public abstract class Enemy extends Sprite {

    protected World world;
    protected PlayScreen firstLevelScreen;
    public Body body;

    public Enemy(PlayScreen firstLevelScreen, float x, float y) {

        this.world = firstLevelScreen.getWorld();
        this.firstLevelScreen = firstLevelScreen;
        setPosition(x,y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
}
