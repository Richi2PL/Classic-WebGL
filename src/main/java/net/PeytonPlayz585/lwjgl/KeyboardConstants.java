package net.PeytonPlayz585.lwjgl;

import org.lwjgl.input.Keyboard;

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
public class KeyboardConstants {

	private static final String[] keyboardNames = new String[256];
	private static final int[] keyboardGLFWToMinecraft = new int[384];
	private static final int[] keyboardMinecraftToGLFW = new int[256];
	private static final int[] keyboardBrowserToMinecraft = new int[384 * 4];
	private static final int[] keyboardMinecraftToBrowser = new int[256];
	private static final char[] keyboardChars = new char[256];
	
	private static final int GLFW_KEY_SPACE = 32, GLFW_KEY_APOSTROPHE = 39, GLFW_KEY_COMMA = 44, GLFW_KEY_MINUS = 45,
			GLFW_KEY_PERIOD = 46, GLFW_KEY_SLASH = 47, GLFW_KEY_0 = 48, GLFW_KEY_1 = 49, GLFW_KEY_2 = 50,
			GLFW_KEY_3 = 51, GLFW_KEY_4 = 52, GLFW_KEY_5 = 53, GLFW_KEY_6 = 54, GLFW_KEY_7 = 55, GLFW_KEY_8 = 56,
			GLFW_KEY_9 = 57, GLFW_KEY_SEMICOLON = 59, GLFW_KEY_EQUAL = 61, GLFW_KEY_A = 65, GLFW_KEY_B = 66,
			GLFW_KEY_C = 67, GLFW_KEY_D = 68, GLFW_KEY_E = 69, GLFW_KEY_F = 70, GLFW_KEY_G = 71, GLFW_KEY_H = 72,
			GLFW_KEY_I = 73, GLFW_KEY_J = 74, GLFW_KEY_K = 75, GLFW_KEY_L = 76, GLFW_KEY_M = 77, GLFW_KEY_N = 78,
			GLFW_KEY_O = 79, GLFW_KEY_P = 80, GLFW_KEY_Q = 81, GLFW_KEY_R = 82, GLFW_KEY_S = 83, GLFW_KEY_T = 84,
			GLFW_KEY_U = 85, GLFW_KEY_V = 86, GLFW_KEY_W = 87, GLFW_KEY_X = 88, GLFW_KEY_Y = 89, GLFW_KEY_Z = 90,
			GLFW_KEY_LEFT_BRACKET = 91, GLFW_KEY_BACKSLASH = 92, GLFW_KEY_RIGHT_BRACKET = 93,
			GLFW_KEY_GRAVE_ACCENT = 96, GLFW_KEY_WORLD_1 = 161, GLFW_KEY_WORLD_2 = 162;
	
	private static final int GLFW_KEY_ESCAPE = 256, GLFW_KEY_ENTER = 257, GLFW_KEY_TAB = 258, GLFW_KEY_BACKSPACE = 259,
			GLFW_KEY_INSERT = 260, GLFW_KEY_DELETE = 261, GLFW_KEY_RIGHT = 262, GLFW_KEY_LEFT = 263,
			GLFW_KEY_DOWN = 264, GLFW_KEY_UP = 265, GLFW_KEY_PAGE_UP = 266, GLFW_KEY_PAGE_DOWN = 267,
			GLFW_KEY_HOME = 268, GLFW_KEY_END = 269, GLFW_KEY_CAPS_LOCK = 280, GLFW_KEY_SCROLL_LOCK = 281,
			GLFW_KEY_NUM_LOCK = 282, GLFW_KEY_PRINT_SCREEN = 283, GLFW_KEY_PAUSE = 284, GLFW_KEY_F1 = 290,
			GLFW_KEY_F2 = 291, GLFW_KEY_F3 = 292, GLFW_KEY_F4 = 293, GLFW_KEY_F5 = 294, GLFW_KEY_F6 = 295,
			GLFW_KEY_F7 = 296, GLFW_KEY_F8 = 297, GLFW_KEY_F9 = 298, GLFW_KEY_F10 = 299, GLFW_KEY_F11 = 300,
			GLFW_KEY_F12 = 301, GLFW_KEY_F13 = 302, GLFW_KEY_F14 = 303, GLFW_KEY_F15 = 304, GLFW_KEY_F16 = 305,
			GLFW_KEY_F17 = 306, GLFW_KEY_F18 = 307, GLFW_KEY_F19 = 308, GLFW_KEY_F20 = 309, GLFW_KEY_F21 = 310,
			GLFW_KEY_F22 = 311, GLFW_KEY_F23 = 312, GLFW_KEY_F24 = 313, GLFW_KEY_F25 = 314, GLFW_KEY_KP_0 = 320,
			GLFW_KEY_KP_1 = 321, GLFW_KEY_KP_2 = 322, GLFW_KEY_KP_3 = 323, GLFW_KEY_KP_4 = 324, GLFW_KEY_KP_5 = 325,
			GLFW_KEY_KP_6 = 326, GLFW_KEY_KP_7 = 327, GLFW_KEY_KP_8 = 328, GLFW_KEY_KP_9 = 329,
			GLFW_KEY_KP_DECIMAL = 330, GLFW_KEY_KP_DIVIDE = 331, GLFW_KEY_KP_MULTIPLY = 332, GLFW_KEY_KP_SUBTRACT = 333,
			GLFW_KEY_KP_ADD = 334, GLFW_KEY_KP_ENTER = 335, GLFW_KEY_KP_EQUAL = 336, GLFW_KEY_LEFT_SHIFT = 340,
			GLFW_KEY_LEFT_CONTROL = 341, GLFW_KEY_LEFT_ALT = 342, GLFW_KEY_LEFT_SUPER = 343, GLFW_KEY_RIGHT_SHIFT = 344,
			GLFW_KEY_RIGHT_CONTROL = 345, GLFW_KEY_RIGHT_ALT = 346, GLFW_KEY_RIGHT_SUPER = 347, GLFW_KEY_MENU = 348,
			GLFW_KEY_LAST = GLFW_KEY_MENU;
	
	private static final int DOM_KEY_LOCATION_STANDARD = 0, DOM_KEY_LOCATION_LEFT = 1, DOM_KEY_LOCATION_RIGHT = 2,
			DOM_KEY_LOCATION_NUMPAD = 3;

	private static void register(int minecraftId, int glfwId, int browserId, int browserLocation, String name, char character) {
		if(keyboardMinecraftToGLFW[minecraftId] != 0) throw new IllegalArgumentException("Duplicate keyboardMinecraftToGLFW entry: " + minecraftId + " -> " + glfwId);
		keyboardMinecraftToGLFW[minecraftId] = glfwId;
		if(keyboardGLFWToMinecraft[glfwId] != 0) throw new IllegalArgumentException("Duplicate keyboardGLFWToMinecraft entry: " + glfwId + " -> " + minecraftId);
		keyboardGLFWToMinecraft[glfwId] = minecraftId;
		if(browserLocation == 0) {
			if(keyboardMinecraftToBrowser[minecraftId] != 0) throw new IllegalArgumentException("Duplicate keyboardMinecraftToBrowser entry: " + minecraftId + " -> " + browserId + "(0)");
			keyboardMinecraftToBrowser[minecraftId] = browserId;
			if(keyboardBrowserToMinecraft[browserId] != 0) throw new IllegalArgumentException("Duplicate keyboardBrowserToMinecraft entry: " + browserId + "(0) -> " + minecraftId);
			keyboardBrowserToMinecraft[browserId] = minecraftId;
		}else {
			browserLocation *= 384;
			if(keyboardMinecraftToBrowser[minecraftId] != 0) throw new IllegalArgumentException("Duplicate keyboardMinecraftToBrowser entry: " + minecraftId + " -> " + browserId + "(" + browserLocation + ")");
			keyboardMinecraftToBrowser[minecraftId] = browserId + browserLocation;
			if(keyboardBrowserToMinecraft[browserId + browserLocation] != 0) throw new IllegalArgumentException("Duplicate keyboardBrowserToMinecraft entry: " + browserId + "(" + browserLocation + ") -> " + minecraftId);
			keyboardBrowserToMinecraft[browserId + browserLocation] = minecraftId;
		}
		if(keyboardNames[minecraftId] != null) throw new IllegalArgumentException("Duplicate keyboardNames entry: " + minecraftId + " -> " + name);
		keyboardNames[minecraftId] = name;
		if(keyboardChars[minecraftId] != '\0') throw new IllegalArgumentException("Duplicate keyboardChars entry: " + minecraftId + " -> " + character);
		keyboardChars[minecraftId] = character;
	}
	
	private static void registerAlt(int minecraftId, int browserId, int browserLocation) {
		if(browserLocation == 0) {
			if(keyboardBrowserToMinecraft[browserId] != 0) throw new IllegalArgumentException("Duplicate (alt) keyboardBrowserToMinecraft entry: " + browserId + " -> " + minecraftId);
			keyboardBrowserToMinecraft[browserId] = minecraftId;
		}else {
			browserLocation *= 384;
			if(keyboardBrowserToMinecraft[browserId + browserLocation] != 0) throw new IllegalArgumentException("Duplicate (alt) keyboardBrowserToMinecraft entry: " + browserId + "(" + browserLocation + ") -> " + minecraftId);
			keyboardBrowserToMinecraft[browserId + browserLocation] = minecraftId;
		}
	}
	
	static {
		register(Keyboard.KEY_SPACE, GLFW_KEY_SPACE, 32, DOM_KEY_LOCATION_STANDARD, "Space", ' ');
		register(Keyboard.KEY_APOSTROPHE, GLFW_KEY_APOSTROPHE, 222, DOM_KEY_LOCATION_STANDARD, "Quote", '\'');
		register(Keyboard.KEY_COMMA, GLFW_KEY_COMMA, 188, DOM_KEY_LOCATION_STANDARD, "Comma", ',');
		register(Keyboard.KEY_MINUS, GLFW_KEY_MINUS, 189, DOM_KEY_LOCATION_STANDARD, "Minus", '-');
		register(Keyboard.KEY_PERIOD, GLFW_KEY_PERIOD, 190, DOM_KEY_LOCATION_STANDARD, "Period", '.');
		register(Keyboard.KEY_SLASH, GLFW_KEY_SLASH, 191, DOM_KEY_LOCATION_STANDARD, "Slash", '/');
		register(Keyboard.KEY_0, GLFW_KEY_0, 48, DOM_KEY_LOCATION_STANDARD, "0", '0');
		register(Keyboard.KEY_1, GLFW_KEY_1, 49, DOM_KEY_LOCATION_STANDARD, "1", '1');
		register(Keyboard.KEY_2, GLFW_KEY_2, 50, DOM_KEY_LOCATION_STANDARD, "2", '2');
		register(Keyboard.KEY_3, GLFW_KEY_3, 51, DOM_KEY_LOCATION_STANDARD, "3", '3');
		register(Keyboard.KEY_4, GLFW_KEY_4, 52, DOM_KEY_LOCATION_STANDARD, "4", '4');
		register(Keyboard.KEY_5, GLFW_KEY_5, 53, DOM_KEY_LOCATION_STANDARD, "5", '5');
		register(Keyboard.KEY_6, GLFW_KEY_6, 54, DOM_KEY_LOCATION_STANDARD, "6", '6');
		register(Keyboard.KEY_7, GLFW_KEY_7, 55, DOM_KEY_LOCATION_STANDARD, "7", '7');
		register(Keyboard.KEY_8, GLFW_KEY_8, 56, DOM_KEY_LOCATION_STANDARD, "8", '8');
		register(Keyboard.KEY_9, GLFW_KEY_9, 57, DOM_KEY_LOCATION_STANDARD, "9", '9');
		register(Keyboard.KEY_SEMICOLON, GLFW_KEY_SEMICOLON, 186, DOM_KEY_LOCATION_STANDARD, "Semicolon", ';');
		register(Keyboard.KEY_EQUALS, GLFW_KEY_EQUAL, 187, DOM_KEY_LOCATION_STANDARD, "Equals", '=');
		register(Keyboard.KEY_A, GLFW_KEY_A, 65, DOM_KEY_LOCATION_STANDARD, "A", 'a');
		register(Keyboard.KEY_B, GLFW_KEY_B, 66, DOM_KEY_LOCATION_STANDARD, "B", 'b');
		register(Keyboard.KEY_C, GLFW_KEY_C, 67, DOM_KEY_LOCATION_STANDARD, "C", 'c');
		register(Keyboard.KEY_D, GLFW_KEY_D, 68, DOM_KEY_LOCATION_STANDARD, "D", 'd');
		register(Keyboard.KEY_E, GLFW_KEY_E, 69, DOM_KEY_LOCATION_STANDARD, "E", 'e');
		register(Keyboard.KEY_F, GLFW_KEY_F, 70, DOM_KEY_LOCATION_STANDARD, "F", 'f');
		register(Keyboard.KEY_G, GLFW_KEY_G, 71, DOM_KEY_LOCATION_STANDARD, "G", 'g');
		register(Keyboard.KEY_H, GLFW_KEY_H, 72, DOM_KEY_LOCATION_STANDARD, "H", 'h');
		register(Keyboard.KEY_I, GLFW_KEY_I, 73, DOM_KEY_LOCATION_STANDARD, "I", 'i');
		register(Keyboard.KEY_J, GLFW_KEY_J, 74, DOM_KEY_LOCATION_STANDARD, "J", 'j');
		register(Keyboard.KEY_K, GLFW_KEY_K, 75, DOM_KEY_LOCATION_STANDARD, "K", 'k');
		register(Keyboard.KEY_L, GLFW_KEY_L, 76, DOM_KEY_LOCATION_STANDARD, "L", 'l');
		register(Keyboard.KEY_M, GLFW_KEY_M, 77, DOM_KEY_LOCATION_STANDARD, "M", 'm');
		register(Keyboard.KEY_N, GLFW_KEY_N, 78, DOM_KEY_LOCATION_STANDARD, "N", 'n');
		register(Keyboard.KEY_O, GLFW_KEY_O, 79, DOM_KEY_LOCATION_STANDARD, "O", 'o');
		register(Keyboard.KEY_P, GLFW_KEY_P, 80, DOM_KEY_LOCATION_STANDARD, "P", 'p');
		register(Keyboard.KEY_Q, GLFW_KEY_Q, 81, DOM_KEY_LOCATION_STANDARD, "Q", 'q');
		register(Keyboard.KEY_R, GLFW_KEY_R, 82, DOM_KEY_LOCATION_STANDARD, "R", 'r');
		register(Keyboard.KEY_S, GLFW_KEY_S, 83, DOM_KEY_LOCATION_STANDARD, "S", 's');
		register(Keyboard.KEY_T, GLFW_KEY_T, 84, DOM_KEY_LOCATION_STANDARD, "T", 't');
		register(Keyboard.KEY_U, GLFW_KEY_U, 85, DOM_KEY_LOCATION_STANDARD, "U", 'u');
		register(Keyboard.KEY_V, GLFW_KEY_V, 86, DOM_KEY_LOCATION_STANDARD, "V", 'v');
		register(Keyboard.KEY_W, GLFW_KEY_W, 87, DOM_KEY_LOCATION_STANDARD, "W", 'w');
		register(Keyboard.KEY_X, GLFW_KEY_X, 88, DOM_KEY_LOCATION_STANDARD, "X", 'x');
		register(Keyboard.KEY_Y, GLFW_KEY_Y, 89, DOM_KEY_LOCATION_STANDARD, "Y", 'y');
		register(Keyboard.KEY_Z, GLFW_KEY_Z, 90, DOM_KEY_LOCATION_STANDARD, "Z", 'z');
		register(Keyboard.KEY_LBRACKET, GLFW_KEY_LEFT_BRACKET, 219, DOM_KEY_LOCATION_STANDARD, "L. Bracket", '[');
		register(Keyboard.KEY_BACKSLASH, GLFW_KEY_BACKSLASH, 220, DOM_KEY_LOCATION_STANDARD, "Backslash", '\\');
		register(Keyboard.KEY_RBRACKET, GLFW_KEY_RIGHT_BRACKET, 221, DOM_KEY_LOCATION_STANDARD, "R. Bracket", ']');
		register(Keyboard.KEY_GRAVE, GLFW_KEY_GRAVE_ACCENT, 192, DOM_KEY_LOCATION_STANDARD, "Backtick", '`');
		register(Keyboard.KEY_ESCAPE, GLFW_KEY_ESCAPE, 27, DOM_KEY_LOCATION_STANDARD, "Escape", '\0');
		register(Keyboard.KEY_RETURN, GLFW_KEY_ENTER, 13, DOM_KEY_LOCATION_STANDARD, "Enter", '\n');
		register(Keyboard.KEY_TAB, GLFW_KEY_TAB, 9, DOM_KEY_LOCATION_STANDARD, "Tab", '\t');
		register(Keyboard.KEY_BACK, GLFW_KEY_BACKSPACE, 8, DOM_KEY_LOCATION_STANDARD, "Backspace", '\0');
		register(Keyboard.KEY_INSERT, GLFW_KEY_INSERT, 45, DOM_KEY_LOCATION_STANDARD, "Insert", '\0');
		register(Keyboard.KEY_DELETE, GLFW_KEY_DELETE, 46, DOM_KEY_LOCATION_STANDARD, "Delete", '\0');
		register(Keyboard.KEY_RIGHT, GLFW_KEY_RIGHT, 39, DOM_KEY_LOCATION_STANDARD, "Right", '\0');
		register(Keyboard.KEY_LEFT, GLFW_KEY_LEFT, 37, DOM_KEY_LOCATION_STANDARD, "Left", '\0');
		register(Keyboard.KEY_DOWN, GLFW_KEY_DOWN, 40, DOM_KEY_LOCATION_STANDARD, "Down", '\0');
		register(Keyboard.KEY_UP, GLFW_KEY_UP, 38, DOM_KEY_LOCATION_STANDARD, "Up", '\0');
		register(Keyboard.KEY_PRIOR, GLFW_KEY_PAGE_UP, 33, DOM_KEY_LOCATION_STANDARD, "Page Up", '\0');
		register(Keyboard.KEY_NEXT, GLFW_KEY_PAGE_DOWN, 34, DOM_KEY_LOCATION_STANDARD, "Page Down", '\0');
		register(Keyboard.KEY_HOME, GLFW_KEY_HOME, 36, DOM_KEY_LOCATION_STANDARD, "Home", '\0');
		register(Keyboard.KEY_END, GLFW_KEY_END, 35, DOM_KEY_LOCATION_STANDARD, "End", '\0');
		register(Keyboard.KEY_CAPITAL, GLFW_KEY_CAPS_LOCK, 20, DOM_KEY_LOCATION_STANDARD, "Caps Lock", '\0');
		register(Keyboard.KEY_SCROLL, GLFW_KEY_SCROLL_LOCK, 145, DOM_KEY_LOCATION_STANDARD, "Scroll Lock", '\0');
		register(Keyboard.KEY_NUMLOCK, GLFW_KEY_NUM_LOCK, 144, DOM_KEY_LOCATION_STANDARD, "Num Lock", '\0');
		register(Keyboard.KEY_PAUSE, GLFW_KEY_PAUSE, 19, DOM_KEY_LOCATION_STANDARD, "Pause", '\0');
		register(Keyboard.KEY_F1, GLFW_KEY_F1, 112, DOM_KEY_LOCATION_STANDARD, "F1", '\0');
		register(Keyboard.KEY_F2, GLFW_KEY_F2, 113, DOM_KEY_LOCATION_STANDARD, "F2", '\0');
		register(Keyboard.KEY_F3, GLFW_KEY_F3, 114, DOM_KEY_LOCATION_STANDARD, "F3", '\0');
		register(Keyboard.KEY_F4, GLFW_KEY_F4, 115, DOM_KEY_LOCATION_STANDARD, "F4", '\0');
		register(Keyboard.KEY_F5, GLFW_KEY_F5, 116, DOM_KEY_LOCATION_STANDARD, "F5", '\0');
		register(Keyboard.KEY_F6, GLFW_KEY_F6, 117, DOM_KEY_LOCATION_STANDARD, "F6", '\0');
		register(Keyboard.KEY_F7, GLFW_KEY_F7, 118, DOM_KEY_LOCATION_STANDARD, "F7", '\0');
		register(Keyboard.KEY_F8, GLFW_KEY_F8, 119, DOM_KEY_LOCATION_STANDARD, "F8", '\0');
		register(Keyboard.KEY_F9, GLFW_KEY_F9, 120, DOM_KEY_LOCATION_STANDARD, "F9", '\0');
		register(Keyboard.KEY_F10, GLFW_KEY_F10, 121, DOM_KEY_LOCATION_STANDARD, "F10", '\0');
		register(Keyboard.KEY_F11, GLFW_KEY_F11, 122, DOM_KEY_LOCATION_STANDARD, "F11", '\0');
		register(Keyboard.KEY_F12, GLFW_KEY_F12, 123, DOM_KEY_LOCATION_STANDARD, "F12", '\0');
		register(Keyboard.KEY_NUMPAD0, GLFW_KEY_KP_0, 96, DOM_KEY_LOCATION_NUMPAD, "Keypad 0", '0');
		register(Keyboard.KEY_NUMPAD1, GLFW_KEY_KP_1, 97, DOM_KEY_LOCATION_NUMPAD, "Keypad 1", '1');
		register(Keyboard.KEY_NUMPAD2, GLFW_KEY_KP_2, 98, DOM_KEY_LOCATION_NUMPAD, "Keypad 2", '2');
		register(Keyboard.KEY_NUMPAD3, GLFW_KEY_KP_3, 99, DOM_KEY_LOCATION_NUMPAD, "Keypad 3", '3');
		register(Keyboard.KEY_NUMPAD4, GLFW_KEY_KP_4, 100, DOM_KEY_LOCATION_NUMPAD, "Keypad 4", '4');
		register(Keyboard.KEY_NUMPAD5, GLFW_KEY_KP_5, 101, DOM_KEY_LOCATION_NUMPAD, "Keypad 5", '5');
		register(Keyboard.KEY_NUMPAD6, GLFW_KEY_KP_6, 102, DOM_KEY_LOCATION_NUMPAD, "Keypad 6", '6');
		register(Keyboard.KEY_NUMPAD7, GLFW_KEY_KP_7, 103, DOM_KEY_LOCATION_NUMPAD, "Keypad 7", '7');
		register(Keyboard.KEY_NUMPAD8, GLFW_KEY_KP_8, 104, DOM_KEY_LOCATION_NUMPAD, "Keypad 8", '8');
		register(Keyboard.KEY_NUMPAD9, GLFW_KEY_KP_9, 105, DOM_KEY_LOCATION_NUMPAD, "Keypad 9", '9');
		register(Keyboard.KEY_DECIMAL, GLFW_KEY_KP_DECIMAL, 110, DOM_KEY_LOCATION_NUMPAD, "Decimal", '.');
		register(Keyboard.KEY_DIVIDE, GLFW_KEY_KP_DIVIDE, 111, DOM_KEY_LOCATION_NUMPAD, "Divide", '/');
		register(Keyboard.KEY_MULTIPLY, GLFW_KEY_KP_MULTIPLY, 106, DOM_KEY_LOCATION_NUMPAD, "Multiply", '*');
		register(Keyboard.KEY_SUBTRACT, GLFW_KEY_KP_SUBTRACT, 109, DOM_KEY_LOCATION_NUMPAD, "Subtract", '-');
		register(Keyboard.KEY_ADD, GLFW_KEY_KP_ADD, 107, DOM_KEY_LOCATION_NUMPAD, "Add", '+');
		register(Keyboard.KEY_NUMPADENTER, GLFW_KEY_KP_ENTER, 13, DOM_KEY_LOCATION_NUMPAD, "Enter", '\n');
		register(Keyboard.KEY_NUMPADEQUALS, GLFW_KEY_KP_EQUAL, 187, DOM_KEY_LOCATION_NUMPAD, "Equals", '=');
		register(Keyboard.KEY_LSHIFT, GLFW_KEY_LEFT_SHIFT, 16, DOM_KEY_LOCATION_LEFT, "L. Shift", '\0');
		register(Keyboard.KEY_LCONTROL, GLFW_KEY_LEFT_CONTROL, 17, DOM_KEY_LOCATION_LEFT, "L. Control", '\0');
		register(Keyboard.KEY_LMENU, GLFW_KEY_LEFT_ALT, 18, DOM_KEY_LOCATION_LEFT, "L. Alt", '\0');
		registerAlt(Keyboard.KEY_LSHIFT, 16, DOM_KEY_LOCATION_STANDARD);
		registerAlt(Keyboard.KEY_LCONTROL, 17, DOM_KEY_LOCATION_STANDARD);
		registerAlt(Keyboard.KEY_LMENU, 18, DOM_KEY_LOCATION_STANDARD);
		register(Keyboard.KEY_RSHIFT, GLFW_KEY_RIGHT_SHIFT, 16, DOM_KEY_LOCATION_RIGHT, "R. Shift", '\0');
		register(Keyboard.KEY_RCONTROL, GLFW_KEY_RIGHT_CONTROL, 17, DOM_KEY_LOCATION_RIGHT, "R. Control", '\0');
		register(Keyboard.KEY_RMENU, GLFW_KEY_RIGHT_ALT, 18, DOM_KEY_LOCATION_RIGHT, "R. Alt", '\0');
	}

	public static String getKeyName(int key) {
		if (key < 0 || key >= 256 || keyboardNames[key] == null) {
			return "Unknown";
		} else {
			return keyboardNames[key];
		}
	}

	public static int getMinecraftKeyFromGLFW(int key) {
		if (key < 0 || key >= 384) {
			return 0;
		} else {
			return keyboardGLFWToMinecraft[key];
		}
	}

	public static int getGLFWKeyFromMinecraft(int key) {
		if (key < 0 || key >= 256) {
			return 0;
		} else {
			return keyboardMinecraftToGLFW[key];
		}
	}
	
	public static int getMinecraftKeyFromBrowser(int key) {
		return getMinecraftKeyFromBrowser(key, 0);
	}
	
	public static int getMinecraftKeyFromBrowser(int key, int location) {
		if (key < 0 || key >= 384) {
			return 0;
		} else {
			if(location <= 0 || location >= 4) {
				return keyboardBrowserToMinecraft[key];
			}else {
				int i = keyboardBrowserToMinecraft[key + location * 384];
				if(i == 0) {
					i = keyboardBrowserToMinecraft[key];
				}
				return i;
			}
		}
	}

	public static int getBrowserKeyFromMinecraft(int key) {
		if (key < 0 || key >= 256) {
			return 0;
		} else {
			return keyboardMinecraftToBrowser[key] % 384;
		}
	}

	public static int getBrowserLocationFromMinecraft(int key) {
		if (key < 0 || key >= 384) {
			return 0;
		} else {
			return keyboardMinecraftToBrowser[key] / 384;
		}
	}
	
	public static char getKeyCharFromMinecraft(int key) {
		if (key < 0 || key >= 256) {
			return '\0';
		} else {
			return keyboardChars[key];
		}
	}
	
}