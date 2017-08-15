/*
 * Copyright 2001-2017 iPoint-systems. All Rights Reserved.
 *
 * This software is proprietary information of iPoint-systems.
 * Use is subject to license terms.
 */
package org.vaadin.addons.excelexporter.demo.component;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.addons.excelexporter.demo.model.DataModel;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Table;


@SuppressWarnings ( "serial" )
public class DataModelTableWithIndexedContainer extends Table {

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

    private final IndexedContainer container = new IndexedContainer ();

    public DataModelTableWithIndexedContainer () {
        setWidth ( 100, Unit.PERCENTAGE );
        setHeight ( null );
        setPageLength ( 0 );

        container.addContainerProperty ( PROPERTY_COUNTRY, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_PRODUCT_TYPE, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_CATALOGUE, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_PLANNED_PRINTER, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_CHEAPEST, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_CONTRACTOR, String.class, StringUtils.EMPTY );
        container.addContainerProperty ( PROPERTY_TOTAL_COSTS, Double.class, null );
        container.addContainerProperty ( PROPERTY_DIFFERENCE_TO_MIN, Double.class, null );
        container.addContainerProperty ( PROPERTY_ACTIVE, Boolean.class, false );
        container.addContainerProperty ( PROPERTY_COUNTER, Integer.class, null );
        container.addContainerProperty ( PROPERTY_COMMENT, String.class, StringUtils.EMPTY );

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
        setColumnHeader ( PROPERTY_PLANNED_PRINTER,   "PLANNED PRINTER" );
        setColumnHeader ( PROPERTY_CHEAPEST,          "CHEAPEST" );
        setColumnHeader ( PROPERTY_CONTRACTOR,        "CONTRACTOR" );
        setColumnHeader ( PROPERTY_TOTAL_COSTS,       "TOTAL COST" );
        setColumnHeader ( PROPERTY_DIFFERENCE_TO_MIN, "DIFFERENCE TO MIN" );
        setColumnHeader ( PROPERTY_COMMENT,           "COMMENT" );
        setColumnHeader ( PROPERTY_ACTIVE,            "ACTIVE" );
        setColumnHeader ( PROPERTY_COUNTER,           "COUNTER" );
        // @formatter:on
    }

    @SuppressWarnings ( "unchecked" )
    public void setRecords ( Collection<DataModel> records ) {
        container.removeAllItems ();
        if ( CollectionUtils.isNotEmpty ( records ) ) {
            for ( DataModel dataModel: records ) {
                Object itemId = container.addItem ();
                Item item = container.getItem ( itemId );
                item.getItemProperty ( PROPERTY_COUNTRY ).setValue ( dataModel.getCountry () );
                item.getItemProperty ( PROPERTY_PRODUCT_TYPE ).setValue ( dataModel.getProductType () );
                item.getItemProperty ( PROPERTY_CATALOGUE ).setValue ( dataModel.getCatalogue () );
                item.getItemProperty ( PROPERTY_PLANNED_PRINTER ).setValue ( dataModel.getPlannedPrinter () );
                item.getItemProperty ( PROPERTY_CHEAPEST ).setValue ( dataModel.getCheapest () );
                item.getItemProperty ( PROPERTY_CONTRACTOR ).setValue ( dataModel.getContractor () );
                item.getItemProperty ( PROPERTY_TOTAL_COSTS ).setValue ( dataModel.getTotalCosts () );
                item.getItemProperty ( PROPERTY_DIFFERENCE_TO_MIN ).setValue ( dataModel.getDifferenceToMin () );
                item.getItemProperty ( PROPERTY_COMMENT ).setValue ( dataModel.getComment () );
                item.getItemProperty ( PROPERTY_ACTIVE ).setValue ( dataModel.getActive () );
                item.getItemProperty ( PROPERTY_COUNTER ).setValue ( dataModel.getCounter () );
            }
        }
    }
}
