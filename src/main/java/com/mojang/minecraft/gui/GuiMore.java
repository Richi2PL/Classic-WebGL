package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;

public class GuiMore extends GuiScreen {
	GuiScreen parent = null;
	String title;
	
	public GuiMore(GuiScreen parent) {
		this.parent = parent;
		this.title = "More Options..."; 
	}
	
	public final void onOpen() {
		this.buttons.add(new Button(50, this.width / 2 - 100, this.height / 6 + 0 + 12, "Mods"));
	    this.buttons.add(new Button(100, this.width / 2 - 100, this.height / 6 + 25 + 12, "Optifine"));
	    this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168 + 12, "Done"));
	}
	
	public final void onButtonClick(Button var1) {
		
		if(var1.id == 50) {
			minecraft.setCurrentScreen(new GuiMods(this));
		}
		
		if(var1.id == 100) {
			minecraft.setCurrentScreen(new GuiOptifine(this));
		}
		
		if(var1.id == 200) {
			minecraft.setCurrentScreen(parent);
		}
	}
	
	public final void render(int var1, int var2) {
	    drawFadingBox(0, 0, this.width, this.height, 1610941696, -1607454624);
	    drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
		super.render(var1, var2);
	}
}
