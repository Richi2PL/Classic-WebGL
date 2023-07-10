package com.mojang.minecraft.level.tile;

import java.util.Random;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.tile.Block;

public final class StoneBlock extends Block {

   public StoneBlock(int var1, int var2) {
      super(var1, var2);
   }

   public final int getDrop() {
	   if(Minecraft.settings.randomDrops) {
		   return new Random().nextInt(49 - 1 + 1) + 1;
	   }
	   return Block.COBBLESTONE.id;
   }
}
