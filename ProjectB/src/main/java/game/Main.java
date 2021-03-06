package game;

import java.util.Scanner;

import fixtures.Room;

public class Main {
	static Player player;
	
	static boolean quit = false;
	
	private static void printRoom() {
		if (player.getCurrentRoom() != null) {
			System.out.println(player.getCurrentRoom().getName() + "\n");
			System.out.println(player.getCurrentRoom().getLongDesc() + "\n");
			for(int i = 0; i < player.getCurrentRoom().getExits().length; i++) {
				
				Room room = player.getCurrentRoom().getExits()[i];
				if(room != null) {
					switch(i) {
					case 0:
						System.out.println("North " + room.getShortDesc());
						break;
						
					case 1: 
						System.out.println("South " + room.getShortDesc());
						break;
						
					case 2: 
						System.out.println("West " + room.getShortDesc());
						break;
						
					case 3: 
						System.out.println("East " + room.getShortDesc());
						break;
					
					case 4: 
						System.out.println("Examine " + room.getShortDesc());
						break;
						
					case 5: 
						System.out.println("Return to " + room.getShortDesc());
						break;
					}
					
				}
				
			}
		}
	}
	
	private static String[] collectInput(Scanner scanner) {
		
		String[] input = null;
		if(scanner.hasNext()) {
			
			String playerInput = scanner.nextLine();
			playerInput = playerInput.toLowerCase();
			input = playerInput.split(" ");
			
		}
		
		return input;
		
	}
	
	private static void parseInput(String[] command) {
		
		if(command == null || command.length == 0) {
			
			return;
			
		}
		switch(command[0].toLowerCase()) {
		case "north":
		case "south": 
		case "west": 
		case "east": 
		case "go":
		case "examine":
		case "return":
			if(command[0].equals("go")){
				if(command.length > 1) {
					Room room = player.getCurrentRoom().getExit(command[1]); //Performs a check for the word go and ignores it; 1 being the direction, because the word go was included, it moved the direction to the next index instead of being at 0
					
					if(room == null) {
						
						System.out.println("You've happened upon a place you shouldn't be; go back");
						break;				
					}
					else{
						player.setCurrentRoom(room);
						printRoom();
						return;
					}
				}
				else {
					System.out.println("Where would you like to go?");
					break;
				}
			}
			else
			{
			Room room = player.getCurrentRoom().getExit(command[0]);
				
				if(room == null) { //This is a failsafe for no exit in that specific direction
					
					System.out.println("You've happened upon a place you shouldn't be; go back");
					break;				
				}
				else{
					player.setCurrentRoom(room);
					printRoom();
					return;
				}
			}
			case "quit":
				quit = true;
				break;
		
			default: 
				System.out.println("What?");
				break;
		}
	}
	
	
	
	public static void main(String[] args) {
		RoomManager manager = new RoomManager(8);
		manager.init();
		player = new Player();
		player.setCurrentRoom(manager.getStartingRoom());
		Scanner scanner = new Scanner(System.in);
		
		printRoom();
		do {
			parseInput(collectInput(scanner));
		} while(!quit);
		
		scanner.close();
	}

}
