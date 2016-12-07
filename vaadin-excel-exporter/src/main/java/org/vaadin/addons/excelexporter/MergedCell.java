/*
 * 
 */
package org.vaadin.addons.excelexporter;

import net.karneim.pojobuilder.GeneratePojoBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class MergedCell.
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(
        intoPackage = "*.builder")
public class MergedCell {
    
    /** The start property. */
    String startProperty;
    
    /** The end property. */
    String endProperty;
    
    /** The header key. */
    String headerKey;

    /**
     * Gets the start property.
     *
     * @return the start property
     */
    public String getStartProperty() {
        return this.startProperty;
    }

    /**
     * Sets the start property.
     *
     * @param startProperty the new start property
     */
    public void setStartProperty(final String startProperty) {
        this.startProperty = startProperty;
    }

    /**
     * Gets the end property.
     *
     * @return the end property
     */
    public String getEndProperty() {
        return this.endProperty;
    }

    /**
     * Sets the end property.
     *
     * @param endProperty the new end property
     */
    public void setEndProperty(final String endProperty) {
        this.endProperty = endProperty;
    }

    /**
     * Gets the header key.
     *
     * @return the header key
     */
    public String getHeaderKey() {
        return this.headerKey;
    }

    /**
     * Sets the header key.
     *
     * @param headerKey the new header key
     */
    public void setHeaderKey(final String headerKey) {
        this.headerKey = headerKey;
    }
}