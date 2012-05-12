package lessons.oop;

import java.awt.Color;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;
import jlm.universe.bugglequest.SimpleBuggle;

public class Brush extends ExerciseTemplated {

	public Brush(Lesson lesson) {
		super(lesson);
		tabName = "MyPicassoBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle",7,7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black, Color.lightGray); 
		setup(myWorld);
	}
	@Override
	public void firstCheck() throws Exception {

		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];
		//To access the Buggle object created from the student's code.
		SimpleBuggle e = (SimpleBuggle) this.currentWorld[0].getEntity(0);
		try {
			lastResult.totalTests++;
			if (testsStat(c)) {
				lastResult.passedTests++;
			} else
				throw new JLMException("Unknown error.");
			lastResult.totalTests++;
			if (this.testsDyn(e)) {
				lastResult.passedTests++;
			} else
				throw new JLMException("Unknown error.");
		}catch(JLMException ex) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n"+ex.getMessage();
			lastResult.details += "\n";
		}

	}
	public static boolean testsStat(Class<?> c) throws JLMException{
		boolean res = true;
		res&=Move.testsStat(c);
		Tests.hasMethod(c, "isBrushDown", new Class[] {});
		Tests.hasMethod(c, "brushUp", new Class[] {});
		Tests.hasMethod(c, "brushDown", new Class[] {});
		return res;
	}
	private boolean testsDyn(SimpleBuggle e) {
		return true;
	}
}
