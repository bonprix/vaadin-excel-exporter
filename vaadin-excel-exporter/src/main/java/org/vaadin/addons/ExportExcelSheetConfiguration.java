package org.vaadin.addons;

import java.util.ArrayList;
import java.util.HashMap;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 *
 * @author Kartik Suba
 * 
 */
@GeneratePojoBuilder(
        intoPackage = "*.builder")
public class ExportExcelSheetConfiguration {

    private Boolean isTotalRowRequired = Boolean.FALSE;
    private Boolean isDefaultSheetTitleRequired = Boolean.TRUE;
    private Boolean isDefaultGeneratedByRequired = Boolean.TRUE;
    private Boolean isHeaderSectionAdded = false;

    public void setIsHeaderSectionAdded(final Boolean isHeaderSectionAdded) {
        this.isHeaderSectionAdded = isHeaderSectionAdded;
    }

    final String DEFAULT_REPORT_TITLE = "Report";
    Integer defaultSheetRowNum = 0;

    public Integer getDefaultSheetRowNum() {
        return this.defaultSheetRowNum;
    }

    public void setDefaultSheetRowNum(final Integer defaultSheetRowNum) {
        this.defaultSheetRowNum = defaultSheetRowNum;
    }

    String dateFormat = "EEE, dd.MM.yyyy";

    String reportTitleRowContent;
    Integer headerCaptionStartCol = 0; // default Value
    Integer headerValueStartCol = 1; // default Value
    Integer noOfColumnsInHeader = 2; // default Value
    Integer noOfColumnsToMergeInAddHeaderValue = 2; // default Value
    Integer noOfColumnsInAddHeader = 2 * this.noOfColumnsToMergeInAddHeaderValue; // default
    Boolean isHeaderSectionRequired = Boolean.FALSE;
    String loggerInfoRowContent;
    String reportTitle;
    Integer[] columnForTitleRegion = new Integer[2];
    Integer[] columnForGeneratedByRegion = new Integer[2];

    ArrayList<ExportExcelComponentConfiguration> componentConfigs = new ArrayList<ExportExcelComponentConfiguration>();
    HashMap<String, String> additionalHeaderInfo = new HashMap<String, String>();
    String sheetName;
    Sheet sheet;

    public Sheet getSheet() {
        return this.sheet;
    }

    public void setSheet(final Sheet sheet) {
        this.sheet = sheet;
    }

    XSSFCellStyle reportTitleStyle;
    XSSFCellStyle generatedByStyle;
    XSSFCellStyle headerCaptionStyle;
    XSSFCellStyle headerValueStyle;
    XSSFCellStyle additionalHeaderCaptionStyle;
    XSSFCellStyle additionalHeaderValueStyle;

    XSSFCellStyle rReportTitleStyle = null;
    XSSFCellStyle rGeneratedByStyle = null;
    XSSFCellStyle rHeaderCaptionStyle = null;
    XSSFCellStyle rHeaderValueStyle = null;
    XSSFCellStyle rAdditionalHeaderCaptionStyle = null;
    XSSFCellStyle rAdditionalHeaderValueStyle = null;

    private String resultantSheetName;
    private String resultantReportTitleRowContent;
    private String resultantLoggerInfoRowContent;
    private Integer[] resultantColumnForTitleRegion;
    private Integer[] resultantColumnForGeneratedByRegion;
    private int resultantHeaderCaptionStartCol;
    private int resultantHeaderValueStartCol;

    public XSSFCellStyle getrReportTitleStyle() {
        return this.rReportTitleStyle;
    }

    public void setrReportTitleStyle(final XSSFCellStyle rReportTitleStyle) {
        this.rReportTitleStyle = rReportTitleStyle;
    }

    public XSSFCellStyle getrGeneratedByStyle() {
        return this.rGeneratedByStyle;
    }

    public void setrGeneratedByStyle(final XSSFCellStyle rGeneratedByStyle) {
        this.rGeneratedByStyle = rGeneratedByStyle;
    }

    public XSSFCellStyle getrHeaderCaptionStyle() {
        return this.rHeaderCaptionStyle;
    }

    public void setrHeaderCaptionStyle(final XSSFCellStyle rHeaderCaptionStyle) {
        this.rHeaderCaptionStyle = rHeaderCaptionStyle;
    }

    public XSSFCellStyle getrHeaderValueStyle() {
        return this.rHeaderValueStyle;
    }

    public void setrHeaderValueStyle(final XSSFCellStyle rHeaderValueStyle) {
        this.rHeaderValueStyle = rHeaderValueStyle;
    }

    public XSSFCellStyle getrAdditionalHeaderCaptionStyle() {
        return this.rAdditionalHeaderCaptionStyle;
    }

    public void setrAdditionalHeaderCaptionStyle(final XSSFCellStyle rAdditionalHeaderCaptionStyle) {
        this.rAdditionalHeaderCaptionStyle = rAdditionalHeaderCaptionStyle;
    }

    public XSSFCellStyle getrAdditionalHeaderValueStyle() {
        return this.rAdditionalHeaderValueStyle;
    }

    public void setrAdditionalHeaderValueStyle(final XSSFCellStyle rAdditionalHeaderValueStyle) {
        this.rAdditionalHeaderValueStyle = rAdditionalHeaderValueStyle;
    }

    public String getResultantReportTitleRowContent() {
        return this.resultantReportTitleRowContent;
    }

    protected void setResultantReportTitleRowContent(final String resultantReportTitleRowContent) {
        this.resultantReportTitleRowContent = resultantReportTitleRowContent;
    }

    public String getResultantLoggerInfoRowContent() {
        return this.resultantLoggerInfoRowContent;
    }

    protected void setResultantLoggerInfoRowContent(final String resultantLoggerInfoRowContent) {
        this.resultantLoggerInfoRowContent = resultantLoggerInfoRowContent;
    }

    public Integer[] getResultantColumnForTitleRegion() {
        return this.resultantColumnForTitleRegion;
    }

    protected void setResultantColumnForTitleRegion(final Integer[] resultantColumnForTitleRegion) {
        this.resultantColumnForTitleRegion = resultantColumnForTitleRegion;
    }

    public Integer[] getResultantColumnForGeneratedByRegion() {
        return this.resultantColumnForGeneratedByRegion;
    }

    protected void setResultantColumnForGeneratedByRegion(final Integer[] resultantColumnForGeneratedByRegion) {
        this.resultantColumnForGeneratedByRegion = resultantColumnForGeneratedByRegion;
    }

    public int getResultantHeaderCaptionStartCol() {
        return this.resultantHeaderCaptionStartCol;
    }

    protected void setResultantHeaderCaptionStartCol(final int resultantHeaderCaptionStartCol) {
        this.resultantHeaderCaptionStartCol = resultantHeaderCaptionStartCol;
    }

    public int getResultantHeaderValueStartCol() {
        return this.resultantHeaderValueStartCol;
    }

    protected void setResultantHeaderValueStartCol(final int resultantHeaderValueStartCol) {
        this.resultantHeaderValueStartCol = resultantHeaderValueStartCol;
    }

    public String getResultantSheetName() {
        return this.resultantSheetName;
    }

    protected void setResultantSheetName(final String resultantSheetName) {
        this.resultantSheetName = resultantSheetName;
    }

    public ArrayList<ExportExcelComponentConfiguration> getComponentConfigs() {
        return this.componentConfigs;
    }

    public void setComponentConfigs(final ArrayList<ExportExcelComponentConfiguration> componentConfigs) {
        this.componentConfigs = componentConfigs;
    }

    public Boolean getIsHeaderSectionAdded() {
        return this.isHeaderSectionAdded;
    }

    public String getDEFAULT_REPORT_TITLE() {
        return this.DEFAULT_REPORT_TITLE;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Boolean getIsTotalRowRequired() {
        return this.isTotalRowRequired;
    }

    public Boolean getIsDefaultSheetTitleRequired() {
        return this.isDefaultSheetTitleRequired;
    }

    public Boolean getIsDefaultGeneratedByRequired() {
        return this.isDefaultGeneratedByRequired;
    }

    public String getReportTitleRowContent() {
        return this.reportTitleRowContent;
    }

    public Integer getHeaderCaptionStartCol() {
        return this.headerCaptionStartCol;
    }

    public Integer getHeaderValueStartCol() {
        return this.headerValueStartCol;
    }

    public Integer getNoOfColumnsInHeader() {
        return this.noOfColumnsInHeader;
    }

    public Integer getNoOfColumnsToMergeInAddHeaderValue() {
        return this.noOfColumnsToMergeInAddHeaderValue;
    }

    public Integer getNoOfColumnsInAddHeader() {
        return this.noOfColumnsInAddHeader;
    }

    public Boolean getIsHeaderSectionRequired() {
        return this.isHeaderSectionRequired;
    }

    public String getLoggerInfoRowContent() {
        return this.loggerInfoRowContent;
    }

    public String getReportTitle() {
        return this.reportTitle;
    }

    public Integer[] getColumnForTitleRegion() {
        return this.columnForTitleRegion;
    }

    public Integer[] getColumnForGeneratedByRegion() {
        return this.columnForGeneratedByRegion;
    }

    public HashMap<String, String> getAdditionalHeaderInfo() {
        return this.additionalHeaderInfo;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public XSSFCellStyle getReportTitleStyle() {
        return this.reportTitleStyle;
    }

    public XSSFCellStyle getGeneratedByStyle() {
        return this.generatedByStyle;
    }

    public XSSFCellStyle getHeaderCaptionStyle() {
        return this.headerCaptionStyle;
    }

    public XSSFCellStyle getHeaderValueStyle() {
        return this.headerValueStyle;
    }

    public XSSFCellStyle getAdditionalHeaderCaptionStyle() {
        return this.additionalHeaderCaptionStyle;
    }

    public XSSFCellStyle getAdditionalHeaderValueStyle() {
        return this.additionalHeaderValueStyle;
    }

    public void setIsTotalRowRequired(final Boolean isTotalRowRequired) {
        this.isTotalRowRequired = isTotalRowRequired;
    }

    public void setIsDefaultSheetTitleRequired(final Boolean isDefaultSheetTitleRequired) {
        this.isDefaultSheetTitleRequired = isDefaultSheetTitleRequired;
    }

    public void setIsDefaultGeneratedByRequired(final Boolean isDefaultGeneratedByRequired) {
        this.isDefaultGeneratedByRequired = isDefaultGeneratedByRequired;
    }

    public void setReportTitleRowContent(final String reportTitleRowContent) {
        this.reportTitleRowContent = reportTitleRowContent;
    }

    public void setHeaderCaptionStartCol(final Integer headerCaptionStartCol) {
        this.headerCaptionStartCol = headerCaptionStartCol;
    }

    public void setHeaderValueStartCol(final Integer headerValueStartCol) {
        this.headerValueStartCol = headerValueStartCol;
    }

    public void setNoOfColumnsInHeader(final Integer noOfColumnsInHeader) {
        this.noOfColumnsInHeader = noOfColumnsInHeader;
    }

    public void setNoOfColumnsToMergeInAddHeaderValue(final Integer noOfColumnsToMergeInAddHeaderValue) {
        this.noOfColumnsToMergeInAddHeaderValue = noOfColumnsToMergeInAddHeaderValue;
    }

    public void setNoOfColumnsInAddHeader(final Integer noOfColumnsInAddHeader) {
        this.noOfColumnsInAddHeader = noOfColumnsInAddHeader;
    }

    public void setIsHeaderSectionRequired(final Boolean isHeaderSectionRequired) {
        this.isHeaderSectionRequired = isHeaderSectionRequired;
    }

    public void setLoggerInfoRowContent(final String loggerInfoRowContent) {
        this.loggerInfoRowContent = loggerInfoRowContent;
    }

    public void setReportTitle(final String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public void setColumnForTitleRegion(final Integer[] columnForTitleRegion) {
        this.columnForTitleRegion = columnForTitleRegion;
    }

    public void setColumnForGeneratedByRegion(final Integer[] columnForGeneratedByRegion) {
        this.columnForGeneratedByRegion = columnForGeneratedByRegion;
    }

    public void setAdditionalHeaderInfo(final HashMap<String, String> additionalHeaderInfo) {
        this.additionalHeaderInfo = additionalHeaderInfo;
    }

    public void setSheetName(final String sheetName) {
        this.sheetName = sheetName;
    }

    public void setReportTitleStyle(final XSSFCellStyle reportTitleStyle) {
        this.reportTitleStyle = reportTitleStyle;
    }

    public void setGeneratedByStyle(final XSSFCellStyle generatedByStyle) {
        this.generatedByStyle = generatedByStyle;
    }

    public void setHeaderCaptionStyle(final XSSFCellStyle headerCaptionStyle) {
        this.headerCaptionStyle = headerCaptionStyle;
    }

    public void setHeaderValueStyle(final XSSFCellStyle headerValueStyle) {
        this.headerValueStyle = headerValueStyle;
    }

    public void setAdditionalHeaderCaptionStyle(final XSSFCellStyle additionalHeaderCaptionStyle) {
        this.additionalHeaderCaptionStyle = additionalHeaderCaptionStyle;
    }

    public void setAdditionalHeaderValueStyle(final XSSFCellStyle additionalHeaderValueStyle) {
        this.additionalHeaderValueStyle = additionalHeaderValueStyle;
    }
}
