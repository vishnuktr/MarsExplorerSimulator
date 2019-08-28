/**
 * 
 */
package com.test.simulator;

import com.test.simulator.util.InputConstant;

public class Movement {

	private final MarsExplorer marsExplorer;

	private static final int POSTION_FOUND = 1;

	private StringBuilder exploreBuilder = new StringBuilder();

	private StringBuilder blockBuilder = new StringBuilder();

	private StringBuilder pathBuilder = new StringBuilder();

	private int currentPositionX = -1;

	private int currentPositionY = -1;

	private String[] explorePosition;

	/**
	 * @param marsExplorer
	 */
	public Movement(MarsExplorer marsExplorer) {
		this.marsExplorer = marsExplorer;

	}

	public void movementProcess() {

		exploreBuilder.append("E: ");

		if (marsExplorer.getBlockCount() == 0 && marsExplorer.getExploreCount() == 0) {
			getAllPosition();
			exploreBuilder.append("B: ");
		} else if (marsExplorer.getBlockCount() != 0 && marsExplorer.getExploreCount() == 0) {
			blockBuilder.append("B: ");
			getAllPosition();
		} else if (marsExplorer.getExploreCount() != 0 && !marsExplorer.isReport()) {
			getAllPosition();
			getExploreMovementPosition();
		} else if (marsExplorer.getExploreCount() != 0 && marsExplorer.isReport()) {
			getAllPosition();
		}

		printOutput();

	}

	private boolean isPositionFound(InputConstant inputConstant, int value) {

		for (int i = 0; i <= marsExplorer.getX(); i++) {
			for (int j = 0; j <= marsExplorer.getY(); j++) {
				switch (inputConstant) {
				case PLACE:
					return marsExplorer.getPlacePosition()[i][j] == value;
				case BLOCK:
					return marsExplorer.getBlockPosition()[i][j] == value;
				case EXPLORE:
					return marsExplorer.getExplorePosition()[i][j] == value;
				default:
					break;
				}
			}
		}

		return false;
	}

	private void getAllPosition() {

		if (marsExplorer.getExploreCount() > 0) {
			explorePosition = new String[marsExplorer.getExploreCount()];
		}

		int exPositionCount = 0;

		for (int i = 0; i <= marsExplorer.getX(); i++) {
			for (int j = 0; j <= marsExplorer.getY(); j++) {

				if (marsExplorer.getPlacePosition()[i][j] == POSTION_FOUND) {
					exploreBuilder.append(" ").append("(").append(i).append(",").append(j).append(")");
					currentPositionX = i;
					currentPositionY = j;
				}

				if (marsExplorer.getBlockPosition()[i][j] == POSTION_FOUND) {
					blockBuilder.append(" ").append("(").append(i).append(",").append(j).append(")");
				}

				if (marsExplorer.getExplorePosition()[i][j] == POSTION_FOUND) {

					if (exPositionCount <= marsExplorer.getExploreCount()) {
						explorePosition[exPositionCount] = i + "," + j;
						exPositionCount = exPositionCount + 1;
					}
				}

			}
		}
	}

	private void getExploreMovementPosition() {

		String finalPosition = explorePosition[explorePosition.length - 1];

		String currentPosition = currentPositionX + "," + currentPositionY;

		String targetPosition = "";

		pathBuilder.append("PATH: ");

		int currentExplorePosition = 1;

		if (explorePosition.length == 1) {
			targetPosition = finalPosition;
		} else {
			targetPosition = explorePosition[0];
		}

		while (!finalPosition.equals(currentPosition)) {

			String[] currentPositionValue = currentPosition.split(",");

			int currentX = Integer.parseInt(currentPositionValue[0]);
			int currentY = Integer.parseInt(currentPositionValue[1]);

			Direction currentDirection = calculateDirection(targetPosition, currentX, currentY);

			if (currentDirection.equals(Direction.TOP)) {
				if (marsExplorer.getBlockPosition()[currentX][(currentY + 1)] != POSTION_FOUND) {
					pathBuilder.append("(").append(currentPosition).append("),");
					currentPosition = (currentX) + "," + (currentY + 1);
					changeNewPosition(currentX, currentY + 1);
				} else {
					currentDirection = Direction.RIGHT;
				}
			}

			if (currentDirection.equals(Direction.RIGHT)) {

				if (marsExplorer.getBlockPosition()[(currentX + 1)][currentY] != POSTION_FOUND) {
					pathBuilder.append("(").append(currentPosition).append("),");
					currentPosition = (currentX + 1) + "," + currentY;
					changeNewPosition(currentX + 1, currentY);
				} else {
					currentDirection = Direction.LEFT;
				}

			}

			if (currentDirection.equals(Direction.LEFT)) {
				if (marsExplorer.getBlockPosition()[currentX - 1][(currentY)] != POSTION_FOUND) {
					pathBuilder.append("(").append(currentPosition).append("),");
					currentPosition = (currentX - 1) + "," + currentY;
					changeNewPosition(currentX - 1, currentY);
				} else {
					currentDirection = Direction.BOTTOM;
				}
			}

			if (currentDirection.equals(Direction.BOTTOM)) {
				if (marsExplorer.getBlockPosition()[(currentX)][currentY - 1] != POSTION_FOUND) {
					pathBuilder.append("(").append(currentPosition).append("),");
					currentPosition = currentX + "," + (currentY - 1);
					changeNewPosition(currentX, currentY - 1);
				} else {
					currentDirection = Direction.TOP;
				}
			}

			if (currentDirection.equals(Direction.NONE)) {
				currentPosition = currentX + "," + (currentY);
				changeNewPosition(currentX, currentY);
			}

			if (currentPosition.equals(targetPosition) && explorePosition.length > 0) {

				if (!targetPosition.equals(finalPosition) && currentExplorePosition != explorePosition.length) {
					targetPosition = explorePosition[currentExplorePosition];
					currentExplorePosition = currentExplorePosition + 1;
				}
			}

		}

		pathBuilder.append("(").append((currentPositionX) + "," + currentPositionY).append("),");

	}

	private void changeNewPosition(int currentX, int currentY) {
		currentPositionX = currentX;
		currentPositionY = currentY;
	}

	private Direction calculateDirection(String targetPosition, int currentX, int currentY) {

		String[] value = targetPosition.split(",");

		int targetX = Integer.parseInt(value[0]);
		int targetY = Integer.parseInt(value[1]);

		if (targetX > currentX) {
			return Direction.RIGHT;
		}

		if (targetY > currentY) {
			return Direction.TOP;
		}

		if (targetX < currentX) {
			return Direction.LEFT;
		}

		if (targetY < currentY) {
			return Direction.BOTTOM;
		}

		return Direction.NONE;

	}

	private void printOutput() {

		System.out.println("Output : ");
		System.out.println("============================");
		if (!marsExplorer.isReport() && marsExplorer.getExploreCount() > 0) {
			System.out.println(pathBuilder.toString());
		} else {
			System.out.println(exploreBuilder.append(blockBuilder.toString()).append(pathBuilder.toString()));
		}
		System.out.println("============================\n");
	}

}

enum Direction {
	NONE, LEFT, TOP, BOTTOM, RIGHT;
}
