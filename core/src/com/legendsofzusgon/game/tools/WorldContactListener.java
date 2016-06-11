package com.legendsofzusgon.game.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.legendsofzusgon.game.sprites.InteractiveTileObjects;

/**
 * Created by Daniel on 5/7/16.
 */
public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() == "body" || fixtureB.getUserData() == "body"){
            Fixture body = fixtureA.getUserData() == "body" ? fixtureA : fixtureB;
            Fixture obj = body == fixtureA ? fixtureB : fixtureA;

            if (obj.getUserData() != null && InteractiveTileObjects.class.isAssignableFrom(obj.getUserData().getClass())){
                ((InteractiveTileObjects) obj.getUserData()).onBodyHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
