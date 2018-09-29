package net.nanoteck137.game;

import static org.lwjgl.opengl.GL11.*;

import org.joml.*;

public class Renderer {

	public Renderer() {
	
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
	
}
