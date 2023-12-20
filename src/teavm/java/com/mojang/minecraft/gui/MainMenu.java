package com.mojang.minecraft.gui;

import org.lwjgl.opengl.GL11;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.render.TextureLocation;

import net.PeytonPlayz585.level.LevelUtils;
import net.lax1dude.eaglercraft.adapter.Tessellator;

public class MainMenu extends GuiScreen {
	
	public final void onOpen() {
        this.buttons.clear();
		this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 48, "Singleplayer"));
		this.buttons.add(new Button(2, this.width / 2 - 100, this.height / 4 + 72, "Multiplayer"));
	}
	
	protected final void onButtonClick(Button var1) {
		if(var1.id == 1) {
			Level level1 = new LevelUtils().load();
       	 	if(level1 == null) {
       	 		Minecraft.getMinecraft().setCurrentScreen(null);
       	 		this.minecraft.generateLevel(1);
       	 		this.minecraft.player.releaseAllKeys();
       	 	} else {
       	 		Minecraft.getMinecraft().setCurrentScreen(null);
       	 		this.minecraft.setLevel(level1, true);
       	 	    this.minecraft.player.releaseAllKeys();
       	 	}
		} else if(var1.id == 2) {
			this.minecraft.setCurrentScreen(new MultiplayerMenu());
		}
	}
	
	public final void render(int var1, int var2) {
		int var4 = this.minecraft.width * 240 / this.minecraft.height;
		int var5 = this.minecraft.height * 240 / this.minecraft.height;
		GL11.glClear(16640);
		Tessellator tessellator = Tessellator.instance;
		int var7 = new TextureLocation("/dirt.png").bindTexture();
		float var10 = 32.0F;
		tessellator.startDrawing(7);
		tessellator.setColorOpaque_I(4210752);
		tessellator.addVertexWithUV(0.0F, (float)var5, 0.0F, 0.0F, (float)var5 / var10);
		tessellator.addVertexWithUV((float)var4, (float)var5, 0.0F, (float)var4 / var10, (float)var5 / var10);
		tessellator.addVertexWithUV((float)var4, 0.0F, 0.0F, (float)var4 / var10, 0.0F);
		tessellator.addVertexWithUV(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		tessellator.draw();
		super.render(var1, var2);
	}

}
