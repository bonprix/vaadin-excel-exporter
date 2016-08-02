/**
 * 
 */
package org.vaadin.addons;

import java.io.File;

import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

/**
 * @author Kartik Suba
 *
 */
public class ExportToPDFUtility<BEANTYPE> extends ExportUtility {

    UI sourceUI = null;

    public ExportToPDFUtility() {
        // TODO Auto-generated constructor stub
    }

    public ExportToPDFUtility(final Table sourceTable) {

    }

    @Override
    protected boolean sendConverted() {
        return false;
    }

    @Override
    protected File generateReportFile() {
        return null;
    }

    public UI getSourceUI() {
        return this.sourceUI;
    }

    public void setSourceUI(final UI sourceUI) {
        this.sourceUI = sourceUI;
    }

}
