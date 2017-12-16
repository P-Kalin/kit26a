package ua.khpi.oop.lytvyn13;

/**
 * Компаратор, що впорядковує {@code String} об'єкти лексикографічно, ігноруючи
 * випадкові відмінності.
 *
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class ComparatorFactory {

	public enum TYPE {
		date, hobby, reqs
	}

	/**
	 * @param type
	 * @return
	 */
	public static ClientComparator getComparator(String type) {
		final TYPE cur = TYPE.valueOf(type);
		return getComparator(cur);
	}

	/**
	 * @param type
	 * @return
	 */
	public static ClientComparator getComparator(TYPE type) {
		switch (type) {
		case date:
			return new DateClientComparator();
		case hobby:
			return new HobbyClientComparator();
		case reqs:
			return new ReqsClientComparator();
		default:
			System.out.println("Error!");
			break;
		}
		return null;
	}
}