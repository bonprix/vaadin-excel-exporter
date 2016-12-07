/*
 * 
 */
package org.vaadin.addons.excelexporter;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.vaadin.ui.Grid;

/**
 * The Class ComponentHeaderConfiguration is used to configure footers for the Excel component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
        intoPackage = "*.builder")
public class ComponentHeaderConfiguration {

    /** The header row. */
    Grid.HeaderRow headerRow;

    /** The column header keys. */
    String[] columnHeaderKeys;

    /** The merged cells. */
    List<MergedCell> mergedCells;

    /** The is default. */
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
     * Gets the header row.
     *
     * @return the header row
     */
    public Grid.HeaderRow getHeaderRow() {
        return this.headerRow;
    }

    /**
     * Sets the header row.
     *
     * @param headerRow the new header row
     */
    public void setHeaderRow(final Grid.HeaderRow headerRow) {
        this.headerRow = headerRow;
    }

    /**
     * Gets the column header keys.
     *
     * @return the column header keys
     */
    public String[] getColumnHeaderKeys() {
        return this.columnHeaderKeys;
    }

    /**
     * Sets the column header keys.
     *
     * @param columnHeaderKeys the new column header keys
     */
    public void setColumnHeaderKeys(final String[] columnHeaderKeys) {
        this.columnHeaderKeys = columnHeaderKeys;
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