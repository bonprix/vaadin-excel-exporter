/**
 * 
 */
package org.vaadin.addons.excelexporter;

/**
 * The listener interface for receiving exportClick events. The class that is interested in processing a exportClick event implements this interface, and the
 * object created with that class is registered with a component using the component's addExportClickListener method. When the exportClick event occurs, that
 * object's appropriate method is invoked.
 *
 * @author Kartik Suba
 * @param <BEANTYPE> the generic type
 */
public interface ExportClickListener<BEANTYPE> {

    /**
     * Configure export PDF utility.
     *
     * @return the export to PDF utility
     */
    ExportToPDFUtility<BEANTYPE> configureExportPDFUtility();

    /**
     * Gets the configured export excel utility.
     *
     * @return the configured export excel utility
     */
    ExportToExcelUtility<BEANTYPE> getConfiguredExportExcelUtility();

    /**
     * Configure export excel utility.
     *
     * @param xlsx the xlsx
     */
    void configureExportExcelUtility(ExportType xlsx);
}
