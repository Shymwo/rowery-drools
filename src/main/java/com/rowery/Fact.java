package com.rowery;

public class Fact {
	private String name;
	private int val;
	
	private int getVal() {
		return val;
	}
	private void setVal(int val) {
		this.val = val;
	}
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	
	public Fact(String n, int v){
		name=n;
		val=v;
		
	}
	public boolean N(String n){
		return name.equals(n);
		
		
	}
	public boolean V(int v){
		return val==v;
		
	}
	
	
	
}
