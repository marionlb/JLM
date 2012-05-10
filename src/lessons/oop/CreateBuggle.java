package lessons.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jem.Definer;

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

	//À remonter dans exerciseTemplated (avec tests une méthode vide à surcharger)
	public void check() throws Exception {
		super.check();
		//To access the student-written Buggle class
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
	//changer le check pour la verification
	//cmt faire pour qu'il prenne le Bug élève et pas le Bug solution ?
	//tests unitaires à faire dans la classe entity ? -> accès au code élève
	//pb, dans ce cas on peut pas override check

	//Hehehe ! Résolu en récuperant la classe du buggle en passant par l'entité
	public static boolean tests(Class<?> c) {
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
		//Vérif statiques, sur le typage et les methodes

		res&=(fields.length>2);
		res&=testField(fx,int.class);
		res&=testField(fy,int.class);
		res&=hasConstructor(c,new Class[] {int.class,int.class});

		res&=hasMethod(c, "getX", new Class[] {});
		res&=hasMethod(c, "getY", new Class[] {});
		res&=hasMethod(c, "setX", new Class[] {int.class});
		res&=hasMethod(c, "setY", new Class[] {int.class});
		return res;

	}

	public static final boolean testField(Field f,Class<?> type) {
		if(f==null)
			return false;
		if(!type.equals(f.getType()))
			return false;
		return true;
	}

	public static final boolean hasConstructor(Class<?> src,Class<?>[] param) {
		Constructor<?> c =null;
		Class<?>[] tab = new Class[param.length+1];
		tab[0]=src.getEnclosingClass();
		for (int i = 1; i < tab.length; i++) {
			tab[i]=param[i-1];
		}
		try {
			c = src.getDeclaredConstructor(tab);
			if(c==null)
				c = src.getDeclaredConstructor(param);
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}
		return c !=null;
	}

	public static final boolean hasMethod(Class<?> src,String name,Class<?>[] param) {
		Method m=null;
		try {
			m=src.getDeclaredMethod(name, param);
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}
		return m !=null;
	}
}
