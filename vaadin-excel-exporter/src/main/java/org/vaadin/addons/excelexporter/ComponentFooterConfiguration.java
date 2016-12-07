/*
 * 
 */
package org.vaadin.addons.excelexporter;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.vaadin.ui.Grid;

/**
 * The Class ComponentFooterConfiguration is used to configure headers for the Excel component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
        intoPackage = "*.builder")
public class ComponentFooterConfiguration {

    /** The footer row. */
    Grid.FooterRow footerRow;

    /** The column footer keys. */
    String[] columnFooterKeys;

    /** The merged cells. */
    List<MergedCell> mergedCells;

    /** The defaultHeader */
    Boolean isDefault;

    /**
     * Gets the checks if is default.
     *
     * @return the checks if is default
     */
    public Boolean getIsDefault() {
        return this.isDefault;
    }

    /**
     * Sets the checks if is default.
     *
     * @param isDefault the new checks if is default
     */
    public void setIsDefault(final Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * Gets the footer row.
     *
     * @return the footer row
     */
    public Grid.FooterRow getFooterRow() {
        return this.footerRow;
    }

    /**
     * Sets the footer row.
     *
     * @param footerRow the new footer row
     */
    public void setFooterRow(final Grid.FooterRow footerRow) {
        this.footerRow = footerRow;
    }

    /**
     * Gets the column footer keys.
     *
     * @return the column footer keys
     */
    public String[] getColumnFooterKeys() {
        return this.columnFooterKeys;
    }

    /**
     * Sets the column footer keys.
     *
     * @param columnFooterKeys the new column footer keys
     */
    public void setColumnFooterKeys(final String[] columnFooterKeys) {
        this.columnFooterKeys = columnFooterKeys;
    }

    /**
     * Gets the merged cells.
     *
     * @return the merged cells
     */
    public List<MergedCell> getMergedCells() {
        return this.mergedCells;
    }

    /**
     * Sets the merged cells.
     *
     * @param mergedCells the new merged cells
     */
    public void setMergedCells(final List<MergedCell> mergedCells) {
        this.mergedCells = mergedCells;
    }

}