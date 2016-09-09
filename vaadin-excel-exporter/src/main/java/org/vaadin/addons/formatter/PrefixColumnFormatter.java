/**
 * 
 */
package org.vaadin.addons.formatter;

/**
 * @author k.suba
 *
 */
/**
 * Used to "format columns with Prefix"; Implement this interface and format the value by adding a prefix.. as needed.
 * 
 */
public class PrefixColumnFormatter implements ColumnFormatter {

    /**
     * 
     */
    private static final long serialVersionUID = -1799235402794402089L;
    String prefix;

    public PrefixColumnFormatter(final String prefix) {
        this.prefix = prefix;
    }

    /**
     * Called by Developer when a cell in a excel column needs to be formatted.
     * 
     * @param value value coming from the model or container
     * @param itemId the itemId (aka rowId) for the of the cell to be generated
     * @param columnId the id for the generated column (as specified in addGeneratedColumn)
     */
    @Override
    public Object generateCell(final Object value, final Object itemId, final Object columnId) {
        return value != null ? this.prefix + " " + (String) value : null;
    }
}
