package com.mojang.minecraft;

import com.mojang.minecraft.render.TextureManager;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.imageio.ImageIO;
import org.lwjgl.input.Keyboard;

import com.mojang.minecraft.gamemode.*;
import com.mojang.minecraft.player.Inventory;

public final class GameSettings
{
	public GameSettings(Minecraft minecraft, File minecraftFolder)
	{
		bindings = new KeyBinding[] {forwardKey, leftKey, backKey, rightKey, jumpKey, buildKey, chatKey, toggleFogKey, saveLocationKey, loadLocationKey};

		settingCount = 8;

		this.minecraft = minecraft;

		settingsFile = new File(minecraftFolder, "options.txt");

		load();
	}

	private static final String[] renderDistances = new String[]{"FAR", "NORMAL", "SHORT", "TINY"};
	public boolean music = true;
	public boolean sound = true;
	public boolean invertMouse = false;
	public boolean showFrameRate = false;
	public int viewDistance = 0;
	public boolean viewBobbing = true;
	public boolean anaglyph = false;
	public boolean limitFramerate = false;
	public boolean ofBetterGrass = false;
	public boolean ofFastMath = true;
	public boolean ofSmoothFPS = false;
	public boolean gamemode = false;
	public boolean randomDrops = false;
	public KeyBinding forwardKey = new KeyBinding("Forward", 17);
	public KeyBinding leftKey = new KeyBinding("Left", 30);
	public KeyBinding backKey = new KeyBinding("Back", 31);
	public KeyBinding rightKey = new KeyBinding("Right", 32);
	public KeyBinding jumpKey = new KeyBinding("Jump", 57);
	public KeyBinding buildKey = new KeyBinding("Build", 48);
	public KeyBinding chatKey = new KeyBinding("Chat", 20);
	public KeyBinding toggleFogKey = new KeyBinding("Toggle fog", 33);
	public KeyBinding saveLocationKey = new KeyBinding("Save location", 28);
	public KeyBinding loadLocationKey = new KeyBinding("Load location", 19);
	public KeyBinding[] bindings;
	private Minecraft minecraft;
	private File settingsFile;
	public int settingCount;

	public String getBinding(int key)
	{
		return bindings[key].name + ": " + Keyboard.getKeyName(bindings[key].key);
	}

	public void setBinding(int key, int keyID)
	{
		bindings[key].key = keyID;

		save();
	}

	public void toggleSetting(int setting, int fogValue)
	{
		if(setting == 0)
		{
			music = !music;
		}

		if(setting == 1)
		{
			sound = !sound;
		}

		if(setting == 2)
		{
			invertMouse = !invertMouse;
		}

		if(setting == 3)
		{
			showFrameRate = !showFrameRate;
		}

		if(setting == 4)
		{
			viewDistance = viewDistance + fogValue & 3;
		}

		if(setting == 5)
		{
			viewBobbing = !viewBobbing;
		}

		if(setting == 6)
		{
			minecraft.levelRenderer.refresh();
			anaglyph = !anaglyph;
			minecraft.levelRenderer.refresh();
		}

		if(setting == 7)
		{
			limitFramerate = !limitFramerate;
		}

		if(setting == 8)  {
			ofBetterGrass = !ofBetterGrass;
			minecraft.levelRenderer.refresh();
		}
		
		if(setting == 9) {
			ofFastMath = !ofFastMath;
			minecraft.levelRenderer.refresh();
		}
		
		if(setting == 11) {
			if(gamemode) {
				GameMode game = new SurvivalGameMode(minecraft);
				game.apply(minecraft.level);
				game.apply(minecraft.player);
				minecraft.gamemode = game;
			} else {
				GameMode game = new CreativeGameMode(minecraft);
				game.apply(minecraft.level);
				game.apply(minecraft.player);
				minecraft.gamemode = game;
			}
			gamemode = !gamemode;
		}
		
		if(setting == 12) {
			randomDrops = !randomDrops;
		}

		save();
	}

	public String getSetting(int id)
	{
		return id == 0 ? "Music: " + (music ? "ON" : "OFF")
				: (id == 1 ? "Sound: " + (sound ? "ON" : "OFF")
				: (id == 2 ? "Invert mouse: " + (invertMouse ? "ON" : "OFF")
				: (id == 3 ? "Show FPS: " + (showFrameRate ? "ON" : "OFF")
				: (id == 4 ? "Render distance: " + renderDistances[viewDistance]
				: (id == 5 ? "View bobbing: " + (viewBobbing ? "ON" : "OFF")
				: (id == 6 ? "3d anaglyph: " + (anaglyph ? "ON" : "OFF")
				: (id == 7 ? "Limit framerate: " + (limitFramerate ? "ON" : "OFF")
				: (id == 8 ? "Better Grass: " + (ofBetterGrass ? "ON" : "OFF")
				: (id == 9 ? "Fast Math: " + (ofFastMath ? "ON" : "OFF")
				: (id == 10 ? "Smooth FPS: " + (ofSmoothFPS ? "ON" : "OFF")
				: (id == 11 ? "GameMode: " + (gamemode ? "Creative" : "Survival")
				: (id == 12 ? "Random Drops: " + (randomDrops ? "ON": "OFF")
				: ""))))))))))));
	}

	private void load()
	{
		try
		{
			if(settingsFile.exists())
			{
				FileReader fileReader = new FileReader(settingsFile);
				BufferedReader reader = new BufferedReader(fileReader);

				String line = null;

				while((line = reader.readLine()) != null)
				{
					String[] setting = line.split(":");

					if(setting[0].equals("music"))
					{
						music = setting[1].equals("true");
					}

					if(setting[0].equals("sound"))
					{
						sound = setting[1].equals("true");
					}

					if(setting[0].equals("invertYMouse"))
					{
						invertMouse = setting[1].equals("true");
					}

					if(setting[0].equals("showFrameRate"))
					{
						showFrameRate = setting[1].equals("true");
					}

					if(setting[0].equals("viewDistance"))
					{
						viewDistance = Integer.parseInt(setting[1]);
					}

					if(setting[0].equals("bobView"))
					{
						viewBobbing = setting[1].equals("true");
					}

					if(setting[0].equals("anaglyph3d"))
					{
						anaglyph = setting[1].equals("true");
					}

					if(setting[0].equals("limitFramerate"))
					{
						limitFramerate = setting[1].equals("true");
					}

					if(setting[0].equals("ofBetterGrass"))
					{
						ofBetterGrass = setting[1].equals("true");
					}
					
					if(setting[0].equals("ofFastMath"))
					{
						ofFastMath = setting[1].equals("true");
					}
					
					if(setting[0].equals("ofSmoothFPS"))
					{
						ofSmoothFPS = setting[1].equals("true");
					}

					for(int index = 0; index < this.bindings.length; index++)
					{
						if(setting[0].equals("key_" + bindings[index].name))
						{
							bindings[index].key = Integer.parseInt(setting[1]);
						}
					}
				}

				reader.close();
			}
		} catch (Exception e) {
			System.out.println("Failed to load options");

			e.printStackTrace();
		}
	}

	private void save()
	{
		try {
			FileWriter fileWriter = new FileWriter(this.settingsFile);
			PrintWriter writer = new PrintWriter(fileWriter);

			writer.println("music:" + music);
			writer.println("sound:" + sound);
			writer.println("invertYMouse:" + invertMouse);
			writer.println("showFrameRate:" + showFrameRate);
			writer.println("viewDistance:" + viewDistance);
			writer.println("bobView:" + viewBobbing);
			writer.println("anaglyph3d:" + anaglyph);
			writer.println("limitFramerate:" + limitFramerate);
			writer.println("ofBetterGrass:" + ofBetterGrass);
			writer.println("ofFastMath:" + ofFastMath);
			writer.println("ofSmoothFPS:" + ofSmoothFPS);

			for(int binding = 0; binding < bindings.length; binding++)
			{
				writer.println("key_" + bindings[binding].name + ":" + bindings[binding].key);
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("Failed to save options");

			e.printStackTrace();
		}
	}

}
