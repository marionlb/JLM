package lessons.oop;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;
import jlm.universe.bugglequest.SimpleBuggle;

public class AddDirection extends ExerciseTemplated {

	public AddDirection(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle", 7, 7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black,
				Color.lightGray);
		setup(myWorld);
	}

	// À remonter dans exerciseTemplated (avec tests une méthode vide à
	// surcharger)
	@Override
	public void firstCheck() throws Exception {

		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];
		// To access the Buggle object created from the student's code.
		SimpleBuggle e = (SimpleBuggle) this.currentWorld[0].getEntity(0);
		try {
			lastResult.totalTests++;
			if (testsStat(c)) {
				lastResult.passedTests++;
			} else
				throw new JLMException("Unknown error.");
		} catch (JLMException ex) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n" + ex.getMessage();
			lastResult.details += "\n";
		}

	}

	public static boolean testsStat(Class<?> c) throws JLMException {
		boolean res = true;
		CreateBuggle.tests(c);
		
		Field fd = Tests.getField(c, "dir");
		Tests.testField(fd, int.class);
		
		Method getD,setD;
		getD=Tests.hasMethod(c, "getDirection", new Class[] {});
		setD=Tests.hasMethod(c, "setDirection", new Class[] { int.class });
		Tests.hasConstructor(c, new Class[] { int.class, int.class, int.class });
		
		int nbDir = 4;
		// 5 tests de chaque
		for (int i = 0; i < 5; i++) {
			Tests.testGet(getD, fd, c, nbDir);
			Tests.testSet(setD, fd, c, nbDir);
		}
		
		return res;
	}

}
