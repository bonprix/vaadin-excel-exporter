/**
 * 
 */
package org.vaadin.addons.formatter;

/**
 * @author k.suba
 *
 */
/**
 * Used to "format Boolean columns"; Implement this interface and format the Boolean value with values such as Yes/No, True/False, Active/Not-Active etc.. as
 * needed.
 * 
 */
public class BooleanColumnFormatter implements ColumnFormatter {

    /**
     * 
     */
    private static final long serialVersionUID = -8041702795081968899L;
    String trueValue, falseValue;

    public BooleanColumnFormatter(final String trueValue, final String falseValue) {
        this.trueValue = trueValue;
        this.falseValue = falseValue;
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
        return value != null ? (((Boolean) value).equals(Boolean.TRUE) ? this.trueValue : this.falseValue) : null;

    }
}
