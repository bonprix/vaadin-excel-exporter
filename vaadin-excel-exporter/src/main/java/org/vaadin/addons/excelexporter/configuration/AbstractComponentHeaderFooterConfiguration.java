/*
 * 
 */
package org.vaadin.addons.excelexporter.configuration;

import java.util.List;

/**
 * The Class ComponentHeaderConfiguration is used to configure footers for the
 * Excel component
 *
 * @author Kartik Suba
 */
public abstract class AbstractComponentHeaderFooterConfiguration<ROWTYPE> {

	/** The row. */
	private ROWTYPE row;

	/** The column keys. */
	private String[] columnKeys;

	/** The merged cells. */
	private List<MergedCell> mergedCells;

	/** The is default. */
	private Boolean defaultConfig;

	public ROWTYPE getRow() {
		return this.row;
	}

	public void setRow(ROWTYPE row) {
		this.row = row;
	}

	public String[] getColumnKeys() {
		return this.columnKeys;
	}

	public void setColumnKeys(String[] columnKeys) {
		this.columnKeys = columnKeys;
	}

	public List<MergedCell> getMergedCells() {
		return this.mergedCells;
	}

	public void setMergedCells(List<MergedCell> mergedCells) {
		this.mergedCells = mergedCells;
	}

	public Boolean getDefaultConfig() {
		return this.defaultConfig;
	}

	public void setDefaultConfig(Boolean defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

}