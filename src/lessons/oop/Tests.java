package
lessons.oop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import jlm.core.JLMException;
import jlm.core.model.lesson.ExerciseTemplated;

/**
 * This class is basically used to centralize all the tests used to check 
 * the classes written by students in the OOP lesson.
 * It concentrates static methods that were created in order to detail the student's different mistakes.
 * Every mistake and/or error is represented by a thrown {@link JLMException} that will be caught in the
 * extends {@link ExerciseTemplated} class.
 * @author marion
 *
 */
public class Tests {

	/**
	 * Checks that the class object isn't null and that it has the right name
	 * @param c The class that the student wrote and that is being checked
	 * @param name The name the class should have
	 * @return whatever
	 * @throws JLMException When the class doesn't exist
	 */
	public static final void classExists(Class<?> c, String name) throws JLMException{
		if(c==null)
			if(name!=null && name !="")
				throw new JLMException("The \""+name+" \" class is missing from your code");
			else
				throw new JLMException("A class is missing from your code");
		else if(!c.getSimpleName().equals(name))
			throw new JLMException("I found the class \""+c.getSimpleName()+"\" but not the class \""+name+"\"");


	}

	/**
	 * Looks up a field in the class written by the student.  
	 * @param c The inspected class
	 * @param rech The name (or part of the name) of the field being looked up.
	 * @return A {@link Field} object of the appropriate field if it exists, else null.
	 * @throws JLMException When the class is null.
	 */
	public static final Field getField(Class<?> c, String rech) throws JLMException {
		rech=rech.toLowerCase();
		if(c==null)
			throw new JLMException("A class is missing.");
		Field[] fields = c.getDeclaredFields();
		Field res=null;
		for (int i = 0; i < fields.length; i++) {
			if(fields[i].getName().toLowerCase().contains(rech))
				res=fields[i];
		}
		return res;
	}
	/**
	 * See {@link Tests#getField(Class, String)} : 
	 * this method gets the first field that is compatible with the type param.
	 * USE WITH CARE (ie only with the most improbable type)
	 * @param type Type to look out for
	 * @throws JLMException When the class is null.
	 * @see {@link Tests#getField(Class, String)} 
	 */
	public static final Field getField(Class<?> c, Class<?> type) throws JLMException {
		if(c==null)
			throw new JLMException("A class is missing.");
		Field[] fields = c.getDeclaredFields();
		Field res=null;
		for (int i = 0; i < fields.length; i++) {
			if(type.isAssignableFrom(fields[i].getType()))
				res=fields[i];
		}
		return res;
	}
	/**
	 * Looks up a method in the class written by the student.  
	 * @param src The inspected class
	 * @param name The name of the method being looked up (without the final parenthesis)
	 * @param param An array of the classes of the method's parameters.
	 * @return
	 * @throws JLMException When the method doesn't exist. 
	 */
	public static final void hasMethod(Class<?> src,String name,Class<?>[] param)  throws JLMException{
		Method m=null;
		String paramS = "{";
		if (param.length>0) {
			for (int i = 0; param.length > 1 && i < param.length - 1; i++) {
				paramS += param[i].getSimpleName() + ",";
			}
			paramS += param[param.length - 1].getSimpleName() ;
		}
		paramS+="}";
		
		JLMException e = new JLMException
				("The method \"" +name+"\" with the parameters "+paramS+" doesn't seem to exist in the class \"" +src.getSimpleName()+"\"");

		try {
			m=src.getDeclaredMethod(name, param);
		} catch (NoSuchMethodException ex) {
			throw e;
		} catch (SecurityException ex) {
			throw e;
		}
		if(m==null)
			throw e;


	}

	/**
	 * Looks up a constructor in the class written by the student.  
	 * @param src The inspected class
	 * @param param An array of the classes of the constructor's parameters.
	 * @return
	 * @throws JLMException When the method doesn't exist. 
	 */
	public static final void hasConstructor(Class<?> src,Class<?>[] param) throws JLMException{
		String paramS = "{";
		if (param.length>0) {
			for (int i = 0; param.length > 1 && i < param.length - 1; i++) {
				paramS += param[i].getSimpleName() + ",";
			}
			paramS += param[param.length - 1].getSimpleName();
		}
		paramS+="}";
		JLMException e = new JLMException
				("The constructor with the parameters "+paramS+" doesn't seem to exist in the class \"" +src.getSimpleName()+"\"");

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
		} catch (NoSuchMethodException ex) {
			throw e;
		} catch (SecurityException ex) {
			throw e;
		}
		if(c==null)
			throw e;


	}

	/**
	 * Tests the actual and expected type of a field
	 * @param f The tested field, usually obtained with {@link Tests#getField(Class, String)}
	 * @param type Expected type
	 * @return
	 * @throws JLMException If the test doesn't check out
	 */
	public static final void testField(Field f,Class<?> type) throws JLMException{
		if(f==null)
			throw new JLMException("A field is missing");
		if(!type.equals(f.getType()))
			throw new JLMException
			("The field "+f.getName()+" is supposed to be of type \"" +type.getSimpleName()+"\" but was found" +
					"to be of type \""+f.getType().getSimpleName()+" \" ");

	}

	/**
	 * Tests if the new class has the accurate number of fields
	 * @param src The inspected class
	 * @param numFields The expected number of fields 
	 * @return An array of fields
	 * @throws JLMException If there are fewer fields than expected.
	 */
	public static final Field[] testFields(Class<?> src, int numFields) throws JLMException{
		Field[] tab = src.getDeclaredFields();
		//The first element of the array is unnecessary (nothing to do with the student code)
		//so it has to go
		Field[] tab2 = new Field[tab.length-1];
		for (int i = 0; i < tab2.length; i++) {
			tab2[i]=tab[i+1];
		}
		//Actual test
		if(tab2.length<numFields) {
			throw new JLMException("Only "+tab2.length+" fields were found in your \""+src.getSimpleName()+"\" class. At least "+numFields+" were expected");
		}
		return tab2;
	}
}
