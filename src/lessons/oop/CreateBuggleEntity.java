package lessons.oop;

import jlm.universe.bugglequest.SimpleBuggle;

public class CreateBuggleEntity extends SimpleBuggle {

	/* BEGIN TEMPLATE */ 
	/* BEGIN SOLUTION */
	public class Buggle {
		private int x;
		private int y;

		public Buggle(int x, int y) {
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
		Buggle buggle = new Buggle(1,1);
		buggle.setX(5);
		buggle.setY(4);
		setX(buggle.getX());
		setY(buggle.getY());
	}
} 
