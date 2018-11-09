package net.nanoteck137.tictactoe.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.joml.Vector4f;

public class LegacyRenderer extends Renderer {

	@Override
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
