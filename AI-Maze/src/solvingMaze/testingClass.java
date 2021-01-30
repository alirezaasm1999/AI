package solvingMaze;

import java.util.Scanner;

public class testingClass {
		
	 static int vertical_col = 0;	
	 static int horizontal_rows = 0;	
	 public static int numOfTraps; //این باعث می شود که (0،0) در هر مقداری بالاتر از آنچه که ما داریم x باشد
	 public static int rowTrap;
	 public static int colTrap;
	 	 
	 public static int start_row;
	 public static int start_col;
	 


	 static int end_row;		//ردیف هدف ما را تعیین می کند
	 static int end_col;		//ستون هدف ما را تعیین می کند
	 
	 public static String searchHolder;	//مطمئن شوید که اسکنر فقط اولین کاراکتر را به دست می آورد
	 public static String searchMethod;	//این جستجوگر تجزیه شده ما است
	 
	 static Scanner in = new Scanner (System.in);
	 
	 
	public static void main (String [] args)
	{
		System.out.println("Running from TestingClass.java");
		setup_maze();
		print_maze();

	}
	public  static void setup_maze() 
	{
		

		System.out.println("Please enter number of horizontal rows");
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		 horizontal_rows = in.nextInt();
		
		System.out.println("Please enter number of vertical columns");
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		vertical_col = in.nextInt();	
		System.out.println("Please enter the number of obstacles you would like to place ");
		numOfTraps = in.nextInt();
		
		
		
		System.out.println("Please enter starting row within 0 to " + (horizontal_rows - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		start_row = in.nextInt();	
		
		System.out.println("Please enter starting column within 0 to " + (vertical_col -1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		start_col = in.nextInt();	
		
		System.out.println("Please enter ending row within 0 to " + (horizontal_rows - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		end_row = in.nextInt();
              
		
		System.out.println("Please enter ending column within 0 to" + (vertical_col - 1));
		while(!in.hasNextInt()) {
			System.out.println("Please enter a valid Int");
		    in.next();
		}
		end_col = in.nextInt();
                
		
	}
	
	static void print_maze() 
	 {
	 	//این مقادیر پیچ و خم را تنظیم می کند
//Maze بر اساس سطر و سپس ستون تنظیم می شود			
	 	
	 		 int[] trap_Rows=new int [numOfTraps];
	 		 int[] trap_cols=new int [numOfTraps];
	 		 
	 		 int testTraps = numOfTraps;
	 			int rowTrap;
	 			int colTrap;
	 			for (int i = 0; i < testTraps; i++)
	 			{
	 				
	 				System.out.println("Please Enter Row of trap " + i);
	 				rowTrap = in.nextInt();
	 				trap_Rows[i] = rowTrap;
	 				System.out.println("Please Enter Col of trap " + i);
	 				colTrap = in.nextInt();
	 				trap_cols[i] = colTrap;
	 			}
	 		 
	 		Node [][] maze = new Node[horizontal_rows][vertical_col];
	 	    Node Start = new Node (start_row,start_col,"S",false); 
	 		Node Goal = new Node (end_row,end_col,"G",false);
	 		
	 		int zs = 0;
	 		
	 		for ( int i = 0; i < maze.length; i++) //این ردیف ها را افزایش می دهد
	 		{ 
	 			for (  int j = 0; j < maze[i].length; j++) 
	 				{	
	 					Node Empty = new Node (i,j,"0",false);
	 					maze [i][j] = Empty;
	 					
	 					for ( int jk = 0; jk < trap_Rows.length; jk++)	
	 					{
	 						if (maze[i][j] == maze[trap_Rows[jk]][trap_cols[jk]]) 
	 						{
	 						Node trap = new Node (i,j,"X",true);
	 						maze[i][j] = trap;
	 						
	 						}
	 					}
	 				}	
	 		}
	 		
	 		maze[start_row][start_col] = Start;
	 		maze[end_row][end_col] = Goal;
	 	
	 		for (Node[] z : maze) 
	 		{ 	
	 			for (Node x : z) 
	 				{ 
	 					System.out.print(x + "\t"); 
	 				} 
	 			System.out.println("\n"); 
	 		} 
	 	



	 searchMethod = "4";


	 	A_Star_Search.Start(Start,Goal,maze,horizontal_rows,vertical_col);	

	 }


}
