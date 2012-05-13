package lessons.oop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

import jlm.core.JLMException;
import jlm.core.model.Game;
import jlm.core.model.lesson.ExerciseTemplated;

/**
 * This class is basically used to centralize all the tests used to check the
 * classes written by students in the OOP lesson. It concentrates static methods
 * that were created in order to detail the student's different mistakes. Every
 * mistake and/or error is represented by a thrown {@link JLMException} that
 * will be caught in the extends {@link ExerciseTemplated} class.
 * 
 * @author marion
 * 
 */
public class Tests {

	/**
	 * Checks that the class object isn't null and that it has the right name
	 * 
	 * @param c
	 *            The class that the student wrote and that is being checked
	 * @param name
	 *            The name the class should have
	 * @return whatever
	 * @throws JLMException
	 *             When the class doesn't exist
	 */
	public static final void classExists(Class<?> c, String name)
			throws JLMException {
		if (c == null)
			if (name != null && name != "")
				throw new JLMException("The \"" + name
						+ " \" class is missing from your code");
			else
				throw new JLMException("A class is missing from your code");
		else if (!c.getSimpleName().equals(name))
			throw new JLMException("I found the class \"" + c.getSimpleName()
					+ "\" but not the class \"" + name + "\"");

	}

	/**
	 * Looks up a field in the class written by the student.
	 * 
	 * @param c
	 *            The inspected class
	 * @param rech
	 *            The name (or part of the name) of the field being looked up.
	 * @return A {@link Field} object of the appropriate field if it exists,
	 *         else null.
	 * @throws JLMException
	 *             When the class is null.
	 */
	public static final Field getField(Class<?> c, String rech)
			throws JLMException {
		rech = rech.toLowerCase();
		if (c == null)
			throw new JLMException("A class is missing.");
		Field[] fields = c.getDeclaredFields();
		Field res = null;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().toLowerCase().contains(rech))
				res = fields[i];
		}
		return res;
	}

	/**
	 * See {@link Tests#getField(Class, String)} : this method gets the first
	 * field that is compatible with the type param. USE WITH CARE (ie only with
	 * the most improbable type)
	 * 
	 * @param type
	 *            Type to look out for
	 * @throws JLMException
	 *             When the class is null.
	 * @see {@link Tests#getField(Class, String)}
	 */
	public static final Field getField(Class<?> c, Class<?> type)
			throws JLMException {
		if (c == null)
			throw new JLMException("A class is missing.");
		Field[] fields = c.getDeclaredFields();
		Field res = null;
		for (int i = 0; i < fields.length; i++) {
			if (type.isAssignableFrom(fields[i].getType()))
				res = fields[i];
		}
		return res;
	}

	/**
	 * Looks up a method in the class written by the student.
	 * 
	 * @param src
	 *            The inspected class
	 * @param name
	 *            The name of the method being looked up (without the final
	 *            parenthesis)
	 * @param param
	 *            An array of the classes of the method's parameters.
	 * @return The Method object
	 * @throws JLMException
	 *             When the method doesn't exist.
	 */
	public static final Method hasMethod(Class<?> src, String name,
			Class<?>[] param) throws JLMException {
		Method m = null;
		String paramS = "{";
		if (param.length > 0) {
			for (int i = 0; param.length > 1 && i < param.length - 1; i++) {
				paramS += param[i].getSimpleName() + ",";
			}
			paramS += param[param.length - 1].getSimpleName();
		}
		paramS += "}";

		JLMException e = new JLMException("The method \"" + name
				+ "\" with the parameters " + paramS
				+ " doesn't seem to exist in the class \""
				+ src.getSimpleName() + "\"");

		try {
			m = src.getDeclaredMethod(name, param);
		} catch (NoSuchMethodException ex) {
			throw e;
		} catch (SecurityException ex) {
			throw e;
		}
		if (m == null)
			throw e;
		return m;

	}

	/**
	 * Looks up a constructor in the class written by the student.
	 * 
	 * @param src
	 *            The inspected class
	 * @param param
	 *            An array of the classes of the constructor's parameters.
	 * @return The Contructor object
	 * @throws JLMException
	 *             When the method doesn't exist.
	 */
	public static final Constructor<?> hasConstructor(Class<?> src,
			Class<?>[] param) throws JLMException {
		String paramS = "{";
		if (param.length > 0) {
			for (int i = 0; param.length > 1 && i < param.length - 1; i++) {
				paramS += param[i].getSimpleName() + ",";
			}
			paramS += param[param.length - 1].getSimpleName();
		}
		paramS += "}";
		JLMException e = new JLMException(
				"The constructor with the parameters " + paramS
						+ " doesn't seem to exist in the class \""
						+ src.getSimpleName() + "\"");

		Constructor<?> c = null;
		Class<?>[] tab = new Class[param.length + 1];
		tab[0] = src.getEnclosingClass();
		for (int i = 1; i < tab.length; i++) {
			tab[i] = param[i - 1];
		}
		try {
			c = src.getDeclaredConstructor(tab);
			if (c == null)
				c = src.getDeclaredConstructor(param);
		} catch (NoSuchMethodException ex) {
			throw e;
		} catch (SecurityException ex) {
			throw e;
		}
		if (c == null)
			throw e;

		return c;
	}

	/**
	 * Tests the actual and expected type of a field
	 * 
	 * @param f
	 *            The tested field, usually obtained with
	 *            {@link Tests#getField(Class, String)}
	 * @param type
	 *            Expected type
	 * @return
	 * @throws JLMException
	 *             If the test doesn't check out
	 */
	public static final void testField(Field f, Class<?> type)
			throws JLMException {
		if (f == null)
			throw new JLMException("A field is missing");
		if (!type.equals(f.getType()))
			throw new JLMException("The field " + f.getName()
					+ " is supposed to be of type \"" + type.getSimpleName()
					+ "\" but was found" + "to be of type \""
					+ f.getType().getSimpleName() + " \" ");

	}

	/**
	 * Tests if the new class has the accurate number of fields
	 * 
	 * @param src
	 *            The inspected class
	 * @param numFields
	 *            The expected number of fields
	 * @return An array of fields
	 * @throws JLMException
	 *             If there are fewer fields than expected.
	 */
	public static final Field[] testFields(Class<?> src, int numFields)
			throws JLMException {
		Field[] tab = src.getDeclaredFields();
		// The first element of the array is unnecessary (nothing to do with the
		// student code)
		// so it has to go
		Field[] tab2 = new Field[tab.length - 1];
		for (int i = 0; i < tab2.length; i++) {
			tab2[i] = tab[i + 1];
		}
		// Actual test
		if (tab2.length < numFields) {
			throw new JLMException("Only " + tab2.length
					+ " fields were found in your \"" + src.getSimpleName()
					+ "\" class. At least " + numFields + " were expected");
		}
		return tab2;
	}

	/**
	 * Test a get Method from another class
	 * 
	 * @param m
	 *            The get Method to test
	 * @param f
	 *            The field it is supposed to get
	 * @param src
	 *            The tested class
	 * @throws JLMException
	 *             If an error is detected in the results
	 * @see {@link Tests#testSet(Method, Field, Class)}
	 */
	public static final void testGet(Method m, Field f, Class<?> src, int modulo)
			throws JLMException {
		if (m.getParameterTypes().length > 0 || m == null || f == null)
			System.out.println("Erreur dans l'utilisation du testGet");
		else {
			Object obj, valAct, valGet;
			f.setAccessible(true);
			try {
				// Objet test random
				obj = newInstance(src);
				// Vraie valeur de l'attribut
				valAct = f.get(obj);
				// On récupère la valeur du get
				valGet = m.invoke(obj, new Object[0]);
				
				if(f.getType()==int.class && modulo!=0){
					valAct = (Integer)valAct%modulo;
					valGet = (Integer)valGet%modulo;
				}
				if (Game.getInstance().isDebugEnabled())
					System.out.println("Method " + src.getSimpleName() + "."
							+ m.getName() + " (Got : " + valGet
							+ " | Expected : " + valAct + ")");

				int h1 = valAct.hashCode(), h2 = valGet.hashCode();
				// vérification
				if (h1 != h2)
					throw new JLMException("Method " + src.getSimpleName()
							+ "." + m.getName() + " (Got : " + valGet
							+ " | Expected : " + valAct + ")");
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Test a set Method from another class
	 * 
	 * @param m
	 *            The set Method to test
	 * @param f
	 *            The field it is supposed to modify
	 * @param src
	 *            The tested class
	 * @param modulo
	 *            Accepted modulo count if f is an int, 0/1 to discard case if f
	 *            is a string
	 * @throws JLMException
	 *             If an error is detected in the results
	 * @see {@link Tests#testGet(Method, Field, Class)}
	 */
	public static final void testSet(Method m, Field f, Class<?> src, int modulo)
			throws JLMException {
		if (m.getParameterTypes().length > 1)
			System.out.println("Erreur dans l'utilisation du testSet");
		Class<?> type = m.getParameterTypes()[0];
		Object obj, valInit, valDem, valFin = null;
		f.setAccessible(true);
		try {
			// Objet à tester
			obj = newInstance(src);

			// attribut initial
			valInit = f.get(obj);
			// Det de val demandée
			valDem = newInstance(type);

			if (modulo != 0) {
				if (type.equals(int.class)) {
					valDem = ((Integer) valDem) % modulo;
				} else if (type.equals(String.class)) {
					valDem = ((String) valDem).toLowerCase();
				}
			}

			// Invoquer la methode set
			m.invoke(obj, valDem);
			// attribut à priori modifié
			valFin = f.get(obj);

			if (modulo != 0) {
				if (type.equals(int.class)) {
					valFin = ((Integer) valFin) % modulo;
				} else if (type.equals(String.class)) {
					valFin = ((String) valFin).toLowerCase();
				}
			}
			
			if (Game.getInstance().isDebugEnabled())
				System.out.println(("Method " + src.getSimpleName() + "."
						+ m.getName() + " (Got : " + valFin + " | Expected : "
						+ valDem + ")"));

			// vérification
			int h1 = valDem.hashCode(), h2 = valFin.hashCode();
			if (h1 != h2)
				throw new JLMException("Method " + src.getSimpleName() + "."
						+ m.getName() + " (Got : " + valFin + " | Expected : "
						+ valDem + ")");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates a random new instance of the desired type. If the type you
	 * asked for is Primitive, it'll return a random instance of the appropriate
	 * Wrapper class.
	 * 
	 * @param type
	 *            The desired type
	 * @return random instance of type
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	private static Object newInstance(Class<?> type)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		Random r = new Random();
		Object res = null;
		type.cast(res);
		// Dans les cas simples, la classe Random permet de générer facilement
		// une valeur
		if (type.isPrimitive() || type.equals(String.class)) {
			if (type.equals(boolean.class))
				res = r.nextBoolean();
			else if (type.equals(int.class))
				// forcé à 7 exprès pour faciliter les tests des setX de Buggles
				// qui sont % de la taille du monde
				res = r.nextInt(100);
			else if (type.equals(char.class))
				// adapté à l'UTF8 pour avoir des caractères potables
				res = (char) (r.nextInt(64) + 48);
			else if (type.equals(short.class))
				res = r.nextInt(65535);
			else if (type.equals(byte.class)) {
				res = new Byte[8];
				r.nextBytes(((byte[]) res));
			} else if (type.equals(long.class))
				res = r.nextLong();
			else if (type.equals(float.class))
				res = r.nextFloat();
			else if (type.equals(double.class))
				res = r.nextDouble();
			else if (type.equals(String.class)) {
				int i = r.nextInt(100);
				String s = "";
				for (int j = 0; j < i; j++) {
					Character c = (Character) newInstance(char.class);
					s += c;
				}
				res = s;
			}

		} else {
			// Sinon, on le fait recursivement pour tous les attributs de la
			// classe type
			Field[] fields = type.getDeclaredFields();
			try {
				res = type.newInstance();
			} catch (InstantiationException e) {
				// Cas ou le constructeur sans paramètres n'existe pas, on
				// récupère le premier contructeur
				// Pb si herite du constructeur de la superclasse (pas de
				// declaredConstructor)
				Constructor<?> c = type.getDeclaredConstructors()[0];
				Class<?>[] tab = c.getParameterTypes();
				Object[] args = new Object[tab.length];
				for (int i = 0; i < tab.length; i++) {
					args[i] = newInstance(tab[i]);
				}
				res = c.newInstance(args);
			}

			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if (!Modifier.isFinal(fields[i].getModifiers()))
					fields[i].set(res, newInstance(fields[i].getType()));
			}
		}
		return res;

	}

}
