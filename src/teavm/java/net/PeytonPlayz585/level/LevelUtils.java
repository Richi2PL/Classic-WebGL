package net.PeytonPlayz585.level;

import java.io.IOException;
import java.util.Iterator;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.player.Inventory;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.Entity;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;

import net.PeytonPlayz595.nbt.*;

public class LevelUtils {
	
	public final Player loadPlayer(Level level) {
		NBTTagCompound var13 = LevelStorageManager.levelStorage;
		NBTTagCompound var2 = var13.getCompoundTag("Player");
		Player player = new Player(level);
		
		player.x = (float)var2.getInteger("player-x");
		player.y = (float)var2.getInteger("player-y");
		player.z = (float)var2.getInteger("player-z");
		
		player.xRot = var2.getFloat("player-xRot");
		player.yRot = var2.getFloat("player-yRot");
		
		player.health = var2.getInteger("player-health");
		player.airSupply = var2.getInteger("player-air");
		
		player.inventory = new Inventory();
		for(int i = 0; i < 9; i++) {
			player.inventory.slots[i] = var2.getInteger("player-inv-" + i);
			player.inventory.count[i] = var2.getInteger("player-inv-" + i + "-size");
		}
		return player;
	}
	
	public final Level load() {
		NBTTagCompound var13 = LevelStorageManager.levelStorage;
		NBTTagCompound var2 = var13.getCompoundTag("About");
		NBTTagCompound var3 = var13.getCompoundTag("Map");
		NBTTagCompound var4 = var13.getCompoundTag("Environment");
		Level level = new Level();
		level.creator = "Player";
		level.name = "Level";
		level.createTime = var2.getLong("CreatedOn");
		
		level.width = var3.getShort("Width");
		level.height = var3.getShort("Height");
		level.depth = var3.getShort("Depth");
		level.blocks = var3.getByteArray("Blocks");
		level.creativeMode = var3.getBoolean("CreativeMode");
		
		level.cloudColor = var4.getInteger("CloudColor");
		level.skyColor = var4.getInteger("SkyColor");
		level.fogColor = var4.getInteger("FogColor");
		level.waterLevel = var4.getInteger("SurroundingWaterHeight");
		
		return level;
	}
	
	public void save() {
		Level var1 = Minecraft.getMinecraft().level;
		NBTTagCompound var3 = new NBTTagCompound();
		
		var3.setInteger("CloudColor", var1.cloudColor);
		var3.setInteger("SkyColor", var1.skyColor);
		var3.setInteger("FogColor", var1.fogColor);
		var3.setShort("SurroundingGroundHeight", (short)var1.getGroundLevel());
		var3.setShort("SurroundingWaterHeight", (short)var1.waterLevel);
		var3.setByte("SurroundingGroundType", (byte)Block.GRASS.id);
		var3.setByte("SurroundingWaterType", (byte)Block.WATER.id);
		
		NBTTagCompound var4 = new NBTTagCompound();
		var4.setShort("Width", (short)var1.width);
		var4.setShort("Height", (short)var1.height);
		var4.setShort("Depth", (short)var1.depth);
		var4.setByteArray("Blocks", var1.blocks);
		var4.setBoolean("CreativeMode", var1.creativeMode);
		
		NBTTagList var5 = new NBTTagList();
		var5.setTag(new NBTTagShort((short)var1.xSpawn));
		var5.setTag(new NBTTagShort((short)var1.ySpawn));
		var5.setTag(new NBTTagShort((short)var1.zSpawn));
		var4.setTag("Spawn", var5);
		
		NBTTagCompound var15 = new NBTTagCompound();
		var15.setString("Author", "Player");
		var15.setString("Name", "Level");
		var15.setLong("CreatedOn", var1.createTime);
		
		NBTTagCompound var6 = new NBTTagCompound();
		Mob player = Minecraft.getMinecraft().player;
		var6.setInteger("player-x", (int)player.x);
		var6.setInteger("player-y", (int)player.y);
		var6.setInteger("player-z", (int)player.z);
		
		var6.setFloat("player-xRot", player.xRot);
		var6.setFloat("player-yRot", player.yRot);
		
		var6.setInteger("player-health", player.health);
		var6.setInteger("player-air", player.airSupply);
		for(int i = 0; i < 9; i++) {
			Player player1 = (Player)player;
			var6.setInteger("player-inv-" + i, player1.inventory.slots[i]);
			var6.setInteger("player-inv-" + i + "-size", player1.inventory.count[i]);
		}
		
		NBTTagCompound var18 = new NBTTagCompound();
		var18.setKey("MinecraftLevel");
		var18.setCompoundTag("About", var15);
		var18.setCompoundTag("Map", var4);
		var18.setCompoundTag("Environment", var3);
		var18.setTag("Player", var6);
		
		LevelStorageManager.levelStorage = var18;
		try {
			LevelStorageManager.saveLevelData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
