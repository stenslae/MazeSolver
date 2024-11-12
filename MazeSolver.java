import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver {
	private char[][] maze = new char[12][12];
	
	public MazeSolver() {
		//make input the file name of your maze:
		String input = "maze.txt";
		this.maze = loadMaze(input);
		//from here actually start doing solve/print/solve/print etc.
		printMaze(maze);
	}
	
	//will load the maze file into maze[][]
	public char[][] loadMaze(String filename){
		File file = new File(filename);
		try {
		Scanner scan = new Scanner(file);
		
		//iterate through maze and add input from file
		for(int i=0; i<12; i++) {
			String input = scan.nextLine();
			for(int j=0; j<12; j++) {
				maze[i][j]= input.charAt(j);
			}
		}
		
		scan.close();
		}catch (FileNotFoundException e) {
			System.out.println("Error, file not found.");
			e.printStackTrace();
			return maze;
		}
		return maze;
	}
	
	//prints the maze[][]
	private void printMaze(char[][] maze2) {
		for(int i=0; i<12; i++) {
			for(int j=0; j<12; j++) {
				System.out.print(maze2[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public void solveMaze() {
		// starting parameters for "maze.txt"
		// If you are using a different maze, you will need to change these four values
		int start_x  = 0;
		int start_y = 2;
		
		int start_hand_x = 0;
		int start_hand_y =3;
		
		make_move(start_x, start_y, start_hand_x, start_hand_y);
		return;
		
	}
	
	//makes a single move in the maze
	public void make_move(int x, int y, int hand_x, int hand_y) {
		//declare variables
		int forward_x=x;
		int forward_y=y;
		int forward_hand_x=hand_x;
		int forward_hand_y=hand_y;
		int right_hand_x=hand_x;
		int right_hand_y=hand_y;
		int right_forward_x=x;
		int right_forward_y=y;
		int left_hand_x=hand_x;
		int left_hand_y=hand_y;
		
		//print most recently made move
		maze[y][x]='X';
		printMaze(maze);
		
		//Determine direction
		//facing north:
		if(hand_x-1==x && hand_y==y) {
			//determine forward block char using input methods
			forward_x= x;
			forward_y= y-1;
			forward_hand_x = hand_x;
			forward_hand_y = hand_y-1;
			//determine right block char using direction
			right_hand_x = hand_x;
			right_hand_y = hand_y+1;
			right_forward_x=x+1;
			right_forward_y=y;
			//determine hand coords when turning left
			left_hand_x = hand_x-1;
			left_hand_y = hand_y-1;
		}
		//facing south:
		else if(hand_x+1==x && hand_y==y){
			//determine forward block char using input methods
			forward_x= x;
			forward_y= y+1;
			forward_hand_x = hand_x;
			forward_hand_y = hand_y+1;
			//determine right block char using direction
			right_hand_x = hand_x;
			right_hand_y = hand_y-1;
			right_forward_x=x-1;
			right_forward_y=y;
			//determine hand coords when turning left
			left_hand_x = hand_x+1;
			left_hand_y = hand_y+1;
		}
		//facing west:
		else if(hand_y+1==y && hand_x==x) {
			//determine forward block char using input methods
			forward_x= x-1;
			forward_y= y;
			forward_hand_x = hand_x-1;
			forward_hand_y = hand_y;
			//determine right block char using direction
			right_hand_x = hand_x+1;
			right_hand_y = hand_y;
			right_forward_x=x;
			right_forward_y=y-1;
			//determine hand coords when turning left
			left_hand_x = hand_x-1;
			left_hand_y = hand_y+1;
		}
		//facing east:
		else if(hand_y-1==y && hand_x==x){
			//determine forward block char using input methods
			forward_x= x+1;
			forward_y= y;
			forward_hand_x = hand_x+1;
			forward_hand_y = hand_y;
			//determine right block char using direction
			right_hand_x = hand_x-1;
			right_hand_y = hand_y;
			right_forward_x=x;
			right_forward_y=y+1;
			//determine hand coords when turning left
			left_hand_x = hand_x+1;
			left_hand_y = hand_y-1;
		}
		else {
			System.out.println("Error!");
		}
		
		//ENDING CONDITION
		if(maze[forward_y][forward_x]=='F') {
			System.out.println("Exit found! Program ending...");
			return;
		}
		//if theres empty space right, turn right and go forward
		else if(maze[hand_y][hand_x]=='.' || maze[hand_y][hand_x]=='X') {
			//System.out.println("Turning right");
			make_move(right_forward_x, right_forward_y, right_hand_x, right_hand_y);
			return;
		}
		//if the forward direction isn't a hash tag, move forward
		else if(maze[forward_y][forward_x]=='.' || maze[forward_y][forward_x]=='X') {
			//System.out.println("Going forward");
			make_move(forward_x, forward_y, forward_hand_x, forward_hand_y);
			return;
		}
		//otherwise, turn left
		else {
			//System.out.println("Turning left");
			make_move(x,y,left_hand_x, left_hand_y);
			return;
		}
	}
	
	
}