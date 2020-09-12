package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RainfallReport {
	
	public List<AnnualRainfall> generateRainfallReport(String filePath) throws IOException, ClassNotFoundException, SQLException {
		BufferedReader bread = new BufferedReader(new FileReader(filePath));
		List<AnnualRainfall> list = new LinkedList<AnnualRainfall>();
			String line;
			while((line=bread.readLine())!=null) {
				try {
					String[] str = line.split(",");
					if(validate(str[0])) {
						AnnualRainfall ar = new AnnualRainfall();
						String[] avg = new String[str.length-2];
						avg = Arrays.copyOfRange(str, 2, str.length-1);
						double[] avg1 = new double[avg.length];
						for(int i=0;i<avg1.length;i++) {
							avg1[i] = Double.parseDouble(avg[i]);
						}
						ar.calculateAverageAnnualRainfall(avg1);
						ar.setCityPincode(Integer.parseInt(str[0]));
						ar.setCityName(str[1]);
						ar.setAverageAnnualRainfall(ar.getAverageAnnualRainfall());
						list.add(ar);
					}
				}catch(InvalidCityPincodeException e) {
					System.out.println("Invalid city pincode");
				}
				continue;
			}
		return list;
	}
	
	public boolean validate(String cityPincode) throws InvalidCityPincodeException {
		String regex = "[0-9]{5}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cityPincode);
		if(m.matches()) {
			return true;
		}else throw new InvalidCityPincodeException();
	}
	
	public List<AnnualRainfall> findMaximumRainfallCities () throws ClassNotFoundException, SQLException, IOException {
		DBHandler db = new DBHandler();
		List<AnnualRainfall> list = new LinkedList<AnnualRainfall>();
		Connection con = db.establishConnection();
		PreparedStatement ps = con.prepareStatement("select * from AnnualRainfall where average_annual_rainfall = (select max(average_annual_rainfall) from AnnualRainfall)");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			AnnualRainfall ar = new AnnualRainfall();
			ar.setCityPincode(rs.getInt(1));
			ar.setCityName(rs.getString(2));
			ar.setAverageAnnualRainfall(rs.getDouble(3));
			list.add(ar);
		}
		return list;
	}
	
}
