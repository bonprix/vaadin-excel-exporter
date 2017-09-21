/*
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

import org.vaadin.addons.excelexporter.formatter.ColumnFormatter;

/**
 * The Class ColumnConfig is used to configure columns of a component
 *
 * @author Kartik Suba
 */
public abstract class ColumnConfig {

    String property;

    /**
     * Note: Using this would change the data type to String.
     */
    ColumnFormatter customColumnFormatter;

    DataTypeEnum datatype = DataTypeEnum.TEXT;

    public String getProperty() {
        return this.property;
    }

    public void setProperty(final String property) {
        this.property = property;
    }

    public ColumnFormatter getCustomColumnFormatter() {
        return this.customColumnFormatter;
    }

    public void setCustomColumnFormatter(final ColumnFormatter customColumnFormatter) {
        this.customColumnFormatter = customColumnFormatter;
    }

    public DataTypeEnum getDatatype() {
        return this.datatype;
    }

    public void setDatatype(final DataTypeEnum datatype) {
        this.datatype = datatype;
    }

}