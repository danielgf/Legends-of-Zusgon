package com.legendsofzusgon.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 5/7/16.
 */
public class Player extends Sprite{

    public enum State{

        WALKINGDOWN,
        WALKINGUP,
        WALKINGRIGHT,
        WALKINGLEFT,
        STANDING
//        ATTACKING
    };

    public State currentState;
    public State previousState;

    public World world;
    public Body body;
    private TextureRegion zusgonStand;

    private Animation zusgonWalkingDown;
    private Animation zusgonWalkingUp;
    private Animation zusgonWalkingRight;
    private Animation zusgonWalkingLeft;
    private Animation zusgonAttack;

    private float stateTimer;
    private boolean walkingDown;
    private boolean walkingUp;
    private boolean walkingRight;
    private boolean walkingLeft;
    private boolean attacking;

    public Player(PlayScreen firstLevelScreen) {
        super(firstLevelScreen.getTextureAtlas().findRegion("zelda"));

        this.world = firstLevelScreen.getWorld();
        defineZusgon();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        walkingDown = true;
        walkingUp = true;
        walkingRight = true;
        walkingLeft = true;


        Array<TextureRegion> frames = new Array<TextureRegion>();

        //walkig down
        for (int i = 1; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i * 65, 128, 52, 52));
        zusgonWalkingDown = new Animation(0.1f,frames);
        frames.clear();

        //Walking up
        for (int i = 4; i < 6; i++)
            frames.add(new TextureRegion(getTexture(), i * 62, 128, 52,52));
        zusgonWalkingUp = new Animation(0.1f,frames);
        frames.clear();

        //walkig left
        for (int i = 7; i < 9; i++)
            frames.add(new TextureRegion(getTexture(), i * 62, 128, 52, 52));
        zusgonWalkingLeft = new Animation(0.1f,frames);
        frames.clear();

        //Walking Right
        for (int i = 10; i < 12; i++)
            frames.add(new TextureRegion(getTexture(), i * 61, 128, 52, 52));
        zusgonWalkingRight = new Animation(0.1f,frames);
        frames.clear();

        zusgonStand = new TextureRegion(getTexture(), 0, 128, 52, 52);
        setBounds(0,128, (52 / 2) / LegendsofZusgon.pixelPerMeter, (52 / 2) / LegendsofZusgon.pixelPerMeter);
        setRegion(zusgonStand);
    }

    public void update(float dt){
        setPosition(body.getPosition().x - getWidth() /2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){

            case WALKINGDOWN:
                region = zusgonWalkingDown.getKeyFrame(stateTimer, true);
                break;
            case WALKINGUP:
                region = zusgonWalkingUp.getKeyFrame(stateTimer,true);
                break;
            case WALKINGRIGHT:
                region = zusgonWalkingRight.getKeyFrame(stateTimer, true);
                break;
            case WALKINGLEFT:
                region = zusgonWalkingLeft.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = zusgonStand;
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if (body.getLinearVelocity().y > 0)
            return State.WALKINGUP;
        else if (body.getLinearVelocity().y < 0)
            return State.WALKINGDOWN;
        else if (body.getLinearVelocity().x > 0)
            return State.WALKINGRIGHT;
        else if (body.getLinearVelocity().x < 0)
            return State.WALKINGLEFT;
        else
            return State.STANDING;
    }

    public void defineZusgon(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(700 / LegendsofZusgon.pixelPerMeter, 400 / LegendsofZusgon.pixelPerMeter);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10 / LegendsofZusgon.pixelPerMeter);

        fixtureDef.filter.categoryBits = LegendsofZusgon.zusgonBit;
        fixtureDef.filter.maskBits = LegendsofZusgon.groundBit |
                LegendsofZusgon.chestsBit |
                LegendsofZusgon.thingsBit |
                LegendsofZusgon.enemyBit |
                LegendsofZusgon.objectBit;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        EdgeShape bodyContactUp = new EdgeShape();
        bodyContactUp.set(new Vector2(-12/ LegendsofZusgon.pixelPerMeter, 0 / LegendsofZusgon.pixelPerMeter),
                new Vector2(12/ LegendsofZusgon.pixelPerMeter, 0 / LegendsofZusgon.pixelPerMeter));
        fixtureDef.shape = bodyContactUp;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData("body");
    }
}
