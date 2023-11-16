package com.stackExamples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.stackExamples.*;

public class StackExamples {
	private Stack<SelectOption> backStack = new Stack<SelectOption>();

	public static void main(String[] args) {
		StackExamples stackExample = new StackExamples();

		stackExample.start();
	}

	private void start() {
		int choice;
		backStack.push(new SelectOption(0, 0));
		System.out.println("Cricbuzz");
		System.out.println("------------------------");
		print(0, 0);
		do {
			Scanner input = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter your option : ");
			choice = input.nextInt();
			if (choice == 9) {
				backStack.pop();

				if (backStack.isEmpty()) {
					break;
				}
			} else {
				backStack.push(new SelectOption(backStack.peek().getLevel() + 1, choice));
			}
			SelectOption top = backStack.peek();

			print(top.getLevel(), top.getChoice());

		} while (choice != 0);
		System.out.println("Thank you for visiting our website...");

	}

	private void print(int level, int choice) {
		JSONArray array = getChoices(level, choice);
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));

		}

	}

	public static JSONArray getChoices(int level, int choice) {

		ArrayList<String> choiceList = new ArrayList<String>();

		JSONParser jsonParser = new JSONParser();
		JSONArray arr = null;
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(
					"C:\\Users\\ELCOT\\eclipse-work\\StackExamples\\src\\com\\stackExamples\\jsonArray.json"));
			if (jsonObject.containsKey(String.valueOf(level))) {
				JSONObject getLevel = (JSONObject) jsonObject.get(String.valueOf(level));
				if (getLevel.containsKey(String.valueOf(choice))) {
					Object getDetails = getLevel.get(String.valueOf(choice));
					arr = (JSONArray) getDetails;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		
		return arr;
//	
	}

}
