package jem;

import java.lang.reflect.Field;

public class Attribut {
	Field f;
	Classe c;
	String name;
	String value;
	int modifier;

	public Attribut(Field f, Object wtf) {
		this.f = f;
		f.setAccessible(true);
		c = new Classe(f.getType(), 1);
		name = f.getName();
		modifier = f.getModifiers();
		value = "null";
		try {
			value = f.get(wtf).toString();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static Attribut[] recup(Field[] f, Object wtf) {
		Attribut[] tab = new Attribut[f.length];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Attribut(f[i], wtf);
		}
		return tab;
	}

	@Override
	public String toString() {
		// attributs[i].getName()+" : "+attributs[i].getType().getSimpleName() +
		// " ("+attributs[i].get(incognito)+")";
		String res = "";
		res += name + " : " + c + " (" + value + ")";
		return res;
	}
}
