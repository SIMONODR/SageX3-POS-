package com.adonix.util;

/**
 * Modelisation de la capacité à fournir sa description
 * @author Adonix Grenoble
 * @version 140_000
 */

public interface IDescriber
{

	/**--------------------------------------------------------------------
	 * ajoute la description à un StringBuffer
	 * @param aSB
	 * @return
	 */
	public StringBuffer addDescrInSB(StringBuffer aSB);
	/**--------------------------------------------------------------------
	 * retourne la longueur de la description
	 * @return
	 */
	public int getLengthOfDescr();

	/**--------------------------------------------------------------------
	 * retourne la description dans une chaîne
	 * @return
	 */
	public String toStringDescr();
}
