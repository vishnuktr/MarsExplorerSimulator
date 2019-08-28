package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.test.simulator.MarsExplorer;
import com.test.simulator.Movement;
import com.test.simulator.util.CommandProcessor;

public class MainCommand {

	private static final String COMMAND_NUMBER_ERROR = "Command should be 1,2,3,4. \\n Please enter the valid command Number.";

	private static final String COMMAND_VALUE_ERROR = "Invalid position";

	private static StringBuilder inputCommands = new StringBuilder();

	public static void main(String[] args) {

		System.out.println("Mars Explorer Simulator");
		System.out.println("============================");
		System.out.println();

		MainCommand mainCommand = new MainCommand();

		int x = 5, y = 5;

		int commandNumber = 0;

		boolean isExit = false;

		MarsExplorer marsExplorer = new MarsExplorer(x, y);

		CommandProcessor commandProcessor = new CommandProcessor(marsExplorer);

		while (!isExit) {

			System.out.println("Enter a command, Valid commands are:");

			System.out.println("1. PLACE");
			System.out.println("2. BLOCK");
			System.out.println("3. EXPLORE");
			System.out.println("4. REPORT");
			System.out.println("5. EXIT");

			System.out.print("\n");

			try {
				String input = mainCommand.readInput(COMMAND_NUMBER_ERROR);

				commandNumber = Integer.parseInt(input);

				if (commandNumber <= 3 && commandNumber >= 0) {

					int inputX = -1;
					int inputY = -1;
					String[] value = null;

					try {

						switch (commandNumber) {
						case 1:

							System.out.println("Enter the PLACE position (x,y) ");

							System.out.print("\n");

							input = mainCommand.readInput("");
							value = input.split(",");

							inputX = Integer.parseInt(value[0]);
							inputY = Integer.parseInt(value[1]);

							
							if(inputX>5 || inputY >5) {
								System.out.println("Position should be (0,0) to (" + marsExplorer.getX() + "," + marsExplorer.getY() + ")");
							}else {
								inputCommands.append("PLACE " + inputX + "," + inputY);
								commandProcessor.addPlace(inputX, inputY);
							}
							printData();
							break;

						case 2:

							System.out.println("Enter the BLOCK command value (x,y) ");

							System.out.print("\n");

							input = mainCommand.readInput("");
							value = input.split(",");

							inputX = Integer.parseInt(value[0]);
							inputY = Integer.parseInt(value[1]);

							if(inputX>5 || inputY >5) {
								System.out.println("Position should be (0,0) to (" + marsExplorer.getX() + "," + marsExplorer.getY() + ")");
							}else {
								inputCommands.append("BLOCK " + inputX + "," + inputY);
								commandProcessor.addBlock(inputX, inputY);
							}
							
							printData();
							break;

						case 3:

							System.out.println("Enter the EXPLORE command value (x,y) \n");

							input = mainCommand.readInput("");
							value = input.split(",");

							inputX = Integer.parseInt(value[0]);
							inputY = Integer.parseInt(value[1]);

							inputCommands.append("EXPLORE " + inputX + "," + inputY);
							commandProcessor.addExplorer(inputX, inputY);
							printData();
							break;

						default:
							break;
						}

						inputCommands.append("\n");

					} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
						System.out.println("invalid Position value");
					}

				} else if (commandNumber == 4) {
					if (inputCommands.length() != 0) {
						marsExplorer.setReport(true);
						inputCommands.append("REPORT");
						isExit = true;
					}
				} else if (commandNumber == 5) {
					if (marsExplorer.getExploreCount() != 0 || marsExplorer.isReport()) {
						isExit = true;
					} else {
						System.out.println("REPORT or EXPLORE Command is not found.");
						printData();
					}
				} else {
					System.out.println("Command should be 1,2,3,4,5. Please enter the valid command Number.");
				}

			} catch (NumberFormatException e) {
				System.out.println("Command should be 1,2,3,4,5. Please enter the valid command Number.");
			}
		}
		printData();

		Movement movement = new Movement(marsExplorer);
		movement.movementProcess();

	}

	private static void printData() {
		if (inputCommands.length() == 0) {
			System.out.println("Input data is empty");
		} else {
			System.out.println("Your Input Data : ");
			System.out.println("============================");
			System.out.println(inputCommands.toString());
			System.out.println("============================\n");
		}
	}

	private String readInput(String errorMessage) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			return br.readLine();

		} catch (IOException | NumberFormatException e) {
			System.out.println("Command should be 1,2,3,4,5. \n Please enter the valid command Number." + errorMessage);
		}

		return "Invalid Data";
	}
}
