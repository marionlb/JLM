package lessons.oop;

import jlm.universe.Direction;
import jlm.universe.bugglequest.AbstractBuggle;

public class AddDirectionEntity extends AbstractBuggle{
	
	/* BEGIN TEMPLATE */ 
	/* BEGIN SOLUTION */
	public class Buggle {
		private int x;
		private int y;
		private int direction;

		public Buggle(int x, int y) {
			this.x = x;
			this.y = y;
			this.direction = 0;
		}
		
	    public Buggle(int x, int y, int direction) {
	    	this.x = x;
	    	this.y = y;
	    	this.direction = direction;
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
		
		public int getDirection() {
			return this.direction;
		}
		
		public void setDirection(int direction) {
			this.direction = direction;
		}

		public void setY(int y) {
			this.y = y;
		}

	}
	/* END SOLUTION */	
	/* END TEMPLATE */

	@Override
	public void run() throws Exception {
		Buggle buggle = new Buggle(1,1,1);
		setDirection(Direction.EAST);
		buggle.setDirection(2);
		if (buggle.getDirection() == 2)
			setDirection(Direction.SOUTH);
		buggle.setDirection(3);
		if (buggle.getDirection() == 3) 
			setDirection(Direction.WEST);
		buggle.setDirection(0);
		if (buggle.getDirection() == 0) 
		   setDirection(Direction.NORTH);
		
		
	}

}
