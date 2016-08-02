/**
 * 
 */
package org.vaadin.addons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.vaadin.ui.UI;

/**
 * @author Kartik Suba
 * 
 */
public abstract class ExportUtility {

    protected static final Logger LOGGER = Logger.getLogger(ExportUtility.class.getName());

    public static String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    public static String CSV_MIME_TYPE = "text/cvs";

    /** The window to send the export result */
    protected String exportWindow = "_self";
    protected String mimeType;

    protected abstract boolean sendConverted();

    protected abstract File generateReportFile();

    /**
     * Create and export the Table contents as some sort of file type. In the case of conversion to Excel it would be an ".xls" file containing the contents as
     * a report. Only the export() method needs to be called. If the user wishes to manipulate the converted object to export, then convertTable() should be
     * called separately, and, after manipulation, sendConverted().
     */

    public void export() {

        File file = generateReportFile();
        if (file != null) {
            sendConverted();
        }
    }

    /**
     * Utility method to send the converted object to the user, if it has been written to a temporary File.
     * 
     * Code obtained from: http://vaadin.com/forum/-/message_boards/view_message/159583
     * 
     * @return true, if successful
     */
    protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName, final String mimeType) {
        setMimeType(mimeType);

        return sendConvertedFileToUser(app, fileToExport, exportFileName);

    }

    @SuppressWarnings("deprecation")
    protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) {
        TemporaryFileDownloadResource resource;
        try {
            resource = new TemporaryFileDownloadResource(app, exportFileName, this.mimeType, fileToExport);
            app.getPage()
               .open(resource, null, false);
        }
        catch (final FileNotFoundException e) {
            LOGGER.warning("Sending file to user failed with FileNotFoundException " + e);

            return false;
        }

        return true;
    }

    public String getExportWindow() {
        return this.exportWindow;
    }

    public void setExportWindow(final String exportWindow) {
        this.exportWindow = exportWindow;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }
}
