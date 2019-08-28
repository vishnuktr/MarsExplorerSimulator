/**
 * 
 */
package com.test.simulator;


public class MarsExplorer {

	private final int x;
	private final int y;

	private int[][] placePosition;
	private int[][] blockPosition;
	private int[][] explorePosition;
	private boolean report;
	private int exploreCount;
	private int blockCount;

	/**
	 * @param row
	 * @param column
	 */
	public MarsExplorer(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		placePosition = new int[x + 1][y + 1];
		blockPosition = new int[x + 1][y + 1];
		explorePosition = new int[x + 1][y + 1];
		init();
	}

	private void init() {

		for (int i = 0; i < placePosition.length; i++) {
			for (int j = 0; j < placePosition.length; j++) {
				placePosition[i][j] = 0;
				blockPosition[i][j] = 0;
				explorePosition[i][j] = 0;
			}
		}

		blockCount = 0;
		exploreCount = 0;

	}

	public void resetData() {
		placePosition = new int[x + 1][y + 1];
		blockPosition = new int[x + 1][y + 1];
		explorePosition = new int[x + 1][y + 1];
		init();
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the placePosition
	 */
	public int[][] getPlacePosition() {
		return placePosition;
	}

	/**
	 * @return the blockPosition
	 */
	public int[][] getBlockPosition() {
		return blockPosition;
	}

	/**
	 * @return the explorePosition
	 */
	public int[][] getExplorePosition() {
		return explorePosition;
	}

	/**
	 * @return the blockCount
	 */
	public int getBlockCount() {
		return blockCount;
	}

	/**
	 * @param blockCount the blockCount to set
	 */
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	/**
	 * @param blockPosition the blockPosition to set
	 */
	public void setBlockPosition(int[][] blockPosition) {
		this.blockPosition = blockPosition;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(boolean report) {
		this.report = report;
	}

	/**
	 * @return the report
	 */
	public boolean isReport() {
		return report;
	}

	/**
	 * @return the exploreCount
	 */
	public int getExploreCount() {
		return exploreCount;
	}

	/**
	 * @param exploreCount the exploreCount to set
	 */
	public void setExploreCount(int exploreCount) {
		this.exploreCount = exploreCount;
	}

}
