package net.nanoteck137.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

public class Program {
	
	private long window;
	
	private Input input;
	
	public Program() {		
		init();
		loop();
	}
	
	private void init() {
		if(!glfwInit()) {
			System.err.println("Error initalizing GLFW");
			System.exit(-1);
		}
		
		window = glfwCreateWindow(800, 800, "Hello World", 0, 0);
		if(window == NULL) {
			System.err.println("Error creating the window");
			System.exit(-1);
		}
		
		input = new Input();
		
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				input.setKeyState(key, action != GLFW_RELEASE);
			}
		});
		
		glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				input.setButtonState(button, action != GLFW_RELEASE);
			}
		});
		
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}
		
		glfwMakeContextCurrent(window);
		
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

			if(input.isButtonPressed(0)) {
				System.out.println("Hello World");
			}
			
			glBegin(GL_LINE);
			
			glVertex2f(0, 0);
			glVertex2f(0, 3);
			
			glEnd();
			
			glfwSwapBuffers(window);
			
			input.update();
			
			if(glfwWindowShouldClose(window)) {
				running = false;
			}
		}
	}
	
	public static void main(String[] args) {
		new Program();
	}
}
