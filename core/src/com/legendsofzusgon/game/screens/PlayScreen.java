package com.legendsofzusgon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.enemies.GoldMan;
import com.legendsofzusgon.game.scenes.Hud;
import com.legendsofzusgon.game.sprites.Player;
import com.legendsofzusgon.game.tools.B2WorldCreator;
import com.legendsofzusgon.game.tools.WorldContactListener;

/**
 * Created by Daniel on 5/7/16.
 */
public class PlayScreen implements Screen{

    private LegendsofZusgon game;
    private TextureAtlas textureAtlas;

    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private boolean line = false;

    private Player player;
    private GoldMan goldMan;

    public PlayScreen(final LegendsofZusgon legendsofZusgon) {

        textureAtlas = new TextureAtlas("ZusgonPack.pack");
        this.game = legendsofZusgon;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(LegendsofZusgon.virtualWidth / LegendsofZusgon.pixelPerMeter, LegendsofZusgon.virtualHeigth / LegendsofZusgon.pixelPerMeter, camera);
        viewport.apply();

        hud = new Hud(legendsofZusgon.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / LegendsofZusgon.pixelPerMeter);
        camera.position.set(viewport.getLeftGutterWidth(),viewport.getBottomGutterHeight(), 0);

        world = new World(new Vector2(0, 0), false);

        box2DDebugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        player = new Player(this);

        world.setContactListener(new WorldContactListener());

        goldMan = new GoldMan(this,32 / LegendsofZusgon.pixelPerMeter,32 /LegendsofZusgon.pixelPerMeter);

    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.body.getLinearVelocity().x <= 0)
            player.body.applyLinearImpulse(new Vector2(0, 0.1f), player.body.getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().y <= 0)
            player.body.applyLinearImpulse(new Vector2(0.1f,0), player.body.getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().y >= 0)
            player.body.applyLinearImpulse(new Vector2(-0.1f,0), player.body.getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.body.getLinearVelocity().x >= 0)
            player.body.applyLinearImpulse(new Vector2(0,-0.1f), player.body.getWorldCenter(), true);
    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);

        //takes 1 step in the physics simulation(60 times per second)
        world.step(1/60f, 6, 2);

        player.update(dt);
        goldMan.update(dt);
        hud.update(dt);

        //attack our game cam to our marioPlayer.x coordinates
        camera.position.x = player.body.getPosition().x;
        camera.position.y = player.body.getPosition().y;

        //update our game camera with correct coordinates after changes
        camera.update();

        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        if (line) {
            //renderer our Box2DDebugLines
            box2DDebugRenderer.render(world, camera.combined);
        }
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        goldMan.draw(game.batch);
        game.batch.end();
        camera.update();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        hud.dispose();
    }
}
