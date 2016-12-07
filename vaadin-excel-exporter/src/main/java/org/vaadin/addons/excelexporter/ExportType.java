/**
 * 
 */
package org.vaadin.addons.excelexporter;

/**
 * The Enum ExportType.
 *
 * @author Kartik Suba
 */
public enum ExportType {

    /** The pdf. */
    PDF(4),

    /** The xls. */
    XLS(2),

    /** The xlsx. */
    XLSX(1);

    /** The id. */
    private final int id;

    /**
     * Instantiates a new export type.
     *
     * @param id the id
     */
    private ExportType(final int id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

}
