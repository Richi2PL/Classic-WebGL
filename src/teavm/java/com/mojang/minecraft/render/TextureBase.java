package com.mojang.minecraft.render;

import org.lwjgl.opengl.GL11;

public class TextureBase {

	public TextureBase() {
		//This overrides the default Java constructor insuring non-instability
	}

	public byte[] func_6481_a(String s) {
		return GL11.loadResourceBytes(s);
	}
}