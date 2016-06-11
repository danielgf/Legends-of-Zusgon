package com.legendsofzusgon.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.legendsofzusgon.game.LegendsofZusgon;

/**
 * Created by Daniel on 5/7/16.
 */
public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    private Integer worldTime;
    private static Integer score;
    private Integer heard;
    private Integer life;
    private float timerCount;
    private  Integer losingLife;

    private Label countDownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label zusgonLabel;
    private Label zusgonHeard;
    private Label heardLabel;
    private Label hrLife;
    private Label lifeLabel;

    public Hud(SpriteBatch batch) {

        worldTime = 300;
        timerCount = 0;
        score = 0;
        heard = 5;
        life = 1000;

        viewport = new StretchViewport(LegendsofZusgon.virtualWidth,LegendsofZusgon.virtualHeigth,new OrthographicCamera());
        stage = new Stage(viewport,batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTime), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel =  new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        zusgonLabel = new Label("ZUSGON", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        zusgonHeard = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        heardLabel = new Label(String.format("%1d", heard),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hrLife = new Label("HR", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeLabel = new Label(String.format("%04d", life), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(zusgonLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(hrLife).expandX().padTop(10);
        table.add(zusgonHeard).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();
        table.add(lifeLabel).expandX();
        table.add(heardLabel).expandX();

        stage.addActor(table);

    }

    public void update(float dt){
        timerCount += dt;
        if (timerCount >= 1){
            worldTime--;
            countDownLabel.setText(String.format("%03d", worldTime));
            timerCount = 0;
        }
    }

    public static void addScore(int value){
        score+=value;
        scoreLabel.setText(String.format("%06d", score));

    }

    public void hitLife(int value){
        life = value;
        if (life > value){
            losingLife = life - value;
            lifeLabel.setText(String.format("%04d", losingLife));
            if (life == 0){
                heard--;
                heardLabel.setText(String.format("%1d", heard));
                life = 1000;
            }
            if (heard == 0){
                Gdx.app.log("Game Over", "You Die");
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
