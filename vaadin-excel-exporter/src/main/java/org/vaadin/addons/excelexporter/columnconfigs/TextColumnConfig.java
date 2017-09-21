/*
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class TextColumnConfig is used to configure Text, String columns of a component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class TextColumnConfig extends ColumnConfig {

    String suffix;

    String prefix;

    public TextColumnConfig() {
        super();
        this.datatype = DataTypeEnum.TEXT;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}