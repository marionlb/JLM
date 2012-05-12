package jem;

public class Definer {

	Object incognito;
	Classe classe;
	Attribut[] attributs;
	String value;

	public Definer(Object wtf) {
		this(wtf, 0);

		// for (int i = 0; i < attributs.length; i++) {
		// attributs[i]=new Definer(att[i],0);
		// }

	}

	public Definer(Object wtf, int i) {
		// Object à décrire
		this.incognito = wtf;

		// On récupère sa classe et des indications pratiques
		this.classe = new Classe(incognito);

		this.attributs = Attribut.recup(classe.getDeclaredFields(), wtf);
		this.value = (incognito == null ? "null" : incognito.toString());
	}

	@Override
	public String toString() {
		String res = "";
		res += classe + " : " + attributs.length + " attributs. Hérite de "
				+ classe.superclass + ".";
		res += "\n\t\t" + value;
		for (int i = 0; i < attributs.length; i++) {
			res += "\n\t" + attributs[i].toString();
		}
		res += "\n";
		return res;
	}
}
