package com.legendsofzusgon.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.screens.PlayScreen;
import com.legendsofzusgon.game.sprites.Chest;
import com.legendsofzusgon.game.sprites.Points;

/**
 * Created by Daniel on 5/7/16.
 */
public class B2WorldCreator {

    public B2WorldCreator(PlayScreen firstLevelScreen) {
        World firstLevelScreenWorld = firstLevelScreen.getWorld();
        TiledMap firstLevelScreenMap = firstLevelScreen.getMap();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //Walls
        for (MapObject object:firstLevelScreenMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / LegendsofZusgon.pixelPerMeter , (rect.getY() + rect.getHeight() / 2) / LegendsofZusgon.pixelPerMeter);

            body = firstLevelScreenWorld.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / LegendsofZusgon.pixelPerMeter, rect.getHeight() / 2 / LegendsofZusgon.pixelPerMeter);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //Objects
        for (MapObject object:firstLevelScreenMap.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / LegendsofZusgon.pixelPerMeter , (rect.getY() + rect.getHeight() / 2) / LegendsofZusgon.pixelPerMeter);

            body = firstLevelScreenWorld.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / LegendsofZusgon.pixelPerMeter, rect.getHeight() / 2 / LegendsofZusgon.pixelPerMeter);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //Things
        for (MapObject object:firstLevelScreenMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / LegendsofZusgon.pixelPerMeter , (rect.getY() + rect.getHeight() / 2) / LegendsofZusgon.pixelPerMeter);

            body = firstLevelScreenWorld.createBody(bodyDef);

            shape.setAsBox(rect.getWidth() / 2 / LegendsofZusgon.pixelPerMeter, rect.getHeight() / 2 / LegendsofZusgon.pixelPerMeter);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        //Chests
        for (MapObject object:firstLevelScreenMap.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Chest(firstLevelScreen,rect);
        }

        //Things to break
        for (MapObject object:firstLevelScreenMap.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            new Points(firstLevelScreen,rect);
        }
    }
}
