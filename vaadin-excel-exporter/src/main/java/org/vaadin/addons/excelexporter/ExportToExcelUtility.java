/*
 * 
 */
package org.vaadin.addons.excelexporter;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.shared.ui.grid.GridStaticCellType;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.FooterCell;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;

/**
 * The Class ExportToExcelUtility is the core algorithm that generates Excel based on the configurations.
 *
 * @author Kartik Suba
 * @param <BEANTYPE> the generic type
 */
public class ExportToExcelUtility<BEANTYPE> extends ExportUtility {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportToExcelUtility.class);

    /** The is pre processing performed. */
    Boolean isPreProcessingPerformed = Boolean.FALSE;

    /** The workbook. */
    XSSFWorkbook workbook;

    /** The resultant export file name. */
    String resultantExportFileName = null;

    /** The temp report file. */
    File tempReportFile = null;

    /** The default sheet name. */
    final String DEFAULT_SHEET_NAME = "Sheet";

    /** The default file name. */
    String DEFAULT_FILE_NAME = "Export_";

    /** The source UI. */
    UI sourceUI = null;

    /** The export excel configuration. */
    ExportExcelConfiguration exportExcelConfiguration;

    /** The type class. */
    Class typeClass = null;

    /** The method map. */
    Hashtable<String, Method> methodMap = new Hashtable<String, Method>();

    /** The resultant export type. */
    private ExportType resultantExportType = null;

    /**
     * Gets the resultant export type.
     *
     * @return the resultant export type
     */
    public ExportType getResultantExportType() {
        return this.resultantExportType;
    }

    /**
     * Sets the resultant export type.
     *
     * @param resultantExportType the new resultant export type
     */
    public void setResultantExportType(final ExportType resultantExportType) {
        this.resultantExportType = resultantExportType;
    }

    /** The resultant selected extension. */
    private String resultantSelectedExtension;

    /************************************** Constructors *********************************************************/

    public ExportToExcelUtility() {
    }

    /**
     * Instantiates a new export to excel utility.
     *
     * @param ui the ui
     * @param exportExcelConfiguration the export excel configuration
     * @param typeClass the type class
     */
    public ExportToExcelUtility(final UI ui, final ExportExcelConfiguration exportExcelConfiguration, final Class typeClass) {

        this.exportExcelConfiguration = exportExcelConfiguration;
        this.sourceUI = ui;
        this.typeClass = typeClass;
        performPreprocessing(exportExcelConfiguration);
    }

    /************************************* Constructors *********************************************************/

    /************************************** Pre-processing and Book-keeping Information *************************/

    /**
     * This method prepares the initial data for work sheet
     * 
     * @param exportExcelConfiguration the export excel configuration
     */
    protected void performPreprocessing(final ExportExcelConfiguration exportExcelConfiguration) {

        getDefaultFileName();
        this.isPreProcessingPerformed = Boolean.TRUE;
        this.exportExcelConfiguration = exportExcelConfiguration;

        int sheetIndex = 0;
        for (ExportExcelSheetConfiguration sheetConfig : exportExcelConfiguration.getSheetConfigs()) {
            sheetConfig.setResultantSheetName((sheetConfig.getSheetName() != null && !sheetConfig.getSheetName()
                                                                                                 .isEmpty()) ? sheetConfig.getSheetName()
                                                                                                         : this.DEFAULT_SHEET_NAME + sheetIndex + 1);
            sheetConfig.setResultantReportTitleRowContent(sheetConfig.getReportTitleRowContent() != null ? sheetConfig.getReportTitleRowContent()
                    : "Report Name: " + sheetConfig.getReportTitle());
            sheetConfig.setResultantLoggerInfoRowContent(sheetConfig.getLoggerInfoRowContent() != null ? sheetConfig.getLoggerInfoRowContent()
                    : "Report generated by: " + exportExcelConfiguration.getGeneratedBy() + "  on " + formatDate(new Date(), sheetConfig));
            sheetConfig.setResultantColumnForTitleRegion((sheetConfig.getColumnForTitleRegion() != null && sheetConfig.getColumnForTitleRegion()[0] != null)
                    ? sheetConfig.getColumnForTitleRegion() : new Integer[] { 0, 3 });
            sheetConfig.setResultantColumnForGeneratedByRegion((sheetConfig.getColumnForGeneratedByRegion() != null
                    && sheetConfig.getColumnForGeneratedByRegion()[0] != null) ? sheetConfig.getColumnForGeneratedByRegion() : new Integer[] { 0, 3 });
            sheetConfig.setResultantHeaderCaptionStartCol(sheetConfig.getHeaderCaptionStartCol());
            sheetConfig.setResultantHeaderValueStartCol(sheetConfig.getHeaderValueStartCol());
            sheetIndex++;
        }
        this.resultantExportType = exportExcelConfiguration.getExportType();

        // one's. Asynchronous Call.
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                getAllMethods(ExportToExcelUtility.this.typeClass, ExportToExcelUtility.this.methodMap);
            }
        });
        thread.start();
    }

    /**
     * Gets the default file name.
     *
     * 
     */
    public void getDefaultFileName() {
        Calendar calendar = Calendar.getInstance();
        this.DEFAULT_FILE_NAME = this.DEFAULT_FILE_NAME + calendar.get(Calendar.DATE) + "_" + (calendar.get(Calendar.MONTH) + 1) + "_"
                + calendar.get(Calendar.YEAR) + "__" + calendar.get(Calendar.HOUR) + "_" + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.SECOND);
    }

    /**
     * Perform initialization. Creates work sheet and initializes styles
     */
    protected void performInitialization() {

        if (this.resultantExportType.equals(ExportType.XLS)) {
            this.resultantSelectedExtension = "xls";
        }
        else {
            this.resultantSelectedExtension = "xlsx";
        }
        Calendar calendar = Calendar.getInstance();
        this.resultantExportFileName = ((this.exportExcelConfiguration.getExportFileName() != null && !this.exportExcelConfiguration.getExportFileName()
                                                                                                                                    .isEmpty())
                                                                                                                                            ? this.exportExcelConfiguration.getExportFileName()
                                                                                                                                                    + "_"
                                                                                                                                                    + calendar.get(Calendar.YEAR)
                                                                                                                                                    + "_"
                                                                                                                                                    + (calendar.get(Calendar.MONTH)
                                                                                                                                                            + 1)
                                                                                                                                                    + "_"
                                                                                                                                                    + calendar.get(Calendar.DATE)
                                                                                                                                            : this.DEFAULT_FILE_NAME)
                + "." + this.resultantSelectedExtension;

        this.workbook = new XSSFWorkbook();

        // Initializing Default Styles
        for (ExportExcelSheetConfiguration sheetConfig : this.exportExcelConfiguration.getSheetConfigs()) {
            sheetConfig.setSheet(this.workbook.createSheet(sheetConfig.getResultantSheetName()));
            sheetConfig.setrReportTitleStyle((sheetConfig.getReportTitleStyle() != null) ? sheetConfig.getReportTitleStyle()
                    : getDefaultReportTitleStyle(this.workbook));
            sheetConfig.setrGeneratedByStyle((sheetConfig.getGeneratedByStyle() != null) ? sheetConfig.getGeneratedByStyle()
                    : getDefaultReportTitleStyle(this.workbook));
            sheetConfig.setrHeaderCaptionStyle((sheetConfig.getHeaderCaptionStyle() != null) ? sheetConfig.getHeaderCaptionStyle()
                    : getDefaultHeaderCaptionStyle(this.workbook));
            sheetConfig.setrHeaderValueStyle((sheetConfig.getHeaderValueStyle() != null) ? sheetConfig.getHeaderValueStyle()
                    : getDefaultHeaderValueStyle(this.workbook));
            sheetConfig.setrAdditionalHeaderCaptionStyle((sheetConfig.getAdditionalHeaderCaptionStyle() != null) ? sheetConfig.getAdditionalHeaderCaptionStyle()
                    : getDefaultAddHeaderCaptionStyle(this.workbook));
            sheetConfig.setrAdditionalHeaderValueStyle((sheetConfig.getAdditionalHeaderValueStyle() != null) ? sheetConfig.getAdditionalHeaderValueStyle()
                    : getDefaultAddHeaderValueStyle(this.workbook));

            for (ExportExcelComponentConfiguration componentConfig : sheetConfig.getComponentConfigs()) {
                componentConfig.setrTableHeaderStyle((componentConfig.getTableHeaderStyle() != null) ? componentConfig.getTableHeaderStyle()
                        : getDefaultTableHeaderStyle(this.workbook));
                componentConfig.setrTableFooterStyle((componentConfig.getTableFooterStyle() != null) ? componentConfig.getTableFooterStyle()
                        : getDefaultTableFooterStyle(this.workbook));
                componentConfig.setrTableContentStyle((componentConfig.getTableContentStyle() != null) ? componentConfig.getTableContentStyle()
                        : getDefaultTableContentStyle(this.workbook));
            }
        }
    }

    /**
     * Adds the report title at first.
     *
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @return the integer
     */
    protected Integer addReportTitleAtFirst(final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration) {

        if (sheetConfiguration.getReportTitle() != null) {
            // Creating Report Name Row
            Row myHeaderRow1 = sheetConfiguration.getSheet()
                                                 .createRow(sheetConfiguration.getDefaultSheetRowNum());
            Cell myHeaderCell1 = myHeaderRow1.createCell(0);
            myHeaderCell1.setCellValue(sheetConfiguration.getResultantReportTitleRowContent());
            myHeaderCell1.setCellStyle(sheetConfiguration.getrReportTitleStyle());
            sheetConfiguration.getSheet()
                              .addMergedRegion(new CellRangeAddress(sheetConfiguration.getDefaultSheetRowNum(), sheetConfiguration.getDefaultSheetRowNum(),
                                      sheetConfiguration.getResultantColumnForTitleRegion()[0], sheetConfiguration.getResultantColumnForTitleRegion()[1]));

            // Adding AutoBreaks
            sheetConfiguration.getSheet()
                              .setAutobreaks(true);

            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
            // Adding an empty row after title
        }

        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /**
     * Adds the generated by at first.
     *
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @return the integer
     */
    protected Integer addGeneratedByAtFirst(final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration) {

        if (sheetConfiguration.getResultantLoggerInfoRowContent() != null && !sheetConfiguration.getResultantLoggerInfoRowContent()
                                                                                                .isEmpty()) {
            // Creating Generated By Row
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
            // Adding an empty row before generated by
            Row myHeaderRow2 = sheetConfiguration.getSheet()
                                                 .createRow(sheetConfiguration.getDefaultSheetRowNum());
            Cell myHeaderCell2 = myHeaderRow2.createCell(0);
            myHeaderCell2.setCellValue(sheetConfiguration.getResultantLoggerInfoRowContent());
            myHeaderCell2.setCellStyle(sheetConfiguration.getrGeneratedByStyle());
            sheetConfiguration.getSheet()
                              .addMergedRegion(new CellRangeAddress(sheetConfiguration.getDefaultSheetRowNum(), sheetConfiguration.getDefaultSheetRowNum(),
                                      sheetConfiguration.getResultantColumnForGeneratedByRegion()[0],
                                      sheetConfiguration.getResultantColumnForGeneratedByRegion()[1]));

            // Adding AutoBreaks
            sheetConfiguration.getSheet()
                              .setAutobreaks(true);

            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
            // Adding an empty row after generated by
        }

        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /************************************* Pre-processing and Book-keeping Information *************************/

    /************************************* Adding Vaadin Grid To Sheet *******************************************/

    /**
     * Adds the Vaadin Grid to the Excel Sheet
     *
     * @param grid the grid
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer addVaadinGridToExcelSheet(final Grid grid, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {
        Integer stIdx = sheetConfiguration.getDefaultSheetRowNum();

        if (this.workbook.getSheet(sheetConfiguration.getResultantSheetName()) != null) {
            sheetConfiguration.setSheet(this.workbook.getSheet(sheetConfiguration.getResultantSheetName()));
        }
        else {
            sheetConfiguration.setSheet(this.workbook.createSheet(sheetConfiguration.getResultantSheetName()));
        }

        if (sheetConfiguration.getIsHeaderSectionRequired()) {
            stIdx = createGridHeaderSection(grid, myWorkBook, sheetConfiguration, componentConfiguration);
        }

        stIdx = createGridContent(grid, myWorkBook, sheetConfiguration, componentConfiguration);

        return stIdx;
    }

    /**
     * Creates the grid header section.
     *
     * @param sourceTable the source table
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGridHeaderSection(final Grid sourceTable, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        return createGenericHeaderSection(null, null, myWorkBook, sheetConfiguration, componentConfiguration);

    }

    /**
     * Creates the grid content.
     *
     * @param grid the grid
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGridContent(final Grid grid, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        return createGenericContent(grid.getContainerDataSource()
                                        .getItemIds(),
                                    myWorkBook, sheetConfiguration, componentConfiguration);
    }

    /************************************ Adding Vaadin Grid To Sheet *******************************************/

    /************************************* Adding Vaadin Table To Sheet *******************************************/
    /**
     * Adds the Vaadin Table to the Excel Sheet
     *
     * @param grid the grid
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer addTableToExcelSheet(final Table grid, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {
        Integer stIdx = sheetConfiguration.getDefaultSheetRowNum();

        if (this.workbook.getSheet(sheetConfiguration.getResultantSheetName()) != null) {
            sheetConfiguration.setSheet(this.workbook.getSheet(sheetConfiguration.getResultantSheetName()));
        }
        else {
            sheetConfiguration.setSheet(this.workbook.createSheet(sheetConfiguration.getResultantSheetName()));
        }

        if (sheetConfiguration.getIsHeaderSectionRequired()) {
            stIdx = createGridHeaderSection(grid, myWorkBook, sheetConfiguration, componentConfiguration);
        }

        stIdx = createGridContent(grid, myWorkBook, sheetConfiguration, componentConfiguration);

        return stIdx;
    }

    /**
     * Creates the grid header section.
     *
     * @param sourceTable the source table
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGridHeaderSection(final Table sourceTable, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        return createGenericHeaderSection(null, null, myWorkBook, sheetConfiguration, componentConfiguration);

    }

    /**
     * Creates the grid content.
     *
     * @param grid the grid
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGridContent(final Table grid, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {
        return createGenericContent(grid.getContainerDataSource()
                                        .getItemIds(),
                                    myWorkBook, sheetConfiguration, componentConfiguration);
    }

    /************************************ Adding Vaadin Table To Sheet *******************************************/

    /************************************ Adding Tree Table To Sheet ***********************************************/

    /**
     * Adds Tree Table to the Excel Sheet
     *
     * @param treeTable the tree table
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer addTreeTableToExcelSheet(final TreeTable treeTable, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {
        Integer stIdx = sheetConfiguration.getDefaultSheetRowNum();

        if (this.workbook.getSheet(sheetConfiguration.getResultantSheetName()) != null) {
            sheetConfiguration.setSheet(this.workbook.getSheet(sheetConfiguration.getResultantSheetName()));
        }
        else {
            sheetConfiguration.setSheet(this.workbook.createSheet(sheetConfiguration.getResultantSheetName()));
        }

        if (sheetConfiguration.getIsHeaderSectionRequired()) {
            stIdx = createTreeTableHeaderSection(treeTable, myWorkBook, sheetConfiguration, componentConfiguration);
        }

        stIdx = createTreeTableContent(treeTable, myWorkBook, sheetConfiguration, componentConfiguration);

        // stIdx = createGenericContent(treeTable.getContainerDataSource()
        // .getItemIds(), myWorkBook, sheetConfiguration, componentConfiguration);

        return stIdx;
    }

    /**
     * Creates the tree table header section.
     *
     * @param treeTable the tree table
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createTreeTableHeaderSection(final TreeTable treeTable, final XSSFWorkbook myWorkBook,
            final ExportExcelSheetConfiguration sheetConfiguration, final ExportExcelComponentConfiguration componentConfiguration) {

        return createGenericHeaderSection(null, null, myWorkBook, sheetConfiguration, componentConfiguration);

    }

    /**
     * Creates the tree table content.
     *
     * @param treeTable the tree table
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createTreeTableContent(final TreeTable treeTable, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        final Collection<?> itemIds = treeTable.getContainerDataSource()
                                               .getItemIds();

        if (sheetConfiguration.getIsHeaderSectionAdded()) {
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        sheetConfiguration.getSheet()
                          .setRowBreak(sheetConfiguration.getDefaultSheetRowNum());

        if (componentConfiguration.getColRowFreeze() != null) {
            sheetConfiguration.getSheet()
                              .createFreezePane(componentConfiguration.getColRowFreeze(), sheetConfiguration.getDefaultSheetRowNum());
        }

        // Adding Column Headers
        if (componentConfiguration.getHeaderConfigs() != null) {
            addGenericHeaderRows(sheetConfiguration, componentConfiguration);
        }

        for (final Object itemId : itemIds) {

            addGenericDataRow(componentConfiguration.getVisibleProperties(), myWorkBook, sheetConfiguration, componentConfiguration, itemId,
                              sheetConfiguration.getDefaultSheetRowNum(), treeTable.getContainerDataSource()
                                                                                   .hasChildren(itemId));
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        // Adding Configured Footer Rows
        if (componentConfiguration.getFooterConfigs() != null) {
            addGenericFooterRows(sheetConfiguration, componentConfiguration);
        }

        // Disabling auto columns for each column
        for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
            sheetConfiguration.getSheet()
                              .autoSizeColumn(columns, false);
        }

        // Adding auto filter in the Excel Header
        // sheetConfiguration.getSheet()
        // .setAutoFilter(new CellRangeAddress(firstRow, sheetConfiguration.getDefaultSheetRowNum(), 0,
        // componentConfiguration.getVisibleProperties().length - 1));

        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /*********************************** Adding Tree Table To Sheet ***********************************************/

    /************************************* Generic Code to add Content and Header Section *******************************************/
    /**
     * Adds the filter field group section as well as a Name, Value paired additional header section
     *
     * @param headerFilterGroup the header filter group
     * @param visiblePropsInHeader the visible props in header
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGenericHeaderSection(final FieldGroup headerFilterGroup, final String[] visiblePropsInHeader, final XSSFWorkbook myWorkBook,
            final ExportExcelSheetConfiguration sheetConfiguration, final ExportExcelComponentConfiguration componentConfiguration) {
        if (headerFilterGroup != null && visiblePropsInHeader != null) {

            sheetConfiguration.setIsHeaderSectionAdded(true);
            int rowCounter = 0;
            Row row = null;

            for (String property : visiblePropsInHeader) {
                if (headerFilterGroup.getField(property) != null) {
                    if (visiblePropsInHeader.length == 1) {
                        row = sheetConfiguration.getSheet()
                                                .createRow(sheetConfiguration.getDefaultSheetRowNum());
                        sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
                    }
                    else if (rowCounter % (sheetConfiguration.getNoOfColumnsInHeader()) != 0) {
                        sheetConfiguration.setResultantHeaderCaptionStartCol(sheetConfiguration.getResultantHeaderCaptionStartCol() + 2);
                        sheetConfiguration.setResultantHeaderValueStartCol(sheetConfiguration.getResultantHeaderValueStartCol() + 2);
                    }
                    else {
                        sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
                        sheetConfiguration.setResultantHeaderCaptionStartCol(0);
                        sheetConfiguration.setResultantHeaderValueStartCol(1);
                        row = sheetConfiguration.getSheet()
                                                .createRow(sheetConfiguration.getDefaultSheetRowNum());
                    }

                    Cell captionCell = row.createCell(sheetConfiguration.getResultantHeaderCaptionStartCol());
                    captionCell.setCellValue(headerFilterGroup.getField(property)
                                                              .getCaption());
                    captionCell.setCellStyle(sheetConfiguration.getrHeaderCaptionStyle());

                    Cell valueCell = row.createCell(sheetConfiguration.getResultantHeaderValueStartCol());
                    valueCell.setCellStyle(sheetConfiguration.getrHeaderValueStyle());
                    if (headerFilterGroup.getField(property)
                                         .getValue() != null) {
                        valueCell.setCellValue(headerFilterGroup.getField(property)
                                                                .getValue()
                                                                .toString());
                    }
                    else {
                        valueCell.setCellValue("");
                    }

                    rowCounter++;
                }
            }
        }

        int additionalInfoCounter = 0;
        if (sheetConfiguration.getAdditionalHeaderInfo() != null && !sheetConfiguration.getAdditionalHeaderInfo()
                                                                                       .isEmpty()) {

            if (sheetConfiguration.getIsHeaderSectionAdded()) {
                sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1); // Adding
                                                                                                          // an
                                                                                                          // empty
                                                                                                          // row
                                                                                                          // after
                                                                                                          // Header
                // Section
            }
            else {

                sheetConfiguration.setIsHeaderSectionAdded(Boolean.TRUE);
            }

            Iterator<Map.Entry<String, String>> it = sheetConfiguration.getAdditionalHeaderInfo()
                                                                       .entrySet()
                                                                       .iterator();
            Row row = null;

            while (it.hasNext()) {
                Map.Entry<String, String> me = it.next();
                if (additionalInfoCounter % (sheetConfiguration.getNoOfColumnsInAddHeader()) != 0) {
                    sheetConfiguration.setResultantHeaderCaptionStartCol(sheetConfiguration.getResultantHeaderCaptionStartCol()
                            + sheetConfiguration.getNoOfColumnsToMergeInAddHeaderValue() + 1);
                    sheetConfiguration.setResultantHeaderValueStartCol(sheetConfiguration.getResultantHeaderValueStartCol()
                            + sheetConfiguration.getNoOfColumnsToMergeInAddHeaderValue() + 1);
                }
                else {
                    sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
                    sheetConfiguration.setResultantHeaderCaptionStartCol(0);
                    sheetConfiguration.setResultantHeaderValueStartCol(1);
                    row = sheetConfiguration.getSheet()
                                            .createRow(sheetConfiguration.getDefaultSheetRowNum());
                }

                Cell captionCell = row.createCell(sheetConfiguration.getResultantHeaderCaptionStartCol());
                captionCell.setCellValue(me.getKey());
                captionCell.setCellStyle(sheetConfiguration.getrAdditionalHeaderCaptionStyle());

                Cell valueCell = row.createCell(sheetConfiguration.getResultantHeaderValueStartCol());
                valueCell.setCellStyle(sheetConfiguration.getrAdditionalHeaderValueStyle());

                CellRangeAddress valueMerged = new CellRangeAddress(sheetConfiguration.getDefaultSheetRowNum(), sheetConfiguration.getDefaultSheetRowNum(),
                        sheetConfiguration.getResultantHeaderValueStartCol(),
                        sheetConfiguration.getResultantHeaderValueStartCol() + (sheetConfiguration.getNoOfColumnsToMergeInAddHeaderValue() - 1));
                sheetConfiguration.getSheet()
                                  .addMergedRegion(valueMerged);

                if (me.getValue() != null) {
                    valueCell.setCellValue(me.getValue());
                }
                else {
                    valueCell.setCellValue("");
                }

                additionalInfoCounter++;
            }
        }

        if (sheetConfiguration.getIsHeaderSectionAdded()) {
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1); // Adding
                                                                                                      // an
                                                                                                      // empty
                                                                                                      // row
                                                                                                      // after
                                                                                                      // Additional
            // Header Section
        }

        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /**
     * Creates the generic content. Adds the component header, data, and footer sections
     *
     * @param itemIds the item ids
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer createGenericContent(final Collection<?> itemIds, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        if (sheetConfiguration.getIsHeaderSectionAdded()) {
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        sheetConfiguration.getSheet()
                          .setRowBreak(sheetConfiguration.getDefaultSheetRowNum());

        if (componentConfiguration.getColRowFreeze() != null) {
            sheetConfiguration.getSheet()
                              .createFreezePane(componentConfiguration.getColRowFreeze(), sheetConfiguration.getDefaultSheetRowNum());
        }

        // Adding Configured Header Rows
        if (componentConfiguration.getHeaderConfigs() != null) {
            addGenericHeaderRows(sheetConfiguration, componentConfiguration);
        }

        for (final Object itemId : itemIds) {
            addGenericDataRow(componentConfiguration.getVisibleProperties(), myWorkBook, sheetConfiguration, componentConfiguration, itemId,
                              sheetConfiguration.getDefaultSheetRowNum(), Boolean.FALSE);
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        // Adding Configured Footer Rows
        if (componentConfiguration.getFooterConfigs() != null) {
            addGenericFooterRows(sheetConfiguration, componentConfiguration);
        }

        // Disabling auto columns for each column
        for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
            sheetConfiguration.getSheet()
                              .autoSizeColumn(columns, false);
        }

        // Adding auto filter in the Excel Header
        // sheetConfiguration.getSheet()
        // .setAutoFilter(new CellRangeAddress(firstRow, sheetConfiguration.getDefaultSheetRowNum(), 0,
        // componentConfiguration.getVisibleProperties().length - 1));

        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /**
     * Adds the generic header rows as configured in the component configuration
     *
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer addGenericHeaderRows(final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        for (ComponentHeaderConfiguration headerConfig : componentConfiguration.getHeaderConfigs()) {
            Row myRow = sheetConfiguration.getSheet()
                                          .createRow(sheetConfiguration.getDefaultSheetRowNum());

            int startMerge = -999;
            int endMerge = -999;
            String mergedText = "-";
            for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
                Cell myCell = myRow.createCell(columns, XSSFCell.CELL_TYPE_STRING);
                if (headerConfig.getMergedCells() != null) {
                    for (MergedCell joinedHeader : headerConfig.getMergedCells()) {
                        if (joinedHeader.getStartProperty()
                                        .equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
                            startMerge = columns;
                            mergedText = joinedHeader.getHeaderKey();
                            myCell.setCellValue(mergedText);
                            break;
                        }
                        else if (joinedHeader.getEndProperty()
                                             .equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
                            endMerge = columns;
                            sheetConfiguration.getSheet()
                                              .addMergedRegion(new CellRangeAddress(sheetConfiguration.getDefaultSheetRowNum(),
                                                      sheetConfiguration.getDefaultSheetRowNum(), startMerge, endMerge));
                            break;
                        }
                        else {

                            if (headerConfig.getHeaderRow() != null) {
                                addGenericGridHeaderRow(headerConfig.getHeaderRow()
                                                                    .getCell(componentConfiguration.getVisibleProperties()[columns]),
                                                        myCell);
                            }
                            else if (headerConfig.getColumnHeaderKeys() != null) {
                                myCell.setCellValue(headerConfig.getColumnHeaderKeys()[columns]);
                            }

                        }
                    }
                    sheetConfiguration.getSheet()
                                      .autoSizeColumn(columns, false);
                }
                else {
                    if (headerConfig.getHeaderRow() != null) {
                        addGenericGridHeaderRow(headerConfig.getHeaderRow()
                                                            .getCell(componentConfiguration.getVisibleProperties()[columns]),
                                                myCell);
                    }
                    else if (headerConfig.getColumnHeaderKeys() != null) {
                        myCell.setCellValue(headerConfig.getColumnHeaderKeys()[columns]);
                    }
                }
                myCell.setCellStyle(componentConfiguration.getrTableHeaderStyle());
            }
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        // Add All the headers
        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /**
     * Adds the generic footer rows as configured in the component configuration
     *
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @return the integer
     */
    protected Integer addGenericFooterRows(final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration) {

        for (ComponentFooterConfiguration footerConfig : componentConfiguration.getFooterConfigs()) {
            Row myRow = sheetConfiguration.getSheet()
                                          .createRow(sheetConfiguration.getDefaultSheetRowNum());

            int startMerge = -999;
            int endMerge = -999;
            String mergedText = "-";
            for (int columns = 0; columns < componentConfiguration.getVisibleProperties().length; columns++) {
                Cell myCell = myRow.createCell(columns, XSSFCell.CELL_TYPE_STRING);
                if (footerConfig.getMergedCells() != null) {
                    for (MergedCell joinedHeader : footerConfig.getMergedCells()) {
                        if (joinedHeader.getStartProperty()
                                        .equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
                            startMerge = columns;
                            mergedText = joinedHeader.getHeaderKey();
                            myCell.setCellValue(mergedText);
                            break;
                        }
                        else if (joinedHeader.getEndProperty()
                                             .equalsIgnoreCase(String.valueOf(componentConfiguration.getVisibleProperties()[columns]))) {
                            endMerge = columns;
                            sheetConfiguration.getSheet()
                                              .addMergedRegion(new CellRangeAddress(sheetConfiguration.getDefaultSheetRowNum(),
                                                      sheetConfiguration.getDefaultSheetRowNum(), startMerge, endMerge));
                            break;
                        }
                        else {

                            if (footerConfig.getFooterRow() != null) {
                                addGenericGridFooterRow(footerConfig.getFooterRow()
                                                                    .getCell(componentConfiguration.getVisibleProperties()[columns]),
                                                        myCell);
                            }
                            else if (footerConfig.getColumnFooterKeys() != null) {
                                myCell.setCellValue(footerConfig.getColumnFooterKeys()[columns]);
                            }

                        }
                    }
                    sheetConfiguration.getSheet()
                                      .autoSizeColumn(columns, false);
                }
                else {
                    if (footerConfig.getFooterRow() != null) {
                        addGenericGridFooterRow(footerConfig.getFooterRow()
                                                            .getCell(componentConfiguration.getVisibleProperties()[columns]),
                                                myCell);
                    }
                    else if (footerConfig.getColumnFooterKeys() != null) {
                        myCell.setCellValue(footerConfig.getColumnFooterKeys()[columns]);
                    }
                }
                myCell.setCellStyle(componentConfiguration.getrTableFooterStyle());
            }
            sheetConfiguration.setDefaultSheetRowNum(sheetConfiguration.getDefaultSheetRowNum() + 1);
        }

        // Add All the headers
        return sheetConfiguration.getDefaultSheetRowNum();
    }

    /**
     * Adds the generic grid header row configured in the header configs
     *
     * @param gridHeaderCell the grid header cell
     * @param myCell the my cell
     */
    private void addGenericGridHeaderRow(final HeaderCell gridHeaderCell, final Cell myCell) {

        if (gridHeaderCell.getCellType()
                          .equals(GridStaticCellType.TEXT)) {
            myCell.setCellValue(gridHeaderCell.getText());
        }
        else if (gridHeaderCell.getCellType()
                               .equals(GridStaticCellType.HTML)) {
            myCell.setCellValue(gridHeaderCell.getHtml());
        }
        else if (gridHeaderCell.getCellType()
                               .equals(GridStaticCellType.WIDGET)) {
            myCell.setCellValue(gridHeaderCell.getComponent()
                                              .toString());
        }
    }

    /**
     * Adds the generic grid footer row configured in the footer configs
     *
     * @param gridHeaderCell the grid header cell
     * @param myCell the my cell
     */
    private void addGenericGridFooterRow(final FooterCell gridHeaderCell, final Cell myCell) {

        if (gridHeaderCell.getCellType()
                          .equals(GridStaticCellType.TEXT)) {
            myCell.setCellValue(gridHeaderCell.getText());
        }
        else if (gridHeaderCell.getCellType()
                               .equals(GridStaticCellType.HTML)) {
            myCell.setCellValue(gridHeaderCell.getHtml());
        }
        else if (gridHeaderCell.getCellType()
                               .equals(GridStaticCellType.WIDGET)) {
            myCell.setCellValue(gridHeaderCell.getComponent()
                                              .toString());
        }
    }

    /**
     * Adds the generic data row. Applies formatting to the cells as configured.
     *
     * @param visibleColumns the visible columns
     * @param myWorkBook the my work book
     * @param sheetConfiguration the sheet configuration
     * @param componentConfiguration the component configuration
     * @param itemId the item id
     * @param localRow the local row
     * @param isParent the is parent
     */
    protected void addGenericDataRow(final Object[] visibleColumns, final XSSFWorkbook myWorkBook, final ExportExcelSheetConfiguration sheetConfiguration,
            final ExportExcelComponentConfiguration componentConfiguration, final Object itemId, final Integer localRow, final Boolean isParent) {
        Row myRow = sheetConfiguration.getSheet()
                                      .createRow(localRow);
        try {

            for (int columns = 0; columns < visibleColumns.length; columns++) {

                Object obj = null;
                if (componentConfiguration.getTable() != null && componentConfiguration.getTable()
                                                                                       .getContainerDataSource() instanceof IndexedContainer) {
                    obj = componentConfiguration.getTable()
                                                .getItem(itemId)
                                                .getItemProperty(visibleColumns[columns])
                                                .getValue();
                    if (componentConfiguration.getTable()
                                              .getColumnGenerator(visibleColumns[columns]) != null) {
                        try {
                            obj = componentConfiguration.getTable()
                                                        .getColumnGenerator(visibleColumns[columns])
                                                        .generateCell(componentConfiguration.getTable(), itemId, visibleColumns[columns]);
                            if (obj != null && !(obj instanceof Component) && !(obj instanceof String)) {
                                // Avoid errors if a generator returns
                                // something
                                // other than a Component or a String
                                obj = obj.toString();
                            }
                        }
                        catch (Exception e) {
                        }
                    }

                }
                else if (componentConfiguration.getGrid() != null && componentConfiguration.getGrid()
                                                                                           .getContainerDataSource() instanceof IndexedContainer) {
                    obj = componentConfiguration.getGrid()
                                                .getContainerDataSource()
                                                .getContainerProperty(itemId, visibleColumns[columns])
                                                .getValue();
                }
                else if (componentConfiguration.getGrid() != null && componentConfiguration.getGrid()
                                                                                           .getContainerDataSource() instanceof GeneratedPropertyContainer) {
                    if (((GeneratedPropertyContainer) componentConfiguration.getGrid()
                                                                            .getContainerDataSource()).getWrappedContainer() != null) {
                        obj = ((GeneratedPropertyContainer) componentConfiguration.getGrid()
                                                                                  .getContainerDataSource()).getWrappedContainer()
                                                                                                            .getContainerProperty(itemId,
                                                                                                                                  visibleColumns[columns])
                                                                                                            .getValue();
                    }
                }
                else if (this.methodMap.containsKey(String.valueOf(visibleColumns[columns]))) {
                    // This condition takes care of the first level properties of the bean
                    obj = this.methodMap.get(String.valueOf(visibleColumns[columns]))
                                        .invoke(itemId);
                }
                else if (componentConfiguration.getGrid() != null && componentConfiguration.getGrid()
                                                                                           .getContainerDataSource() instanceof BeanItemContainer) {

                    // These properties cannot be taken care by the above method map solution
                    // This code is mainly to include the nested properties
                    obj = componentConfiguration.getGrid()
                                                .getContainerDataSource()
                                                .getContainerProperty(itemId, visibleColumns[columns])
                                                .getValue();
                }

                Cell myCell = myRow.createCell(columns, XSSFCell.CELL_TYPE_STRING);

                if ((obj != null)) {
                    if (componentConfiguration.getDateFormattingProperties() != null && componentConfiguration.getDateFormattingProperties()
                                                                                                              .contains(String.valueOf(visibleColumns[columns]))) {
                        myCell.setCellValue(formatDate((Date) obj, sheetConfiguration));
                    }
                    else if (componentConfiguration.getIntegerFormattingProperties() != null && componentConfiguration.getIntegerFormattingProperties()
                                                                                                                      .contains(String.valueOf(visibleColumns[columns]))) {

                        String formattedInteger = localizedFormat(obj != null && !String.valueOf(obj)
                                                                                        .isEmpty() ? String.valueOf(obj) : null,
                                                                  Boolean.TRUE);
                        String customFormattedString = applyColumnFormatter(visibleColumns, componentConfiguration, itemId, columns, formattedInteger);
                        myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedInteger);
                    }
                    else if (componentConfiguration.getFloatFormattingProperties() != null && componentConfiguration.getFloatFormattingProperties()
                                                                                                                    .contains(String.valueOf(visibleColumns[columns]))) {
                        if (obj instanceof Double) {

                            String formattedDouble = formatFloat((Double) obj);
                            String customFormattedString = applyColumnFormatter(visibleColumns, componentConfiguration, itemId, columns, formattedDouble);
                            myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedDouble);
                        }
                        else if (obj instanceof BigDecimal) {
                            {
                                String formattedBigDecimal = formatFloat(((BigDecimal) obj).doubleValue());
                                String customFormattedString = applyColumnFormatter(visibleColumns, componentConfiguration, itemId, columns,
                                                                                    formattedBigDecimal);
                                myCell.setCellValue(customFormattedString != null ? customFormattedString : formattedBigDecimal);
                            }
                        }
                    }
                    else if (componentConfiguration.getBooleanFormattingProperties() != null && componentConfiguration.getBooleanFormattingProperties()
                                                                                                                      .contains(String.valueOf(visibleColumns[columns]))) {
                        String customFormattedString = applyColumnFormatter(visibleColumns, componentConfiguration, itemId, columns,
                                                                            Boolean.valueOf((boolean) obj));
                        myCell.setCellValue(customFormattedString != null ? customFormattedString : obj.toString());
                    }
                    else {

                        String customFormattedString = applyColumnFormatter(visibleColumns, componentConfiguration, itemId, columns, obj);
                        myCell.setCellValue(customFormattedString != null ? customFormattedString : obj.toString());
                    }
                }
                else {
                    myCell.setCellValue("");
                }

                if (isParent) {
                    myCell.setCellStyle(getDefaultTableContentParentStyle(myWorkBook));
                }
                if (localRow % 2 == 0) {
                    myCell.setCellStyle(isParent ? getDefaultTableContentParentStyle(myWorkBook) : componentConfiguration.getrTableContentStyle());
                }

            }
        }
        catch (Exception e) {
            ExportToExcelUtility.LOGGER.error("addGenericDataRow" + ") throws + " + e.getMessage());
        }
    }

    /**
     * Apply column formatter.
     *
     * @param visibleColumns the visible columns
     * @param componentConfiguration the component configuration
     * @param itemId the item id
     * @param columns the columns
     * @param obj the obj
     * @return the string
     */
    private String applyColumnFormatter(final Object[] visibleColumns, final ExportExcelComponentConfiguration componentConfiguration, final Object itemId,
            final int columns, final Object obj) {
        String formatted = null;
        if (componentConfiguration.getColumnFormatter(visibleColumns[columns]) != null) {
            try {
                formatted = (String) componentConfiguration.getColumnFormatter(visibleColumns[columns])
                                                           .generateCell(obj, itemId, visibleColumns[columns]);
            }
            catch (Exception e) {
            }
        }
        return formatted;
    }

    /**
     * Gets the all methods. Creates a cache of method names from the bound BEANTYPE
     *
     * @param type the type
     * @param map the map
     * 
     */
    public void getAllMethods(Class type, final Hashtable<String, Method> map) {

        while (type != Object.class) {
            for (final Field field : type.getDeclaredFields()) {

                if (!field.getName()
                          .equals("serialVersionUID")) {
                    field.setAccessible(true);

                    String name = field.getName()
                                       .substring(0, 1)
                                       .toUpperCase()
                                       .concat(field.getName()
                                                    .substring(1));
                    Method method = null;
                    try {
                        method = type.getMethod("get" + name);
                    }
                    catch (NoSuchMethodException | SecurityException e) {
                        ExportToExcelUtility.LOGGER.info("getAllMethods" + ") throws + " + e.getMessage());
                    }
                    if (method == null) {
                        try {
                            method = type.getMethod("is" + name);
                        }
                        catch (NoSuchMethodException | SecurityException e) {
                            ExportToExcelUtility.LOGGER.info("getAllMethods" + ") throws + " + e.getMessage());
                        }
                    }
                    if (method != null) {
                        map.put(field.getName(), method);
                    }
                    else {
                        new NoSuchMethodException(
                                type.getCanonicalName() + ".get" + name + " or " + type.getCanonicalName() + ".is" + name + " not found!").printStackTrace();
                    }
                }
            }
            type = type.getSuperclass();
        }

    }

    /**
     * Gets the all fields of the bound BEANTYPE
     *
     * @param fields the fields
     * @param type the type
     * @param map the map
     * @return the all fields
     */
    public List<Field> getAllFields(List<Field> fields, final Class<?> type, final Map<String, Field> map) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass(), map);
        }

        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        return fields;
    }

    /************************************ Generic Code to add Content and Header Section *******************************************/

    /************************************* Using above methods to add data in to Excel *******************************************/
    /**
     * Adds configured sheets and components within
     *
     * @return the boolean
     */
    protected Boolean addConfiguredComponentToExcel() {

        if (this.isPreProcessingPerformed) {
            performInitialization();

            for (ExportExcelSheetConfiguration sheetConfig : this.exportExcelConfiguration.getSheetConfigs()) {
                if (sheetConfig.getIsDefaultSheetTitleRequired()) {
                    sheetConfig.setDefaultSheetRowNum(addReportTitleAtFirst(this.workbook, sheetConfig));
                }

                if (sheetConfig.getIsDefaultGeneratedByRequired()) {
                    sheetConfig.setDefaultSheetRowNum(addGeneratedByAtFirst(this.workbook, sheetConfig));
                }

                for (ExportExcelComponentConfiguration componentConfig : sheetConfig.getComponentConfigs()) {
                    if (componentConfig.getGrid() != null) {
                        sheetConfig.setDefaultSheetRowNum(addVaadinGridToExcelSheet(componentConfig.getGrid(), this.workbook, sheetConfig, componentConfig));
                    }

                    if (componentConfig.getTreeTable() != null) {
                        sheetConfig.setDefaultSheetRowNum(addTreeTableToExcelSheet(componentConfig.getTreeTable(), this.workbook, sheetConfig,
                                                                                   componentConfig));
                    }

                    if (componentConfig.getTable() != null) {
                        sheetConfig.setDefaultSheetRowNum(addTableToExcelSheet(componentConfig.getTable(), this.workbook, sheetConfig, componentConfig));
                    }
                    sheetConfig.setDefaultSheetRowNum(sheetConfig.getDefaultSheetRowNum() + 1);
                }
            }
        }

        return this.isPreProcessingPerformed;
    }

    /**
     * Generate report file.
     *
     * @return the file
     */
    @Override
    protected File generateReportFile() {

        Boolean proceed = addConfiguredComponentToExcel();
        File tempFile = null;
        if (proceed) {

            FileOutputStream fileOut = null;
            try {
                tempFile = File.createTempFile("tmp", "." + this.resultantSelectedExtension);
                fileOut = new FileOutputStream(tempFile);
                this.workbook.write(fileOut);
                if (null == this.mimeType) {
                    setMimeType(EXCEL_MIME_TYPE);
                }
            }
            catch (final IOException e) {
                LOGGER.warn("Converting to XLS failed with IOException " + e);
                return null;
            }
            finally {
                if (tempFile != null) {
                    tempFile.deleteOnExit();
                }
                try {
                    if (fileOut != null) {
                        fileOut.close();
                    }
                }
                catch (final IOException e) {
                }
            }

            this.tempReportFile = tempFile;
        }
        return tempFile;
    }

    /**
     * Export the workbook to the end-user.
     * 
     * Code obtained from: http://vaadin.com/forum/-/message_boards/view_message/159583
     * 
     * @return true, if successful
     */
    @Override
    protected boolean sendConverted() {
        final boolean success = super.sendConvertedFileToUser(this.sourceUI, this.tempReportFile, this.resultantExportFileName);
        return success;
    }

    /************************************ Using above methods to add data in to Excel *******************************************/

    /************************************* Getters and Setters *******************************************/
    /**
     * Gets the bound Vaadin UI
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

    /**
     * Gets the workbook.
     *
     * @return the workbook
     */
    public XSSFWorkbook getWorkbook() {
        return this.workbook;
    }

    /**
     * Gets the checks if is pre processing performed.
     *
     * @return the checks if is pre processing performed
     */
    public Boolean getIsPreProcessingPerformed() {
        return this.isPreProcessingPerformed;
    }

    /**
     * Sets the checks if is pre processing performed.
     *
     * @param isPreProcessingPerformed the new checks if is pre processing performed
     */
    public void setIsPreProcessingPerformed(final Boolean isPreProcessingPerformed) {
        this.isPreProcessingPerformed = isPreProcessingPerformed;
    }

    /**
     * Gets the export excel configuration.
     *
     * @return the export excel configuration
     */
    public ExportExcelConfiguration getExportExcelConfiguration() {
        return this.exportExcelConfiguration;
    }

    /**
     * Sets the export excel configuration.
     *
     * @param exportExcelConfiguration the new export excel configuration
     */
    public void setExportExcelConfiguration(final ExportExcelConfiguration exportExcelConfiguration) {
        this.exportExcelConfiguration = exportExcelConfiguration;
    }

    /************************************ Getters and Setters *******************************************/

    /************************************* Styles and Designing of Excel Content *******************************************/
    /**
     * Creates the styles.
     *
     * @param wb the wb
     * @return the map
     */
    protected Map<String, XSSFCellStyle> createStyles(final XSSFWorkbook wb) {
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
        XSSFDataFormat fmt = wb.createDataFormat();

        XSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style1.setDataFormat(fmt.getFormat("0.0%"));
        style1.setWrapText(false);
        styles.put("percent", style1);

        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setDataFormat(fmt.getFormat("0.0X"));
        style2.setWrapText(false);
        styles.put("coeff", style2);

        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style3.setDataFormat(fmt.getFormat("$#,##0.00"));
        style3.setWrapText(false);
        styles.put("currency", style3);

        XSSFCellStyle style4 = wb.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style4.setDataFormat(fmt.getFormat("mmm dd"));
        style4.setWrapText(false);
        styles.put("date", style4);

        XSSFCellStyle style5 = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        style5.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style5.setFont(headerFont);
        style5.setWrapText(false);
        styles.put("header", style5);

        XSSFCellStyle style6 = wb.createCellStyle();
        XSSFFont font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style6.setFont(font);
        style6.setWrapText(false);
        styles.put("reportFormat", style6);

        return styles;
    }

    /**
     * Gets Default Report Title Style
     *
     * @param myWorkBook the my work book
     * @return the default report title style
     */
    protected XSSFCellStyle getDefaultReportTitleStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle defaultReportTitleStyle = myWorkBook.createCellStyle();

        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        boldFont.setFontHeightInPoints((short) 14);

        defaultReportTitleStyle.setFont(boldFont);

        return defaultReportTitleStyle;
    }

    /**
     * Gets the default generated by style.
     *
     * @param myWorkBook the my work book
     * @return the default generated by style
     */
    protected XSSFCellStyle getDefaultGeneratedByStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle defaultGeneratedByStyle = myWorkBook.createCellStyle();
        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        boldFont.setFontHeightInPoints((short) 10);

        defaultGeneratedByStyle.setFont(boldFont);

        return defaultGeneratedByStyle;
    }

    /**
     * Gets the default header caption style.
     *
     * @param myWorkBook the my work book
     * @return the default header caption style
     */
    protected XSSFCellStyle getDefaultHeaderCaptionStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(50, 86, 110)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle = setBorders(headerCellStyle, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Color.BLACK);

        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        boldFont.setColor(IndexedColors.WHITE.getIndex());

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default header value style.
     *
     * @param myWorkBook the my work book
     * @return the default header value style
     */
    protected XSSFCellStyle getDefaultHeaderValueStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(209, 220, 227)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle = setBorders(headerCellStyle, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Color.BLACK);

        XSSFFont boldFont = myWorkBook.createFont();

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default add header caption style.
     *
     * @param myWorkBook the my work book
     * @return the default add header caption style
     */
    protected XSSFCellStyle getDefaultAddHeaderCaptionStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(50, 86, 110)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle = setBorders(headerCellStyle, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Color.BLACK);

        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        boldFont.setColor(IndexedColors.WHITE.getIndex());

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default add header value style.
     *
     * @param myWorkBook the my work book
     * @return the default add header value style
     */
    protected XSSFCellStyle getDefaultAddHeaderValueStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(209, 220, 227)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle.setWrapText(true);

        XSSFFont boldFont = myWorkBook.createFont();

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default table header style.
     *
     * @param myWorkBook the my work book
     * @return the default table header style
     */
    protected XSSFCellStyle getDefaultTableHeaderStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(50, 86, 110)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        headerCellStyle = setBorders(headerCellStyle, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Color.WHITE);

        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setColor(IndexedColors.WHITE.getIndex());
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default table footer style.
     *
     * @param myWorkBook the my work book
     * @return the default table footer style
     */
    protected XSSFCellStyle getDefaultTableFooterStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle = myWorkBook.createCellStyle();
        headerCellStyle.setFillForegroundColor(new XSSFColor(new Color(50, 86, 110)));
        headerCellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        headerCellStyle = setBorders(headerCellStyle, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Color.WHITE);

        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setColor(IndexedColors.WHITE.getIndex());
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

        headerCellStyle.setFont(boldFont);

        return headerCellStyle;
    }

    /**
     * Gets the default table content style.
     *
     * @param myWorkBook the my work book
     * @return the default table content style
     */
    protected XSSFCellStyle getDefaultTableContentStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle cellStyle = myWorkBook.createCellStyle();
        cellStyle = myWorkBook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new Color(228, 234, 238)));
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }

    /**
     * Gets the default table content parent style.
     *
     * @param myWorkBook the my work book
     * @return the default table content parent style
     */
    protected XSSFCellStyle getDefaultTableContentParentStyle(final XSSFWorkbook myWorkBook) {
        XSSFCellStyle cellStyle = myWorkBook.createCellStyle();
        cellStyle = myWorkBook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new Color(189, 203, 211)));
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        XSSFFont boldFont = myWorkBook.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

        cellStyle.setFont(boldFont);
        return cellStyle;
    }

    /**
     * Sets the borders.
     *
     * @param headerCellStyle the header cell style
     * @param left the left
     * @param right the right
     * @param top the top
     * @param bottom the bottom
     * @param color the color
     * @return the XSSF cell style
     */
    protected XSSFCellStyle setBorders(final XSSFCellStyle headerCellStyle, final Boolean left, final Boolean right, final Boolean top, final Boolean bottom,
            final Color color) {
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

    /************************************ Styles and Designing of Excel Content *******************************************/

    /**
     * Formats Date based on the UI's locale
     *
     * @param val the val
     * @param sheetConfiguration the sheet configuration
     * @return the string
     */
    private String formatDate(final Date val, final ExportExcelSheetConfiguration sheetConfiguration) {
        if (val == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(sheetConfiguration.getDateFormat(), this.sourceUI.getLocale());
        return df.format(val);
    }

    /**
     * Localized format for Integer and BigDecimal values
     *
     * @param value the value
     * @param isIntOrBigD the is int or big D
     * @return the string
     */
    public String localizedFormat(final String value, final Boolean isIntOrBigD) {
        Locale loc = this.sourceUI.getLocale();
        if (isIntOrBigD) {
            Integer modifiedValue = unLocalizedFormatForInt(value);

            NumberFormat nf = NumberFormat.getNumberInstance(loc);
            nf.setParseIntegerOnly(true);
            return nf.format(modifiedValue);
        }
        else {
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
     * @param value the value
     * @return the string
     */
    public String formatFloat(final Double value) {
        Locale loc = this.sourceUI.getLocale();
        NumberFormat nf = NumberFormat.getNumberInstance(loc);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(value);
    }

    /**
     * Un localized format for int.
     *
     * @param value the value
     * @return the integer
     */
    public Integer unLocalizedFormatForInt(final String value) {
        Locale loc = this.sourceUI.getLocale();
        Integer modifiedValue = 0;

        if (value != null && !value.contains(".") && !value.contains(",")) {
            modifiedValue = Integer.valueOf(value);
        }
        else {
            if (value != null && loc.getLanguage()
                                    .equals("en")) {
                modifiedValue = Integer.valueOf(value.replaceAll(",", ""));
            }
            else if (value != null && loc.getLanguage()
                                         .equals("de")) {
                modifiedValue = Integer.valueOf(value.replaceAll("\\.", ""));
            }
        }
        return modifiedValue;
    }

    /**
     * Un localized format for big decimal.
     *
     * @param value the value
     * @return the big decimal
     */
    public BigDecimal unLocalizedFormatForBigDecimal(final String value) {
        Locale loc = UI.getCurrent()
                       .getLocale();
        BigDecimal modifiedValue = new BigDecimal(Double.valueOf("0"));

        if (value != null && !value.contains(".") && !value.contains(",")) {

            modifiedValue = BigDecimal.valueOf(Double.valueOf(value));
        }
        else {
            if (value != null && loc.getLanguage()
                                    .equals("en")) {

                modifiedValue = BigDecimal.valueOf(Double.valueOf(value.replaceAll(",", "")));
            }
            else if (value != null && loc.getLanguage()
                                         .equals("de")) {

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

}
