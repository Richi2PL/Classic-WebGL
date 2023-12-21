package com.mojang.minecraft.gui;

import org.lwjgl.opengl.GL11;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.SessionData;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.render.TextureLocation;

import net.PeytonPlayz585.level.LevelUtils;
import net.lax1dude.eaglercraft.adapter.Tessellator;

public class MultiplayerMenu extends GuiScreen {
	
	boolean textBox1Active = false;
	boolean textBox2Active = false;
	private int counter = 0;
	String server = "";
	String username = "";
	Button connect;
	
	public final void onOpen() {
		GL11.enableRepeatEvents(true);
        this.buttons.clear();
		this.buttons.add(connect = new Button(0, this.width / 2 - 100, this.height / 4 + 96 + 12, "Connect"));
		this.buttons.add(new Button(1, this.width / 2 - 100, this.height / 4 + 120 + 12, "Cancel"));
		connect.active = false;
	}
	
	public final void tick() {
		++this.counter;
	}
	
	protected final void onButtonClick(Button var1) {
		if(var1.id == 0 && var1.active) {
			GL11.enableRepeatEvents(false);
			minecraft.session = new SessionData(this.username, "mcpass");
			Level var85;
			(var85 = new Level()).setData(8, 8, 8, new byte[512]);
			minecraft.setLevel(var85, false);
			minecraft.networkManager = new NetworkManager(minecraft, this.server, minecraft.session.username, minecraft.session.mppass);
		} else if(var1.id == 1) {
			GL11.enableRepeatEvents(false);
			minecraft.setCurrentScreen(new MainMenu());
		}
	}
	
	protected void onMouseClick(int var1, int var2, int var3) {
		if(var3 == 0) {
			if(var1 >= this.width / 2 - 100 && var1 < (this.width / 2 - 100) + 200 && var2 >= this.height / 4 - 10 + 50 + 18 && var2 < (this.height / 4 - 10 + 50 + 18) + 20) {
				GL11.enableRepeatEvents(true);
				textBox1Active = true;
				textBox2Active = false;
			} else if(var1 >= this.width / 2 - 100 && var1 < (this.width / 2 - 100) + 200 && var2 >= this.height / 4 - 10 + 50 - 20 && var2 < (this.height / 4 - 10 + 50 - 20) + 20) {
				GL11.enableRepeatEvents(true);
				textBox2Active = true;
				textBox1Active = false;
			} else {
				GL11.enableRepeatEvents(false);
				textBox1Active = false;
				textBox2Active = false;
			}
		}
		super.onMouseClick(var1, var2, var3);
	}
	
	protected final void onKeyPress(char var1, int var2) {
		if(textBox1Active) {
			if(var2 == 14 && this.server.length() > 0) {
				this.server = this.server.substring(0, this.server.length() - 1);
			}
			if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_\'*!\\\"#%/()=+?[]{}<>@|$;".indexOf(var1) >= 0 && this.server.length() < 64) {
				this.server = this.server + var1;
			}
			if(server.length() > 0 && username.length() > 0) {
				connect.active = true;
			} else {
				connect.active = false;
			}
		} else if(textBox2Active) {
			if(var2 == 14 && this.username.length() > 0) {
				this.username = this.username.substring(0, this.username.length() - 1);
			}
			if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_\'*!\\\"#%/()=+?[]{}<>@|$;".indexOf(var1) >= 0 && this.username.length() < 64) {
				this.username = this.username + var1;
			}
			if(server.length() > 0 && username.length() > 0) {
				connect.active = true;
			} else {
				connect.active = false;
			}
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
		
		//username
		drawBox((this.width / 2 - 100) - 1, (this.height / 4 - 10 + 50 - 20) - 1, (this.width / 2 - 100) + 200 + 1, (this.height / 4 - 10 + 50 - 20) + 20 + 1, -6250336);
		drawBox(this.width / 2 - 100, this.height / 4 - 10 + 50 - 20, (this.width / 2 - 100) + 200, (this.height / 4 - 10 + 50 - 20) + 20, -16777216);
		
		this.drawString(this.fontRenderer, "Username:", this.width / 2 - 100, this.height / 4 - 10 + 50 - 30, 10526880);
		
		//server IP
		drawBox((this.width / 2 - 100) - 1, (this.height / 4 - 10 + 50 + 18) - 1, (this.width / 2 - 100) + 200 + 1, (this.height / 4 - 10 + 50 + 18) + 20 + 1, -6250336);
		drawBox(this.width / 2 - 100, this.height / 4 - 10 + 50 + 18, (this.width / 2 - 100) + 200, (this.height / 4 - 10 + 50 + 18) + 20, -16777216);
		
		this.drawString(this.fontRenderer, "Server address:", this.width / 2 - 100, this.height / 4 - 10 + 50 + 8, 10526880);
		if(textBox1Active) {
			boolean e = this.counter / 6 % 2 == 0;
			this.drawString(this.fontRenderer, server + (e ? "_" : ""), (this.width / 2 - 100) + 4, (this.height / 4 - 10 + 50 + 18) + (20 - 8) / 2, 14737632);
		} else {
			this.drawString(this.fontRenderer, server, (this.width / 2 - 100) + 4, (this.height / 4 - 10 + 50 + 18) + (20 - 8) / 2, 14737632);
		}
		if(textBox2Active) {
			boolean e = this.counter / 6 % 2 == 0;
			this.drawString(this.fontRenderer, username + (e ? "_" : ""), (this.width / 2 - 100) + 4, (this.height / 4 - 10 + 50 - 20) + (20 - 8) / 2, 14737632);
		} else {
			this.drawString(this.fontRenderer, username, (this.width / 2 - 100) + 4, (this.height / 4 - 10 + 50 - 20) + (20 - 8) / 2, 14737632);
		}
		super.render(var1, var2);
	}

}
