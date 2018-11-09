package net.nanoteck137.tictactoe.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.joml.*;

public abstract class Renderer {

	protected Vector4f clearColor;
	
	public Renderer() {
		clearColor = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public void clear() {
		glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public abstract void renderRect(float x, float y, float width, float height, Vector4f color);
	
	public void setClearColor(Vector4f clearColor) {
		this.clearColor = clearColor;
	}
	
	public Vector4f getClearColor() {
		return clearColor;
	}
	
}
