package lessons.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.Direction;
import jlm.universe.bugglequest.Buggle;
import jlm.universe.bugglequest.BuggleWorld;

@SuppressWarnings("unused")
public class CreateBuggle extends ExerciseTemplated /*implements Tests*/ {

	public CreateBuggle(Lesson lesson) {
		super(lesson);
		tabName = "MyBuggle";
		BuggleWorld myWorld = new BuggleWorld("Buggle",7,7);
		new Buggle(myWorld, "Buggle ", 1, 3, Direction.NORTH, Color.black, Color.lightGray); 
		setup(myWorld);
	}

	public void check() throws Exception {
		super.check();
		
		try {
			lastResult.totalTests++;
			if (tests(Bug.class)) {
				lastResult.passedTests++;
			}
		}catch(AssertionError e) {
			lastResult.details += "The unit testing fails.";
			lastResult.details += "\n"+e.getMessage();
			lastResult.details += "\n";
		}

	}
	//changer le check pour la verification
	//cmt faire pour qu'il prenne le Bug élève et pas le Bug solution ?
	//tests unitaires à faire dans la classe entity ? -> accès au code élève
	//pb, dans ce cas on peut pas override check

	//Hehehe ! Résolu en dégageant la classe BugTest et en mettant tout en interne
	public static boolean tests(Class<Bug> c) {
		boolean res = true;
		
		Field[] fields = c.getDeclaredFields();
		Field fx=null,fy=null,fd=null,fb=null;
		for (int i = 0; i < fields.length; i++) {
			if(fields[i].getName().toLowerCase().contains("x"))
				fx=fields[i];
			else if (fields[i].getName().toLowerCase().contains("y"))
				fy=fields[i];
			else if (fields[i].getName().toLowerCase().contains("dir"))
				fd=fields[i];
			else if (fields[i].getName().toLowerCase().contains("brush"))
				fb=fields[i];
		}
		
		//Verif specifiques à cet exo
		assertTrue(fields.length>2);
		res&=testField(fx,int.class);
		res&=testField(fy,int.class);
		assertTrue( hasConstructor(new Class[] {int.class,int.class}));
		
		return res;

	}

	public static  boolean testField(Field f,Class<?> type) {
		assertNotNull(f);
		assertEquals( type,f.getType());
		return true;
	}

	public static  final boolean hasConstructor(Class<?>[] param) {
		Constructor<Bug> c =null;
		try {
			c = Bug.class.getDeclaredConstructor(param);
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}
		return c !=null;
	}
	
}
