package net.nanoteck137.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

public class Program {
	
	private Window window;
	
	public Program() {		
		init();
		loop();
	}
	
	private void init() {
		window = new Window("Hello World", 800, 800);
	}
	
	private void loop() {
		GL.createCapabilities();

		glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		glOrtho(-3.0f, 3.0f, -3.0f, 3.0f, -1.0f, 1.0f);
		
		boolean running = true;
		while(running) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			if(window.getInput().isKeyPressed(GLFW_KEY_W)) {
				System.out.println("Hello World");
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
