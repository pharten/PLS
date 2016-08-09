package com.pls.pls_example;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.nd4j.linalg.api.complex.IComplexNDArray;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import com.pls.pls_example.helpers;
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
    	double[][] csv = helpers.readCSV(filePath);
    	Data data = new Data(csv);
    	
    	PLS pls = new PLS(data);
    	pls.run();
    	
    	System.out.println("done");
    }
}
