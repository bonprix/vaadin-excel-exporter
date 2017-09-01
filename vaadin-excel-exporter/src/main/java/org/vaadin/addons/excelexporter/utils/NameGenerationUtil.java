package org.vaadin.addons.excelexporter.utils;

import java.util.Calendar;
import org.vaadin.addons.excelexporter.model.ExportType;

public class NameGenerationUtil {

	private static final String DEFAULT_FILENAME = "Export";
	private final static String DEFAULT_REPORTTITLE = "Report";
	private static final String DEFAULT_SHEETNAME = "Sheet";

	private NameGenerationUtil() {

	}

	public static String getFilename(final String filename, int maxFilenameCalendarExtension, ExportType exportType) {
		String result = filename;
		if (result == null || result.isEmpty()) {
			result = DEFAULT_FILENAME;
		}

		Calendar calendar = Calendar.getInstance();

		String dateTimeExtension = "";
		switch (maxFilenameCalendarExtension) {
		case Calendar.SECOND:
			dateTimeExtension = "_" + calendar.get(Calendar.SECOND) + dateTimeExtension;
		case Calendar.MINUTE:
			dateTimeExtension = "_" + calendar.get(Calendar.MINUTE) + dateTimeExtension;
		case Calendar.HOUR:
			dateTimeExtension = "_" + calendar.get(Calendar.HOUR) + dateTimeExtension;
		case Calendar.DATE:
			dateTimeExtension = "_" + (calendar.get(Calendar.MONTH) + 1) + dateTimeExtension;
		case Calendar.MONTH:
			dateTimeExtension = "_" + calendar.get(Calendar.MONTH) + dateTimeExtension;
		case Calendar.YEAR:
			dateTimeExtension = "_" + calendar.get(Calendar.YEAR) + dateTimeExtension;
		default:
			break;
		}

		result += dateTimeExtension;

		result += "." + exportType.getExtension();
		return result;
	}

	public static String getSheetname(String sheetname, int index) {
		if (sheetname == null || sheetname.isEmpty()) {
			return DEFAULT_SHEETNAME + index;
		}

		return sheetname;
	}

	public static String getReportTitle(String reportTitle, int index) {
		if (reportTitle == null || reportTitle.isEmpty()) {
			return DEFAULT_REPORTTITLE + ": " + index;
		}

		return reportTitle;
	}

}
