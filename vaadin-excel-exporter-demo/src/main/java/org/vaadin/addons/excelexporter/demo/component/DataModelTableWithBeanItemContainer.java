/*
 * Copyright 2001-2017 iPoint-systems. All Rights Reserved.
 *
 * This software is proprietary information of iPoint-systems.
 * Use is subject to license terms.
 */
package org.vaadin.addons.excelexporter.demo.component;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.vaadin.addons.excelexporter.demo.model.DataModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;


@SuppressWarnings ( "serial" )
public class DataModelTableWithBeanItemContainer extends Table {

    private static final String PROPERTY_COUNTRY = "country";
    private static final String PROPERTY_PRODUCT_TYPE = "productType";
    private static final String PROPERTY_CATALOGUE = "catalogue";
    private static final String PROPERTY_PLANNED_PRINTER = "plannedPrinter";
    private static final String PROPERTY_CHEAPEST = "cheapest";
    private static final String PROPERTY_CONTRACTOR = "contractor";
    private static final String PROPERTY_TOTAL_COSTS = "totalCosts";
    private static final String PROPERTY_DIFFERENCE_TO_MIN = "differenceToMin";
    private static final String PROPERTY_ACTIVE = "active";
    private static final String PROPERTY_COUNTER = "counter";
    private static final String PROPERTY_COMMENT = "comment";

    private final BeanItemContainer<DataModel> container = new BeanItemContainer<> ( DataModel.class );

    public DataModelTableWithBeanItemContainer () {
        setWidth ( 100, Unit.PERCENTAGE );
        setHeight ( null );
        setPageLength ( 0 );

        setContainerDataSource ( container );

        // @formatter:off
        setVisibleColumns (
            PROPERTY_COUNTRY,
            PROPERTY_PRODUCT_TYPE,
            PROPERTY_CATALOGUE,
            PROPERTY_PLANNED_PRINTER,
            PROPERTY_CHEAPEST,
            PROPERTY_CONTRACTOR,
            PROPERTY_TOTAL_COSTS,
            PROPERTY_DIFFERENCE_TO_MIN,
            PROPERTY_COMMENT,
            PROPERTY_ACTIVE,
            PROPERTY_COUNTER
        );
        // @formatter:on

        // @formatter:off
        setColumnHeader ( PROPERTY_COUNTRY,           "COUNTRY" );
        setColumnHeader ( PROPERTY_PRODUCT_TYPE,      "PRODUCT TYPE" );
        setColumnHeader ( PROPERTY_CATALOGUE,         "CATALOGUE" );
        setColumnHeader ( PROPERTY_PLANNED_PRINTER,   "PLANNED_PRINTER" );
        setColumnHeader ( PROPERTY_CHEAPEST,          "CHEAPEST" );
        setColumnHeader ( PROPERTY_CONTRACTOR,        "CONTRACTOR" );
        setColumnHeader ( PROPERTY_TOTAL_COSTS,       "TOTAL COST" );
        setColumnHeader ( PROPERTY_DIFFERENCE_TO_MIN, "DIFFERENCE TO MIN" );
        setColumnHeader ( PROPERTY_COMMENT,           "COMMENT" );
        setColumnHeader ( PROPERTY_ACTIVE,            "ACTIVE" );
        setColumnHeader ( PROPERTY_COUNTER,           "COUNTER" );
        // @formatter:on
    }

    public void setRecords ( Collection<DataModel> records ) {
        container.removeAllItems ();
        if ( CollectionUtils.isNotEmpty ( records ) ) {
            container.addAll ( records );
        }
    }

}
