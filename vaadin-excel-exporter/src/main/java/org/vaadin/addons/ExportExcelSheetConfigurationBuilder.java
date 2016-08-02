package org.vaadin.addons;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Generated;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * The {@link ExportExcelSheetConfigurationBuilder} is a Builder for {@link ExportExcelSheetConfiguration} objects.
 *
 * <p>
 * This class has been generated. Please DO NOT MODIFIY this file since it will be overwritten by the PojoBuilder generator.
 * </p>
 * 
 * @author Kartik Suba
 */
@Generated("PojoBuilder")
public class ExportExcelSheetConfigurationBuilder implements Cloneable {
    protected ExportExcelSheetConfigurationBuilder self;
    protected Integer value$defaultSheetRowNum$java$lang$Integer;
    protected boolean isSet$defaultSheetRowNum$java$lang$Integer = false;

    protected XSSFCellStyle value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Boolean value$isDefaultGeneratedByRequired$java$lang$Boolean;
    protected boolean isSet$isDefaultGeneratedByRequired$java$lang$Boolean = false;

    protected Boolean value$isHeaderSectionRequired$java$lang$Boolean;
    protected boolean isSet$isHeaderSectionRequired$java$lang$Boolean = false;

    protected Integer[] value$columnForTitleRegion$java$lang$Integer$;
    protected boolean isSet$columnForTitleRegion$java$lang$Integer$ = false;

    protected Boolean value$isHeaderSectionAdded$java$lang$Boolean;
    protected boolean isSet$isHeaderSectionAdded$java$lang$Boolean = false;

    protected XSSFCellStyle value$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Integer[] value$resultantColumnForGeneratedByRegion$java$lang$Integer$;
    protected boolean isSet$resultantColumnForGeneratedByRegion$java$lang$Integer$ = false;

    protected Integer value$noOfColumnsInAddHeader$java$lang$Integer;
    protected boolean isSet$noOfColumnsInAddHeader$java$lang$Integer = false;

    protected XSSFCellStyle value$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Integer value$headerCaptionStartCol$java$lang$Integer;
    protected boolean isSet$headerCaptionStartCol$java$lang$Integer = false;

    protected Integer value$noOfColumnsInHeader$java$lang$Integer;
    protected boolean isSet$noOfColumnsInHeader$java$lang$Integer = false;

    protected Boolean value$isTotalRowRequired$java$lang$Boolean;
    protected boolean isSet$isTotalRowRequired$java$lang$Boolean = false;

    protected XSSFCellStyle value$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected String value$reportTitle$java$lang$String;
    protected boolean isSet$reportTitle$java$lang$String = false;

    protected String value$dateFormat$java$lang$String;
    protected boolean isSet$dateFormat$java$lang$String = false;

    protected Sheet value$sheet$org$apache$poi$ss$usermodel$Sheet;
    protected boolean isSet$sheet$org$apache$poi$ss$usermodel$Sheet = false;

    protected XSSFCellStyle value$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected String value$resultantSheetName$java$lang$String;
    protected boolean isSet$resultantSheetName$java$lang$String = false;

    protected Integer value$headerValueStartCol$java$lang$Integer;
    protected boolean isSet$headerValueStartCol$java$lang$Integer = false;

    protected XSSFCellStyle value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected XSSFCellStyle value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Boolean value$isDefaultSheetTitleRequired$java$lang$Boolean;
    protected boolean isSet$isDefaultSheetTitleRequired$java$lang$Boolean = false;

    protected HashMap<String, String> value$additionalHeaderInfo$java$util$HashMap;
    protected boolean isSet$additionalHeaderInfo$java$util$HashMap = false;

    protected int value$resultantHeaderValueStartCol$int;
    protected boolean isSet$resultantHeaderValueStartCol$int = false;

    protected String value$loggerInfoRowContent$java$lang$String;
    protected boolean isSet$loggerInfoRowContent$java$lang$String = false;

    protected Integer[] value$resultantColumnForTitleRegion$java$lang$Integer$;
    protected boolean isSet$resultantColumnForTitleRegion$java$lang$Integer$ = false;

    protected XSSFCellStyle value$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Integer value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer;
    protected boolean isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = false;

    protected String value$resultantLoggerInfoRowContent$java$lang$String;
    protected boolean isSet$resultantLoggerInfoRowContent$java$lang$String = false;

    protected ArrayList<ExportExcelComponentConfiguration> value$componentConfigs$java$util$ArrayList;
    protected boolean isSet$componentConfigs$java$util$ArrayList = false;

    protected XSSFCellStyle value$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected XSSFCellStyle value$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Integer[] value$columnForGeneratedByRegion$java$lang$Integer$;
    protected boolean isSet$columnForGeneratedByRegion$java$lang$Integer$ = false;

    protected String value$reportTitleRowContent$java$lang$String;
    protected boolean isSet$reportTitleRowContent$java$lang$String = false;

    protected XSSFCellStyle value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected XSSFCellStyle value$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected String value$resultantReportTitleRowContent$java$lang$String;
    protected boolean isSet$resultantReportTitleRowContent$java$lang$String = false;

    protected int value$resultantHeaderCaptionStartCol$int;
    protected boolean isSet$resultantHeaderCaptionStartCol$int = false;

    protected String value$sheetName$java$lang$String;
    protected boolean isSet$sheetName$java$lang$String = false;

    /**
     * Creates a new {@link ExportExcelSheetConfigurationBuilder}.
     */
    public ExportExcelSheetConfigurationBuilder() {
        this.self = this;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#defaultSheetRowNum} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withDefaultSheetRowNum(final Integer value) {
        this.value$defaultSheetRowNum$java$lang$Integer = value;
        this.isSet$defaultSheetRowNum$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#additionalHeaderCaptionStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withAdditionalHeaderCaptionStyle(final XSSFCellStyle value) {
        this.value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#isDefaultGeneratedByRequired} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withIsDefaultGeneratedByRequired(final Boolean value) {
        this.value$isDefaultGeneratedByRequired$java$lang$Boolean = value;
        this.isSet$isDefaultGeneratedByRequired$java$lang$Boolean = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#isHeaderSectionRequired} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withIsHeaderSectionRequired(final Boolean value) {
        this.value$isHeaderSectionRequired$java$lang$Boolean = value;
        this.isSet$isHeaderSectionRequired$java$lang$Boolean = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#columnForTitleRegion} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withColumnForTitleRegion(final Integer[] value) {
        this.value$columnForTitleRegion$java$lang$Integer$ = value;
        this.isSet$columnForTitleRegion$java$lang$Integer$ = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#isHeaderSectionAdded} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withIsHeaderSectionAdded(final Boolean value) {
        this.value$isHeaderSectionAdded$java$lang$Boolean = value;
        this.isSet$isHeaderSectionAdded$java$lang$Boolean = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rAdditionalHeaderValueStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRAdditionalHeaderValueStyle(final XSSFCellStyle value) {
        this.value$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantColumnForGeneratedByRegion} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantColumnForGeneratedByRegion(final Integer[] value) {
        this.value$resultantColumnForGeneratedByRegion$java$lang$Integer$ = value;
        this.isSet$resultantColumnForGeneratedByRegion$java$lang$Integer$ = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#noOfColumnsInAddHeader} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withNoOfColumnsInAddHeader(final Integer value) {
        this.value$noOfColumnsInAddHeader$java$lang$Integer = value;
        this.isSet$noOfColumnsInAddHeader$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#generatedByStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withGeneratedByStyle(final XSSFCellStyle value) {
        this.value$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#headerCaptionStartCol} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withHeaderCaptionStartCol(final Integer value) {
        this.value$headerCaptionStartCol$java$lang$Integer = value;
        this.isSet$headerCaptionStartCol$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#noOfColumnsInHeader} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withNoOfColumnsInHeader(final Integer value) {
        this.value$noOfColumnsInHeader$java$lang$Integer = value;
        this.isSet$noOfColumnsInHeader$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#isTotalRowRequired} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withIsTotalRowRequired(final Boolean value) {
        this.value$isTotalRowRequired$java$lang$Boolean = value;
        this.isSet$isTotalRowRequired$java$lang$Boolean = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#headerCaptionStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withHeaderCaptionStyle(final XSSFCellStyle value) {
        this.value$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#reportTitle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withReportTitle(final String value) {
        this.value$reportTitle$java$lang$String = value;
        this.isSet$reportTitle$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#dateFormat} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withDateFormat(final String value) {
        this.value$dateFormat$java$lang$String = value;
        this.isSet$dateFormat$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#sheet} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withSheet(final Sheet value) {
        this.value$sheet$org$apache$poi$ss$usermodel$Sheet = value;
        this.isSet$sheet$org$apache$poi$ss$usermodel$Sheet = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rGeneratedByStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRGeneratedByStyle(final XSSFCellStyle value) {
        this.value$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantSheetName} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantSheetName(final String value) {
        this.value$resultantSheetName$java$lang$String = value;
        this.isSet$resultantSheetName$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#headerValueStartCol} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withHeaderValueStartCol(final Integer value) {
        this.value$headerValueStartCol$java$lang$Integer = value;
        this.isSet$headerValueStartCol$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#headerValueStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withHeaderValueStyle(final XSSFCellStyle value) {
        this.value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rHeaderValueStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRHeaderValueStyle(final XSSFCellStyle value) {
        this.value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#isDefaultSheetTitleRequired} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withIsDefaultSheetTitleRequired(final Boolean value) {
        this.value$isDefaultSheetTitleRequired$java$lang$Boolean = value;
        this.isSet$isDefaultSheetTitleRequired$java$lang$Boolean = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#additionalHeaderInfo} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withAdditionalHeaderInfo(final HashMap<String, String> value) {
        this.value$additionalHeaderInfo$java$util$HashMap = value;
        this.isSet$additionalHeaderInfo$java$util$HashMap = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantHeaderValueStartCol} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantHeaderValueStartCol(final int value) {
        this.value$resultantHeaderValueStartCol$int = value;
        this.isSet$resultantHeaderValueStartCol$int = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#loggerInfoRowContent} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withLoggerInfoRowContent(final String value) {
        this.value$loggerInfoRowContent$java$lang$String = value;
        this.isSet$loggerInfoRowContent$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantColumnForTitleRegion} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantColumnForTitleRegion(final Integer[] value) {
        this.value$resultantColumnForTitleRegion$java$lang$Integer$ = value;
        this.isSet$resultantColumnForTitleRegion$java$lang$Integer$ = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rAdditionalHeaderCaptionStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRAdditionalHeaderCaptionStyle(final XSSFCellStyle value) {
        this.value$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#noOfColumnsToMergeInAddHeaderValue} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withNoOfColumnsToMergeInAddHeaderValue(final Integer value) {
        this.value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = value;
        this.isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantLoggerInfoRowContent} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantLoggerInfoRowContent(final String value) {
        this.value$resultantLoggerInfoRowContent$java$lang$String = value;
        this.isSet$resultantLoggerInfoRowContent$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#componentConfigs} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withComponentConfigs(final ArrayList<ExportExcelComponentConfiguration> value) {
        this.value$componentConfigs$java$util$ArrayList = value;
        this.isSet$componentConfigs$java$util$ArrayList = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#reportTitleStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withReportTitleStyle(final XSSFCellStyle value) {
        this.value$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rReportTitleStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRReportTitleStyle(final XSSFCellStyle value) {
        this.value$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#columnForGeneratedByRegion} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withColumnForGeneratedByRegion(final Integer[] value) {
        this.value$columnForGeneratedByRegion$java$lang$Integer$ = value;
        this.isSet$columnForGeneratedByRegion$java$lang$Integer$ = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#reportTitleRowContent} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withReportTitleRowContent(final String value) {
        this.value$reportTitleRowContent$java$lang$String = value;
        this.isSet$reportTitleRowContent$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#additionalHeaderValueStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withAdditionalHeaderValueStyle(final XSSFCellStyle value) {
        this.value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#rHeaderCaptionStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withRHeaderCaptionStyle(final XSSFCellStyle value) {
        this.value$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantReportTitleRowContent} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantReportTitleRowContent(final String value) {
        this.value$resultantReportTitleRowContent$java$lang$String = value;
        this.isSet$resultantReportTitleRowContent$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#resultantHeaderCaptionStartCol} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withResultantHeaderCaptionStartCol(final int value) {
        this.value$resultantHeaderCaptionStartCol$int = value;
        this.isSet$resultantHeaderCaptionStartCol$int = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelSheetConfiguration#sheetName} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelSheetConfigurationBuilder withSheetName(final String value) {
        this.value$sheetName$java$lang$String = value;
        this.isSet$sheetName$java$lang$String = true;
        return this.self;
    }

    /**
     * Returns a clone of this builder.
     * 
     * @return the clone
     */
    @Override
    public Object clone() {
        try {
            ExportExcelSheetConfigurationBuilder result = (ExportExcelSheetConfigurationBuilder) super.clone();
            result.self = result;
            return result;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    /**
     * Returns a clone of this builder.
     * 
     * @return the clone
     */
    public ExportExcelSheetConfigurationBuilder but() {
        return (ExportExcelSheetConfigurationBuilder) clone();
    }

    /**
     * Creates a new {@link ExportExcelSheetConfiguration} based on this builder's settings.
     * 
     * @return the created ExportExcelSheetConfiguration
     */
    public ExportExcelSheetConfiguration build() {
        ExportExcelSheetConfiguration result = new ExportExcelSheetConfiguration();

        if (this.isSet$defaultSheetRowNum$java$lang$Integer) {
            result.setDefaultSheetRowNum(this.value$defaultSheetRowNum$java$lang$Integer);
        }
        if (this.isSet$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setAdditionalHeaderCaptionStyle(this.value$additionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$isDefaultGeneratedByRequired$java$lang$Boolean) {
            result.setIsDefaultGeneratedByRequired(this.value$isDefaultGeneratedByRequired$java$lang$Boolean);
        }
        if (this.isSet$isHeaderSectionRequired$java$lang$Boolean) {
            result.setIsHeaderSectionRequired(this.value$isHeaderSectionRequired$java$lang$Boolean);
        }
        if (this.isSet$columnForTitleRegion$java$lang$Integer$) {
            result.setColumnForTitleRegion(this.value$columnForTitleRegion$java$lang$Integer$);
        }
        if (this.isSet$isHeaderSectionAdded$java$lang$Boolean) {
            result.setIsHeaderSectionAdded(this.value$isHeaderSectionAdded$java$lang$Boolean);
        }
        if (this.isSet$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrAdditionalHeaderValueStyle(this.value$rAdditionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$resultantColumnForGeneratedByRegion$java$lang$Integer$) {
            result.setResultantColumnForGeneratedByRegion(this.value$resultantColumnForGeneratedByRegion$java$lang$Integer$);
        }
        if (this.isSet$noOfColumnsInAddHeader$java$lang$Integer) {
            result.setNoOfColumnsInAddHeader(this.value$noOfColumnsInAddHeader$java$lang$Integer);
        }
        if (this.isSet$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setGeneratedByStyle(this.value$generatedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$headerCaptionStartCol$java$lang$Integer) {
            result.setHeaderCaptionStartCol(this.value$headerCaptionStartCol$java$lang$Integer);
        }
        if (this.isSet$noOfColumnsInHeader$java$lang$Integer) {
            result.setNoOfColumnsInHeader(this.value$noOfColumnsInHeader$java$lang$Integer);
        }
        if (this.isSet$isTotalRowRequired$java$lang$Boolean) {
            result.setIsTotalRowRequired(this.value$isTotalRowRequired$java$lang$Boolean);
        }
        if (this.isSet$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setHeaderCaptionStyle(this.value$headerCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$reportTitle$java$lang$String) {
            result.setReportTitle(this.value$reportTitle$java$lang$String);
        }
        if (this.isSet$dateFormat$java$lang$String) {
            result.setDateFormat(this.value$dateFormat$java$lang$String);
        }
        if (this.isSet$sheet$org$apache$poi$ss$usermodel$Sheet) {
            result.setSheet(this.value$sheet$org$apache$poi$ss$usermodel$Sheet);
        }
        if (this.isSet$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrGeneratedByStyle(this.value$rGeneratedByStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$resultantSheetName$java$lang$String) {
            result.setResultantSheetName(this.value$resultantSheetName$java$lang$String);
        }
        if (this.isSet$headerValueStartCol$java$lang$Integer) {
            result.setHeaderValueStartCol(this.value$headerValueStartCol$java$lang$Integer);
        }
        if (this.isSet$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setHeaderValueStyle(this.value$headerValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrHeaderValueStyle(this.value$rHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$isDefaultSheetTitleRequired$java$lang$Boolean) {
            result.setIsDefaultSheetTitleRequired(this.value$isDefaultSheetTitleRequired$java$lang$Boolean);
        }
        if (this.isSet$additionalHeaderInfo$java$util$HashMap) {
            result.setAdditionalHeaderInfo(this.value$additionalHeaderInfo$java$util$HashMap);
        }
        if (this.isSet$resultantHeaderValueStartCol$int) {
            result.setResultantHeaderValueStartCol(this.value$resultantHeaderValueStartCol$int);
        }
        if (this.isSet$loggerInfoRowContent$java$lang$String) {
            result.setLoggerInfoRowContent(this.value$loggerInfoRowContent$java$lang$String);
        }
        if (this.isSet$resultantColumnForTitleRegion$java$lang$Integer$) {
            result.setResultantColumnForTitleRegion(this.value$resultantColumnForTitleRegion$java$lang$Integer$);
        }
        if (this.isSet$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrAdditionalHeaderCaptionStyle(this.value$rAdditionalHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer) {
            result.setNoOfColumnsToMergeInAddHeaderValue(this.value$noOfColumnsToMergeInAddHeaderValue$java$lang$Integer);
        }
        if (this.isSet$resultantLoggerInfoRowContent$java$lang$String) {
            result.setResultantLoggerInfoRowContent(this.value$resultantLoggerInfoRowContent$java$lang$String);
        }
        if (this.isSet$componentConfigs$java$util$ArrayList) {
            result.setComponentConfigs(this.value$componentConfigs$java$util$ArrayList);
        }
        if (this.isSet$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setReportTitleStyle(this.value$reportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrReportTitleStyle(this.value$rReportTitleStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$columnForGeneratedByRegion$java$lang$Integer$) {
            result.setColumnForGeneratedByRegion(this.value$columnForGeneratedByRegion$java$lang$Integer$);
        }
        if (this.isSet$reportTitleRowContent$java$lang$String) {
            result.setReportTitleRowContent(this.value$reportTitleRowContent$java$lang$String);
        }
        if (this.isSet$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setAdditionalHeaderValueStyle(this.value$additionalHeaderValueStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrHeaderCaptionStyle(this.value$rHeaderCaptionStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$resultantReportTitleRowContent$java$lang$String) {
            result.setResultantReportTitleRowContent(this.value$resultantReportTitleRowContent$java$lang$String);
        }
        if (this.isSet$resultantHeaderCaptionStartCol$int) {
            result.setResultantHeaderCaptionStartCol(this.value$resultantHeaderCaptionStartCol$int);
        }
        if (this.isSet$sheetName$java$lang$String) {
            result.setSheetName(this.value$sheetName$java$lang$String);
        }

        return result;
    }

}