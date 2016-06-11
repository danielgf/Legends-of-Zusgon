package com.legendsofzusgon.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.scenes.Hud;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 5/11/16.
 */
public class Points extends InteractiveTileObjects {

    public Points(PlayScreen firstLevelScreen, Rectangle bounds) {
        super(firstLevelScreen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(LegendsofZusgon.thingsBit);
    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Things to broke", "Collision");
        setCategoryFilter(LegendsofZusgon.destroyBit);
        getCell().setTile(null);
        Hud.addScore(200);
    }
}
