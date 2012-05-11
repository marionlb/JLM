package lessons.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.Entity;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;
import jlm.universe.bugglequest.SimpleBuggle;

@SuppressWarnings("unused")
public class CreateBuggle extends ExerciseTemplated /*implements Tests*/ {

	public CreateBuggle(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle",7,7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black, Color.lightGray); 
		setup(myWorld);
	}
 
	//À remonter dans exerciseTemplated (avec tests une méthode vide à surcharger)
	@Override
	public void check() throws Exception {

		super.check();
		//To access the student-written Buggle class
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
		}catch(JLMException er) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n"+er.getMessage();
			//-> à changer pour récuperer des messages plus explicites
			//-> soit créér une classe d'Exception, soit voir ce qui est dispo
			lastResult.details += "\n";
		}
		
	}
	private boolean testsDyn(SimpleBuggle e) {
		//tester les 
		return true;
	}

	//changer le check pour la verification
	//cmt faire pour qu'il prenne le Bug élève et pas le Bug solution ?
	//tests unitaires à faire dans la classe entity ? -> accès au code élève
	//pb, dans ce cas on peut pas override check

	//Hehehe ! Résolu en récuperant la classe du buggle en passant par l'entité
	/**
	 * Vérif statiques, sur le typage et les methodes, spécifiques à cet exo
	 * @param c
	 * @return
	 * @throws JLMException
	 */
	public static boolean testsStat(Class<?> c) throws JLMException {
		boolean res = true;

		Field[] fields = Tests.testFields(c, 2);
		Field fx=null,fy=null;

		fx=Tests.getField(c, "x");
		fy=Tests.getField(c, "y");

		Tests.classExists(c, "Buggle");
		
		Tests.testField(fx,int.class);
		Tests.testField(fy,int.class);
		Tests.hasConstructor(c,new Class[] {int.class,int.class});

		Tests.hasMethod(c, "getX", new Class[] {});
		Tests.hasMethod(c, "getY", new Class[] {});
		Tests.hasMethod(c, "setX", new Class[] {int.class});
		Tests.hasMethod(c, "setY", new Class[] {int.class});
		
		//TODO :Pour les tests sur le contenu des méthodes, utiliser les tests de SimpleBuggle
		
		return res;

	}	
}
