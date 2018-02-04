/*
 * 
 */
package org.vaadin.addons.excelexporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addons.excelexporter.configuration.AbstractComponentHeaderFooterConfiguration;
import org.vaadin.addons.excelexporter.configuration.ComponentFooterConfiguration;
import org.vaadin.addons.excelexporter.configuration.ComponentHeaderConfiguration;
import org.vaadin.addons.excelexporter.configuration.ExportExcelComponentConfiguration;
import org.vaadin.addons.excelexporter.configuration.ExportExcelConfiguration;
import org.vaadin.addons.excelexporter.configuration.ExportExcelSheetConfiguration;
import org.vaadin.addons.excelexporter.configuration.MergedCell;
import org.vaadin.addons.excelexporter.model.ExportType;
import org.vaadin.addons.excelexporter.utils.ExcelStyleUtil;
import org.vaadin.addons.excelexporter.utils.FormatUtil;
import org.vaadin.addons.excelexporter.utils.NameGenerationUtil;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.FooterRow;
import com.vaadin.ui.components.grid.HeaderRow;

/**
 * The Class ExportToExcelUtility is the core algorithm that generates Excel
 * based on the configurations.
 *
 * @author Kartik Suba
 * @param <BEANTYPE>
 *            the generic type
 */
public class ExportToExcel<BEANTYPE> extends AbstractExportTo {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExportToExcel.class);

	/** The excel mime type. */
	private static String EXCEL_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/** The workbook. */
	protected XSSFWorkbook workbook;

	/** The export excel configuration. */
	protected final ExportExcelConfiguration<BEANTYPE> exportExcelConfiguration;

	/** The resultant export type. */
	protected final ExportType exportType;

	/**
	 * Gets the resultant export type.
	 *
	 * @return the resultant export type
	 */
	public ExportType getResultantExportType() {
		return this.exportType;
	}

	/************************************** Constructors *********************************************************/

	/**
	 * Instantiates a new export to excel utility.
	 *
	 * @param exportType
	 *            the export type
	 * @param exportExcelConfiguration
	 *            the export excel configuration
	 */
	public ExportToExcel(final ExportType exportType,
			final ExportExcelConfiguration<BEANTYPE> exportExcelConfiguration) {
		super(EXCEL_MIME_TYPE);
		this.exportType = exportType;
		this.exportExcelConfiguration = exportExcelConfiguration;
		this.workbook = new XSSFWorkbook();

		process(this.workbook, exportExcelConfiguration.getSheetConfigs());
	}

	/************************************* Constructors *********************************************************/

	/**************************************
	 * Pre-processing and Book-keeping Information
	 *************************/

	/**
	 * This method processes the sheet configs
	 * 
	 * @param workbook
	 * @param sheetConfigs
	 * 
	 */
	protected void process(XSSFWorkbook workbook, List<ExportExcelSheetConfiguration<BEANTYPE>> sheetConfigs) {
		for (ExportExcelSheetConfiguration<BEANTYPE> sheetConfig : sheetConfigs) {
			Sheet sheet = workbook.createSheet(sheetConfig.getSheetname());
			sheet.setAutobreaks(true);

			int rowNum = 0;
			rowNum = addSheetTitle(workbook, sheetConfig, sheet, rowNum);
			rowNum = addSheetGeneratedBy(workbook, sheetConfig, sheet, rowNum);
			addComponents(workbook, sheetConfig, sheet, rowNum);
		}
	}

	private void addComponents(XSSFWorkbook workbook, ExportExcelSheetConfiguration<BEANTYPE> sheetConfig, Sheet sheet,
			int rowNum) {
		int tmpRowNum = rowNum;
		for (ExportExcelComponentConfiguration<BEANTYPE> componentConfig : sheetConfig.getComponentConfigs()) {
			if (componentConfig.getGrid() != null) {
				tmpRowNum = addGridToExcelSheet(componentConfig.getGrid(), workbook, sheet, tmpRowNum, sheetConfig,
												componentConfig);
			}
		}
	}

	private int addSheetGeneratedBy(XSSFWorkbook workbook, ExportExcelSheetConfiguration<BEANTYPE> sheetConfig,
			Sheet sheet, int rowNum) {
		int tmpRowNum = rowNum;
		if (sheetConfig.getIsDefaultGeneratedByRequired()) {
			// Adding an empty row before generated by
			Row myHeaderRow2 = sheet.createRow(tmpRowNum++);
			Cell myHeaderCell2 = myHeaderRow2.createCell(0);
			myHeaderCell2.setCellValue(sheetConfig.getLoggerInfoRowContent());
			myHeaderCell2.setCellStyle(sheetConfig.getGeneratedByStyleFunction()
				.apply(workbook));
			sheet.addMergedRegion(new CellRangeAddress(tmpRowNum, tmpRowNum,
					sheetConfig.getColumnForGeneratedByRegion()[0], sheetConfig.getColumnForGeneratedByRegion()[1]));
		}
		return tmpRowNum;
	}

	private int addSheetTitle(XSSFWorkbook workbook, ExportExcelSheetConfiguration<BEANTYPE> sheetConfig, Sheet sheet,
			int rowNum) {
		int tmpRowNum = rowNum;
		if (sheetConfig.getIsDefaultSheetTitleRequired()) {
			// Creating Report Name Row
			Row myHeaderRow1 = sheet.createRow(tmpRowNum++);
			Cell myHeaderCell1 = myHeaderRow1.createCell(0);

			myHeaderCell1.setCellValue(sheetConfig.getReportTitleRowContent());
			myHeaderCell1.setCellStyle(sheetConfig.getReportTitleStyleFunction()
				.apply(workbook));
			sheet.addMergedRegion(new CellRangeAddress(tmpRowNum, tmpRowNum, sheetConfig.getColumnForTitleRegion()[0],
					sheetConfig.getColumnForTitleRegion()[1]));
		}
		return tmpRowNum;
	}

	/*************************************
	 * Adding Grid To Sheet
	 *******************************************/

	/**
	 * Creates the grid content.
	 *
	 * @param grid
	 *            the grid
	 * @param myWorkBook
	 *            the my work book
	 * @param sheet
	 *            the sheet
	 * @param rowNum
	 *            current rownum
	 * @param sheetConfiguration
	 *            the sheet configuration
	 * @param componentConfiguration
	 *            the component configuration
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	protected Integer addGridToExcelSheet(final Grid<BEANTYPE> grid, final XSSFWorkbook myWorkBook, Sheet sheet,
			int rowNum, final ExportExcelSheetConfiguration<BEANTYPE> sheetConfiguration,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration) {

		Collection<BEANTYPE> items;
		if (grid.getDataProvider() instanceof ListDataProvider) {
			items = ((ListDataProvider<BEANTYPE>) grid.getDataProvider()).getItems();
		} else if (grid.getDataProvider() instanceof ConfigurableFilterDataProvider) {
			items = new ArrayList<>();
			((ConfigurableFilterDataProvider<BEANTYPE, Void, ?>) grid.getDataProvider()).fetch(new Query<>()).forEach(item -> items.add(item));
		} else {
			throw new UnsupportedOperationException("dataProvider " + grid.getDataProvider()
				.getClass() + " of grid is not supported");
		}

		return addGridToExcelSheet(items, myWorkBook, sheet, rowNum, sheetConfiguration, componentConfiguration);
	}

	/**
	 * Creates the generic content. Adds the component header, data, and footer
	 * sections
	 *
	 * @param itemIds
	 *            the item ids
	 * @param myWorkBook
	 *            the my work book
	 * @param sheet
	 *            the sheet
	 * @param rowNum
	 *            current rownum
	 * @param sheetConfiguration
	 *            the sheet configuration
	 * @param componentConfiguration
	 *            the component configuration
	 * @return the integer
	 */
	protected int addGridToExcelSheet(final Collection<BEANTYPE> itemIds, final XSSFWorkbook myWorkBook, Sheet sheet,
			int rowNum, final ExportExcelSheetConfiguration<BEANTYPE> sheetConfiguration,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration) {

		int tmpRowNum = rowNum;
		// create space before each grid
		tmpRowNum++;

		sheet.setRowBreak(tmpRowNum);

		sheet.createFreezePane(sheetConfiguration.getFrozenColumns(), sheetConfiguration.getFrozenRows());

		tmpRowNum = addGridHeaderRows(sheet, tmpRowNum, componentConfiguration);
		tmpRowNum = addGridContent(itemIds, myWorkBook, sheet, tmpRowNum, sheetConfiguration, componentConfiguration);
		tmpRowNum = addGridFooterRows(sheet, tmpRowNum, componentConfiguration);

		// Disabling auto columns for each column
		for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
			sheet.autoSizeColumn(columns, false);
		}

		return tmpRowNum;
	}

	private int addGridContent(final Collection<BEANTYPE> itemIds, final XSSFWorkbook myWorkBook, Sheet sheet,
			int rowNum, final ExportExcelSheetConfiguration<BEANTYPE> sheetConfiguration,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration) {
		int tmpRowNum = rowNum;
		int dataRowContentStart = rowNum;
		for (final BEANTYPE itemId : itemIds) {
			addGridDataRow(	myWorkBook, sheet, sheetConfiguration, componentConfiguration, itemId, tmpRowNum,
							dataRowContentStart);
			tmpRowNum++;
		}
		return tmpRowNum;
	}

	/**
	 * Adds the generic header rows as configured in the component configuration
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowNum
	 *            current rownum
	 *
	 * @param componentConfiguration
	 *            the component configuration
	 * @return the integer
	 */
	protected Integer addGridHeaderRows(Sheet sheet, int rowNum,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration) {
		if (componentConfiguration.getHeaderConfigs() == null) {
			return rowNum;
		}

		int tmpRowNum = rowNum;

		for (ComponentHeaderConfiguration headerConfig : componentConfiguration.getHeaderConfigs()) {
			tmpRowNum = addHeaderFooterRow(sheet, tmpRowNum, componentConfiguration, headerConfig);
		}

		// Add All the headers
		return tmpRowNum;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int addHeaderFooterRow(Sheet sheet, int rowNum,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration,
			AbstractComponentHeaderFooterConfiguration headerFooterConfig) {
		int tmpRowNum = rowNum;

		Row myRow = sheet.createRow(tmpRowNum);

		int startMerge = -999;
		for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
			Cell myCell = myRow.createCell(columns, XSSFCell.CELL_TYPE_STRING);
			String columnId = componentConfiguration.getVisibleProperties()[columns];
			if (headerFooterConfig.getMergedCells() != null) {
				startMerge = addMergedCell(	sheet, tmpRowNum, componentConfiguration, headerFooterConfig, startMerge,
											columns, myCell);
			} else if (headerFooterConfig.getRow() != null) {
				if (headerFooterConfig.getRow() instanceof HeaderRow) {
					ExcelStyleUtil.addGenericGridHeaderRow(	((HeaderRow) headerFooterConfig.getRow()).getCell(columnId),
															myCell);
				} else if (headerFooterConfig.getRow() instanceof FooterRow) {
					ExcelStyleUtil.addGenericGridFooterRow(	((FooterRow) headerFooterConfig.getRow()).getCell(columnId),
															myCell);
				}
			} else if (headerFooterConfig.getColumnKeys() != null) {
				myCell.setCellValue(headerFooterConfig.getColumnKeys()[columns]);
			}
			myCell.setCellStyle(componentConfiguration.getHeaderStyleFunction()
				.apply(this.workbook, columnId));
		}

		if (headerFooterConfig instanceof ComponentHeaderConfiguration
				&& ((ComponentHeaderConfiguration) headerFooterConfig).isAutoFilter()) {
			sheet.setAutoFilter(new CellRangeAddress(tmpRowNum, tmpRowNum, 0,
					componentConfiguration.getVisibleProperties().length - 1));
		}

		tmpRowNum++;
		return tmpRowNum;
	}

	private int addMergedCell(Sheet sheet, int rowNum,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration,
			AbstractComponentHeaderFooterConfiguration<Object> headerFooterConfig, int startMerge, int columns,
			Cell myCell) {
		int tmpStartMerge = startMerge;
		for (MergedCell joinedHeader : headerFooterConfig.getMergedCells()) {
			if (joinedHeader.getStartProperty()
				.equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
				tmpStartMerge = columns;
				myCell.setCellValue(joinedHeader.getHeaderKey());
			} else if (joinedHeader.getEndProperty()
				.equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
				int endMerge = columns;
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, tmpStartMerge, endMerge));
			}
		}
		sheet.autoSizeColumn(columns, false);
		return tmpStartMerge;
	}

	/**
	 * Adds the generic footer rows as configured in the component configuration
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowNum
	 *            current rownum
	 * @param componentConfiguration
	 *            the component configuration
	 * @return the integer
	 */
	protected Integer addGridFooterRows(Sheet sheet, int rowNum,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration) {
		if (componentConfiguration.getFooterConfigs() == null) {
			return rowNum;
		}

		int tmpRowNum = rowNum;
		for (ComponentFooterConfiguration footerConfig : componentConfiguration.getFooterConfigs()) {
			tmpRowNum = addHeaderFooterRow(sheet, tmpRowNum, componentConfiguration, footerConfig);
		}

		return tmpRowNum;
	}

	/**
	 * Adds the generic data row. Applies formatting to the cells as configured.
	 *
	 * @param myWorkBook
	 *            the my work book
	 * @param sheet
	 *            the sheet
	 * @param rowNum
	 *            current rownum
	 * @param sheetConfiguration
	 *            the sheet configuration
	 * @param componentConfiguration
	 *            the component configuration
	 * @param itemId
	 *            the item id
	 * @param rowNum
	 *            the local row
	 * @param dataRowContentStart
	 */
	protected void addGridDataRow(final XSSFWorkbook myWorkBook, Sheet sheet,
			final ExportExcelSheetConfiguration<BEANTYPE> sheetConfiguration,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration, final BEANTYPE itemId,
			final Integer rowNum, int dataRowContentStart) {

		Row myRow = sheet.createRow(rowNum);
		try {

			for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
				String columnId = componentConfiguration.getVisibleProperties()[columns];
				Function<BEANTYPE, ?> function = componentConfiguration.getGrid()
					.getColumn(columnId)
					.getValueProvider();

				Object value = function.apply(itemId);
				Cell myCell = myRow.createCell(columns, XSSFCell.CELL_TYPE_STRING);

				myCell.setCellStyle(componentConfiguration.getContentStyleFunction()
					.apply(myWorkBook, columnId, value, rowNum - dataRowContentStart));

				if (value == null) {
					myCell.setCellValue("");
					continue;
				}

				setCellValueBasedOnValueDataType(	sheetConfiguration, componentConfiguration, itemId, columns, value,
													myCell);

			}
		} catch (Exception e) {
			ExportToExcel.LOGGER.error("addGenericDataRow throws + " + e.getMessage(), e);
		}
	}

	private void setCellValueBasedOnValueDataType(final ExportExcelSheetConfiguration<BEANTYPE> sheetConfiguration,
			final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration, final BEANTYPE itemId,
			int columns, Object value, Cell myCell) {
		// Date
		if (componentConfiguration.getDateFormattingProperties()
			.contains(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
			myCell.setCellValue(FormatUtil.formatDate((Date) value, sheetConfiguration.getDateFormat()));
			return;
		}

		// Integer
		if (componentConfiguration.getIntegerFormattingProperties()
			.contains(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {

			String formattedInteger = FormatUtil.localizedFormat(value != null && !String.valueOf(value)
				.isEmpty() ? String.valueOf(value) : null, Boolean.TRUE);
			String customFormattedString = FormatUtil
				.applyColumnFormatter(	componentConfiguration.getVisibleProperties(), componentConfiguration, itemId,
										columns, formattedInteger);
			myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedInteger);
			return;
		}

		// Float
		if (componentConfiguration.getFloatFormattingProperties()
			.contains(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
			setCellValueBasedOnFloat(componentConfiguration, itemId, columns, value, myCell);
			return;
		}

		// Boolean
		if (componentConfiguration.getBooleanFormattingProperties()
			.contains(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
			String customFormattedString = FormatUtil
				.applyColumnFormatter(	componentConfiguration.getVisibleProperties(), componentConfiguration, itemId,
										columns, Boolean.valueOf((boolean) value));
			myCell.setCellValue(customFormattedString != null ? customFormattedString : value.toString());
			return;
		}

		String customFormattedString = FormatUtil.applyColumnFormatter(	componentConfiguration.getVisibleProperties(),
																		componentConfiguration, itemId, columns, value);
		myCell.setCellValue(customFormattedString != null ? customFormattedString : value.toString());
	}

	private void setCellValueBasedOnFloat(final ExportExcelComponentConfiguration<BEANTYPE> componentConfiguration,
			final BEANTYPE itemId, int columns, Object value, Cell myCell) {
		if (value instanceof Double) {

			String formattedDouble = FormatUtil.formatFloat((Double) value);
			String customFormattedString = FormatUtil
				.applyColumnFormatter(	componentConfiguration.getVisibleProperties(), componentConfiguration, itemId,
										columns, formattedDouble);
			myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedDouble);
		} else if (value instanceof BigDecimal) {
			String formattedBigDecimal = FormatUtil.formatFloat(((BigDecimal) value).doubleValue());
			String customFormattedString = FormatUtil
				.applyColumnFormatter(	componentConfiguration.getVisibleProperties(), componentConfiguration, itemId,
										columns, formattedBigDecimal);
			myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedBigDecimal);
		}
	}

	/************************************
	 * Generic Code to add Content and Header Section
	 *******************************************/

	/**
	 * Generate report file.
	 *
	 * @return the file
	 */
	@Override
	protected File generateReportFile() {

		File tempFile = null;

		FileOutputStream fileOut = null;
		try {
			tempFile = File.createTempFile("tmp", "." + this.exportType.getExtension());
			fileOut = new FileOutputStream(tempFile);
			this.workbook.write(fileOut);
		} catch (final IOException e) {
			LOGGER.warn("Converting to XLS failed with IOException " + e);
			return null;
		} finally {
			if (tempFile != null) {
				tempFile.deleteOnExit();
			}
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (final IOException e) {
				LOGGER.warn("Closing file to XLS failed with IOException " + e);
			}
		}

		return tempFile;
	}

	/**
	 * Export the workbook to the end-user.
	 * 
	 * Code obtained from:
	 * http://vaadin.com/forum/-/message_boards/view_message/159583
	 * 
	 * @return true, if successful
	 */
	@Override
	protected boolean sendConverted(File file) {
		return super.sendConvertedFileToUser(file, NameGenerationUtil
			.getFilename(	this.exportExcelConfiguration.getExportFileName(),
							this.exportExcelConfiguration.getMaxFilenameCalendarExtension(), this.exportType));
	}

}
