package solvingMaze;
import java.util.Scanner;
public class Node implements Comparable<Node>{
	public int x, y;
	
	public  double distance;
	
	public double northCost = 3;
	public double westCost = 4;
	public double southCost = 1;
	public double eastCost = 2;
	public double accumulated_Cost;
	
	public String s;
	
	public boolean is_forbid; 
	
	public boolean visited; 
	
	public boolean in_fringe;
	
	public Node parent;
	static Scanner in = new Scanner (System.in);

	
	public Node(int x, int y, String s, boolean f){
		this.x = x;
		this.y = y;
		this.is_forbid = f;
		this.in_fringe = false;
		this.visited = false;
		this.s =s;
		northCost = 3;
		westCost = 4;
		southCost = 1;
		eastCost = 2;
		}

	
	public Node(String s, boolean f){
		this.s = s;
		this.is_forbid = f;
	}
	
	public Node(Node queueAdder, double heurstics) {
		
		this.distance = heurstics;
	}

	public String toString () //  اینصورت مکانهای حافظه را بدست می آوریم
	{
		return "(" + this.x + "," + this.y +")" + "(" + s + ")";


	}
	
	public Node getParent(){
		return parent;
		
	}
	
	//این روش مقدار گره ای را که قبلاً ایجاد شده است ، می گیرد
	//و به ما اجازه می دهد که مقدار فاصله را قرار دهیم
	public static Node addDistance(Node changedNode, double heuristic)
	{
		changedNode.distance = heuristic;
		return changedNode;
		
	}
	
	public static Node addTotalCost(Node changedNode, Node oldNode, double cost)
	{

		changedNode.accumulated_Cost = oldNode.accumulated_Cost + cost;
		return changedNode;
		
	}
	
	public static Node addTotalCost(Node changedNode, double cost)
	{

		changedNode.accumulated_Cost =  cost;
		return changedNode;
		
	}
	

	
	public int compareTo(Node n) 
	{
        if(this.distance < n.distance)
        {
            return -1;
        }
        else if(this.distance > n.distance)
        {
            return 1;
        }
        else
            return 0;
    }
}
	

