package net.nanoteck137.game;

import org.joml.*;
import org.lwjgl.glfw.*;

public class Input {

	private boolean[] keys;
	private boolean[] prevKeys;
	
	private boolean[] buttons;
	private boolean[] prevButtons;
	
	private Vector2f mousePosition;
	
	public Input() {
		keys = new boolean[1024];
		prevKeys = new boolean[1024];
		
		buttons = new boolean[8];
		prevButtons = new boolean[8];
		
		mousePosition = new Vector2f(0.0f, 0.0f);
	}
	
	public void update() {
		for(int i = 0; i < keys.length; i++) {
			prevKeys[i] = keys[i];
		}
		
		for(int i = 0; i < buttons.length; i++) {
			prevButtons[i] = buttons[i];
		}
	}
	
	public boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public boolean isKeyPressed(int key) {
		return keys[key] && !prevKeys[key];
	}
	
	public boolean isButtonDown(int key) {
		return buttons[key];
	}
	
	public boolean isButtonPressed(int button) {
		return buttons[button] && !prevButtons[button];
	}
	
	public void setKeyState(int key, boolean state) {
		keys[key] = state;
	}
	
	public void setButtonState(int button, boolean state) {
		buttons[button] = state;
	}
	
	public void setMousePosition(float x, float y) {
		mousePosition.x = x;
		mousePosition.y = y;
	}
	
	public Vector2f getMousePosition() {
		return mousePosition;
	}
	
}
