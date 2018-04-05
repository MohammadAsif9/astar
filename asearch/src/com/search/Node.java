package com.search;

import java.util.ArrayList;
import java.util.Arrays;

class Node {

    private Node parent;
    private ArrayList<Node> neighbors;
    private int x;
    private int y;
    private int f;
    private int g;
    private int h;
    private boolean wall;
    
    private Object data;
    private String symbol;

    public Node() {
        neighbors = new ArrayList<Node>();
        data = new Object();
    }

    public Node(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Node(Node parent) {
        this();
        this.parent = parent;
    }

    public Node(Node parent, int x, int y) {
        this();
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public Node(int x, int y, String symbol) {
    	this.symbol = symbol;
		this.x = x;
		this.y = y;
		neighbors = new ArrayList<Node>();
        data = new Object();
	}

	public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(Node node) {
        this.neighbors.add(node);
    }

//    public void addNeighbors(Node... node) {
//        this.neighbors.addAll(Arrays.asList(node));
//    }
    
    public void addNeighbors(ArrayList<Node> nodes) {
        this.neighbors.addAll(nodes);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
    public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
     
	
	

	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}
	
	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean equals(Node n) {
        return this.x == n.x && this.y == n.y;
    }
	
	@Override
	public String toString() {
		//return "Node [symbol=" + symbol + ", x=" + x + ", y=" + y + "]";
		return symbol + " ";
	}
}