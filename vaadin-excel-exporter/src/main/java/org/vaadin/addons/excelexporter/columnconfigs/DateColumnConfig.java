/*
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class DateColumnConfig is used to configure Date columns of a component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class DateColumnConfig extends ColumnConfig {

    String customFormat;

    public DateColumnConfig() {
        super();
        this.datatype = DataTypeEnum.DATE;
    }

    public String getCustomFormat() {
        return this.customFormat;
    }

    public void setCustomFormat(final String customFormat) {
        this.customFormat = customFormat;
    }

}