package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;

public class GuiMods extends GuiScreen {
	GuiScreen parent = null;
	String title = "Optifine";
	
	public GuiMods(GuiScreen parent) {
		this.parent = parent;
	}
	
	public final void onOpen() {
		this.buttons.add(new OptionButton(1, this.width / 2 - 155 + 0 % 2 * 160, this.height / 6 + 24 * (0 >> 1), minecraft.settings.getSetting(11)));
		this.buttons.add(new OptionButton(2, this.width / 2 - 155 + 1 % 2 * 160, this.height / 6 + 24 * (1 >> 1), minecraft.settings.getSetting(12)));
		this.buttons.add(new Button(200, this.width / 2 - 100, this.height / 6 + 168 + 12, "Done"));
	}
	
	public final void onButtonClick(Button var1) {
		if(var1.id == 1) {
			minecraft.settings.toggleSetting(11, 0);
			var1.text = minecraft.settings.getSetting(11);
		}
		
		if(var1.id == 2) {
			minecraft.settings.toggleSetting(12, 0);
			var1.text = minecraft.settings.getSetting(12);
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
