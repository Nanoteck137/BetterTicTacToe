package net.nanoteck137.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.lang.Math;

import org.joml.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.*;

public class Program {
	
	private Window window;
	private Renderer renderer;
	
	private Board board;
	
	private boolean gameOver = false;
	private int playerWon = -1;
	
	public Program() {
		init();
		loop();
	}
	
	private void init() {
		window = new Window("Hello World", 800, 800);
		renderer = new Renderer();
		
		board = new Board();
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

	        if(board.needReset()) {
	            gameOver = true;
	            playerWon = board.getPlayerWon();
	        }

	        if(!gameOver)
			    board.update(matrix, window.getInput());

            board.render(renderer);
			
			if(gameOver) {
				if(window.getInput().isButtonPressed(0)) {
					board = new Board();
					gameOver = false;
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
