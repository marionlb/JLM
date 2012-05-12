package lessons.oop;

import jlm.universe.bugglequest.SimpleBuggle;

public class BaggleEntity extends SimpleBuggle {
	/* BEGIN TEMPLATE */ 
	/* BEGIN SOLUTION */
	public class Baggle {
		private int x;
		private int y;

		public Baggle(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

	}
	/* END SOLUTION */	
	/* END TEMPLATE */
	@Override
	public void run() throws Exception {
		// TODO Auto-generated method stub

	}

}
