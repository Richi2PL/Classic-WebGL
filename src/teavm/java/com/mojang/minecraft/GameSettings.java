package com.mojang.minecraft;

import com.mojang.minecraft.gamemode.*;

import net.PeytonPlayz585.storage.LocalStorageManager;
import net.PeytonPlayz595.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;

public final class GameSettings
{
	public GameSettings(Minecraft minecraft)
	{
		bindings = new KeyBinding[] {forwardKey, leftKey, backKey, rightKey, jumpKey, buildKey, chatKey, toggleFogKey, saveLocationKey, loadLocationKey};

		settingCount = 11;

		this.minecraft = minecraft;
		
		Minecraft.minecraft = minecraft;

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
	public boolean gamemode = false;
	public boolean fullscreen = false;
	public boolean mobSpawns = false;
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
	public int settingCount;

	public String getBinding(int key)
	{
		return bindings[key].name + ": " + GL11.getKeyName(bindings[key].key);
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
		
		if(setting == 8) {
			gamemode = !gamemode;
			
			if(gamemode) {
				GameMode gamemode = new CreativeGameMode(minecraft);
				gamemode.apply(minecraft.level);
				gamemode.apply(minecraft.player);
				minecraft.gamemode = gamemode;
			} else {
				GameMode gamemode = new SurvivalGameMode(minecraft);
				gamemode.apply(minecraft.level);
				gamemode.apply(minecraft.player);
				minecraft.gamemode = gamemode;
			}
		}
		
		if(setting == 9) {
			fullscreen = !fullscreen;
			GL11.setFullscreen(fullscreen);
		}
		
		if(setting == 10) {
			mobSpawns = !mobSpawns;
			minecraft.gamemode.spawnMob();
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
				: (id == 8 ? "Game Mode: " + (gamemode ? "Creative" : "Survival")
				: (id == 9 ? "Fullscreen: " + (fullscreen ? "ON" : "OFF")
				: (id == 10 ? "Mob Spawning: " + (mobSpawns ? "ON" : "OFF")
				: ""))))))))));
	}

	private void load()
	{
		NBTTagCompound settingsFile = LocalStorageManager.gameSettingsStorage;
		
		if(!settingsFile.hasNoTags()) {
			if(settingsFile.hasKey("music")) {
				music = settingsFile.getBoolean("music");
			}
			
			if(settingsFile.hasKey("sound")) {
				sound = settingsFile.getBoolean("sound");
			}
			
			if(settingsFile.hasKey("invertYMouse")) {
				invertMouse = settingsFile.getBoolean("invertYMouse");
			}
			
			if(settingsFile.hasKey("showFrameRate")) {
				showFrameRate = settingsFile.getBoolean("showFrameRate");
			}
			
			if(settingsFile.hasKey("viewDistance")) {
				viewDistance = settingsFile.getInteger("viewDistance");
			}
			
			if(settingsFile.hasKey("bobView")) {
				viewBobbing = settingsFile.getBoolean("bobView");
			}
			
			if(settingsFile.hasKey("anaglyph3d")) {
				anaglyph = settingsFile.getBoolean("anaglyph3d");
			}
			
			if(settingsFile.hasKey("limitFramerate")) {
				limitFramerate = settingsFile.getBoolean("limitFramerate");
			}
			
			if(settingsFile.hasKey("mobSpawns")) {
				mobSpawns = settingsFile.getBoolean("mobSpawns");
			}
			
			for(int i = 0; i < bindings.length; ++i) {
				String k = "key_" + bindings[i].name;
				if(settingsFile.hasKey(k)) bindings[i].key = (int)settingsFile.getShort(k) & 0xFFFF;
			}
		}
	}

	private void save()
	{
		NBTTagCompound settingsFile = LocalStorageManager.gameSettingsStorage;
		settingsFile.setBoolean("music", music);
		settingsFile.setBoolean("sound", sound);
		settingsFile.setBoolean("invertYMouse", invertMouse);
		settingsFile.setBoolean("showFrameRate", showFrameRate);
		settingsFile.setInteger("viewDistance", viewDistance);
		settingsFile.setBoolean("bobView", viewBobbing);
		settingsFile.setBoolean("anaglyph3d", anaglyph);
		settingsFile.setBoolean("limitFramerate", limitFramerate);
		settingsFile.setBoolean("mobSpawns", mobSpawns);
		
		for(int i = 0; i < bindings.length; ++i) {
			String k = "key_" + bindings[i].name;
			settingsFile.setShort(k, (short)bindings[i].key);
		}
		LocalStorageManager.saveStorageG();
	}

}
