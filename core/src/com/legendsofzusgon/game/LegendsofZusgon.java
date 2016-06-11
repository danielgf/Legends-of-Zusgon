package com.legendsofzusgon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.legendsofzusgon.game.states.MenuState;

public class LegendsofZusgon extends Game {

    public static final String title = "Legends of Zusgon";
    public static final float virtualWidth = 480;
    public static final float virtualHeigth = 480;
    public static final float pixelPerMeter = 100;
    public static final float scale = 2.0f;

    public static final short groundBit = 1;
    public static final short zusgonBit = 2;
    public static final short thingsBit = 4;
    public static final short chestsBit = 8;
    public static final short destroyBit = 16;
    public static final short objectBit = 32;
    public static final short enemyBit = 64;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuState(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
