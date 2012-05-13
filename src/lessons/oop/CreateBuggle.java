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

public class CreateBuggle extends ExerciseTemplated /* implements Tests */{

	public CreateBuggle(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle", 7, 7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black,
				Color.lightGray);
		setup(myWorld);
	}

	@Override
	public void firstCheck() throws Exception {

		// To access the student-written Buggle class
		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];
		// To access the Buggle object created from the student's code.
		SimpleBuggle e = (SimpleBuggle) this.currentWorld[0].getEntity(0);
		try {
			lastResult.totalTests++;
			if (tests(c)) {
				lastResult.passedTests++;
			} else {
				throw new JLMException("Unknown error.");
			}
		} catch (JLMException er) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n" + er.getMessage();
			lastResult.details += "\n\n";
		}
	}

	// changer le check pour la verification
	// cmt faire pour qu'il prenne le Bug élève et pas le Bug solution ?
	// tests unitaires à faire dans la classe entity ? -> accès au code élève
	// pb, dans ce cas on peut pas override check

	// Hehehe ! Résolu en récuperant la classe du buggle en passant par l'entité
	/**
	 * Vérif statiques, sur le typage et les methodes, spécifiques à cet exo
	 * 
	 * @param c
	 * @return
	 * @throws JLMException
	 */
	public static boolean tests(Class<?> c) throws JLMException {
		boolean res = true;
		Method getX, getY, setX, setY;
		Tests.classExists(c, "Buggle");

		Field[] fields = Tests.testFields(c, 2);
		Field fx = null, fy = null;

		fx = Tests.getField(c, "x");
		fy = Tests.getField(c, "y");

		Tests.testField(fx, int.class);
		Tests.testField(fy, int.class);
		Tests.hasConstructor(c, new Class[] { int.class, int.class });

		getX = Tests.hasMethod(c, "getX", new Class[] {});
		getY = Tests.hasMethod(c, "getY", new Class[] {});
		setX = Tests.hasMethod(c, "setX", new Class[] { int.class });
		setY = Tests.hasMethod(c, "setY", new Class[] { int.class });

		int tailleM = 7;
		// 5 tests de chaque
		for (int i = 0; i < 5; i++) {
			Tests.testGet(getX, fx, c, tailleM);
			Tests.testGet(getY, fy, c, tailleM);
			Tests.testSet(setX, fx, c, tailleM);
			Tests.testSet(setY, fy, c, tailleM);
		}
		return res;

	}
}
