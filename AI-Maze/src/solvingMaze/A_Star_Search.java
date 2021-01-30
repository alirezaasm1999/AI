package solvingMaze;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class A_Star_Search{


static void Start(Node Start, Node Goal, Node maze[][], int horizontal_rows, int vertical_cols)
{
	System.out.println("Starting a A* Search");
	System.out.println();
	int numOfSteps = 0;
	//این برای نگهداری مقادیر ابتکاری ابتکاری استفاده خواهد شد
	float heuristic = 0;
	
	boolean goalFound = false;
		
	Queue queue = new LinkedList();
	Queue PathtoGoal = new LinkedList();
	Queue visted = new LinkedList();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(); //این با استفاده از کلاس Node ، صف اولویت ما است

	
	
	MazeFirstStart(Start,Goal,maze,queue,numOfSteps,PathtoGoal,goalFound,visted,horizontal_rows,vertical_cols,pq);

}	


@SuppressWarnings("unchecked")
private static void MazeFirstStart(Node start,Node goal, Node maze[][], Queue queue, int numOfSteps, Queue PathtoGoal, boolean goalFound, Queue visted,int horizontal, int vertical, PriorityQueue<Node> pq) 
{
	int x = start.x;
	int y = start.y;
	int horizontal_rows = horizontal;
	int vertical_col = vertical;	  
	int xG = goal.x;
	int yG = goal.y;
	
	double heuristic_North = 0;
	double heuristic_West = 0;
	double heuristic_South = 0;
	double heuristic_East = 0;
	double complete_Cost = 0;
	
	if(x - 1 >= 0)
	{
 	 heuristic_North = Math.pow( ((x-1) - xG),2) + Math.pow(( y - yG),2); 	
	}
	else if (x - 1 < 0) 
	{
		heuristic_North =  Double.NaN;
	}

	
	if(y - 1 >= 0)
	{
 	 heuristic_West = Math.pow((x - xG),2) + Math.pow(((y-1) - yG),2); 	

	}
	else if (y - 1 < 0)
	{
		heuristic_West = Double.NaN;
	}
	
	if (x + 1 <= horizontal_rows)
	{
 	 heuristic_South = Math.pow(((x+1) - xG),2) - Math.pow(( y - yG),2);	
	}
	else  
	{
		heuristic_South = Double.NaN;
	}
	
	if (y + 1 < vertical_col) 
	{
 	 heuristic_East = Math.pow((x - xG),2) - Math.pow(((y+1) - yG),2); 	
	}
	else if (y + 1 >= vertical_col)
	{
		heuristic_East = Double.NaN;
	}
 	
	start.visited = true;
	queue.add(start);		
	pq.add(start); 			
	
	
	if (start.x - 1 >= 0) //تست شمال 
	{		
		Node test = maze[start.x-1][y];
		complete_Cost = Math.sqrt(Math.abs(heuristic_North)) + start.northCost;
		Node.addDistance(test, complete_Cost); 
		Node.addTotalCost(test, start.northCost);
		
			if(test.is_forbid == true)
				{
					System.out.println("This Northern Path is forbidden");
				}
			else 
				{
					System.out.println("Testing North: Node is now " + test);
					
					test.visited = true;
					test.parent = null;
					pq.add(test);		
				}
	}
	
	//تست غرب
	if (start.y - 1 >= 0)
	{		
		Node test = maze[x][start.y-1];
		complete_Cost = Math.sqrt(Math.abs(heuristic_West)) + start.westCost;
		Node.addDistance(test, complete_Cost);
		Node.addTotalCost(test, start.westCost);

			if(test.is_forbid == true)
			{
				System.out.println("This Western Path is forbidden");
			}
			else if (test.is_forbid == false)
			{
				System.out.println("Testing West: Node is now " + test);
				test.visited = true;
				test.parent = null;

				pq.add(test);
			}
	}
	
	//Test جنوب
	if (start.x + 1 < horizontal_rows)
	{	
		Node test = maze[start.x+1][y];
		complete_Cost = Math.sqrt(Math.abs(heuristic_South)) + start.southCost;
		Node.addDistance(test, complete_Cost); //TODO test this, seems to work
		Node.addTotalCost(test, start.southCost);

			if(test.is_forbid == true)
			{
				System.out.println("This Southern Path is forbidden");
			}
		else if (test.is_forbid == false)
			{
				System.out.println("Testing South: Node is now " + test);
				test.visited = true;
				test.parent = null;

				pq.add(test);
			}
	}
	
	 //تست شرق
if (start.y + 1 < vertical_col)
{	
	Node test = maze[x][start.y+1];
	complete_Cost = Math.sqrt(Math.abs(heuristic_East)) + start.eastCost;
	Node.addDistance(test, complete_Cost); 
	Node.addTotalCost(test, start.eastCost);



	if(test.is_forbid == true)
		{
			System.out.println("This Eastern Path is forbidden");
			System.out.println();
		}
		else if (test.is_forbid == false)
		{
			System.out.println("Testing East: Node is now " + test);
			System.out.println("");
			test.visited = true;
			test.parent = null;

			pq.add(test);
		}
		
		
}

MazeSecondCheck(start,goal,maze,queue,numOfSteps,PathtoGoal,goalFound,visted,horizontal_rows,vertical_col,pq); 
}


//چک کردن این که مقادیر درست جایگذاری شده است
private static void MazeSecondCheck(Node Start, Node Goal, Node[][] maze, Queue queue, int numOfSteps, Queue PathtoGoal, boolean goalFound,Queue visted, int horizonal, int vertical, PriorityQueue<Node> pq) {
	int horizontal_rows = horizonal;
	int vertical_col = vertical;
	
	double heurstic_North =  0; 
	double heuristic_West =  0;
	double heuristic_South = 0;
	double heuristic_East =  0;
	double complete_Cost = 	 0;
	
	Node first = (Node) pq.remove();
	
	numOfSteps++;
	
	PathtoGoal.add(first);
	
	while (!pq.isEmpty())
	{	
		if (first.x == Goal.x && first.y == Goal.y)
		{
			System.out.println("Goal was on top of start: Finished");
			goalFound = true;
			break;
		}
		
		//ما صف را باز می کنیم و گره را دریافت می کنیم.
		
		Node Crawler = (Node) pq.remove();
		//صف را جدا کنید تا مسیر ما را دنبال کنید
		PathtoGoal.add(Crawler);		
		numOfSteps++;
		
		if (Crawler == Goal)
		{
			System.out.println("WE FOUND THE GOAL " + Goal);
			goalFound = true;
			visted.add(Goal);
			
			while (Crawler.parent != null)
			{
			Crawler = Crawler.getParent();
			visted.add(Crawler);
			}
			visted.add(Start);

			break;
		}
		
		Crawler.s = "V";
		
		System.out.println("Popped Value is: " + Crawler);
		System.out.println();
		
		//اطمینان حاصل میکنیم که محرک از مرز خارج نمی شود
			if (Crawler.x - 1 >= 0)
			{		
					Node QueueAdder = maze[Crawler.x - 1][Crawler.y]; 	
					heurstic_North = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
					heurstic_North = Math.abs(Math.sqrt(heurstic_North));																						
					complete_Cost = heurstic_North + QueueAdder.northCost + Crawler.accumulated_Cost;
					
					
					Node.addTotalCost(QueueAdder,Crawler, QueueAdder.northCost);
					Node.addDistance(QueueAdder, complete_Cost);

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
			{
				System.out.println("This North Node has been visisted or is forbidden, will not visit ");
				System.out.println();
			}

			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing North Version 2: Node is now " + QueueAdder);
					System.out.println("The Total traveled cost of this node is " + complete_Cost);

				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
				System.out.println();
			}
			}
			
			// غرب
			if (Crawler.y - 1 >= 0)
			{		
				Node QueueAdder = maze[Crawler.x][Crawler.y-1];
				heuristic_West = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
				heuristic_West = Math.abs(Math.sqrt(heuristic_West));																							 
				complete_Cost = heuristic_West + QueueAdder.westCost + Crawler.accumulated_Cost;
				
				Node.addTotalCost(QueueAdder, Crawler,QueueAdder.westCost);
				Node.addDistance(QueueAdder, complete_Cost); 

				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
				{
					System.out.println("This West node has been visisted, will not revisit ");
					System.out.println();

				}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
				{
					System.out.println("Testing West Version 2: Node is now " + QueueAdder);
					
					QueueAdder.visited = true;
					QueueAdder.in_fringe = true;
					QueueAdder.parent = Crawler;
					pq.add(QueueAdder);
					System.out.println();
				}
			}	
			
			if (Crawler.x + 1 < horizontal_rows) 
			{
				Node QueueAdder = maze[Crawler.x+1][Crawler.y];
				heuristic_South = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
				heuristic_South = Math.abs(Math.sqrt(heuristic_South));																						 
				complete_Cost = heuristic_South + QueueAdder.southCost + Crawler.accumulated_Cost;
				
				Node.addTotalCost(QueueAdder,Crawler, QueueAdder.southCost);
				Node.addDistance(QueueAdder, complete_Cost);

			if(QueueAdder.visited == true || QueueAdder.is_forbid == true )
			{
			System.out.println("This South Node has been visisted, will not revisit ");
			System.out.println();

			}
			else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
			{
				System.out.println("Testing South Version 2 : Node is now " + QueueAdder);

				QueueAdder.visited = true;
				QueueAdder.in_fringe = true;
				QueueAdder.parent = Crawler;
				pq.add(QueueAdder);
				System.out.println();
			}
			}

			//شرق
			if (Crawler.y + 1 < (vertical_col))
				{	
		Node QueueAdder = maze[Crawler.x][Crawler.y+1];
		heuristic_East = Math.pow((QueueAdder.x - Goal.x),2) + Math.pow(( QueueAdder.y - Goal.y),2); 
		heuristic_East = Math.abs(Math.sqrt(heuristic_East));	//ABS just in case																						  //So now all we need \to do is sqrt it
		complete_Cost = heuristic_East + QueueAdder.eastCost + Crawler.accumulated_Cost;
		Node.addTotalCost(QueueAdder,Crawler, QueueAdder.eastCost);
		Node.addDistance(QueueAdder, complete_Cost);

				if(QueueAdder.visited == true || QueueAdder.is_forbid == true)
					{
						System.out.println("This East node has been visisted, will not revisit ");
						System.out.println("");
					}
				else if (QueueAdder.is_forbid == false || QueueAdder.visited == false)
					{
			System.out.println("Testing East Version 2: Node is now " + QueueAdder);

			System.out.println("");
			QueueAdder.visited = true;
			QueueAdder.in_fringe = true;
			QueueAdder.parent = Crawler;
			pq.add(QueueAdder);
			System.out.println();

	if (Crawler.y + 1 >= (horizontal_rows-1))
	{
		System.out.println();
	}


		}
	}
		}

	successor(queue,PathtoGoal,visted,goalFound,numOfSteps,maze);

}
private static void successor(Queue queue, Queue PathtoGoal, Queue visted, boolean goalFound, int numOfSteps, Node maze[][]) {
	if(queue.isEmpty() && goalFound == false)
	{
		System.out.println("Could not find or access the Goal");
	}
	 
	 System.out.println("Number of Steps taken " + numOfSteps);
	 System.out.println("Our A* path is ");
	 System.out.print(PathtoGoal + "");

	 for (Node[] z : maze) 	
	 	{
		for (Node B : z) 
			{ 
			System.out.print(B + "\t"); 
			} 
		
		System.out.println("\n"); 
	 	} 
	}
}

