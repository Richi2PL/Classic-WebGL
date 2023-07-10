package com.mojang.util;

import com.mojang.minecraft.Minecraft;

public final class MathHelper {

   private static float[] SIN_TABLE = new float[65536];
   private static float[] SIN_TABLE_FAST = new float[4096];

   public static final float sin(float var0) {
	   if(Minecraft.settings.ofFastMath) {
		   return SIN_TABLE_FAST[(int)(var0 * 651.8986F) & 4095];
	   } else {
		   return SIN_TABLE[(int)(var0 * 10430.378F) & '\uffff'];
	   }
   }

   public static final float cos(float var0) {
	   if(Minecraft.settings.ofFastMath) {
		   return SIN_TABLE_FAST[(int)((var0 + ((float)Math.PI / 2F)) * 651.8986F) & 4095]; 
	   } else {
		   return SIN_TABLE[(int)(var0 * 10430.378F + 16384.0F) & '\uffff'];
	   }
   }

   public static final float sqrt(float var0) {
      return (float)Math.sqrt((double)var0);
   }

   static {
	  for(int var0 = 0; var0 < 65536; ++var0) {
	     SIN_TABLE[var0] = (float)Math.sin((double)var0 * 3.141592653589793D * 2.0D / 65536.0D);
	  }
	   
      for (int j = 0; j < 4096; ++j) {
         SIN_TABLE_FAST[j] = (float)Math.sin((double)(((float)j + 0.5F) / 4096.0F * ((float)Math.PI * 2F)));
      }

      for (int l = 0; l < 360; l += 90) {
         SIN_TABLE_FAST[(int)((float)l * 11.377778F) & 4095] = (float)Math.sin((double)((float)l * 0.017453292F));
      } 
   }
}
