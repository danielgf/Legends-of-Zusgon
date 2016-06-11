package com.legendsofzusgon.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.scenes.Hud;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 5/9/16.
 */
public class Chest extends InteractiveTileObjects {

    private static TiledMapTileSet tileSet;

    public Chest(PlayScreen firstLevelScreen, Rectangle bounds){
        super(firstLevelScreen,bounds);
        tileSet = map.getTileSets().getTileSet("chest");
        fixture.setUserData(this);
        setCategoryFilter(LegendsofZusgon.chestsBit);
    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Chest", "Collision");
        Hud.addScore(100);
    }
}
