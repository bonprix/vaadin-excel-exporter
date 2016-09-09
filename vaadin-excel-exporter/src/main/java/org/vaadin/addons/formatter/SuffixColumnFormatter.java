/**
 * 
 */
package org.vaadin.addons.formatter;

/**
 * @author k.suba
 *
 */
/**
 * Used to "format columns with Suffix"; Implement this interface and format the value by adding a suffix like %, $, kg etc.. as needed.
 * 
 */
public class SuffixColumnFormatter implements ColumnFormatter {

    /**
     * 
     */
    private static final long serialVersionUID = -4124097204858705984L;
    String suffix;

    public SuffixColumnFormatter(final String suffix) {
        this.suffix = suffix;
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
        return value != null ? (String) value + " " + this.suffix : null;
    }
}
