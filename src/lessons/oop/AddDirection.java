package lessons.oop;

import java.awt.Color;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;

public class AddDirection extends ExerciseTemplated{

	public AddDirection(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle",7,7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black, Color.lightGray); 
		setup(myWorld);
	}

	//À remonter dans exerciseTemplated (avec tests une méthode vide à surcharger)
	@Override
	public void check() throws Exception {

		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];

		try {
			lastResult.totalTests++;
			if (tests(c)) {
				lastResult.passedTests++;
			} else
				throw new JLMException("Unknown error.");
		}catch(JLMException e) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n"+e.getMessage();
			lastResult.details += "\n";
		}

		super.check();
	}
	public static boolean tests(Class<?> c) throws JLMException {
		boolean res = true;
		CreateBuggle.testsStat(c);
		Tests.hasMethod(c, "getDirection", new Class[] {});
		Tests.hasMethod(c, "setDirection", new Class[] {int.class});
		Tests.hasConstructor(c, new Class[]{int.class,int.class,int.class});
		return res;
	}
	
}
