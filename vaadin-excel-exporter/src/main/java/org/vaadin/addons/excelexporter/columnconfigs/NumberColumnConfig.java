/*
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class FloatColumnConfig is used to configure Float, Double, BigDecimal columns of a component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class NumberColumnConfig extends ColumnConfig {

    String suffix;

    String prefix;

    Boolean thousandSeperationRequired;

    String customFormat;

    public NumberColumnConfig() {
        this.datatype = DataTypeEnum.INTEGER;
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

    public Boolean getThousandSeperationRequired() {
        return this.thousandSeperationRequired;
    }

    public void setThousandSeperationRequired(final Boolean thousandSeperationRequired) {
        this.thousandSeperationRequired = thousandSeperationRequired;
    }

    public String getCustomFormat() {
        return this.customFormat;
    }

    public void setCustomFormat(final String customFormat) {
        this.customFormat = customFormat;
    }

}