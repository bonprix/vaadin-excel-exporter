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
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;


@SuppressWarnings ( "serial" )
public class DataModelGridWithBeanItemContainer extends Grid {

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

    public DataModelGridWithBeanItemContainer () {
        setWidth ( 100, Unit.PERCENTAGE );
        setHeight ( null );

        setContainerDataSource ( container );

        // @formatter:off
        setColumns (
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

        getColumn ( PROPERTY_COUNTRY ).setHeaderCaption ( "COUNTRY" );
        getColumn ( PROPERTY_PRODUCT_TYPE ).setHeaderCaption ( "PRODUCT TYPE" );
        getColumn ( PROPERTY_CATALOGUE ).setHeaderCaption ( "CATALOGUE" );
        getColumn ( PROPERTY_PLANNED_PRINTER ).setHeaderCaption ( "PLANNED PRINTER" );
        getColumn ( PROPERTY_CHEAPEST ).setHeaderCaption ( "CHEAPEST" );
        getColumn ( PROPERTY_CONTRACTOR ).setHeaderCaption ( "CONTRACTOR" );
        getColumn ( PROPERTY_TOTAL_COSTS ).setHeaderCaption ( "TOTAL COST" );
        getColumn ( PROPERTY_DIFFERENCE_TO_MIN ).setHeaderCaption ( "DIFFERENCE TO MIN" );
        getColumn ( PROPERTY_COMMENT ).setHeaderCaption ( "COMMENT" );
        getColumn ( PROPERTY_ACTIVE ).setHeaderCaption ( "ACTIVE" );
        getColumn ( PROPERTY_COUNTER ).setHeaderCaption ( "COUNTER" );
    }

    public void setRecords ( Collection<DataModel> records ) {
        container.removeAllItems ();
        if ( CollectionUtils.isNotEmpty ( records ) ) {
            container.addAll ( records );
        }
        setHeightMode ( HeightMode.ROW );
        setHeightByRows ( container.size () );
    }
}
