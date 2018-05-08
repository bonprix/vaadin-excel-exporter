/**
 * 
 */
package org.vaadin.addons.excelexporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.vaadin.ui.UI;

/**
 * The Class ExportUtility is the abstract class that defines the structure of
 * the utility
 *
 * @author Kartik Suba
 */
public abstract class ExportUtility {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(ExportUtility.class.getName());

	/** The excel mime type. */
	public static String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	/** The csv mime type. */
	public static String CSV_MIME_TYPE = "text/csv";

	/** The window to send the export result. */
	protected String exportWindow = "_self";

	/** The mime type. */
	protected String mimeType;

	/**
	 * Send converted.
	 *
	 * @return true, if successful
	 */
	protected abstract boolean sendConverted();

	/**
	 * Generate report file.
	 *
	 * @return the file
	 */
	protected abstract File generateReportFile();

	/**
	 * Create and export the Table contents as some sort of file type. In the
	 * case of conversion to Excel it would be an ".xls" file containing the
	 * contents as a report. Only the export() method needs to be called. If the
	 * user wishes to manipulate the converted object to export, then
	 * convertTable() should be called separately, and, after manipulation,
	 * sendConverted().
	 */

	public void export() {

		File file = generateReportFile();
		if (file != null) {
			sendConverted();
		}
	}

	/**
	 * Utility method to send the converted object to the user, if it has been
	 * written to a temporary File.
	 * 
	 * Code obtained from:
	 * http://vaadin.com/forum/-/message_boards/view_message/159583
	 *
	 * @param app
	 *            the app
	 * @param fileToExport
	 *            the file to export
	 * @param exportFileName
	 *            the export file name
	 * @param mimeType
	 *            the mime type
	 * @return true, if successful
	 */
	protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName,
			final String mimeType) {
		setMimeType(mimeType);

		return sendConvertedFileToUser(app, fileToExport, exportFileName);

	}

	/**
	 * Send converted file to user.
	 *
	 * @param app
	 *            the app
	 * @param fileToExport
	 *            the file to export
	 * @param exportFileName
	 *            the export file name
	 * @return true, if successful
	 */
	@SuppressWarnings("deprecation")
	protected boolean sendConvertedFileToUser(final UI app, final File fileToExport, final String exportFileName) {
		TemporaryFileDownloadResource resource;
		try {
			resource = new TemporaryFileDownloadResource(app, exportFileName, this.mimeType, fileToExport);
			app.getPage()
				.open(resource, null, false);
		} catch (final FileNotFoundException e) {
			LOGGER.warning("Sending file to user failed with FileNotFoundException " + e);

			return false;
		}

		return true;
	}

	/**
	 * Gets the export window.
	 *
	 * @return the export window
	 */
	public String getExportWindow() {
		return this.exportWindow;
	}

	/**
	 * Sets the export window.
	 *
	 * @param exportWindow
	 *            the new export window
	 */
	public void setExportWindow(final String exportWindow) {
		this.exportWindow = exportWindow;
	}

	/**
	 * Gets the mime type.
	 *
	 * @return the mime type
	 */
	public String getMimeType() {
		return this.mimeType;
	}

	/**
	 * Sets the mime type.
	 *
	 * @param mimeType
	 *            the new mime type
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}
}
