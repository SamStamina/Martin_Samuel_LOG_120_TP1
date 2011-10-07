package ets.log120.tp1;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Analyseur chargé de découper les requêtes fournies par le serveur.
 * 
 * @author Martin Desharnais
 */
public class RequestParser {

	private static Pattern pattern = Pattern
			.compile("(\\d+)\\s+<(\\w+)>\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+\\s+)?</\\2>");
	private String request;
	private int sequenceNumber;
	private String shapeType;
	private int[] values;

	// ////////////////////////////////////////////////
	// Constructor(s)
	// ////////////////////////////////////////////////

	/**
	 * Construit un analyseur vide.
	 */
	public RequestParser() {
	}

	/**
	 * Construit un analyseur et procède à l'analyse de la requête reçue en
	 * paramètre.
	 */
	public RequestParser(String request) {
		parse(request);
		this.request = request;
	}

	// ////////////////////////////////////////////////
	// Accessor(s)
	// ////////////////////////////////////////////////

	/**
	 * Retourne la requête initiale.
	 */
	public String toString() {
		return request;
	}

	/**
	 * Retourne le numéro séquenciel.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Retourne le type de forme.
	 */
	public String getShapeType() {
		return shapeType;
	}

	/**
	 * Retourne le nombre située à l'indice reçu en paramètre.
	 */
	public int getValueAt(int index) {
		return values[index];
	}

	// ////////////////////////////////////////////////
	// Mutator(s)
	// ////////////////////////////////////////////////

	/**
	 * Analyse une nouvelle requête et stoque les données extraites.
	 */
	public void parse(String request) {
		request = request.trim();

		Matcher matcher = pattern.matcher(request);

		if (matcher.find()) {
			sequenceNumber = Integer.parseInt(matcher.group(1));
			shapeType = matcher.group(2);
			values = new int[shapeType.equalsIgnoreCase("CERCLE") ? 3 : 4];

			for (int i = 0; i < values.length; i++)
				values[i] = Integer.parseInt(matcher.group(i + 3).trim());
		}
	}
}