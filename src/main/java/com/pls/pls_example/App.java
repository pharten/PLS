package com.pls.pls_example;

import java.io.File;

import Jama.Matrix;

import static com.pls.pls_example.helpers.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String filePath = new File("").getAbsolutePath();
		filePath = filePath.substring(0, filePath.length() - 3);
		filePath += "PLS\\data\\housing.csv";
    	double[][] csv = readCSV(filePath);
    	Data data = new Data(csv);
    	
//    	Matrix Dmat = new Matrix(csv);
//    	int rank = Dmat.svd().rank();
    	
    	PLS_method method = new PLS_method(data.xTrain, data.yTrain, 20);
    	double [][] yPrediction = predict(method, data.xTest);
    	try {
			reportAccuracy(data.yTest, yPrediction, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	System.out.println("done");
    }
}
