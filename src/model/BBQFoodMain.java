package model;

import java.util.List;
import java.util.Scanner;

import controller.BBQMenuHelper;

public class BBQFoodMain {
	
	static Scanner in = new Scanner(System.in);
	static BBQMenuHelper bmhelp = new BBQMenuHelper();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;
		System.out.println("*****\\ BBQ Food Menu /*****\n");
		System.out.println("Select an option from the menu below.");
		System.out.println("1. Add a new food item");
		System.out.println("2. Edit an existing food item");
		System.out.println("3. Delete a food item");
		System.out.println("4. View the full menu");
		System.out.println("5. Exit");
		
		System.out.print("Enter menu option here: ");
		int input = in.nextInt();
		
		if (input == 1) {
			addFood();
		} else if (input == 2) {
			editFood();
		} else if (input == 3) {
			deleteFood();
		} else if (input == 4) {
			showAll();
		} else {
			bmhelp.cleanUp();
			System.out.println("Peace Out!");
			run = false;
		}
	}
	
	private static void addFood() {
		System.out.print("Enter a food: ");
		String type = in.nextLine();
		in.nextLine();
		
		System.out.print("Enter a quantity: ");
		String inputQuantity = in.nextLine();
		int quantity = Integer.parseInt(inputQuantity);
		
		FoodList add = new FoodList(type, quantity);
		bmhelp.insertItem(add);
	}
	
	private static void deleteFood() {
		System.out.print("Enter a food to delete: ");
		String type = in.nextLine();
		
		System.out.print("Enter the quantity to delete: ");
		String inputQuantity = in.nextLine();
		int quantity = Integer.parseInt(inputQuantity);
		
		FoodList delete = new FoodList(type, quantity);
		bmhelp.deleteItem(delete);
	}
	
	private static void editFood() {
		System.out.println("Enter a number below to begin your search.");
		System.out.println("1 : Search by Food Type");
		System.out.println("2 : Search by Quantity");
		int searchInput = in.nextInt();
		
		in.nextLine();
		List<FoodList> list;
		if (searchInput == 1) {
			System.out.print("Enter the food type: ");
			String food = in.nextLine();
			list = bmhelp.searchByType(food);
		} else {
			System.out.print("Enter the quantity: ");
			String InputQuantity = in.nextLine();
			int quantity = Integer.parseInt(InputQuantity);
			list = bmhelp.searchByQuantity(quantity);
		}

		if (!list.isEmpty()) {
			System.out.println("Results:");
			for (FoodList l : list) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			
			System.out.print("Select an ID to edit: ");
			int id = in.nextInt();

			FoodList toEdit = bmhelp.searchById(id);
			System.out.println("Retrieved " + toEdit.getId() + " from " + toEdit.getType());
			System.out.println("1 : Update Food");
			System.out.println("2 : Update Quantity");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Food: ");
				String newFood = in.nextLine();
				toEdit.setType(newFood);
			} else if (update == 2) {
				System.out.print("New Quantity: ");
				String newQuantity = in.nextLine();
				int quantity = Integer.parseInt(newQuantity);
				toEdit.setQuantity(quantity);
			}

			bmhelp.updateItem(toEdit);

		} else {
			System.out.println("No results found. Please try searching again.");
		}
	}
	
	private static void showAll() {
		// TODO Auto-generated method stub
		List<FoodList> allFood = bmhelp.showAll();
		for(FoodList singleItem : allFood) {
			System.out.println(singleItem.returnFoodList());
		}
	}

}
