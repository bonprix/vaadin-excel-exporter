/**
 * 
 */
package org.vaadin.addons.excelexporter.formatter;

/**
 * The Class SuffixColumnFormatter is used to attach configured suffix to the bean value
 *
 * @author k.suba
 */
/**
 * Used to "format columns with Suffix"; Implement this interface and format the
 * value by adding a suffix like %, $, kg etc.. as needed.
 * 
 */
public class SuffixColumnFormatter implements ColumnFormatter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4124097204858705984L;

	/** The suffix. */
	private String suffix;

	/**
	 * Instantiates a new suffix column formatter.
	 *
	 * @param suffix
	 *            the suffix
	 */
	public SuffixColumnFormatter(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Called by Developer when a cell in a excel column needs to be formatted.
	 *
	 * @param value
	 *            value coming from the model or container
	 * @param itemId
	 *            the itemId (aka rowId) for the of the cell to be generated
	 * @param columnId
	 *            the id for the generated column (as specified in
	 *            addGeneratedColumn)
	 * @return the object
	 */
	@Override
	public Object generateCell(final Object value, final Object itemId, final Object columnId) {
		if (value == null) {
			return "";
		}

		if (!(value instanceof String)) {
			return "Not convertable to string value";
		}

		return (String) value + " " + this.suffix;
	}
}
