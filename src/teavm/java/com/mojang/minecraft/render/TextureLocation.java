package com.mojang.minecraft.render;

import java.util.ArrayList;

import com.mojang.minecraft.Minecraft;

public class TextureLocation {

	private String path;
	private int glObject;

	public TextureLocation(String path) {
		this.path = path;
		this.glObject = -1;
		locations.add(this);
	}

	public static void freeTextures() {
		for (TextureLocation l : locations) {
			l.glObject = -1;
		}
	}

	public int getTexturePointer() {
		RenderEngine r = new RenderEngine();
		if (glObject == -1) {
			glObject = r.getTexture(path);
			if (glObject == -1) {
				System.err.println("could not load: " + path);
			}
		}
		return glObject;
	}
	
	public int bindTexture() {
		Minecraft.getMinecraft().renderer.setLighting(true);
		RenderEngine r = new RenderEngine();
		int i = getTexturePointer();
		if(i != -1) {
			r.bindTexture(i);
		}
		Minecraft.getMinecraft().renderer.setLighting(false);
		return i;
	}

	private static final ArrayList<TextureLocation> locations = new ArrayList();
}