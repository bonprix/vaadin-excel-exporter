/**
 * 
 */
package org.vaadin.addons.excelexporter.formatter;

/**
 * Used to "format Boolean columns"; Implement this interface and format the
 * Boolean value with values such as Yes/No, True/False, Active/Not-Active etc..
 * as needed.
 *
 * @author k.suba
 */
public class BooleanColumnFormatter implements ColumnFormatter {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8041702795081968899L;

	/** Placeholder string when the value are true or false */
	private String truePlaceholder;
	private String falsePlaceholder;

	/**
	 * Instantiates a new boolean column formatter.
	 *
	 * @param truePlaceholder
	 *            the true value
	 * @param falsePlaceholder
	 *            the false value
	 */
	public BooleanColumnFormatter(final String truePlaceholder, final String falsePlaceholder) {
		this.truePlaceholder = truePlaceholder;
		this.falsePlaceholder = falsePlaceholder;
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

		if (!(value instanceof Boolean)) {
			return "Not convertable to boolean value";
		}

		Boolean bool = (Boolean) value;

		return bool ? this.truePlaceholder : this.falsePlaceholder;
	}
}
