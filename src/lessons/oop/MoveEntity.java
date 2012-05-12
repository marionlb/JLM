package lessons.oop;

import jlm.universe.bugglequest.AbstractBuggle;

public class MoveEntity extends AbstractBuggle{

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
		
		public void forward() {
			switch (direction) {
			case 0:
				y++;
				break;
			case 3:
				x--;
				break;
			case 2:
				y--;
				break;
			case 1:
				x++;
				break;
			default:
				break;
			}
		}
		
		public void backward() {
			switch (direction) {
			case 0:
				y--;
				break;
			case 3:
				x++;
				break;
			case 2:
				y++;
				break;
			case 1:
				x--;
				break;
			default:
				break;
			}
		}
		
		public void turnLeft() {
			direction = (direction-- + 4) % 4;
		}
		
		public void turnRight() {
			direction = (direction++) % 4;
		}

	}
	/* END SOLUTION */	
	/* END TEMPLATE */
	
	
	
	@Override
	public void run() throws Exception {
		
		
	}

}
