/**
 * 
 */
package com.test.simulator.util;

import com.test.simulator.MarsExplorer;

public class CommandProcessor {

	private MarsExplorer marsExplorer;

	public CommandProcessor(MarsExplorer marsExplorer) {
		this.marsExplorer = marsExplorer;
	}

	public void addPlace(int inputX, int inputY) {

		marsExplorer.resetData();

		if ((inputX <= marsExplorer.getX() && inputX >= 0) && (inputY <= marsExplorer.getY() && inputY >= 0)) {
			marsExplorer.getPlacePosition()[inputX][inputY] = 1;
		} else {
			log();
		}
	}

	public void addBlock(int inputX, int inputY) {

		if ((inputX <= marsExplorer.getX() && inputX >= 0) && (inputY <= marsExplorer.getY() && inputY >= 0)) {
			marsExplorer.getBlockPosition()[inputX][inputY] = 1;
			marsExplorer.setBlockCount(marsExplorer.getBlockCount() + 1);
		} else {
			log();
		}
	}

	public void addExplorer(int inputX, int inputY) {

		if ((inputX <= marsExplorer.getX() && inputX >= 0) && (inputY <= marsExplorer.getY() && inputY >= 0)) {
			marsExplorer.getExplorePosition()[inputX][inputY] = 1;
			marsExplorer.setExploreCount(marsExplorer.getExploreCount() + 1);
			if (marsExplorer.getBlockPosition()[inputX][inputY] == 1) {
				marsExplorer.getBlockPosition()[inputX][inputY] = 0;
				marsExplorer.setBlockCount(marsExplorer.getBlockCount() - 1);
			}
		} else {
			log();
		}
	}

	private void log() {
		System.out.println("Position should be (0,0) to (" + marsExplorer.getX() + "," + marsExplorer.getY() + ")");
	}

}
