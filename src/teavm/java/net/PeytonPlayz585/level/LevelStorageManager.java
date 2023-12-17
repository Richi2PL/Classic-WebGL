package net.PeytonPlayz585.level;

import java.io.*;

import org.lwjgl.opengl.GL11;

import net.PeytonPlayz595.nbt.NBTBase;
import net.PeytonPlayz595.nbt.NBTTagCompound;

public class LevelStorageManager {
	
	public static NBTTagCompound levelStorage = null;
	
	public static void loadLevelData() throws IOException {
		byte[] levelData = GL11.readFile("/saves/level1.dat");
		
		if(levelData != null) {
			NBTBase nbtBase = NBTBase.readTag(new DataInputStream(new ByteArrayInputStream(levelData)));
			if(nbtBase != null && nbtBase instanceof NBTTagCompound) {
				levelStorage = (NBTTagCompound)nbtBase;
			}
		} else {
			return;
		}
		
		if(levelStorage.tagMap == null) {
			levelStorage = null;
		} else if(levelStorage.tagMap.size() == 0) {
			levelStorage = null;
		}
	}
	
	public static void saveLevelData() throws IOException {
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		NBTBase.writeTag(levelStorage, new DataOutputStream(s));
		GL11.writeFile("/saves/level1.dat", s.toByteArray());
		if(levelStorage.tagMap == null) {
			levelStorage = null;
		} else if(levelStorage.tagMap.size() == 0) {
			levelStorage = null;
		}
	}

}
