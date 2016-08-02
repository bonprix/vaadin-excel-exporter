/**
 * 
 */
package org.vaadin.addons;

/**
 * @author Kartik Suba
 *
 */
public interface ExportClickListener<BEANTYPE> {

    ExportToPDFUtility<BEANTYPE> configureExportPDFUtility();

    ExportToExcelUtility<BEANTYPE> getConfiguredExportExcelUtility();

    void configureExportExcelUtility(ExportType xlsx);
}
