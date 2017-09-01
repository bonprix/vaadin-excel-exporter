/**
 * 
 */
package org.vaadin.addons.excelexporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.vaadin.addons.excelexporter.stream.TemporaryFileDownloadResource;

import com.vaadin.ui.UI;

/**
 * The Class ExportUtility is the abstract class that defines the structure of
 * the utility
 *
 * @author Kartik Suba
 */
public abstract class AbstractExportTo {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = Logger.getLogger(AbstractExportTo.class.getName());

	protected final String mimeType;

	public AbstractExportTo(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * Send converted.
	 * 
	 * @param file
	 *
	 * @return true, if successful
	 */
	protected abstract boolean sendConverted(File file);

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
			sendConverted(file);
		}
	}

	/**
	 * Send converted file to user.
	 *
	 * @param fileToExport
	 *            the file to export
	 * @param exportFileName
	 *            the export file name
	 * @return true, if successful
	 */
	@SuppressWarnings("deprecation")
	protected boolean sendConvertedFileToUser(final File fileToExport, final String exportFileName) {
		UI ui = UI.getCurrent();
		TemporaryFileDownloadResource resource;
		try {
			resource = new TemporaryFileDownloadResource(ui, exportFileName, this.mimeType, fileToExport);
			ui.getPage()
				.open(resource, null, false);
		} catch (final FileNotFoundException e) {
			LOGGER.warning("Sending file to user failed with FileNotFoundException " + e);

			return false;
		}

		return true;
	}

}
