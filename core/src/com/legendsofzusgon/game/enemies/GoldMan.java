package com.legendsofzusgon.game.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 6/10/16.
 */
public class GoldMan extends Enemy {


    private float stateTimer;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public GoldMan(PlayScreen firstLevelScreen, float x, float y) {
        super(firstLevelScreen, x, y);

        frames = new Array<TextureRegion>();
        for (int i = 0; i < 8; i++ )
            frames.add(new TextureRegion(firstLevelScreen.getTextureAtlas().findRegion("goldMan"), i * 39, 1, 38,50));
        walkAnimation = new Animation(0.4f,frames);
        stateTimer = 0;
        setBounds(getX(),getY(),(37 * 0.8f)/ LegendsofZusgon.pixelPerMeter,(50 * 0.8f)/ LegendsofZusgon.pixelPerMeter);
    }

    public void update(float dt){
        stateTimer += dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTimer, true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(50 / LegendsofZusgon.pixelPerMeter, 70 / LegendsofZusgon.pixelPerMeter);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / LegendsofZusgon.pixelPerMeter);

        fixtureDef.filter.categoryBits = LegendsofZusgon.enemyBit;
        fixtureDef.filter.maskBits = LegendsofZusgon.groundBit |
                LegendsofZusgon.chestsBit |
                LegendsofZusgon.thingsBit |
                LegendsofZusgon.enemyBit |
                LegendsofZusgon.objectBit |
                LegendsofZusgon.zusgonBit;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

    }

}
