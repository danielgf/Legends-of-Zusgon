package com.legendsofzusgon.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 5/9/16.
 */
public abstract class InteractiveTileObjects {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObjects(PlayScreen firstLevelScreen, Rectangle bounds){

        this.world = firstLevelScreen.getWorld();
        this.map = firstLevelScreen.getMap();
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / LegendsofZusgon.pixelPerMeter , (bounds.getY() + bounds.getHeight() / 2) / LegendsofZusgon.pixelPerMeter);

        body = world.createBody(bodyDef);

        shape.setAsBox(bounds.getWidth() / 2 / LegendsofZusgon.pixelPerMeter, bounds.getHeight() / 2 / LegendsofZusgon.pixelPerMeter);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onBodyHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(6);
        return layer.getCell((int)(body.getPosition().x * LegendsofZusgon.pixelPerMeter / 16),
                (int)(body.getPosition().y * LegendsofZusgon.pixelPerMeter / 16));
    }
}
