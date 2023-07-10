package net.PeytonPlayz585.lwjgl;

import java.util.LinkedList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.teavm.jso.JSBody;
import org.teavm.jso.browser.TimerHandler;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.events.WheelEvent;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.webgl.WebGLFramebuffer;
import org.teavm.jso.webgl.WebGLRenderbuffer;

import net.PeytonPlayz585.main.MinecraftMain;
import net.PeytonPlayz585.teavm.WebGL2RenderingContext;

import static net.PeytonPlayz585.teavm.WebGL2RenderingContext.*;

/**
 * Copyright (c) 2022 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info)
 * 
 */
public class PlatformInput {
	static WebGLFramebuffer mainFramebuffer = null;
	static WebGLRenderbuffer mainColorRenderbuffer = null;
	static WebGLRenderbuffer mainDepthRenderbuffer = null;
	private static int framebufferWidth = -1;
	private static int framebufferHeight = -1;
	
	private static EventListener contextmenu = null;
	private static EventListener mousedown = null;
	private static EventListener mouseup = null;
	private static EventListener mousemove = null;
	private static EventListener mouseenter = null;
	private static EventListener mouseleave = null;
	private static EventListener keydown = null;
	private static EventListener keyup = null;
	private static EventListener keypress = null;
	private static EventListener wheel = null;
	private static EventListener pointerlock = null;

	private static List<MouseEvent> mouseEvents = new LinkedList();
	private static List<KeyboardEvent> keyEvents = new LinkedList();

	private static int mouseX = 0;
	private static int mouseY = 0;
	private static double mouseDX = 0.0D;
	private static double mouseDY = 0.0D;
	private static double mouseDWheel = 0.0D;
	private static int width = 0;
	private static int height = 0;
	private static boolean enableRepeatEvents = true;
	private static boolean isWindowFocused = true;
	private static boolean isMouseOverWindow = true;
	static boolean unpressCTRL = false;

	private static int windowWidth = -1;
	private static int windowHeight = -1;
	private static int lastWasResizedWindowWidth = -2;
	private static int lastWasResizedWindowHeight = -2;
	
	private static MouseEvent currentEvent = null;
	private static KeyboardEvent currentEventK = null;
	private static boolean[] buttonStates = new boolean[8];
	private static boolean[] keyStates = new boolean[256];

	private static int functionKeyModifier = Keyboard.KEY_F;

	private static long mouseUngrabTimer = 0l;
	private static long mouseGrabTimer = 0l;
	private static int mouseUngrabTimeout = -1;
	private static boolean pointerLockFlag = false;
	
	@JSBody(params = { }, script = "window.onbeforeunload = () => {return false;};")
	private static native void onBeforeCloseRegister();
	
	public static void initHooks() {
		MinecraftMain.win.addEventListener("contextmenu", contextmenu = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
			}
		});
		MinecraftMain.canvas.addEventListener("mousedown", mousedown = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
				int b = evt.getButton();
				buttonStates[b == 1 ? 2 : (b == 2 ? 1 : b)] = true;
				mouseEvents.add(evt);
			}
		});
		MinecraftMain.canvas.addEventListener("mouseup", mouseup = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
				int b = evt.getButton();
				buttonStates[b == 1 ? 2 : (b == 2 ? 1 : b)] = false;
				mouseEvents.add(evt);
			}
		});
		MinecraftMain.canvas.addEventListener("mousemove", mousemove = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
				mouseX = (int)(getOffsetX(evt) * MinecraftMain.win.getDevicePixelRatio());
				mouseY = (int)((MinecraftMain.canvas.getClientHeight() - getOffsetY(evt)) * MinecraftMain.win.getDevicePixelRatio());
				mouseDX += evt.getMovementX();
				mouseDY += -evt.getMovementY();
				if(hasBeenActive()) {
					mouseEvents.add(evt);
				}
			}
		});
		MinecraftMain.canvas.addEventListener("mouseenter", mouseenter = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				isMouseOverWindow = true;
			}
		});
		MinecraftMain.canvas.addEventListener("mouseleave", mouseleave = new EventListener<MouseEvent>() {
			@Override
			public void handleEvent(MouseEvent evt) {
				isMouseOverWindow = false;
			}
		});
		MinecraftMain.win.addEventListener("keydown", keydown = new EventListener<KeyboardEvent>() {
			@Override
			public void handleEvent(KeyboardEvent evt) {
				int w = getWhich(evt);
				if (w == 122) return; // F11
				evt.preventDefault();
				evt.stopPropagation();
				if(!enableRepeatEvents && evt.isRepeat()) return;
				w = processFunctionKeys(w);
				keyStates[KeyboardConstants.getMinecraftKeyFromBrowser(w, evt.getLocation())] = true;
				keyEvents.add(evt);
			}
		});
		MinecraftMain.win.addEventListener("keyup", keyup = new EventListener<KeyboardEvent>() {
			@Override
			public void handleEvent(KeyboardEvent evt) {
				int w = getWhich(evt);
				if (w == 122) return; // F11
				evt.preventDefault();
				evt.stopPropagation();
				if(!enableRepeatEvents && evt.isRepeat()) return;
				w = processFunctionKeys(w);
				keyStates[KeyboardConstants.getMinecraftKeyFromBrowser(w, evt.getLocation())] = false;
				keyEvents.add(evt);
			}
		});
		MinecraftMain.win.addEventListener("keypress", keypress = new EventListener<KeyboardEvent>() {
			@Override
			public void handleEvent(KeyboardEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
				if(enableRepeatEvents && evt.isRepeat()) keyEvents.add(evt);
			}
		});
		MinecraftMain.canvas.addEventListener("wheel", wheel = new EventListener<WheelEvent>() {
			@Override
			public void handleEvent(WheelEvent evt) {
				evt.preventDefault();
				evt.stopPropagation();
				mouseEvents.add(evt);
				mouseDWheel += evt.getDeltaY();
			}
		});
		MinecraftMain.win.addEventListener("blur", new EventListener<WheelEvent>() {
			@Override
			public void handleEvent(WheelEvent evt) {
				isWindowFocused = false;
				for(int i = 0; i < buttonStates.length; ++i) {
					buttonStates[i] = false;
				}
				for(int i = 0; i < keyStates.length; ++i) {
					keyStates[i] = false;
				}
			}
		});
		MinecraftMain.win.addEventListener("focus", new EventListener<WheelEvent>() {
			@Override
			public void handleEvent(WheelEvent evt) {
				isWindowFocused = true;
			}
		});
		MinecraftMain.win.getDocument().addEventListener("pointerlockchange", pointerlock = new EventListener<WheelEvent>() {
			@Override
			public void handleEvent(WheelEvent evt) {
				Window.setTimeout(new TimerHandler() {
					@Override
					public void onTimer() {
						boolean grab = isPointerLocked();
						if(!grab) {
							if(pointerLockFlag) {
								mouseUngrabTimer = System.currentTimeMillis();
							}
						}
						pointerLockFlag = grab;
					}
				}, 60);
				mouseDX = 0.0D;
				mouseDY = 0.0D;
			}
		});
		onBeforeCloseRegister();
	}

	@JSBody(params = { }, script = "if(window.navigator.userActivation){return window.navigator.userActivation.hasBeenActive;}else{return false;}")
	public static native boolean hasBeenActive();
	
	@JSBody(params = { "m" }, script = "return m.offsetX;")
	private static native int getOffsetX(MouseEvent m);
	
	@JSBody(params = { "m" }, script = "return m.offsetY;")
	private static native int getOffsetY(MouseEvent m);
	
	@JSBody(params = { "e" }, script = "return e.which;")
	private static native int getWhich(KeyboardEvent e);
	
	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}

	public static boolean getWindowFocused() {
		return isWindowFocused || isPointerLocked();
	}

	public static boolean isCloseRequested() {
		return false;
	}

	public static void update() {
		double r = MinecraftMain.win.getDevicePixelRatio();
		int w = MinecraftMain.parent.getClientWidth();
		int h = MinecraftMain.parent.getClientHeight();
		int w2 = windowWidth = (int)(w * r);
		int h2 = windowHeight = (int)(h * r);
		if(MinecraftMain.canvas.getWidth() != w2) {
			MinecraftMain.canvas.setWidth(w2);
		}
		if(MinecraftMain.canvas.getHeight() != h2) {
			MinecraftMain.canvas.setHeight(h2);
		}
		flipBuffer();
		MinecraftMain.sleep(1l);
	}
	
	public static void initFramebuffer(WebGLFramebuffer fbo, int sw, int sh) {
		mainFramebuffer = fbo;
		
		framebufferWidth = windowWidth = sw;
		framebufferHeight = windowHeight = sh;
		
		MinecraftMain.webgl.bindFramebuffer(FRAMEBUFFER, fbo);

		mainColorRenderbuffer = MinecraftMain.webgl.createRenderbuffer();
		mainDepthRenderbuffer = MinecraftMain.webgl.createRenderbuffer();
		
		MinecraftMain.webgl.bindRenderbuffer(RENDERBUFFER, mainColorRenderbuffer);
		MinecraftMain.webgl.renderbufferStorage(RENDERBUFFER, RGBA8, sw, sh);
		MinecraftMain.webgl.framebufferRenderbuffer(FRAMEBUFFER, COLOR_ATTACHMENT0, RENDERBUFFER, mainColorRenderbuffer);
		
		MinecraftMain.webgl.bindRenderbuffer(RENDERBUFFER, mainDepthRenderbuffer);
		MinecraftMain.webgl.renderbufferStorage(RENDERBUFFER, DEPTH_COMPONENT32F, sw, sh);
		MinecraftMain.webgl.framebufferRenderbuffer(FRAMEBUFFER, DEPTH_ATTACHMENT, RENDERBUFFER, mainDepthRenderbuffer);
		
		MinecraftMain.webgl.drawBuffers(new int[] { COLOR_ATTACHMENT0 });
	}
	
	private static void flipBuffer() {
		
		MinecraftMain.webgl.bindFramebuffer(READ_FRAMEBUFFER, mainFramebuffer);
		MinecraftMain.webgl.bindFramebuffer(DRAW_FRAMEBUFFER, null);
		MinecraftMain.webgl.blitFramebuffer(0, 0, framebufferWidth, framebufferHeight, 0, 0, windowWidth, windowHeight, COLOR_BUFFER_BIT, NEAREST);
		
		MinecraftMain.webgl.bindFramebuffer(FRAMEBUFFER, mainFramebuffer);
		
		if(windowWidth != framebufferWidth || windowHeight != framebufferHeight) {
			framebufferWidth = windowWidth;
			framebufferHeight = windowHeight;
			
			MinecraftMain.webgl.bindRenderbuffer(RENDERBUFFER, mainColorRenderbuffer);
			MinecraftMain.webgl.renderbufferStorage(RENDERBUFFER, RGBA8, framebufferWidth, framebufferHeight);
			
			MinecraftMain.webgl.bindRenderbuffer(RENDERBUFFER, mainDepthRenderbuffer);
			MinecraftMain.webgl.renderbufferStorage(RENDERBUFFER, DEPTH_COMPONENT32F, framebufferWidth, framebufferHeight);
		}
		
	}
	
	public static boolean wasResized() {
		if(windowWidth != lastWasResizedWindowWidth || windowHeight != lastWasResizedWindowHeight) {
			lastWasResizedWindowWidth = windowWidth;
			lastWasResizedWindowHeight = windowHeight;
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean keyboardNext() {
		if(unpressCTRL) { //un-press ctrl after copy/paste permission
			keyEvents.clear();
			currentEventK = null;
			keyStates[29] = false;
			keyStates[157] = false;
			keyStates[28] = false;
			keyStates[219] = false;
			keyStates[220] = false;
			unpressCTRL = false;
			return false;
		}
		currentEventK = null;
		return !keyEvents.isEmpty() && (currentEventK = keyEvents.remove(0)) != null;
	}

	public static boolean keyboardGetEventKeyState() {
		return currentEventK == null? false : !currentEventK.getType().equals("keyup");
	}

	public static int keyboardGetEventKey() {
		int w = processFunctionKeys(getWhich(currentEventK));
		return currentEventK == null ? -1 : KeyboardConstants.getMinecraftKeyFromBrowser(w, currentEventK.getLocation());
	}

	public static char keyboardGetEventCharacter() {
		if(currentEventK == null) return '\0';
		String s = currentEventK.getKey();
		return currentEventK == null ? ' ' : (char) (s.length() > 1 ? '\0' : s.charAt(0));
	}

	public static boolean keyboardIsKeyDown(int key) {
		if(unpressCTRL) { //un-press ctrl after copy/paste permission
			keyStates[28] = false;
			keyStates[29] = false;
			keyStates[157] = false;
			keyStates[219] = false;
			keyStates[220] = false;
		}
		return key < 0 || key >= keyStates.length ? false : keyStates[key];
	}

	public static boolean keyboardIsRepeatEvent() {
		return currentEventK == null ? false : currentEventK.isRepeat();
	}

	public static void keyboardEnableRepeatEvents(boolean b) {
		enableRepeatEvents = b;
	}

	public static boolean mouseNext() {
		currentEvent = null;
		return !mouseEvents.isEmpty() && (currentEvent = mouseEvents.remove(0)) != null;
	}

	public static boolean mouseGetEventButtonState() {
		return currentEvent == null ? false : currentEvent.getType().equals(MouseEvent.MOUSEDOWN);
	}

	public static int mouseGetEventButton() {
		if(currentEvent == null || currentEvent.getType().equals(MouseEvent.MOUSEMOVE)) return -1;
		int b = currentEvent.getButton();
		return b == 1 ? 2 : (b == 2 ? 1 : b);
	}

	public static int mouseGetEventX() {
		return currentEvent == null ? -1 : (int)(currentEvent.getClientX() * MinecraftMain.win.getDevicePixelRatio());
	}

	public static int mouseGetEventY() {
		return currentEvent == null ? -1 : (int)((MinecraftMain.canvas.getClientHeight() - currentEvent.getClientY()) * MinecraftMain.win.getDevicePixelRatio());
	}

	public static int mouseGetEventDWheel() {
		return ("wheel".equals(currentEvent.getType())) ? (((WheelEvent)currentEvent).getDeltaY() == 0.0D ? 0 : (((WheelEvent)currentEvent).getDeltaY() > 0.0D ? -1 : 1)) : 0;
	}

	public static int mouseGetX() {
		return mouseX;
	}

	public static int mouseGetY() {
		return mouseY;
	}

	public static boolean mouseIsButtonDown(int i) {
		return buttonStates[i];
	}

	public static int mouseGetDWheel() {
		int ret = (int)mouseDWheel;
		mouseDWheel = 0.0D;
		return ret;
	}

	public static void mouseSetGrabbed(boolean grab) {
		long t = System.currentTimeMillis();
		pointerLockFlag = grab;
		mouseGrabTimer = t;
		if(grab) {
			MinecraftMain.canvas.requestPointerLock();
			if(mouseUngrabTimeout != -1) Window.clearTimeout(mouseUngrabTimeout);
			mouseUngrabTimeout = -1;
			if(t - mouseUngrabTimer < 3000l) {
				mouseUngrabTimeout = Window.setTimeout(new TimerHandler() {
					@Override
					public void onTimer() {
						MinecraftMain.canvas.requestPointerLock();
					}
				}, 3100 - (int)(t - mouseUngrabTimer));
			}
		}else {
			if(mouseUngrabTimeout != -1) Window.clearTimeout(mouseUngrabTimeout);
			mouseUngrabTimeout = -1;
			Window.current().getDocument().exitPointerLock();
		}
		mouseDX = 0.0D;
		mouseDY = 0.0D;
	}

	public static boolean isMouseGrabbed() {
		return pointerLockFlag;
	}

	@JSBody(params = { }, script = "return document.pointerLockElement != null;")
	public static native boolean isPointerLocked();

	public static int mouseGetDX() {
		int ret = (int)mouseDX;
		mouseDX = 0.0D;
		return ret;
	}

	public static int mouseGetDY() {
		int ret = (int)mouseDY;
		mouseDY = 0.0D;
		return ret;
	}

	public static void mouseSetCursorPosition(int x, int y) {
		// obsolete
	}

	public static boolean mouseIsInsideWindow() {
		return isMouseOverWindow;
	}
	
	private static int processFunctionKeys(int key) {
		if(keyboardIsKeyDown(functionKeyModifier)) {
			if(key >= 49 && key <= 57) {
				key = key - 49 + 112;
			}
		}
		return key;
	}

	public static void setFunctionKeyModifier(int key) {
		functionKeyModifier = key;
	}

	public static void removeEventHandlers() {
		MinecraftMain.win.removeEventListener("contextmenu", contextmenu);
		MinecraftMain.canvas.removeEventListener("mousedown", mousedown);
		MinecraftMain.canvas.removeEventListener("mouseup", mouseup);
		MinecraftMain.canvas.removeEventListener("mousemove", mousemove);
		MinecraftMain.canvas.removeEventListener("mouseenter", mouseenter);
		MinecraftMain.canvas.removeEventListener("mouseleave", mouseleave);
		MinecraftMain.win.removeEventListener("keydown", keydown);
		MinecraftMain.win.removeEventListener("keyup", keyup);
		MinecraftMain.win.removeEventListener("keypress", keypress);
		MinecraftMain.canvas.removeEventListener("wheel", wheel);
		MinecraftMain.win.getDocument().removeEventListener("pointerlockchange", pointerlock);
		if(mouseUngrabTimeout != -1) {
			Window.clearTimeout(mouseUngrabTimeout);
			mouseUngrabTimeout = -1;
		}
	}

	public static void clearEvenBuffers() {
		mouseEvents.clear();
		keyEvents.clear();
	}
	
}