package org.vaadin.addons.excelexporter.utils;

import java.awt.Color;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import com.vaadin.shared.ui.grid.GridStaticCellType;
import com.vaadin.ui.components.grid.FooterCell;
import com.vaadin.ui.components.grid.HeaderCell;

public class ExcelStyleUtil {

	private ExcelStyleUtil() {

	}

	/**
	 * Sets the borders.
	 *
	 * @param headerCellStyle
	 *            the header cell style
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @param top
	 *            the top
	 * @param bottom
	 *            the bottom
	 * @param color
	 *            the color
	 * @return the XSSF cell style
	 */
	public static XSSFCellStyle setBorders(final XSSFCellStyle headerCellStyle, final Boolean left, final Boolean right,
			final Boolean top, final Boolean bottom, final Color color) {
		if (bottom) {
			headerCellStyle.setBorderBottom(BorderStyle.THIN);
			headerCellStyle.setBorderColor(BorderSide.BOTTOM, new XSSFColor(color));
		}

		if (top) {
			headerCellStyle.setBorderTop(BorderStyle.THIN);
			headerCellStyle.setBorderColor(BorderSide.TOP, new XSSFColor(color));
		}

		if (left) {
			headerCellStyle.setBorderLeft(BorderStyle.THIN);
			headerCellStyle.setBorderColor(BorderSide.LEFT, new XSSFColor(color));
		}

		if (right) {
			headerCellStyle.setBorderRight(BorderStyle.THIN);
			headerCellStyle.setBorderColor(BorderSide.RIGHT, new XSSFColor(color));
		}

		return headerCellStyle;
	}

	/**
	 * Adds the generic grid header row configured in the header configs
	 *
	 * @param gridHeaderCell
	 *            the grid header cell
	 * @param myCell
	 *            the my cell
	 */
	public static void addGenericGridHeaderRow(final HeaderCell gridHeaderCell, final Cell myCell) {

		if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.TEXT)) {
			myCell.setCellValue(gridHeaderCell.getText());
		} else if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.HTML)) {
			myCell.setCellValue(gridHeaderCell.getHtml());
		} else if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.WIDGET)) {
			myCell.setCellValue(gridHeaderCell.getComponent()
				.toString());
		}
	}

	/**
	 * Adds the generic grid footer row configured in the footer configs
	 *
	 * @param gridHeaderCell
	 *            the grid header cell
	 * @param myCell
	 *            the my cell
	 */
	public static void addGenericGridFooterRow(final FooterCell gridHeaderCell, final Cell myCell) {

		if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.TEXT)) {
			myCell.setCellValue(gridHeaderCell.getText());
		} else if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.HTML)) {
			myCell.setCellValue(gridHeaderCell.getHtml());
		} else if (gridHeaderCell.getCellType()
			.equals(GridStaticCellType.WIDGET)) {
			myCell.setCellValue(gridHeaderCell.getComponent()
				.toString());
		}
	}

}
