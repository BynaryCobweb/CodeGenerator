/* <LICENSE>
Copyright (C) 2013-2016 Louis JEAN

This file is part of Terra Magnetica.

Terra Magnetica is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Terra Magnetica is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Terra Magnetica. If not, see <http://www.gnu.org/licenses/>.
 </LICENSE> */

package org.terramagnetica.opengl.miscellaneous;

import org.lwjgl.glfw.GLFW;

import net.bynaryscode.util.Util;

public class GLFWUtil {
	public static final int[] GLFW_KEYS = {
			GLFW.GLFW_KEY_SPACE,
			GLFW.GLFW_KEY_APOSTROPHE,
			GLFW.GLFW_KEY_COMMA,
			GLFW.GLFW_KEY_MINUS,
			GLFW.GLFW_KEY_PERIOD,
			GLFW.GLFW_KEY_SLASH,
			GLFW.GLFW_KEY_0,
			GLFW.GLFW_KEY_1,
			GLFW.GLFW_KEY_2,
			GLFW.GLFW_KEY_3,
			GLFW.GLFW_KEY_4,
			GLFW.GLFW_KEY_5,
			GLFW.GLFW_KEY_6,
			GLFW.GLFW_KEY_7,
			GLFW.GLFW_KEY_8,
			GLFW.GLFW_KEY_9,
			GLFW.GLFW_KEY_SEMICOLON,
			GLFW.GLFW_KEY_EQUAL,
			GLFW.GLFW_KEY_A,
			GLFW.GLFW_KEY_B,
			GLFW.GLFW_KEY_C,
			GLFW.GLFW_KEY_D,
			GLFW.GLFW_KEY_E,
			GLFW.GLFW_KEY_F,
			GLFW.GLFW_KEY_G,
			GLFW.GLFW_KEY_H,
			GLFW.GLFW_KEY_I,
			GLFW.GLFW_KEY_J,
			GLFW.GLFW_KEY_K,
			GLFW.GLFW_KEY_L,
			GLFW.GLFW_KEY_M,
			GLFW.GLFW_KEY_N,
			GLFW.GLFW_KEY_O,
			GLFW.GLFW_KEY_P,
			GLFW.GLFW_KEY_Q,
			GLFW.GLFW_KEY_R,
			GLFW.GLFW_KEY_S,
			GLFW.GLFW_KEY_T,
			GLFW.GLFW_KEY_U,
			GLFW.GLFW_KEY_V,
			GLFW.GLFW_KEY_W,
			GLFW.GLFW_KEY_X,
			GLFW.GLFW_KEY_Y,
			GLFW.GLFW_KEY_Z,
			GLFW.GLFW_KEY_LEFT_BRACKET,
			GLFW.GLFW_KEY_BACKSLASH,
			GLFW.GLFW_KEY_RIGHT_BRACKET,
			GLFW.GLFW_KEY_GRAVE_ACCENT,
			GLFW.GLFW_KEY_WORLD_1,
			GLFW.GLFW_KEY_WORLD_2,
			GLFW.GLFW_KEY_ESCAPE,
			GLFW.GLFW_KEY_ENTER,
			GLFW.GLFW_KEY_TAB,
			GLFW.GLFW_KEY_BACKSPACE,
			GLFW.GLFW_KEY_INSERT,
			GLFW.GLFW_KEY_DELETE,
			GLFW.GLFW_KEY_RIGHT,
			GLFW.GLFW_KEY_LEFT,
			GLFW.GLFW_KEY_DOWN,
			GLFW.GLFW_KEY_UP,
			GLFW.GLFW_KEY_PAGE_UP,
			GLFW.GLFW_KEY_PAGE_DOWN,
			GLFW.GLFW_KEY_HOME,
			GLFW.GLFW_KEY_END,
			GLFW.GLFW_KEY_CAPS_LOCK,
			GLFW.GLFW_KEY_SCROLL_LOCK,
			GLFW.GLFW_KEY_NUM_LOCK,
			GLFW.GLFW_KEY_PRINT_SCREEN,
			GLFW.GLFW_KEY_PAUSE,
			GLFW.GLFW_KEY_F1,
			GLFW.GLFW_KEY_F2,
			GLFW.GLFW_KEY_F3,
			GLFW.GLFW_KEY_F4,
			GLFW.GLFW_KEY_F5,
			GLFW.GLFW_KEY_F6,
			GLFW.GLFW_KEY_F7,
			GLFW.GLFW_KEY_F8,
			GLFW.GLFW_KEY_F9,
			GLFW.GLFW_KEY_F10,
			GLFW.GLFW_KEY_F11,
			GLFW.GLFW_KEY_F12,
			GLFW.GLFW_KEY_F13,
			GLFW.GLFW_KEY_F14,
			GLFW.GLFW_KEY_F15,
			GLFW.GLFW_KEY_F16,
			GLFW.GLFW_KEY_F17,
			GLFW.GLFW_KEY_F18,
			GLFW.GLFW_KEY_F19,
			GLFW.GLFW_KEY_F20,
			GLFW.GLFW_KEY_F21,
			GLFW.GLFW_KEY_F22,
			GLFW.GLFW_KEY_F23,
			GLFW.GLFW_KEY_F24,
			GLFW.GLFW_KEY_F25,
			GLFW.GLFW_KEY_KP_0,
			GLFW.GLFW_KEY_KP_1,
			GLFW.GLFW_KEY_KP_2,
			GLFW.GLFW_KEY_KP_3,
			GLFW.GLFW_KEY_KP_4,
			GLFW.GLFW_KEY_KP_5,
			GLFW.GLFW_KEY_KP_6,
			GLFW.GLFW_KEY_KP_7,
			GLFW.GLFW_KEY_KP_8,
			GLFW.GLFW_KEY_KP_9,
			GLFW.GLFW_KEY_KP_DECIMAL,
			GLFW.GLFW_KEY_KP_DIVIDE,
			GLFW.GLFW_KEY_KP_MULTIPLY,
			GLFW.GLFW_KEY_KP_SUBTRACT,
			GLFW.GLFW_KEY_KP_ADD,
			GLFW.GLFW_KEY_KP_ENTER,
			GLFW.GLFW_KEY_KP_EQUAL,
			GLFW.GLFW_KEY_LEFT_SHIFT,
			GLFW.GLFW_KEY_LEFT_CONTROL,
			GLFW.GLFW_KEY_LEFT_ALT,
			GLFW.GLFW_KEY_LEFT_SUPER,
			GLFW.GLFW_KEY_RIGHT_SHIFT,
			GLFW.GLFW_KEY_RIGHT_CONTROL,
			GLFW.GLFW_KEY_RIGHT_ALT,
			GLFW.GLFW_KEY_RIGHT_SUPER,
			GLFW.GLFW_KEY_MENU,
			GLFW.GLFW_KEY_LAST
	};
	
	public static final boolean keyExists(int keycode) {
		return Util.arrayContainsi(GLFW_KEYS, keycode);
	}
	
	public static final String getKeyName(int keycode) {
		String result = GLFW.glfwGetKeyName(keycode, GLFW.GLFW_KEY_UNKNOWN);
		if (result == null) {
			if (keycode == GLFW.GLFW_KEY_RIGHT) {
				result = "droite";
			}
			else if (keycode == GLFW.GLFW_KEY_LEFT) {
				result = "gauche";
			}
			else if (keycode == GLFW.GLFW_KEY_UP) {
				result = "haut";
			}
			else if (keycode == GLFW.GLFW_KEY_DOWN) {
				result = "bas";
			}
			else if (keycode == GLFW.GLFW_KEY_ENTER) {
				result = "entr�e";
			}
			else if (keycode == GLFW.GLFW_KEY_SPACE) {
				result = "espace";
			}
			else if (keycode == GLFW.GLFW_KEY_ESCAPE) {
				result = "echap";
			}
			else if (keycode == GLFW.GLFW_KEY_TAB) {
				result = "tab";
			}
			else if (keycode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
				result = "shift droit";
			}
			else if (keycode == GLFW.GLFW_KEY_LEFT_SHIFT) {
				result = "shift gauche";
			}
			else {
				result = "";
			}
		}
		return result;
	}
}
