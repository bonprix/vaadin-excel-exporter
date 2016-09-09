/**
 * 
 */
package org.vaadin.addons.formatter;

import java.io.Serializable;

/**
 * @author k.suba
 *
 */
/**
 * Used to "format columns"; Implement this interface and format the value of a particular column as needed.
 * 
 */
public interface ColumnFormatter extends Serializable {

    /**
     * Called by Developer when a cell in a excel column needs to be formatted.
     * 
     * @param value value coming from the model or container
     * @param itemId the itemId (aka rowId) for the of the cell to be generated
     * @param columnId the id for the generated column (as specified in addGeneratedColumn)
     */
    public Object generateCell(Object value, Object itemId, Object columnId);
}
