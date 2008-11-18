package lessons.welcome;

import java.awt.Color;

public class MethodsPicture3Entity extends universe.bugglequest.SimpleBuggle {

	/* BEGIN SOLUTION */
	void mark() {
		brushDown();
		brushUp();
	}
		
	void makeV(Color c) {
		setBrushColor(c);
		forward();
		mark();
		
		forward();
		turnLeft();
		forward();
		mark();
		
		backward();
		turnRight();
		forward();
		mark();
		
		forward();
		turnLeft();
	}

	void makePattern() {
		makeV(Color.yellow);
		makeV(Color.red);
		makeV(Color.blue);
		makeV(Color.green);
		forward(5);
	}
	
	void makeLine(int count){
		for (int i=0; i<count;i++)
			makePattern();
		backward(count*5);
	}
	
	void nextLine() {
		turnLeft();
		forward(5);
		turnRight();	
	}
	
	public void run() {
		for (int i=0; i<9; i++) {
			makeLine(9);
			nextLine();
		}
	}
	/* END TEMPLATE */
}