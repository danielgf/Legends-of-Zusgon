package com.legendsofzusgon.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.legendsofzusgon.game.LegendsofZusgon;
import com.legendsofzusgon.game.screens.PlayScreen;

/**
 * Created by Daniel on 5/6/16.
 */
public class MenuState implements Screen {

    private Sprite backGround;
    private OrthographicCamera camera;
    private Viewport viewport;
    private LegendsofZusgon game;

    public MenuState(LegendsofZusgon legendsofZusgon) {
        this.game = legendsofZusgon;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(LegendsofZusgon.virtualWidth, LegendsofZusgon.virtualHeigth, camera);
        viewport.apply();
        backGround = new Sprite(new Texture("BackGround.jpg"));
        backGround.setPosition(0,0);
        backGround.setSize(LegendsofZusgon.virtualWidth,LegendsofZusgon.virtualHeigth);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        backGround.draw(game.batch);
        game.batch.end();

        if (Gdx.input.justTouched()){
            game.setScreen(new PlayScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
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

    }
}
