package com.bridgelabz.stockmanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import jdk.internal.org.jline.reader.Parser;

@SuppressWarnings("unused")
public class StockManagement {
	public static JSONArray stockList = new JSONArray();
	public static Scanner sc = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public void addStock() {
		JSONObject jsonObj = new JSONObject();

		System.out.println("Enter Stock Name");
		String stockName = sc.next();
		jsonObj.put("Stock Name : ", stockName);

		System.out.println("Enter number of share: ");
		String numOfShare = sc.next();
		jsonObj.put("Number Of Share : ", numOfShare);

		System.out.println("Enter Shere Price");
		String price = sc.next();
		jsonObj.put("Shere Price : ", price);

		stockList.add(jsonObj);
		writeFile();
		System.out.println("Added : " + jsonObj);
	}

	public static void writeFile() {
		try {
			FileWriter writer = new FileWriter(".\\JsonFile\\StockDetails.json");
			writer.write(stockList.toJSONString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stockDisplay() {

		JSONParser parser = new JSONParser();
		try {
			JSONObject stockObj = new JSONObject();
			FileReader reader = new FileReader(".\\JsonFile\\StockDetails.json");
			JSONArray stockArray = (JSONArray) parser.parse(reader);
			for (int i = 0; i < stockArray.size(); i++) {
				stockObj = (JSONObject) stockArray.get(i);
				String name = (String) stockObj.get("stockName");
				String noOfShare = (String) stockObj.get("numOfShare");
				String price = (String) stockObj.get("price");

				System.out.println("\nShare name is : " + name);
				System.out.println("Number of share is : " + noOfShare);
				System.out.println("Share price is : " + price);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		StockManagement obj = new StockManagement();
		boolean isExit = false;
		while (!isExit) {
			System.out.println("\nEnter what you want to do.\n1.Add stock.\n2.Display all stock.\n3.Exit");
			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				obj.addStock();
				break;
			case 2:
				obj.stockDisplay();
				break;
			case 3:
				System.out.println("Exited");
				isExit = true;
				break;
			default:
				System.out.println("Enter valid option");
			}
		}

	}
}