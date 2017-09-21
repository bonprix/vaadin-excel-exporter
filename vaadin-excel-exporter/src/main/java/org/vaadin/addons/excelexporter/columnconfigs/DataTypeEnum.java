/**
 *
 */
package org.vaadin.addons.excelexporter.columnconfigs;

/**
 * @author k.suba
 *
 */
public enum DataTypeEnum {

    INTEGER(256),

    LONG(128),

    SHORT(64),

    FLOAT(32),

    DOUBLE(16),

    BIGDECIMAL(8),

    DATE(4),

    TEXT(2),

    BOOLEAN(1);

    /** The id. */
    private final int id;

    /**
     * Instantiates a new export type.
     *
     * @param id the id
     */
    private DataTypeEnum(final int id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }
}
