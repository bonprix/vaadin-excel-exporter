/*
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

import org.vaadin.addons.excelexporter.formatter.BooleanColumnFormatter;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class BooleanColumnConfig is used to configure Boolean columns of a component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class BooleanColumnConfig extends ColumnConfig {

    BooleanColumnFormatter booleanColumnFormatter;

    public BooleanColumnConfig() {
        super();
        this.datatype = DataTypeEnum.BOOLEAN;
    }

    public BooleanColumnFormatter getBooleanColumnFormatter() {
        return this.booleanColumnFormatter;
    }

    public void setBooleanColumnFormatter(final BooleanColumnFormatter booleanColumnFormatter) {
        this.booleanColumnFormatter = booleanColumnFormatter;
    }
}