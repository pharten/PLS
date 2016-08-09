package com.pls.pls_example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.nd4j.linalg.api.complex.IComplexNDArray;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

import org.nd4j.linalg.eigen.Eigen;

public class helpers {
	
	public static double MACHEPS = 2E-16;
	public static Matrix pinv(Matrix x) {
	    if (x.rank() < 1) {return null;}
	    if (x.getColumnDimension() > x.getRowDimension()){ return pinv(x.transpose()).transpose();}
	    SingularValueDecomposition svdX = new SingularValueDecomposition(x);
	    double[] singularValues = svdX.getSingularValues();
	    double tol = Math.max(x.getColumnDimension(),
	    x.getRowDimension()) * singularValues[0] * MACHEPS;
	    double[] singularValueRecip = new double[singularValues.length];
	    for (int i = 0; i < singularValues.length; i++){
	        singularValueRecip[i] = Math.abs(singularValues[i]) < tol ? 0 : (1.0 / singularValues[i]);
	    }
	    double[][] u = svdX.getU().getArray();
	    double[][] v = svdX.getV().getArray();
	    int min = Math.min(x.getColumnDimension(), u[0].length);
	    double[][] inverse = new double[x.getColumnDimension()][x.getRowDimension()];
	    for (int i = 0; i < x.getColumnDimension(); i++){
	        for (int j = 0; j < u.length; j++){
	            for (int k = 0; k < min; k++){
	                inverse[i][j] += v[i][k] * singularValueRecip[k] * u[j][k];
	            }
	        }
	    }
	    return new Matrix(inverse);
	}
	
	public static double[][] readCSV(String path){
		List<String[]> rowList = new ArrayList<String[]>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineItems = line.split(",");
				rowList.add(lineItems);
			}
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String[][] matrix = new String[rowList.size()][];
		for (int i = 0; i < rowList.size(); i++) {
			String[] row = rowList.get(i);
			matrix[i] = row;
		}
		double[][] tableDouble = new double[matrix.length][matrix[0].length];
		for(int i=0; i<matrix.length; i++) {
		    for(int j=0; j<matrix[0].length; j++) {
		        tableDouble[i][j]= Double.parseDouble(matrix[i][j]);
		    }
		}
		return tableDouble;
	}
	
	
	
}
