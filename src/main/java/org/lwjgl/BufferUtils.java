package org.lwjgl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.PeytonPlayz585.minecraft.GLAllocation;

public class BufferUtils {
	
	public static ByteBuffer createByteBuffer(int parInt) {
		return GLAllocation.createDirectByteBuffer(parInt);
	}
	
	public static IntBuffer createIntBuffer(int parInt) {
		return GLAllocation.createDirectIntBuffer(parInt);
	}
	
	public static FloatBuffer createFloatBuffer(int parInt) {
		return GLAllocation.createDirectFloatBuffer(parInt);
	}

}
