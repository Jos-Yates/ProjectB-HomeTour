package game;

import fixtures.Room;

public class RoomManager {
	private Room startingRoom;
	
	private Room[] rooms;
	
	public RoomManager(int roomCount) {
		super();
		rooms = new Room[roomCount];
	}
	
	public void init() { //Where the rooms are
		Room room1 = new Room(
				"Entrance",
				"- The Pristine Room",
				"A barren room with no fixtures. The walls, floor, and ceiling are all a blinding, pure white with no discernable difference in texture. Despite the lack of a light source, the room is well lit. \n \nYou do not recall how you got here, no matter how hard you try to remember."
				);
		this.rooms[0] = room1;
		
		Room room2 = new Room(
			"The Gallery",
			"- A room with a single painting",
			"Similar in structure and detail to the Entrance, however hanging from the wall across the entrance is a painting. There still seems to be no visible source of light illuminating the room. \n To the East is the faint outline of a doorway, yet completely sealed off. \n ... You try not to think about it.");
		this.rooms[1] = room2;
		
		Room room3 = new Room(
				"The Sunroom",
				"- A room with a single window",
				"While larger in size in comparison to the other rooms, it is identical with all the rest. This time, positioned in the center of the ceiling, is a glass pane window.");
		this.rooms[2] = room3;
		
		Room room4 = new Room(
				"The Bedroom",
				"- A dim bedroom",
				"A room thankfully dimmed, allowing your eyes some reprieve from the eye-piercing white. \nAgainst the wall is a bed. \nTo the West you see a doorway leading directly to the room with the painting, despite it being there an impossibility.\nWhen you look behind yourself, you find that the passage to the Sunroom is now gone. In its place is a door, the color visibly darker than its surroundings.");
		this.rooms[3] = room4;
		
		Room room5 = new Room(
				"The End",
				"- The Closet(?)",
				"Upon opening the dark door and entering inside, all light and vision is robbed from you as you're left in complete darkness.\nYou cannot feel anything around you, let alone the pathway from which you came.\nPerhaps the best option to take in such a consuming void is to *quit* while you're ahead...");
		this.rooms[4] = room5;
		
		Room painting = new Room(
				"The Painting",
				"the painting",
				"A barren canvas with a pearly-white frame. The only outlier is a small black dot in the center of the canvas, almost unnoticeable from its blinding surroundings.");
		this.rooms[5] = painting;
		
		Room window = new Room(
				"The Window",
				"the window",
				"A plain window, though the glass is the same as its surroudnings: sheer white. \nYou cannot even catch a glimpse out the outside world.");
		this.rooms[6] = window;
		
		Room bed = new Room(
				"The Bed",
				"the bed",
				"A plain bed, though through the covers you can make out a slight indentation sunk in the mattress in the shape of a body. \n... You don't know of any kind of person with an inhumane shape like that.");
		this.rooms[7] = bed;
		
		this.startingRoom = room1;
		
		Room[] room1Exits = {room2, room3}; // Room Exits
		room1.setExits(room1Exits);
		
		room2.setExits(room1, 1);
		room2.setExits(room4, 3);
		room2.setExits(painting, 4);
		painting.setExits(room2, 5);
		
		room3.setExits(room1, 0);
		room3.setExits(room4, 1);
		room3.setExits(window, 4);
		window.setExits(room3, 5);
		
		
		room4.setExits(room2, 2);
		room4.setExits(room5, 0);
		room4.setExits(bed, 4);
		bed.setExits(room4, 5);
	}
	
	public Room getRoom(int index) {
		return rooms[index];
	}
	
	public Room getRoom(String roomName) {
		int index = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (roomName.toUpperCase() == rooms[i].getName().toUpperCase()) {
				// return the room based on the index (we call the overloaded function)
				return getRoom(i);
			}
		}
		
		// If the for loop does NOT find a matching room name
		return null;
	}
	
	public Room getStartingRoom() {
		return this.startingRoom;
	}
	
	public void setStartingRoom(Room startingRoom) {
		this.startingRoom = startingRoom;
	}
	
	public Room[] getRooms() {
		return this.rooms;
	}
	
	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
}