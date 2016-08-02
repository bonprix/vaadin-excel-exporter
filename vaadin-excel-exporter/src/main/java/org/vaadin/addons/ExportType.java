/**
 * 
 */
package org.vaadin.addons;

/**
 * @author Kartik Suba
 * 
 */
public enum ExportType {

    PDF(4),
    XLS(2),
    XLSX(1);

    private final int id;

    private ExportType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
