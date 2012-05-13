package lessons.oop;

import jlm.universe.Entity;

public class CreateWorldEntity extends Entity {

	/* BEGIN TEMPLATE */
	/* BEGIN SOLUTION */
	public class World {
		private Cell[][] cells;
		private int sizeX;
		private int sizeY;

		public World(int sizeX, int sizeY) {
			super();
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			this.cells = new Cell[sizeX][sizeY];
		}

		public Cell[][] getCells() {
			return cells;
		}

		public void setCells(Cell[][] cells) {
			this.cells = cells;
		}

		public int getSizeX() {
			return sizeX;
		}

		public void setSizeX(int sizeX) {
			this.sizeX = sizeX;
		}

		public int getSizeY() {
			return sizeY;
		}

		public void setSizeY(int sizeY) {
			this.sizeY = sizeY;
		}
	}

	public class Cell {
		private World world;
		private int x;
		private int y;

		public World getWorld() {
			return world;
		}

		public void setWorld(World world) {
			this.world = world;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	@Override
	public Entity copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() throws Exception {
		// TODO Auto-generated method stub

	}

	/* END SOLUTION */
	/* END TEMPLATE */

}
