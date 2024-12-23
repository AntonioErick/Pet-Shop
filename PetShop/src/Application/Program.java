package Application;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import Utilities.Client;
import Utilities.Animal;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);

		List<Client> clientList = new ArrayList<>();
		List<Animal> animalList = new ArrayList<>();
		
		menu();
		int op =  getValidNumber(sc);
		
		do {
			switch(op) {
			case 1:
				
				System.out.print("How many clients do you want to add? ");
				int n = getValidNumber(sc);
				
				for(int i = 0; i < n; i++) {
					sc.nextLine();
					
					System.out.print("Enter "+(i+1)+"° client ID: ");
					int id = getValidNumber(sc);
					
					while(hasId(clientList, id)) {
						System.out.print("ID already taken, Try again: ");
						id = getValidNumber(sc);
					}
					
					sc.nextLine();
					System.out.print("Enter "+(i+1)+"° client name: ");
					String name = sc.nextLine();
					
					System.out.print("Enter "+(i+1)+"° client address: ");
					String address = sc.nextLine();
					
					System.out.print("Enter "+(i+1)+"° client phone: ");
					int phone = getValidNumber(sc);
					
					clientList.add(new Client(name, phone, address, id));
				}
				System.out.println(" ");
				System.out.println("Clients added!");
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 2:
				if(clientList.isEmpty()) {
					System.out.println("There is no client registed.");
					menu();
					op = getValidNumber(sc);
					break;
				}
				else {
					System.out.print("How many clients do you want to remove? ");
					int n1 = getValidNumber(sc);
					
					if(n1 > clientList.size()) {
						System.out.println("The number of clients to remove cannot exceed the number of clients in the list.");
						menu();
						op = getValidNumber(sc);
						break;
					}
					else {
						for(int i = 0; i < n1; i++) {
							System.out.print("Enter the client's ID that you want to remove:");
							int id = getValidNumber(sc);
							removeClient(clientList, id);
						}
					}
				}
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 3:
				
				if(clientList.isEmpty()) {
					System.out.println("No registered clients. Add clients first.");
					menu();
					op = getValidNumber(sc);
					break;
				}
				
				else {
					System.out.print("How many animals do you want to add? ");
					int n2 = getValidNumber(sc);
					
					System.out.println("Clients: ");
					for(Client client : clientList) {
						System.out.println(client);
					}
					System.out.println("-------------------------------------------------");
					
					System.out.print("Enter the ID of the client to associate this animal: ");
					for(int i = 0; i < n2; i++) {
				
						int clientId = getValidNumber(sc);
						
						Client client = clientList.stream().filter(x -> x.getId() == clientId).findFirst().orElse(null);
						if(client == null) {
							System.out.print("Invalid client ID. Try again: ");
							i--;
							continue; //pula para a próxima repetição
						}
						
						sc.nextLine();
						System.out.print("Enter "+(i+1)+"° animal specie: ");
						String specie = sc.nextLine();
			
						System.out.print("Enter "+(i+1)+"° animal name: ");
						String name = sc.nextLine();
						
						System.out.print("Enter "+(i+1)+"° animal health status: ");
						String health = sc.nextLine();
						
						Animal animal = new Animal(name, specie, health);
						animal.setOwner(client);
						animalList.add(animal);
					}
					System.out.println(" ");
					System.out.println("Animals added!");
				}
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 4:
				sc.nextLine();
				
				System.out.print("Enter the animal's name tha you want to remove: ");
				String nameAnimalRemove = sc.nextLine();
				
				removeAniaml(animalList, nameAnimalRemove);
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 5:
				sc.nextLine();
				System.out.print("Enter the animal's name you want to edit: ");
				String editAnimal = sc.nextLine();
				
				System.out.println("-------------------------------------------------");
				Animal animalEdit = animalList.stream().filter(x -> x.getName().equals(editAnimal)).findFirst().orElse(null);
				
				if (animalEdit != null) {
					System.out.println("Animal found -> "+animalEdit);
					
					System.out.print("Enter a new name or press [ENTER] to keep "+ animalEdit.getName()+": ");
					String newName = sc.nextLine();
					if(!newName.isEmpty()) {
						animalEdit.setName(newName);
					}
					
					System.out.print("Enter a new specie or press [ENTER] to keep "+ animalEdit.getSpecie()+": ");
					String newSpecie = sc.nextLine();
					if(!newSpecie.isEmpty()) {
						animalEdit.setSpecie(newSpecie);
					}
					
					System.out.println("Enter a new healt status or press [ENTER] to keep "+ animalEdit.getHealth()+": ");
					String newHealth = sc.nextLine();
					if(!newHealth.isEmpty()) {
						animalEdit.setHealth(newHealth);
					}
					
					System.out.println("Animal register edited!");
				}
				else {
					System.out.println("Animal not found.");
				}
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 6:
				sc.nextLine();
				
				System.out.println("Enter the animal name: ");
				String nameAnimalSearch = sc.nextLine();
				
				System.out.println(searchAnimal(animalList, nameAnimalSearch));
				
				menu();
				op = getValidNumber(sc);
				break;
				
			case 7:
				if(clientList.isEmpty()) {
					System.out.println("No registered customer");
				}
				else {
					for(Client x : clientList) {
						System.out.println(x);
					
						List<Animal> associatedAnimal = animalList.stream().filter(animal -> animal.getOwner() != null && animal.getOwner().getId() == x.getId()) .collect(Collectors.toList());
					
						if(associatedAnimal.isEmpty()) {
							System.out.println("No animals associated.");
						}
						else {
							System.out.println("Animals: ");
							for(Animal y : associatedAnimal) {
								System.out.println("["+y+"]");
							}
						}
					}
				}
				menu();
				op = getValidNumber(sc);
				break;
				
			case 8:
				break;
				
			default:
				op = getValidNumber(sc);
				break;
			}
		}while(op != 8);
		
		System.out.println("Leaving...");
		
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
	
	private static String searchAnimal(List<Animal> animalList, String name) {
		Animal ani = animalList.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null); //.equals() compara o conteúdo de Strings
		return (ani != null) ? ani.toString() : "Animal not found"; 
	}
	
	private static void removeAniaml(List<Animal> animalList, String name) {
		Animal ani = animalList.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
		if(ani == null) {
			System.out.println("Animal does not exist");
		}
		else {
			animalList.remove(ani);
			System.out.println("Animal successfully removed!");
		}
	}
	
	public static int getValidNumber(Scanner sc) {
		 int number = -1;
		    while (number < 0) { 
		        if (sc.hasNextInt()) {
		            number = sc.nextInt(); 
		            if (number < 0) { 
		                System.out.println("The number must be non-negative. Try again.");
		            }
		        } else {
		            System.out.print("Invalid input. Please enter a valid number: ");
		            sc.next(); 
		        }
		    }
		    System.out.println("--------------------------------------");
		    return number;
	}
}
