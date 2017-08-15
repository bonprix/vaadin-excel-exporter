/*
 * Copyright 2001-2017 iPoint-systems. All Rights Reserved.
 *
 * This software is proprietary information of iPoint-systems.
 * Use is subject to license terms.
 */
package org.vaadin.addons.excelexporter.demo;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.excelexporter.demo.model.DataModel;


/**
 * Helper class for data mocks.
 */
public final class MockData {

    private static List<DataModel> orderList;

    /**
     * Avoid instances.
     */
    private MockData () {
    }

    public static List<DataModel> getDataForTheComponents () {
        if ( orderList == null ) {
            orderList = new ArrayList<> ();

            DataModel orderPlacementReport1 = new DataModel ();
            orderPlacementReport1.setCatalogue ( "ABC" );
            orderPlacementReport1.setCountry ( "India" );
            orderPlacementReport1.setProductType ( "Cars" );
            orderPlacementReport1.setPlannedPrinter ( "Audi" );
            orderPlacementReport1.setCheapest ( "112365$" );
            orderPlacementReport1.setContractor ( "contractor1" );
            orderPlacementReport1.setTotalCosts ( 123654.00d );
            orderPlacementReport1.setDifferenceToMin ( 12361223.12d );
            orderPlacementReport1.setActive ( true );
            orderPlacementReport1.setCounter ( 1459622 );
            orderPlacementReport1.setComment ( "Luxury Car" );
            orderList.add ( orderPlacementReport1 );

            DataModel orderPlacementReport2 = new DataModel ();
            orderPlacementReport2.setCatalogue ( "XYZ" );
            orderPlacementReport2.setCountry ( "German" );
            orderPlacementReport2.setProductType ( "Cars" );
            orderPlacementReport2.setPlannedPrinter ( "VW" );
            orderPlacementReport2.setCheapest ( "1125895$" );
            orderPlacementReport2.setContractor ( "contractor2" );
            orderPlacementReport2.setTotalCosts ( 14526.19d );
            orderPlacementReport2.setDifferenceToMin ( 587499.12d );
            orderPlacementReport2.setActive ( false );
            orderPlacementReport2.setCounter ( 2587416 );
            orderPlacementReport2.setComment ( "Premium Car" );
            orderList.add ( orderPlacementReport2 );

            DataModel orderPlacementReport3 = new DataModel ();
            orderPlacementReport3.setCatalogue ( "DEF" );
            orderPlacementReport3.setCountry ( "French" );
            orderPlacementReport3.setProductType ( "Cars" );
            orderPlacementReport3.setPlannedPrinter ( "Fiat" );
            orderPlacementReport3.setCheapest ( "3698541$" );
            orderPlacementReport3.setContractor ( "contractor3" );
            orderPlacementReport3.setTotalCosts ( 25896.25d );
            orderPlacementReport3.setDifferenceToMin ( 2541896.36d );
            orderPlacementReport3.setActive ( false );
            orderPlacementReport3.setCounter ( 999859 );
            orderPlacementReport3.setComment ( "Sports Car" );
            orderList.add ( orderPlacementReport3 );

            DataModel orderPlacementReport4 = new DataModel ();
            orderPlacementReport4.setCatalogue ( "PQR" );
            orderPlacementReport4.setCountry ( "Mexican" );
            orderPlacementReport4.setProductType ( "Cars" );
            orderPlacementReport4.setPlannedPrinter ( "Lexus" );
            orderPlacementReport4.setCheapest ( "3434$" );
            orderPlacementReport4.setContractor ( "contractor4" );
            orderPlacementReport4.setTotalCosts ( 254163.19d );
            orderPlacementReport4.setDifferenceToMin ( 8574859.19d );
            orderPlacementReport4.setActive ( false );
            orderPlacementReport4.setCounter ( 6652416 );
            orderPlacementReport4.setComment ( "Racing Car" );
            orderList.add ( orderPlacementReport4 );

            DataModel orderPlacementReport5 = new DataModel ();
            orderPlacementReport5.setCatalogue ( "GHI" );
            orderPlacementReport5.setCountry ( "Japanese" );
            orderPlacementReport5.setProductType ( "Cars" );
            orderPlacementReport5.setPlannedPrinter ( "Suzuki" );
            orderPlacementReport5.setCheapest ( "23432323$" );
            orderPlacementReport5.setContractor ( "contractor5" );
            orderPlacementReport5.setTotalCosts ( 9639985.20d );
            orderPlacementReport5.setDifferenceToMin ( 41521.19d );
            orderPlacementReport5.setActive ( true );
            orderPlacementReport5.setCounter ( 44521635 );
            orderPlacementReport5.setComment ( "Standard Car" );
            orderList.add ( orderPlacementReport5 );
        }
        return orderList;
    }
}
