package com;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RainfallMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		RainfallReport report = new RainfallReport();
		List<AnnualRainfall> list = report.generateRainfallReport("AllCityMonthlyRainfall.txt");
        list.forEach(a->System.out.println(a.getCityPincode()+" "+a.getCityName()+" "+a.getAverageAnnualRainfall()));
        System.out.println();
        System.out.println("Max rainfall cities are");
        List<AnnualRainfall> list1 = report.findMaximumRainfallCities();
        list1.forEach(a->System.out.println(a.getCityPincode()+" "+a.getCityName()));
 	}

}
