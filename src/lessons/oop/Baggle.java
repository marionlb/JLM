package lessons.oop;

import java.awt.Color;
import java.lang.reflect.Field;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;

public class Baggle extends ExerciseTemplated {

	public Baggle(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle", 7, 7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black,
				Color.lightGray);
		setup(myWorld);
	}

	@Override
	public void firstCheck() throws Exception {
		Class<?>[] tab = this.currentWorld[0].getEntity(0).getClass()
				.getClasses();
		Class<?> bug = null, bag = null;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i].getSimpleName().toLowerCase().contains("buggle"))
				bug = tab[i];
			else if (tab[i].getSimpleName().toLowerCase().contains("baggle"))
				bag = tab[i];
		}
		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];

		try {
			lastResult.totalTests++;
			if (testsStat(bag, bug)) {
				lastResult.passedTests++;
			} else
				throw new JLMException("");
		} catch (JLMException e) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n" + e.getMessage();
			lastResult.details += "\n";
		}

	}

	public static boolean testsStat(Class<?> bag, Class<?> bug)
			throws JLMException {
		boolean res = true;

		Tests.classExists(bag, "Baggle");
		Field[] fields = Tests.testFields(bag, 2);

		Field fx = null, fy = null;
		fx = Tests.getField(bag, "x");
		fy = Tests.getField(bag, "y");

		Tests.testField(fx, int.class);
		Tests.testField(fy, int.class);
		Tests.hasConstructor(bag, new Class[] { int.class, int.class });
		Tests.hasMethod(bag, "getX", new Class[] {});
		Tests.hasMethod(bag, "getY", new Class[] {});
		Tests.hasMethod(bag, "setX", new Class[] { int.class });
		Tests.hasMethod(bag, "setY", new Class[] { int.class });

		return true;
	}
}
