package com.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * ShortestRootFinder using A star Algorithm
 * 
 * @author Asif Laptop
 *
 */
public class ShortestRootFinder {

    private PriorityQueue<Node> openList;
    private ArrayList<Node> closedList;
    private int initialCapacity 		= 100;
    private int DISTANC_EBETWEEN_NODES 	= 1;
    public static Node[][] inputMap;
    public static int ROWS = 10;
    public static int COLS = 20;
    static Node START, GOAL;
    static ArrayList<ArrayList> mapList;

    public ShortestRootFinder() 
    {
        openList = new PriorityQueue<Node>(initialCapacity, new ShortComparator());
        closedList = new ArrayList<Node>();
    }

    public static void main(String[] args) {
    	
    	
    	
    	File f = new File("D:/SharedFeedLocation/FileFeed.txt");
		
        try
        {
        	int i = 0;
            ArrayList<ArrayList> mapList = get_arraylistMap_from_file(f);
           
            inputMap 	= new Node[10][20];
            int nodeNum = 0;
            for(int x = 0; x < mapList.size(); x++)
            {
            	ArrayList<Node> lineArray = mapList.get(x);
            //	System.out.println("lineArray.size()" +lineArray.size());
            	
            	StringBuilder sb =  new StringBuilder();
            	
            	 for(int y = 0; y < lineArray.size(); y++)
            	 {
            		 inputMap[x][y] = lineArray.get(y);
            		 inputMap[x][y].setData("n-"+ ++nodeNum + "["+x+ ","+y+"]");
            		 
            		 if(inputMap[x][y].getSymbol().equalsIgnoreCase("S"))
            		 {
            			 START = inputMap[x][y];
            		 }
            		 
            		 if(inputMap[x][y].getSymbol().equalsIgnoreCase("E"))
            		 {
            			 GOAL = inputMap[x][y];
            		 }
            		 
            		 if(inputMap[x][y].getSymbol().equalsIgnoreCase("W"))
            		 {
            			inputMap[x][y].setWall(true);
            		 }
            		
            		sb.append(lineArray.get(y));
            	 }
            	 System.out.println(sb.toString());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
       // Node[] n 	= new Node[10];
    	
    	//inputMap 	= new Node[ROWS][COLS];
//        for (int i = 0; i < n.length; i++) {
//            n[i] = new Node();
//            n[i].setData("n-" + i);
//        }
    	 
//    	int nodeNum = 0;
//    	 for (int i = 0; i < ROWS; i++) 
//    	 {
//    		 for (int j = 0; j < COLS; j++)
//    		 {
//    			 Node n = new Node();
//    			 n.setData("n-"+ ++nodeNum + "["+i+ ","+j+"]");
//    			 n.setXY(i, j);
//    			 inputMap[i][j] = n;
//                 
//             }
//    	  }
    	 
    	 //add neighbors
    	 for (int i = 0; i < ROWS; i++) 
    	 {
    		 for (int j = 0; j < COLS; j++)
    		 {
    			 
    			 inputMap[i][j].addNeighbors(addNeighbors(inputMap, i, j));
                 
             }
    	  }
    	 
    	
    	 
    	 

        new ShortestRootFinder().traverse(START, GOAL);
       
        
     //print result
   	 for (int i = 0; i < ROWS; i++) 
   	 {
   		 for (int j = 0; j < COLS ; j++)
   		 {
   			 System.out.print(inputMap[i][j].getSymbol()+ " ");
              
          }
   		 System.out.println("");
   	  }
        
    }

    public void traverse(Node start, Node end) {
        openList.clear();
        closedList.clear();
        
        start.setG(0);
        start.setH(heuristic(start, GOAL));

        //gVals.put(start, 0);
        openList.add(start);

        while (!openList.isEmpty()) 
        {
            Node current = openList.element();
            
            if (current.equals(end)) 
            {
                System.out.println("Done!!!");
                printPath(current);
                return;
            }
            
            // Remove from OpenList and Add into CloseList
            closedList.add(openList.poll());
            ArrayList<Node> neighbors = current.getNeighbors();

            for (Node neighbor : neighbors) 
            {
                if(!closedList.contains(neighbor) && !neighbor.isWall())
                {
                	int tempG = current.getG() + DISTANC_EBETWEEN_NODES;
                	if(openList.contains((neighbor)))
        			{
                		if(tempG < neighbor.getG())                		
                		{
                			neighbor.setG(tempG);
                		} 
        			}
                	else
                	{
                		neighbor.setG(tempG);
                		openList.add(neighbor);
                	}
                	
                	neighbor.setH(heuristic(neighbor, GOAL));
                	neighbor.setF(neighbor.getG() + neighbor.getH());
                	neighbor.setParent(current);
                }
            }
        }
        System.out.println("FAIL");
    }

    private int heuristic(Node node, Node goal) {
        int x = node.getX() - goal.getX();
        int y = node.getY() - goal.getY();
        //return x * x + y * y;
        return  Math.abs(x) + Math.abs(y);
    }

    private void printPath(Node node) {
        //System.out.println(node.getData());
        //node.setSymbol("o");
        while (node.getParent() != null) {
            node = node.getParent();
            node.setSymbol("o");
           // System.out.println(node.getData());
        }
        node.setSymbol("S");
    }

    static ArrayList<Node> addNeighbors(Node[][] inputMap, int i, int j)
    {
    	ArrayList<Node> neighbors =  new ArrayList<Node>();
    	
   			 if(j < COLS-1)
   			 {
   				    // neighbors.add(inputMap[i+1][j]);
   				   neighbors.add(inputMap[i][(j+1)]);
   				 
   			 }
   			 
   			if(j > 0)
  			 {
  				//neighbors.add(inputMap[i-1][j]);
   				neighbors.add(inputMap[i][(j-1)]);
  				 
  			 }
   			
   			if(i < ROWS -1)
 			 {
 				 //neighbors.add(inputMap[i][j+1]);
   				neighbors.add(inputMap[(i+1)][j]);
 				 
 			 }
   			
   			if(i > 0)
			 {
				//neighbors.add(inputMap[i][j-1]);
   				neighbors.add(inputMap[(i-1)][j]);
				 
			 }
   			 
   			 return neighbors;
   			
   			 //inputMap[i][j].setNeighbors(neighbors);               
           
   	  
    }
    
    public static ArrayList<ArrayList> get_arraylistMap_from_file(File f) 
            throws FileNotFoundException {
            Scanner s;
            ArrayList<ArrayList> sampleMap  = new ArrayList<ArrayList>();
            ArrayList<Node> lineArrayList;
            s = new Scanner(f);
            s.useDelimiter("\n");
            int row = 0;
            while (s.hasNext()) 
            {
            	String nextLine  = s.next();
            	//char[] charArray = nextLine.toCharArray();
            	nextLine = nextLine.substring(0, nextLine.length()-1);
            	 String[] tokens = nextLine.split(" ");
            	lineArrayList = new ArrayList<Node>();
            //	System.out.println(el);
            	 
            	 for (int i = 0; i < tokens.length; i++)
            	 {
            		 Node n = new Node(row, i, tokens[i]);
            		 lineArrayList.add(n);
            	 }
            	 sampleMap.add(lineArrayList);
            	 row++;
            }
            s.close();
            return sampleMap;
        }
    
    class ShortComparator implements Comparator<Node> 
    {

    	 public int compare(Node o1, Node o2) 
         {
             if (o1.getF() < o2.getF()) 
             {
                 return -1;            	 
                 
             }
             else if (o1.getF() > o2.getF()) 
             {
                 return 1;                
             } 
             else 
             {
                 return 1;
            	
             }
         }
    }
}
