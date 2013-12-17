package com.rowery;

public class Fact {
	private String name;
	private int val;
	
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Fact(String n, int v){
		name=n;
		val=v;
		
	}
	public boolean name(String n){
		return name.equals(n);
		
		
	}

	
	
}
