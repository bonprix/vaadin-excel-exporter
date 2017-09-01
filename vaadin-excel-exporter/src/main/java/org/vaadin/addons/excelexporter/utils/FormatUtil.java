package org.vaadin.addons.excelexporter.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addons.excelexporter.configuration.ExportExcelComponentConfiguration;
import com.vaadin.ui.UI;

public class FormatUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelStyleUtil.class);

	private FormatUtil() {

	}

	/************************************
	 * Styles and Designing of Excel Content
	 *******************************************/

	/**
	 * Formats Date based on the UI's locale
	 *
	 * @param val
	 *            the val
	 * @param datePattern
	 *            the pattern
	 * @return the string
	 */
	public static String formatDate(final Date val, String datePattern) {
		if (val == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(datePattern, UI.getCurrent()
			.getLocale());
		return df.format(val);
	}

	/**
	 * Localized format for Integer and BigDecimal values
	 *
	 * @param value
	 *            the value
	 * @param isIntOrBigD
	 *            the is int or big D
	 * @return the string
	 */
	public static String localizedFormat(final String value, final Boolean isIntOrBigD) {
		Locale loc = UI.getCurrent()
			.getLocale();
		if (isIntOrBigD) {
			Integer modifiedValue = unLocalizedFormatForInt(value);

			NumberFormat nf = NumberFormat.getNumberInstance(loc);
			nf.setParseIntegerOnly(true);
			return nf.format(modifiedValue);
		} else {
			BigDecimal modifiedValue = unLocalizedFormatForBigDecimal(value);
			NumberFormat nf = NumberFormat.getNumberInstance(loc);
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			return nf.format(modifiedValue);
		}

	}

	/**
	 * Format float.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String formatFloat(final Double value) {
		Locale loc = UI.getCurrent()
			.getLocale();
		NumberFormat nf = NumberFormat.getNumberInstance(loc);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(value);
	}

	/**
	 * Un localized format for int.
	 *
	 * @param value
	 *            the value
	 * @return the integer
	 */
	private static Integer unLocalizedFormatForInt(final String value) {
		Locale loc = UI.getCurrent()
			.getLocale();
		Integer modifiedValue = 0;

		if (value != null && !value.contains(".") && !value.contains(",")) {
			modifiedValue = Integer.valueOf(value);
		} else {
			if (value != null && "en".equals(loc.getLanguage())) {
				modifiedValue = Integer.valueOf(value.replaceAll(",", ""));
			} else if (value != null && "de".equals(loc.getLanguage())) {
				modifiedValue = Integer.valueOf(value.replaceAll("\\.", ""));
			}
		}
		return modifiedValue;
	}

	/**
	 * Un localized format for big decimal.
	 *
	 * @param value
	 *            the value
	 * @return the big decimal
	 */
	private static BigDecimal unLocalizedFormatForBigDecimal(final String value) {
		Locale loc = UI.getCurrent()
			.getLocale();
		BigDecimal modifiedValue = new BigDecimal(Double.valueOf("0"));

		if (value != null && !value.contains(".") && !value.contains(",")) {

			modifiedValue = BigDecimal.valueOf(Double.valueOf(value));
		} else {
			if (value != null && "en".equals(loc.getLanguage())) {

				modifiedValue = BigDecimal.valueOf(Double.valueOf(value.replaceAll(",", "")));
			} else if (value != null && "de".equals(loc.getLanguage())) {

				String temp = value;
				if (value.contains(".")) {
					temp = value.replaceAll("\\.", "");

				}
				if (temp.contains(",")) {
					temp = temp.replaceAll(",", "\\.");

				}
				modifiedValue = BigDecimal.valueOf(Double.valueOf(temp));
			}
		}
		return modifiedValue;
	}

	/**
	 * Apply column formatter.
	 *
	 * @param visibleColumns
	 *            the visible columns
	 * @param componentConfiguration
	 *            the component configuration
	 * @param itemId
	 *            the item id
	 * @param columns
	 *            the columns
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static <BEANTYPE> String applyColumnFormatter(final Object[] visibleColumns,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration, final BEANTYPE itemId,
			final int columns, final Object value) {
		String formatted = null;
		if (componentConfiguration.getColumnFormatter(visibleColumns[columns]) != null) {
			try {
				formatted = (String) componentConfiguration.getColumnFormatter(visibleColumns[columns])
					.generateCell(value, itemId, visibleColumns[columns]);
			} catch (Exception e) {
				LOGGER.info("Formatting failed", e);
			}
		}
		return formatted;
	}

}
