package org.vaadin.addons;

import java.util.ArrayList;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 *
 * @author Kartik Suba
 * 
 */
@GeneratePojoBuilder(
        intoPackage = "*.builder")
public class ExportExcelConfiguration {

    public ArrayList<ExportExcelSheetConfiguration> getSheetConfigs() {
        return this.sheetConfigs;
    }

    public void setSheetConfigs(final ArrayList<ExportExcelSheetConfiguration> sheetConfigs) {
        this.sheetConfigs = sheetConfigs;
    }

    private String exportFileName;
    String generatedBy;

    ExportType exportType;
    String exportExtension;

    ArrayList<ExportExcelSheetConfiguration> sheetConfigs = new ArrayList<ExportExcelSheetConfiguration>();

    public String getExportFileName() {
        return this.exportFileName;
    }

    public String getGeneratedBy() {
        return this.generatedBy;
    }

    public ExportType getExportType() {
        return this.exportType;
    }

    public String getExportExtension() {
        return this.exportExtension;
    }

    public void setExportFileName(final String exportFileName) {
        this.exportFileName = exportFileName;
    }

    public void setGeneratedBy(final String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public void setExportType(final ExportType exportType) {
        this.exportType = exportType;
    }

    public void setExportExtension(final String exportExtension) {
        this.exportExtension = exportExtension;
    }
}
