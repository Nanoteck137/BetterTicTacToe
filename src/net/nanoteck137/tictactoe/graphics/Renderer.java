package net.nanoteck137.tictactoe.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.joml.*;

public class Renderer {

	private Vector4f clearColor;
	
	public Renderer() {
		clearColor = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public void clear() {
		glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void renderRect(float x, float y, float width, float height, Vector4f color) {
		glBegin(GL_QUADS);
	
		glColor4f(color.x, color.y, color.z, color.w);
		glVertex2f(x, y);
		glVertex2f(x, y + height);
		glVertex2f(x + width, y + height);
		glVertex2f(x + width, y);
		
		glEnd();
	}
	
	public void setClearColor(Vector4f clearColor) {
		this.clearColor = clearColor;
	}
	
	public Vector4f getClearColor() {
		return clearColor;
	}
	
}
