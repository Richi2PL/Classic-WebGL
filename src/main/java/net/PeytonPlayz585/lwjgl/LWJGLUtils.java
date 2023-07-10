package net.PeytonPlayz585.lwjgl;

import org.lwjgl.opengl.GL11;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.canvas.ImageData;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLImageElement;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Uint8Array;
import org.teavm.jso.typedarrays.Uint8ClampedArray;

import net.PeytonPlayz585.main.MinecraftMain;
import net.PeytonPlayz585.minecraft.MinecraftImage;

public class LWJGLUtils extends GL11 {
	
	public LWJGLUtils() {
		
	}
	
	public static final MinecraftImage loadPNG(byte[] data) {
		ArrayBuffer arr = ArrayBuffer.create(data.length);
		Uint8Array.create(arr).set(data);
		return loadPNG0(arr);
	}

	@Async
	private static native MinecraftImage loadPNG0(ArrayBuffer data);

	private static void loadPNG0(ArrayBuffer data, final AsyncCallback<MinecraftImage> ret) {
		final HTMLImageElement toLoad = (HTMLImageElement) MinecraftMain.doc.createElement("img");
		toLoad.addEventListener("load", new EventListener<Event>() {
			@Override
			public void handleEvent(Event evt) {
				if(MinecraftMain.imageLoadCanvas == null) {
					MinecraftMain.imageLoadCanvas = (HTMLCanvasElement) MinecraftMain.doc.createElement("canvas");
				}
				if(MinecraftMain.imageLoadCanvas.getWidth() < toLoad.getWidth()) {
					MinecraftMain.imageLoadCanvas.setWidth(toLoad.getWidth());
				}
				if(MinecraftMain.imageLoadCanvas.getHeight() < toLoad.getHeight()) {
					MinecraftMain.imageLoadCanvas.setHeight(toLoad.getHeight());
				}
				if(MinecraftMain.imageLoadContext == null) {
					MinecraftMain.imageLoadContext = (CanvasRenderingContext2D) MinecraftMain.imageLoadCanvas.getContext("2d");
				}
				MinecraftMain.imageLoadContext.clearRect(0, 0, toLoad.getWidth(), toLoad.getHeight());
				MinecraftMain.imageLoadContext.drawImage(toLoad, 0, 0, toLoad.getWidth(), toLoad.getHeight());
				ImageData pxlsDat = MinecraftMain.imageLoadContext.getImageData(0, 0, toLoad.getWidth(), toLoad.getHeight());
				Uint8ClampedArray pxls = pxlsDat.getData();
				int totalPixels = pxlsDat.getWidth() * pxlsDat.getHeight();
				MinecraftMain.freeDataURL(toLoad.getSrc());
				if(pxls.getByteLength() < totalPixels * 4) {
					ret.complete(null);
					return;
				}
				int[] pixels = new int[totalPixels];
				for(int i = 0; i < pixels.length; ++i) {
					pixels[i] = (pxls.get(i * 4) << 16) | (pxls.get(i * 4 + 1) << 8) | pxls.get(i * 4 + 2) | (pxls.get(i * 4 + 3) << 24);
				}
				ret.complete(new MinecraftImage(pixels, pxlsDat.getWidth(), pxlsDat.getHeight(), true));
			}
		});
		toLoad.addEventListener("error", new EventListener<Event>() {
			@Override
			public void handleEvent(Event evt) {
				MinecraftMain.freeDataURL(toLoad.getSrc());
				ret.complete(null);
			}
		});
		String src = MinecraftMain.getDataURL(data, "image/png");
		if(src == null) {
			ret.complete(null);
		}else {
			toLoad.setSrc(src);
		}
	}
}
