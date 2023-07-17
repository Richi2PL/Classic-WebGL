package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.FontRenderer;
import com.mojang.minecraft.gui.Screen;
import com.mojang.minecraft.render.TextureLocation;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class GuiScreen extends Screen {

   protected Minecraft minecraft;
   protected int width;
   protected int height;
   protected List buttons = new ArrayList();
   public boolean grabsMouse = false;
   protected FontRenderer fontRenderer;


   public void render(int var1, int var2) {
      for(int var3 = 0; var3 < this.buttons.size(); ++var3) {
         Button var10000 = (Button)this.buttons.get(var3);
         Minecraft var7 = this.minecraft;
         Button var4 = var10000;
         if(var10000.visible) {
            FontRenderer var8 = var7.fontRenderer;
            new TextureLocation("/gui/gui.png").bindTexture();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            byte var9 = 1;
            boolean var6 = var1 >= var4.x && var2 >= var4.y && var1 < var4.x + var4.width && var2 < var4.y + var4.height;
            if(!var4.active) {
               var9 = 0;
            } else if(var6) {
               var9 = 2;
            }

            var4.drawImage(var4.x, var4.y, 0, 46 + var9 * 20, var4.width / 2, var4.height);
            var4.drawImage(var4.x + var4.width / 2, var4.y, 200 - var4.width / 2, 46 + var9 * 20, var4.width / 2, var4.height);
            if(!var4.active) {
               Button.drawCenteredString(var8, var4.text, var4.x + var4.width / 2, var4.y + (var4.height - 8) / 2, -6250336);
            } else if(var6) {
               Button.drawCenteredString(var8, var4.text, var4.x + var4.width / 2, var4.y + (var4.height - 8) / 2, 16777120);
            } else {
               Button.drawCenteredString(var8, var4.text, var4.x + var4.width / 2, var4.y + (var4.height - 8) / 2, 14737632);
            }
         }
      }

   }

   protected void onKeyPress(char var1, int var2) {
      if(var2 == 1) {
         this.minecraft.setCurrentScreen((GuiScreen)null);
         this.minecraft.grabMouse();
      }

   }

   protected void onMouseClick(int var1, int var2, int var3) {
      if(var3 == 0) {
         for(var3 = 0; var3 < this.buttons.size(); ++var3) {
            Button var4;
            Button var7;
            if((var7 = var4 = (Button)this.buttons.get(var3)).active && var1 >= var7.x && var2 >= var7.y && var1 < var7.x + var7.width && var2 < var7.y + var7.height) {
               this.onButtonClick(var4);
            }
         }
      }

   }

   protected void onButtonClick(Button var1) {}
   
   public void setWorldAndResolution(Minecraft minecraft, int i, int j, GuiScreen gui) {
	   if(gui != null) {
		   gui.minecraft = minecraft;
		   gui.fontRenderer = minecraft.fontRenderer;
		   gui.width = i;
		   gui.height = j;
		   gui.buttons.clear();
		   gui.minecraft.setCurrentScreen(gui);
	   }
	   
	   for(int var1 = 0; var1 < 5; var1++) {
		   minecraft.width = GL11.parent.getClientWidth();
     	   minecraft.height = GL11.parent.getClientHeight();
     	   GL11.canvas.setWidth(minecraft.width);
     	   GL11.canvas.setHeight(minecraft.height);
		   minecraft.hud = null;
		   HUDScreen hud = new HUDScreen(minecraft, minecraft.width, minecraft.height);
		   hud.render(minecraft.timer.delta, minecraft.currentScreen != null, GL11.mouseGetX() * minecraft.width * 240 / minecraft.height / minecraft.width, minecraft.height * 240 / minecraft.height - GL11.mouseGetY() * minecraft.height * 240 / minecraft.height / minecraft.height - 1);
		   minecraft.hud = hud;
	   }
	   GL11.updateDisplay();
   }

   public final void open(Minecraft var1, int var2, int var3) {
      this.minecraft = var1;
      this.fontRenderer = var1.fontRenderer;
      this.width = var2;
      this.height = var3;
      this.onOpen();
   }

   public void onOpen() {}

   public final void doInput() {
      while(GL11.mouseNext()) {
         this.mouseEvent();
      }

      while(GL11.keysNext()) {
         this.keyboardEvent();
      }

   }

   public final void mouseEvent() {
      if(GL11.mouseGetEventButtonState()) {
         int var1 = GL11.mouseGetEventX() * this.width / this.minecraft.width;
         int var2 = this.height - GL11.mouseGetEventY() * this.height / this.minecraft.height - 1;
         this.onMouseClick(var1, var2, GL11.mouseGetEventButton());
      }

   }

   public final void keyboardEvent() {
      if(GL11.getEventKeyState()) {
         this.onKeyPress(GL11.getEventChar(), GL11.getEventKey());
      }

   }

   public void tick() {}

   public void onClose() {}
}
