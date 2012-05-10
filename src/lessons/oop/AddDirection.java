package lessons.oop;

import java.awt.Color;
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
	public void check() throws Exception {
		super.check();
		Class<?> c = this.currentWorld[0].getEntity(0).getClass().getClasses()[0];
		
		try {
			lastResult.totalTests++;
			if (tests(c)) {
				lastResult.passedTests++;
			} else
				throw new AssertionError();
		}catch(AssertionError e) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n"+e.getMessage();
			lastResult.details += "\n";
		}
		
		
	}

	public boolean tests(Class<?> c) {
		boolean res = true;
		res&=CreateBuggle.tests(c);
		res&=CreateBuggle.hasMethod(c, "getDirection", new Class[] {});
		res&=CreateBuggle.hasMethod(c, "setDirection", new Class[] {int.class});
		res&=CreateBuggle.hasConstructor(c, new Class[]{int.class,int.class,int.class});
		return res;
	}
}
