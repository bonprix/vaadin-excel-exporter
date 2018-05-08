/*
 *
 */
package org.vaadin.addons.excelexporter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.vaadin.addons.excelexporter.columnconfigs.ColumnConfig;
import org.vaadin.addons.excelexporter.formatter.ColumnFormatter;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class ExportExcelComponentConfiguration is used to configure the
 * component as in Table, Tree Table or Grid Several additional properties such
 * as formatting, headers, footers, styles etc. can also be configured
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(intoPackage = "*.builder")
public class ExportExcelComponentConfiguration {

	/** The visible properties. */
	Object[] visibleProperties;

	/** The column configs. */
	List<? extends ColumnConfig> columnConfigs;

	/** The date formatting properties. */
	List<String> dateFormattingProperties;

	/** The integer formatting properties. */
	List<String> integerFormattingProperties;

	/** The float formatting properties. */
	List<String> floatFormattingProperties;

	/** The boolean formatting properties. */
	List<String> booleanFormattingProperties;

	/** The column formatters. */
	Map<Object, ColumnFormatter> columnFormatters = new LinkedHashMap<>();

	public List<? extends ColumnConfig> getColumnConfigs() {
		return this.columnConfigs;
	}

	public void setColumnConfigs(final List<? extends ColumnConfig> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}

	/** The header configs. */
	List<ComponentHeaderConfiguration> headerConfigs;

	/** The footer configs. */
	List<ComponentFooterConfiguration> footerConfigs;

	/** The table header style. */
	XSSFCellStyle tableHeaderStyle;

	/** The table footer style. */
	XSSFCellStyle tableFooterStyle;

	/** The table content style. */
	XSSFCellStyle tableContentStyle;

	/** The resultant table header style. */
	XSSFCellStyle rTableHeaderStyle = null;

	/** The resultant table footer style. */
	XSSFCellStyle rTableFooterStyle = null;

	/** The resultant table content style. */
	XSSFCellStyle rTableContentStyle = null;

	/** The table. */
	Table table;

	/** The col row freeze. */
	Integer colRowFreeze = null;

	/** The grid. */
	Grid grid;

	/** The tree table. */
	TreeTable treeTable;

	/**
	 * Gets the col row freeze.
	 *
	 * @return the col row freeze
	 */
	public Integer getColRowFreeze() {
		return this.colRowFreeze;
	}

	/**
	 * Sets the col row freeze.
	 *
	 * @param colRowFreeze
	 *            the new col row freeze
	 */
	public void setColRowFreeze(final Integer colRowFreeze) {
		this.colRowFreeze = colRowFreeze;
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * Sets the table.
	 *
	 * @param table
	 *            the new table
	 */
	public void setTable(final Table table) {
		this.table = table;
	}

	/**
	 * Gets the grid.
	 *
	 * @return the grid
	 */
	public Grid getGrid() {
		return this.grid;
	}

	/**
	 * Sets the grid.
	 *
	 * @param grid
	 *            the new grid
	 */
	public void setGrid(final Grid grid) {
		this.grid = grid;
	}

	/**
	 * Gets the tree table.
	 *
	 * @return the tree table
	 */
	public TreeTable getTreeTable() {
		return this.treeTable;
	}

	/**
	 * Sets the tree table.
	 *
	 * @param treeTable
	 *            the new tree table
	 */
	public void setTreeTable(final TreeTable treeTable) {
		this.treeTable = treeTable;
	}

	/**
	 * Gets the table footer style.
	 *
	 * @return the table footer style
	 */
	public XSSFCellStyle getTableFooterStyle() {
		return this.tableFooterStyle;
	}

	/**
	 * Sets the table footer style.
	 *
	 * @param tableFooterStyle
	 *            the new table footer style
	 */
	public void setTableFooterStyle(final XSSFCellStyle tableFooterStyle) {
		this.tableFooterStyle = tableFooterStyle;
	}

	/**
	 * Gets the resultant table footer style.
	 *
	 * @return the resultant table footer style
	 */
	public XSSFCellStyle getrTableFooterStyle() {
		return this.rTableFooterStyle;
	}

	/**
	 * Sets the resultant table footer style.
	 *
	 * @param rTableFooterStyle
	 *            the new resultant table footer style
	 */
	protected void setrTableFooterStyle(final XSSFCellStyle rTableFooterStyle) {
		this.rTableFooterStyle = rTableFooterStyle;
	}

	/**
	 * Gets the resultant table header style.
	 *
	 * @return the resultant table header style
	 */
	public XSSFCellStyle getrTableHeaderStyle() {
		return this.rTableHeaderStyle;
	}

	/**
	 * Sets the resultant table header style.
	 *
	 * @param rTableHeaderStyle
	 *            the new resultant table header style
	 */
	protected void setrTableHeaderStyle(final XSSFCellStyle rTableHeaderStyle) {
		this.rTableHeaderStyle = rTableHeaderStyle;
	}

	/**
	 * Gets the resultant table content style.
	 *
	 * @return the resultant table content style
	 */
	public XSSFCellStyle getrTableContentStyle() {
		return this.rTableContentStyle;
	}

	/**
	 * Sets the resultant table content style.
	 *
	 * @param rTableContentStyle
	 *            the new resultant table content style
	 */
	protected void setrTableContentStyle(final XSSFCellStyle rTableContentStyle) {
		this.rTableContentStyle = rTableContentStyle;
	}

	// /**
	// * Gets the date formatting properties.
	// *
	// * @return the date formatting properties
	// */
	// public List<String> getDateFormattingProperties() {
	// return this.dateFormattingProperties;
	// }
	//
	// /**
	// * Sets the date formatting properties.
	// *
	// * @param dateFormattingProperties the new date formatting properties
	// */
	// public void setDateFormattingProperties(final List<String>
	// dateFormattingProperties) {
	// this.dateFormattingProperties = dateFormattingProperties;
	// }
	//
	// /**
	// * Gets the integer formatting properties.
	// *
	// * @return the integer formatting properties
	// */
	// public List<String> getIntegerFormattingProperties() {
	// return this.integerFormattingProperties;
	// }
	//
	// /**
	// * Sets the integer formatting properties.
	// *
	// * @param integerFormattingProperties the new integer formatting
	// properties
	// */
	// public void setIntegerFormattingProperties(final List<String>
	// integerFormattingProperties) {
	// this.integerFormattingProperties = integerFormattingProperties;
	// }
	//
	// /**
	// * Gets the float formatting properties.
	// *
	// * @return the float formatting properties
	// */
	// public List<String> getFloatFormattingProperties() {
	// return this.floatFormattingProperties;
	// }
	//
	// /**
	// * Sets the float formatting properties.
	// *
	// * @param floatFormattingProperties the new float formatting properties
	// */
	// public void setFloatFormattingProperties(final List<String>
	// floatFormattingProperties) {
	// this.floatFormattingProperties = floatFormattingProperties;
	// }
	//
	// /**
	// * Sets the boolean formatting properties.
	// *
	// * @param booleanFormattingProperties the new boolean formatting
	// properties
	// */
	// public void setBooleanFormattingProperties(final List<String>
	// booleanFormattingProperties) {
	// this.booleanFormattingProperties = booleanFormattingProperties;
	// }

	/**
	 * Sets the header configs.
	 *
	 * @param headerConfigs
	 *            the new header configs
	 */
	public void setHeaderConfigs(final List<ComponentHeaderConfiguration> headerConfigs) {
		this.headerConfigs = headerConfigs;
	}

	/**
	 * Sets the footer configs.
	 *
	 * @param footerConfigs
	 *            the new footer configs
	 */
	public void setFooterConfigs(final List<ComponentFooterConfiguration> footerConfigs) {
		this.footerConfigs = footerConfigs;
	}

	/**
	 * Gets the visible properties.
	 *
	 * @return the visible properties
	 */
	public Object[] getVisibleProperties() {
		return this.visibleProperties;
	}

	/**
	 * Gets the table header style.
	 *
	 * @return the table header style
	 */
	public XSSFCellStyle getTableHeaderStyle() {
		return this.tableHeaderStyle;
	}

	/**
	 * Gets the table content style.
	 *
	 * @return the table content style
	 */
	public XSSFCellStyle getTableContentStyle() {
		return this.tableContentStyle;
	}

	/**
	 * Sets the visible properties.
	 *
	 * @param visibleProperties
	 *            the new visible properties
	 */
	public void setVisibleProperties(final Object[] visibleProperties) {
		this.visibleProperties = visibleProperties;
	}

	/**
	 * Sets the table header style.
	 *
	 * @param tableHeaderStyle
	 *            the new table header style
	 */
	public void setTableHeaderStyle(final XSSFCellStyle tableHeaderStyle) {
		this.tableHeaderStyle = tableHeaderStyle;
	}

	/**
	 * Sets the table content style.
	 *
	 * @param tableContentStyle
	 *            the new table content style
	 */
	public void setTableContentStyle(final XSSFCellStyle tableContentStyle) {
		this.tableContentStyle = tableContentStyle;
	}

	// /**
	// * Gets the boolean formatting properties.
	// *
	// * @return the boolean formatting properties
	// */
	// public List<String> getBooleanFormattingProperties() {
	// return this.booleanFormattingProperties;
	// }

	/**
	 * Gets the header configs.
	 *
	 * @return the header configs
	 */
	public List<ComponentHeaderConfiguration> getHeaderConfigs() {
		return this.headerConfigs;
	}

	/**
	 * Gets the footer configs.
	 *
	 * @return the footer configs
	 */
	public List<ComponentFooterConfiguration> getFooterConfigs() {
		return this.footerConfigs;
	}

	public List<String> getDateFormattingProperties() {
		return this.dateFormattingProperties;
	}

	public void setDateFormattingProperties(List<String> dateFormattingProperties) {
		this.dateFormattingProperties = dateFormattingProperties;
	}

	public List<String> getIntegerFormattingProperties() {
		return this.integerFormattingProperties;
	}

	public void setIntegerFormattingProperties(List<String> integerFormattingProperties) {
		this.integerFormattingProperties = integerFormattingProperties;
	}

	public List<String> getFloatFormattingProperties() {
		return this.floatFormattingProperties;
	}

	public void setFloatFormattingProperties(List<String> floatFormattingProperties) {
		this.floatFormattingProperties = floatFormattingProperties;
	}

	public List<String> getBooleanFormattingProperties() {
		return this.booleanFormattingProperties;
	}

	public void setBooleanFormattingProperties(List<String> booleanFormattingProperties) {
		this.booleanFormattingProperties = booleanFormattingProperties;
	}

	/**
	 * Gets the column formatter.
	 *
	 * @param columnId
	 *            the column id
	 * @return the column formatter
	 */
	public ColumnFormatter getColumnFormatter(final Object columnId) {
		if (this.columnFormatters != null && !this.columnFormatters.isEmpty()
				&& this.columnFormatters.containsKey(columnId)) {
			return this.columnFormatters.get(columnId);
		}
		return null;
	}

}
