package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.FontRenderer;

import net.lax1dude.eaglercraft.adapter.Tessellator;

import org.lwjgl.opengl.GL11;

public class Screen {

   protected float imgZ = 0.0F;


   protected static void drawBox(int var0, int var1, int var2, int var3, int var4) {
      float var5 = (float)(var4 >>> 24) / 255.0F;
      float var6 = (float)(var4 >> 16 & 255) / 255.0F;
      float var7 = (float)(var4 >> 8 & 255) / 255.0F;
      float var9 = (float)(var4 & 255) / 255.0F;
      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(var6, var7, var9, var5);
      tessellator.startDrawing(7);
      tessellator.addVertex((float)var0, (float)var3, 0.0F);
      tessellator.addVertex((float)var2, (float)var3, 0.0F);
      tessellator.addVertex((float)var2, (float)var1, 0.0F);
      tessellator.addVertex((float)var0, (float)var1, 0.0F);
      tessellator.draw();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   protected static void drawFadingBox(int i, int j, int k, int l, int i1, int j1) {
	    float f = (float) (i1 >> 24 & 0xff) / 255F;
		float f1 = (float) (i1 >> 16 & 0xff) / 255F;
		float f2 = (float) (i1 >> 8 & 0xff) / 255F;
		float f3 = (float) (i1 & 0xff) / 255F;
		float f4 = (float) (j1 >> 24 & 0xff) / 255F;
		float f5 = (float) (j1 >> 16 & 0xff) / 255F;
		float f6 = (float) (j1 >> 8 & 0xff) / 255F;
		float f7 = (float) (j1 & 0xff) / 255F;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3008 /* GL_ALPHA_TEST */);
		GL11.glBlendFunc(770, 771);
		GL11.glShadeModel(7425 /* GL_SMOOTH */);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(7);
		tessellator.setColorRGBA_F(f1, f2, f3, f);
		tessellator.addVertex(k, j, 0.0D);
		tessellator.addVertex(i, j, 0.0D);
		tessellator.setColorRGBA_F(f5, f6, f7, f4);
		tessellator.addVertex(i, l, 0.0D);
		tessellator.addVertex(k, l, 0.0D);
		tessellator.draw();
		GL11.glShadeModel(7424 /* GL_FLAT */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glEnable(3008 /* GL_ALPHA_TEST */);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);

   }

   public static void drawCenteredString(FontRenderer var0, String var1, int var2, int var3, int var4) {
      var0.drawString(var1, var2 - var0.getStringWidth(var1) / 2, var3, var4);
   }

   public static void drawString(FontRenderer var0, String var1, int var2, int var3, int var4) {
      var0.drawString(var1, var2, var3, var4);
   }

   public final void drawImage(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawing(7);
      tessellator.addVertexWithUV((float)var1, (float)(var2 + var6), this.imgZ, (float)var3 * var7, (float)(var4 + var6) * var8);
      tessellator.addVertexWithUV((float)(var1 + var5), (float)(var2 + var6), this.imgZ, (float)(var3 + var5) * var7, (float)(var4 + var6) * var8);
      tessellator.addVertexWithUV((float)(var1 + var5), (float)var2, this.imgZ, (float)(var3 + var5) * var7, (float)var4 * var8);
      tessellator.addVertexWithUV((float)var1, (float)var2, this.imgZ, (float)var3 * var7, (float)var4 * var8);
      tessellator.draw();
   }
}
