package org.vaadin.addons;

import java.util.ArrayList;

import javax.annotation.Generated;

/**
 * The {@link ExportExcelConfigurationBuilder} is a Builder for {@link ExportExcelConfiguration} objects.
 *
 * <p>
 * This class has been generated. Please DO NOT MODIFIY this file since it will be overwritten by the PojoBuilder generator.
 * </p>
 * 
 * @author Kartik Suba
 */
@Generated("PojoBuilder")
public class ExportExcelConfigurationBuilder implements Cloneable {
    protected ExportExcelConfigurationBuilder self;
    protected ArrayList<ExportExcelSheetConfiguration> value$sheetConfigs$java$util$ArrayList;
    protected boolean isSet$sheetConfigs$java$util$ArrayList = false;

    protected String value$exportExtension$java$lang$String;
    protected boolean isSet$exportExtension$java$lang$String = false;

    protected String value$exportFileName$java$lang$String;
    protected boolean isSet$exportFileName$java$lang$String = false;

    protected String value$generatedBy$java$lang$String;
    protected boolean isSet$generatedBy$java$lang$String = false;

    protected ExportType value$exportType$org$vaadin$addons$ExportType;
    protected boolean isSet$exportType$org$vaadin$addons$ExportType = false;

    /**
     * Creates a new {@link ExportExcelConfigurationBuilder}.
     */
    public ExportExcelConfigurationBuilder() {
        this.self = this;
    }

    /**
     * Sets the default value for the {@link ExportExcelConfiguration#sheetConfigs} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelConfigurationBuilder withSheetConfigs(final ArrayList<ExportExcelSheetConfiguration> value) {
        this.value$sheetConfigs$java$util$ArrayList = value;
        this.isSet$sheetConfigs$java$util$ArrayList = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelConfiguration#exportExtension} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelConfigurationBuilder withExportExtension(final String value) {
        this.value$exportExtension$java$lang$String = value;
        this.isSet$exportExtension$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelConfiguration#exportFileName} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelConfigurationBuilder withExportFileName(final String value) {
        this.value$exportFileName$java$lang$String = value;
        this.isSet$exportFileName$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelConfiguration#generatedBy} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelConfigurationBuilder withGeneratedBy(final String value) {
        this.value$generatedBy$java$lang$String = value;
        this.isSet$generatedBy$java$lang$String = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelConfiguration#exportType} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelConfigurationBuilder withExportType(final ExportType value) {
        this.value$exportType$org$vaadin$addons$ExportType = value;
        this.isSet$exportType$org$vaadin$addons$ExportType = true;
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
            ExportExcelConfigurationBuilder result = (ExportExcelConfigurationBuilder) super.clone();
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
    public ExportExcelConfigurationBuilder but() {
        return (ExportExcelConfigurationBuilder) clone();
    }

    /**
     * Creates a new {@link ExportExcelConfiguration} based on this builder's settings.
     * 
     * @return the created ExportExcelConfiguration
     */
    public ExportExcelConfiguration build() {
        ExportExcelConfiguration result = new ExportExcelConfiguration();

        if (this.isSet$sheetConfigs$java$util$ArrayList) {
            result.setSheetConfigs(this.value$sheetConfigs$java$util$ArrayList);
        }
        if (this.isSet$exportExtension$java$lang$String) {
            result.setExportExtension(this.value$exportExtension$java$lang$String);
        }
        if (this.isSet$exportFileName$java$lang$String) {
            result.setExportFileName(this.value$exportFileName$java$lang$String);
        }
        if (this.isSet$generatedBy$java$lang$String) {
            result.setGeneratedBy(this.value$generatedBy$java$lang$String);
        }
        if (this.isSet$exportType$org$vaadin$addons$ExportType) {
            result.setExportType(this.value$exportType$org$vaadin$addons$ExportType);
        }

        return result;
    }

}