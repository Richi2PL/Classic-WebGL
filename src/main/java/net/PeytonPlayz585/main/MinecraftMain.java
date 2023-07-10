package net.PeytonPlayz585.main;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.ajax.ReadyStateChangeHandler;
import org.teavm.jso.ajax.XMLHttpRequest;
import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Uint8Array;
import org.teavm.jso.webgl.WebGLRenderingContext;

import net.PeytonPlayz585.minecraft.MinecraftClient;
import net.PeytonPlayz585.teavm.WebGL2RenderingContext;

public class MinecraftMain {
	
	public static Window win = null;
	public static HTMLCanvasElement canvas = null;
	public static HTMLElement rootElement = null;
	public static WebGL2RenderingContext webgl = null;
	public static HTMLElement parent = null;
	public static HTMLDocument doc = null;
	public static CanvasRenderingContext2D canvasContext = null;
	public static HTMLCanvasElement canvasBack = null;
	public static HTMLCanvasElement imageLoadCanvas = null;
	public static CanvasRenderingContext2D imageLoadContext = null;
	private static byte[] loadedPackage = null;
	
	public static int width = 0;
	public static int height = 0;
	
	public static void main(String args[]) {
		String[] element = getClassicConfig();
		initContext(rootElement = Window.current().getDocument().getElementById(element[0]), element[1]);
	}
	
	public final static void initContext(HTMLElement rootElement, String assetsURI) {
		parent = rootElement;
		String s = parent.getAttribute("style");
		parent.setAttribute("style", (s == null ? "" : s)+"overflow-x:hidden;overflow-y:hidden;");
		win = Window.current();
		doc = win.getDocument();
		canvas = (HTMLCanvasElement)doc.createElement("canvas");
		width = rootElement.getClientWidth();
		height = rootElement.getClientHeight();
		canvas.setWidth(width);
		canvas.setHeight(height);
		canvasContext = (CanvasRenderingContext2D) canvas.getContext("2d");
		canvas.setAttribute("id", "minecraftClassicBrowser");
		rootElement.appendChild(canvas);
		canvasBack = (HTMLCanvasElement)doc.createElement("canvas");
		canvasBack.setWidth(width);
		canvasBack.setHeight(height);
		webgl = (WebGL2RenderingContext) canvasBack.getContext("webgl2");
		if(webgl == null) {
			throw new RuntimeException("WebGL 2.0 is not supported in your browser, please get a new one!");
		}
		setCurrentContext(webgl);
		GL11.initWebGL(webgl);
		
		webgl.getExtension("EXT_texture_filter_anisotropic");
		
		downloadAssetPack(assetsURI);
		
		try {
			MinecraftClient.install(loadedPackage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Start Game Function
		
	}
	
	@Async
	public static native String downloadAssetPack(String assetPackageURI);
	
	private static void downloadAssetPack(String assetPackageURI, final AsyncCallback<String> cb) {
		final XMLHttpRequest request = XMLHttpRequest.create();
		request.setResponseType("arraybuffer");
		request.open("GET", assetPackageURI, true);
		request.setOnReadyStateChange(new ReadyStateChangeHandler() {
			@Override
			public void stateChanged() {
				if(request.getReadyState() == XMLHttpRequest.DONE) {
					Uint8Array bl = Uint8Array.create((ArrayBuffer)request.getResponse());
					loadedPackage = new byte[bl.getByteLength()];
					for(int i = 0; i < loadedPackage.length; ++i) {
						loadedPackage[i] = (byte) bl.get(i);
					}
					cb.complete("yee");
				}
			}
		});
		request.send();
	}
	
	public static final int getCanvasWidth() {
		int w = parent.getClientWidth();
		if(w != width) {
			canvas.setWidth(w);
			canvasBack.setWidth(w);
			width = w;
		}
		return w;
	}
	public static final int getCanvasHeight() {
		int h = parent.getClientHeight();
		if(h != height) {
			canvas.setHeight(h);
			canvasBack.setHeight(h);
			height = h;
		}
		return h;
	}
	
	@JSBody(params = { "obj" }, script = "window.currentContext = obj;")
	private static native int setCurrentContext(JSObject obj);
	
	@JSBody(params = { }, script = "return window.classicConfig;")
	public static native String[] getClassicConfig();
	
	@JSBody(params = { "obj" }, script = "if(obj.commit) obj.commit();")
	public static native int commitContext(JSObject obj);
	
	@JSBody(params = { "url" }, script = "URL.revokeObjectURL(url);")
	public static native void freeDataURL(String url);
	
	@JSBody(params = { "buf", "mime" }, script = "return URL.createObjectURL(new Blob([buf], {type: mime}));")
	public static native String getDataURL(ArrayBuffer buf, String mime);
}
