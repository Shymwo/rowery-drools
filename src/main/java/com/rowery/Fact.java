package com.rowery;

public class Fact {
	private String name;
	private String val;

	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Fact(String n, String v){
		name=n;
		val=v;

	}
	public boolean name(String n){
		return name.equals(n);
	}

}