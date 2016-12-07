/**
 * 
 */
package org.vaadin.addons.excelexporter.formatter;

/**
 * The Class PrefixColumnFormatter is used to attach configured prefix to the bean value
 *
 * @author k.suba
 */
/**
 * Used to "format columns with Prefix"; Implement this interface and format the value by adding a prefix.. as needed.
 * 
 */
public class PrefixColumnFormatter implements ColumnFormatter {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1799235402794402089L;

    /** The prefix. */
    String prefix;

    /**
     * Instantiates a new prefix column formatter.
     *
     * @param prefix the prefix
     */
    public PrefixColumnFormatter(final String prefix) {
        this.prefix = prefix;
    }

    /**
     * Called by Developer when a cell in a excel column needs to be formatted.
     *
     * @param value value coming from the model or container
     * @param itemId the itemId (aka rowId) for the of the cell to be generated
     * @param columnId the id for the generated column (as specified in addGeneratedColumn)
     * @return the object
     */
    @Override
    public Object generateCell(final Object value, final Object itemId, final Object columnId) {
        return value != null ? this.prefix + " " + (String) value : null;
    }
}
