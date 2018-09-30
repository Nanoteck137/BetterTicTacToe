package net.nanoteck137.tictactoe.app;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;

import java.nio.*;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

public class Window {

	private long window;
	
	private int width;
	private int height;
	
	private Input input;
	
	public Window(String title, int width, int height) {
		this.width = width;
		this.height = height;
		
		if(!glfwInit()) {
			System.err.println("Error initalizing GLFW");
			System.exit(-1);
		}
		
		input = new Input();
		
		glfwWindowHint(GLFW_RESIZABLE, 0);
		window = glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {
			System.err.println("Error creating the window");
			System.exit(-1);
		}
		
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
		
		glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
			public void invoke(long window, double x, double y) {
				input.setMousePosition((float)x, (float)y);
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
	
	public void update() {

		glfwSwapBuffers(window);
		input.update();
	}
	
	public boolean closed() {
		return glfwWindowShouldClose(window);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Input getInput() {
		return input;
	}
	
}
