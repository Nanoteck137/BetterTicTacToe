package net.nanoteck137.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.lang.Math;

import org.joml.*;
import org.lwjgl.opengl.*;

public class Program {
	
	private Window window;
	private Renderer renderer;
	
	private Vector4f[] cells = new Vector4f[] {
		new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
		new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
		new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
	};
	
	public Program() {
		init();
		loop();
	}
	
	private void init() {
		window = new Window("Hello World", 800, 800);
		renderer = new Renderer();
	}
	
	private void loop() {
		GL.createCapabilities();

		Matrix4f matrix = new Matrix4f().ortho(0.0f, 9.0f, 0.0f, 9.0f, -1.0f, 1.0f);
		Vector3f v = matrix.transformProject(new Vector3f(400, 400, 0));
		
		glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		
		float[] matrixArr = new float[16];
		matrix.get(matrixArr);
		
		glLoadMatrixf(matrixArr);
		//glOrtho(-3.0f, 3.0f, -3.0f, 3.0f, -1.0f, 1.0f);
		
		boolean running = true;
		while(running) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			if(window.getInput().isButtonPressed(0)) {
				Vector2f mousePos = window.getInput().getMousePosition();
				
				Vector3f test = new Vector3f(mousePos.x, 800 -mousePos.y, 0.0f);
				Vector3f test2 = new Vector3f();
				matrix.unproject(test, new int[] { 0, 0, 800, 800 }, test2);
				
				int x = (int)Math.floor(test2.x) / 3;
				int y = (int)Math.floor(test2.y) / 3;
				
				cells[x + y * 3] = new Vector4f(0.0f, 1.0f, 0.0f, 1.0f);
			}
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					renderer.renderRect(i * 3.0f + 0.05f, j * 3.0f + 0.05f, 2.9f, 2.9f, cells[i + j * 3]);
				}
			}
			
			window.update();
			
			if(window.closed()) {
				running = false;
			}
		}
	}
	
	public static void main(String[] args) {
		new Program();
	}
}
