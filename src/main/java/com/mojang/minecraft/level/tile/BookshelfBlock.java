package com.mojang.minecraft.level.tile;

import java.util.Random;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.tile.Block;

public final class BookshelfBlock extends Block {

   public BookshelfBlock(int var1, int var2) {
      super(47, 35);
   }

   protected final int getTextureId(int texture) {
      return texture <= 1?4:this.textureId;
   }
   
   public final int getDrop() {
	   if(Minecraft.settings.randomDrops) {
		   return new Random().nextInt(49 - 1 + 1) + 1;
	   }
	   return Block.BOOKSHELF.id;
   }

   public final int getDropCount() {
      return 0;
   }
}
