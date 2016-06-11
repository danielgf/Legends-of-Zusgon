package com.legendsofzusgon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.legendsofzusgon.game.LegendsofZusgon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = LegendsofZusgon.title;
		new LwjglApplication(new LegendsofZusgon(), config);
	}
}
