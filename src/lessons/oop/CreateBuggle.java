package lessons.oop;

import java.awt.Color;

import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.Direction;
import jlm.universe.bugglequest.BuggleWorld;
import lessons.sort.AlgBubbleSort;


public class CreateBuggle extends ExerciseTemplated {

	public CreateBuggle(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle",7,7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black, Color.lightGray); 
		setup(myWorld);
	}

}
