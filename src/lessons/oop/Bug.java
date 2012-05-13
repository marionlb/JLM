package lessons.oop;

@SuppressWarnings("unused")
public class Bug {
	private int posX;
	private int posY;
	private boolean brushDown;
	private int direction;
	public static final int tailleMonde = 7;

	/*
	 * Nord 0 Est Ouest 1 3 2 Sud
	 */

	public Bug() {
		this(2, 3, 1);
	}

	public Bug(int x, int y) {
		this(x, y, 0);
	}

	public Bug(int x, int y, int dir) {
		posX = x % tailleMonde;
		posY = y % tailleMonde;
		brushDown = false;
		direction = dir % 4;
	}

	// code test pour l'élève, pas vraiment dans la classe mais pratique de le
	// mettre là.
	public void run() {
		for (int i = 0; i < 4; i++) {
			forward();
			turnLeft();
		}
		for (int i = 0; i < 4; i++) {
			backward();
			turnRight();
		}
	}

	private void brushUp() {
		brushDown = false;
	}

	private void brushDown() {
		brushDown = true;
	}

	private void forward() {
		switch (direction) {
		case 0:
			posY++;
			break;
		case 3:
			posX--;
			break;
		case 2:
			posY--;
			break;
		case 1:
			posX++;
			break;
		default:
			break;
		}
	}

	private void turnLeft() {
		direction = (direction-- + 4) % 4;
	}

	private void turnRight() {
		direction = (direction++) % 4;
	}

	private void backward() {
		switch (direction) {
		case 0:
			posY--;
			break;
		case 3:
			posX++;
			break;
		case 2:
			posY++;
			break;
		case 1:
			posX--;
			break;
		default:
			break;
		}
	}

	public int getX() {
		return posX;
	}

	public void setX(int posX) {
		this.posX = posX % tailleMonde;
	}

	public int getY() {
		return posY;
	}

	public void setY(int posY) {
		this.posY = posY % tailleMonde;
	}

	public boolean isBrushDown() {
		return brushDown;
	}

	public void setBrushDown(boolean brushDown) {
		this.brushDown = brushDown;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
