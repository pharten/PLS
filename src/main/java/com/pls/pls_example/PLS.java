package com.pls.pls_example;

public class PLS {
	private PLS_method method;
	public PLS_method getMethod(){return method;}
	private double[][] prediction;
	public double[][] getT(){return prediction;}
	private double accuracy;
	public double getAccuracy(){return accuracy;}
	private Data data;
	public Data getData(){return data;}
	public PLS(Data data){
		this.data = data;
	}
	public void run(){
		method = new PLS_method(data.xTrain, data.xTrain, 20);
		System.out.println("ABADFGASDF");
	}
}
