/**
 * 
 */
package org.vaadin.addons.excelexporter;

import java.io.File;

import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

/**
 * The Class ExportToPDFUtility is not yet developed. Its aim is to generate PDF report similar to Excel
 *
 * @author Kartik Suba
 * @param <BEANTYPE> the generic type
 */
public class ExportToPDFUtility<BEANTYPE> extends ExportUtility {

    /** The source UI. */
    UI sourceUI = null;

    /**
     * Instantiates a new export to PDF utility.
     */
    public ExportToPDFUtility() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new export to PDF utility.
     *
     * @param sourceTable the source table
     */
    public ExportToPDFUtility(final Table sourceTable) {

    }

    /**
     * Send converted.
     *
     * @return true, if successful
     */
    @Override
    protected boolean sendConverted() {
        return false;
    }

    /**
     * Generate report file.
     *
     * @return the file
     */
    @Override
    protected File generateReportFile() {
        return null;
    }

    /**
     * Gets the source UI.
     *
     * @return the source UI
     */
    public UI getSourceUI() {
        return this.sourceUI;
    }

    /**
     * Sets the source UI.
     *
     * @param sourceUI the new source UI
     */
    public void setSourceUI(final UI sourceUI) {
        this.sourceUI = sourceUI;
    }

}
