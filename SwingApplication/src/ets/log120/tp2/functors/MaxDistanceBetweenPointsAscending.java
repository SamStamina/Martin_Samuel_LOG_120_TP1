package ets.log120.tp2.functors;

import ets.log120.tp2.Shape;

/**
 * Foncteur indiquant si une forme a un numéro de séquence inférieur à une seconde forme.
 * 
 * @author Martin Desharnais
 */
public class MaxDistanceBetweenPointsAscending implements java.util.Comparator<Shape> {
	@Override
	public int compare(Shape left, Shape right) {
		return (int) (left.getMaxDistanceBetweenPoints() - right.getMaxDistanceBetweenPoints());
	}
}