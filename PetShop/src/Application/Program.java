package Application;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Utilities.Client;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		List<Client> clientList = new ArrayList<>();
		
		menu();
		int op = sc.nextInt();
		
		do {
			switch(op) {
			case 1:
				
				System.out.print("How many clients do you want to add? ");
				int n = sc.nextInt();
				
				for(int i = 0; i < n; i++) {
					sc.nextLine();
					System.out.println("--------------------------------------");
					
					System.out.println("Enter "+(i+1)+"° client ID: ");
					int id = sc.nextInt();
					
					while(hasId(clientList, id)) {
						System.out.print("ID already taken, Try again: ");
						id = sc.nextInt();
					}
					
					sc.nextLine();
					System.out.println("Enter "+(i+1)+"° client name: ");
					String name = sc.nextLine();
					
					System.out.println("Enter "+(i+1)+"° client address: ");
					String address = sc.nextLine();
					
					System.out.println("Enter "+(i+1)+"° client phone: ");
					int phone = sc.nextInt();
					
					clientList.add(new Client(name, phone, address, id));
				}
				
				menu();
				op = sc.nextInt();
				break;
				
			case 2:
				System.out.print("How many clients do you want to remove? ");
				int n1 = sc.nextInt();
				
				if(n1 > clientList.size()) {
					System.out.println("The number of clients to remove cannot exceed the number of clients in the list.");
					menu();
					op = sc.nextInt();
					break;
				}
				else {
					for(int i = 0; i < n1; i++) {
						System.out.print("Enter the client's ID that you want to remove:");
						int id = sc.nextInt();
						removeClient(clientList, id);
					}
				}
				
				menu();
				op = sc.nextInt();
				break;
				
			case 3:
				System.out.println("working on it");
				menu();
				op = sc.nextInt();
				break;
				
			case 4:
				System.out.println("working on it");
				menu();
				op = sc.nextInt();
				break;
				
			case 5:
				System.out.println("working on it");
				menu();
				op = sc.nextInt();
				break;
				
			case 6:
				System.out.println("working on it");
				menu();
				op = sc.nextInt();
				break;
				
			case 7:
				if(clientList.isEmpty()) {
					System.out.println("No registered customer");
				}
				else {
					for(Client x : clientList) {
						System.out.println(x);
					}
				}
				menu();
				op = sc.nextInt();
				break;
				
			case 8:
				System.out.println("Leaving...");
				break;
				
			default:
				System.out.print("Invalid Option. Try again: ");
				op = sc.nextInt();
				break;
			}
		}while(op != 8);
		
		sc.close();
	}

	private static void menu() {
		System.out.println("_________________________________________________");
		System.out.println("[1] - Add client");
		System.out.println("[2] - Remove client");
		System.out.println("[3] - Add animal");
		System.out.println("[4] - Remove animal");
		System.out.println("[5] - Edit animal register");
		System.out.println("[6] - Search animal by name");
		System.out.println("[7] - List clients and their respective animals");
		System.out.println("[8] - Quit");
		System.out.println("_________________________________________________");
	}
	
	private static boolean hasId(List<Client> clientList, int id) {
		Client clt = clientList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return clt != null;
	}
	
	private static void removeClient(List<Client> clientList, int id) {
		Client clt = clientList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		if(clt == null) {
			System.out.println("Client does not exist");
		}
		else {
			clientList.remove(clt);
			System.out.println("Client successfully removed!");
		}
	}
	
}
