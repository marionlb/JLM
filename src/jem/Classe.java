package jem;

import java.lang.reflect.Field;

public class Classe {
	Object toto;
	Class<? extends Object> dynClass;
	Class<?> statClass;
	String longnameDyn;
	String simpleNameDyn;
	String longnameStat;
	String simpleNameStat;
	boolean field;
	boolean isArray;
	boolean isEnum;
	Classe superclass;
	Field[] declaredFields;

	public Classe(Object o) {
		this(o.getClass(), 0);
		toto = o;

		field = (toto instanceof Field);
		statClass = (field ? ((Field) toto).getDeclaringClass() : dynClass);

		longnameStat = statClass.getName();
		simpleNameStat = statClass.getSimpleName();
	}

	public Classe(Class<?> c, int i) {
		dynClass = c;
		statClass = c;
		longnameDyn = dynClass.getName();
		simpleNameDyn = dynClass.getSimpleName();
		longnameStat = statClass.getName();
		simpleNameStat = statClass.getSimpleName();

		isEnum = c.isEnum();
		isArray = c.isArray();
		if (i == 0)
			superclass = new Classe(dynClass.getSuperclass(), 1);
		declaredFields = dynClass.getDeclaredFields();
	}

	@Override
	public String toString() {
		return simpleNameDyn;
	}

	public String toString(int i) {
		return longnameDyn;
	}

	public boolean isEnum() {
		return isEnum;
	}

	public boolean isArray() {
		return isArray;
	}

	public Classe getSuperclass() {
		return superclass;
	}

	public Field[] getDeclaredFields() {
		return declaredFields;
	}
}
