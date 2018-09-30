package net.nanoteck137.tictactoe;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import net.nanoteck137.tictactoe.app.*;
import net.nanoteck137.tictactoe.game.*;
import net.nanoteck137.tictactoe.graphics.*;

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
		window = new Window("TicTacToe", 800, 800);
		renderer = new Renderer();
		
		board = new Board();
	}
	
	private void loop() {
		GL.createCapabilities();

		Matrix4f matrix = new Matrix4f().ortho(0.0f, 9.0f, 0.0f, 9.0f, -1.0f, 1.0f);
		
		glMatrixMode(GL_PROJECTION_MATRIX);
		glLoadIdentity();
		
		float[] matrixArr = new float[16];
		matrix.get(matrixArr);
		
		glLoadMatrixf(matrixArr);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		boolean running = true;
		while(running) {
			glfwPollEvents();
			
			if(!gameOver) {
				if(board.getCurrentPlayer() == 0) {
					renderer.setClearColor(new Vector4f(0.7f, 0.1f, 0.1f, 0.0f));
				} else if(board.getCurrentPlayer() == 1) {
					renderer.setClearColor(new Vector4f(0.1f, 0.1f, 0.7f, 0.0f));
				}
			}
			
			renderer.clear();

	        if(board.needReset()) {
	            gameOver = true;
	            playerWon = board.getPlayerWon();
	            renderer.setClearColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
	        }

	        if(!gameOver) {
			    board.update(matrix, window.getInput());
	        }

        	board.render(renderer);
			
			if(gameOver) {
				if(playerWon == -2) {
					System.out.println("Nobody won");
				}
				else {
					System.out.println("Player " + (playerWon + 1) + " won the game");
				}
				
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
