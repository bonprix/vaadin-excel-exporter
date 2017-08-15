package org.vaadin.addons.excelexporter.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.excelexporter.ExportExcelComponentConfiguration;
import org.vaadin.addons.excelexporter.ExportExcelConfiguration;
import org.vaadin.addons.excelexporter.ExportExcelSheetConfiguration;
import org.vaadin.addons.excelexporter.ExportToExcelUtility;
import org.vaadin.addons.excelexporter.ExportType;
import org.vaadin.addons.excelexporter.builder.ComponentHeaderConfigurationBuilder;
import org.vaadin.addons.excelexporter.builder.ExportExcelComponentConfigurationBuilder;
import org.vaadin.addons.excelexporter.builder.ExportExcelConfigurationBuilder;
import org.vaadin.addons.excelexporter.builder.ExportExcelSheetConfigurationBuilder;
import org.vaadin.addons.excelexporter.builder.MergedCellBuilder;
import org.vaadin.addons.excelexporter.demo.component.DataModelGridWithBeanItemContainer;
import org.vaadin.addons.excelexporter.demo.component.DataModelGridWithIndexedContainer;
import org.vaadin.addons.excelexporter.demo.component.DataModelTableWithBeanItemContainer;
import org.vaadin.addons.excelexporter.demo.component.DataModelTableWithIndexedContainer;
import org.vaadin.addons.excelexporter.demo.component.DataModelTreeTable;
import org.vaadin.addons.excelexporter.demo.model.DataModel;
import org.vaadin.addons.excelexporter.formatter.BooleanColumnFormatter;
import org.vaadin.addons.excelexporter.formatter.ColumnFormatter;
import org.vaadin.addons.excelexporter.formatter.SuffixColumnFormatter;

import com.thetransactioncompany.cors.CORSFilter;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


/**
 * The Demo UI.
 */
@SuppressWarnings ( "serial" )
@Theme ( "demo" )
@Title ( "Excel Exporter Add-on Demo" )
@SpringUI
@PreserveOnRefresh
public class DemoUI extends UI {

    private final Button exportXLSButton = new Button ( "XLS", FontAwesome.DOWNLOAD );
    private final Button exportXLSXButton = new Button ( "XLSX", FontAwesome.DOWNLOAD );
    private final DataModelGridWithBeanItemContainer grid1 = new DataModelGridWithBeanItemContainer ();
    private final DataModelGridWithIndexedContainer grid2 = new DataModelGridWithIndexedContainer ();
    private final DataModelTableWithBeanItemContainer table1 = new DataModelTableWithBeanItemContainer ();
    private final DataModelTableWithIndexedContainer table2 = new DataModelTableWithIndexedContainer ();
    private final DataModelTreeTable treeTable1 = new DataModelTreeTable ();

    @Override
    protected void init ( VaadinRequest request ) {

        List<DataModel> records = MockData.getDataForTheComponents ();
        grid1.setRecords ( records );
        grid2.setRecords ( records.subList ( 0, 2 ) );
        table1.setRecords ( records );
        table2.setRecords ( records.subList ( 0, 2 ) );

        //A couple of example records
        DataModel item1 = records.get ( 0 );
        treeTable1.addItem ( item1 );

        DataModel item2 = records.get ( 1 );
        treeTable1.addItem ( item2 );
        treeTable1.setParent ( item2, item1 );
        treeTable1.setChildrenAllowed ( item2, false );

        DataModel item3 = records.get ( 2 );
        treeTable1.addItem ( item3 );
        treeTable1.setParent ( item3, item1 );
        treeTable1.setChildrenAllowed ( item3, false );

        DataModel item4 = records.get ( 3 );
        treeTable1.addItem ( item4 );

        DataModel item5 = records.get ( 4 );
        treeTable1.addItem ( item5 );
        treeTable1.setParent ( item5, item4 );
        treeTable1.setChildrenAllowed ( item5, false );

        exportXLSButton.addStyleName ( ValoTheme.BUTTON_ICON_ALIGN_TOP );
        exportXLSButton.addClickListener ( event -> export ( ExportType.XLS ) );

        exportXLSXButton.addStyleName ( ValoTheme.BUTTON_ICON_ALIGN_TOP );
        exportXLSXButton.addClickListener ( event -> export ( ExportType.XLSX ) );

        grid1.setCaption ( "Vaadin Grid with BeanItemContainer" );
        grid2.setCaption ( "Vaadin Grid with IndexedContainer" );
        table1.setCaption ( "Vaadin Table with BeanItemContainer" );
        table2.setCaption ( "Vaadin Table with IndexedContainer" );
        treeTable1.setCaption ( "Vaadin TreeTable" );

        HorizontalLayout buttons = new HorizontalLayout ( exportXLSButton, exportXLSXButton );
        buttons.setExpandRatio ( exportXLSXButton, 1.0f );
        buttons.setWidth ( 100, Unit.PERCENTAGE );
        buttons.setMargin ( false );
        buttons.setSpacing ( true );
        buttons.addStyleName ( ValoTheme.WINDOW_TOP_TOOLBAR );

        VerticalLayout root = new VerticalLayout ();
        root.setSizeFull ();
        root.setMargin ( false );
        root.setSpacing ( false );
        root.addComponent ( buttons );

        VerticalLayout panelContent = new VerticalLayout ();
        panelContent.setMargin ( true );
        panelContent.setSpacing ( true );
        Panel panel = new Panel ( panelContent );
        panel.setSizeFull ();
        panelContent.addComponent ( grid1 );
        panelContent.addComponent ( grid2 );
        panelContent.addComponent ( table1 );
        panelContent.addComponent ( table2 );
        panelContent.addComponent ( treeTable1 );
        root.addComponent ( panel );
        root.setExpandRatio ( panel, 1.0f );
        setContent ( root );
    }

    private void export ( ExportType exportType ) {
        ExportToExcelUtility<DataModel> exportToExcelUtility = customizeExportExcelUtility ();
        exportToExcelUtility.setSourceUI ( UI.getCurrent () );
        exportToExcelUtility.setResultantExportType ( exportType );
        exportToExcelUtility.export ();
    }

    /**
     * Configuring ExportToExcel Utility This configuration allows the end user-developer to \ Add multiple sheets and configure them separately \ Configure
     * components to be added in each sheet and their properties
     *
     * @return ExportToExcelUtility
     */
    private ExportToExcelUtility<DataModel> customizeExportExcelUtility () {

        HashMap<Object, ColumnFormatter> columnFormatters = new HashMap<> ();

        // Suffix Formatter provided
        columnFormatters.put ( "totalCosts", new SuffixColumnFormatter ( "$" ) );
        columnFormatters.put ( "differenceToMin", new SuffixColumnFormatter ( "$" ) );
        columnFormatters.put ( "cheapest", new SuffixColumnFormatter ( "-quite cheap" ) );

        // Boolean Formatter provided
        columnFormatters.put ( "active", new BooleanColumnFormatter ( "Yes", "No" ) );

        // Custom Formatting also possible
        columnFormatters.put ( "catalogue", new ColumnFormatter () {

            @Override
            public Object generateCell ( final Object value, final Object itemId, final Object columnId ) {
                return ( value != null ) ? ( (String) value ).toLowerCase () : null;
            }
        } );

        /* Configuring Components with Headers and Footers */
        ExportExcelComponentConfiguration componentConfig1 = new ExportExcelComponentConfigurationBuilder ().withTable ( table1 )
            .withVisibleProperties ( table1.getVisibleColumns () )
            .withHeaderConfigs ( Arrays.asList (
                new ComponentHeaderConfigurationBuilder ().withColumnHeaderKeys ( table1.getColumnHeaders () )
                    .withMergedCells ( Arrays.asList ( new MergedCellBuilder ().withStartProperty ( "cheapest" )
                        .withEndProperty ( "totalCosts" )
                        .withHeaderKey ( "Costs" )
                        .build (),
                        new MergedCellBuilder ().withStartProperty ( "productType" )
                            .withEndProperty ( "plannedPrinter" )
                            .withHeaderKey ( "Types" )
                            .build (),
                        new MergedCellBuilder ().withStartProperty ( "differenceToMin" )
                            .withEndProperty ( "counter" )
                            .withHeaderKey ( "Remaining" )
                            .build () ) )
                    .build (),
                new ComponentHeaderConfigurationBuilder ().withColumnHeaderKeys ( table1.getColumnHeaders () )
                    .build () ) )
            .withIntegerFormattingProperties ( Arrays.asList ( "counter" ) )
            .withFloatFormattingProperties ( Arrays.asList ( "totalCosts", "differenceToMin" ) )
            .withBooleanFormattingProperties ( Arrays.asList ( "active" ) )
            .withColumnFormatters ( columnFormatters )
            .build ();

        ExportExcelComponentConfiguration componentConfig2 = new ExportExcelComponentConfigurationBuilder ().withTable ( table2 )
            .withVisibleProperties ( table2.getVisibleColumns () )
            .withHeaderConfigs (
                Arrays.asList ( new ComponentHeaderConfigurationBuilder ().withColumnHeaderKeys ( table2.getColumnHeaders () )
                    .build () ) )
            .withIntegerFormattingProperties ( Arrays.asList ( "counter" ) )
            .withFloatFormattingProperties ( Arrays.asList ( "totalCosts", "differenceToMin" ) )
            .withBooleanFormattingProperties ( Arrays.asList ( "active" ) )
            .withColumnFormatters ( columnFormatters )
            .build ();

        ExportExcelComponentConfiguration componentConfig3 = new ExportExcelComponentConfigurationBuilder ().withTreeTable ( treeTable1 )
            .withVisibleProperties ( treeTable1.getVisibleColumns () )
            .withHeaderConfigs ( Arrays.asList ( new ComponentHeaderConfigurationBuilder ().withColumnHeaderKeys ( treeTable1.getColumnHeaders () )
                .build () ) )
            .withColRowFreeze ( 3 )
            .withIntegerFormattingProperties ( Arrays.asList ( "counter" ) )
            .withFloatFormattingProperties ( Arrays.asList ( "totalCosts", "differenceToMin" ) )
            .withBooleanFormattingProperties ( Arrays.asList ( "active" ) )
            .withColumnFormatters ( columnFormatters )
            .build ();

        ExportExcelComponentConfiguration componentConfig5 = new ExportExcelComponentConfigurationBuilder ().withGrid ( grid1 )
            .withVisibleProperties ( grid1.getColumns ().stream ().filter ( column -> !column.isHidden () ).map ( Column::getPropertyId )
                .toArray ( Object []::new ) )
            .withHeaderConfigs ( Arrays.asList (
                new ComponentHeaderConfigurationBuilder ().withHeaderRow ( grid1.getDefaultHeaderRow () )
                    .withMergedCells ( Arrays.asList ( new MergedCellBuilder ().withStartProperty ( "cheapest" )
                        .withEndProperty ( "totalCosts" )
                        .withHeaderKey ( "Costs" )
                        .build () ) )
                    .build (),
                new ComponentHeaderConfigurationBuilder ()
                    .withColumnHeaderKeys ( grid1.getColumns ().stream ().filter ( column -> !column.isHidden () ).map ( Column::getHeaderCaption )
                        .toArray ( String []::new ) )
                    .build () ) )
            .withIntegerFormattingProperties ( Arrays.asList ( "counter" ) )
            .withFloatFormattingProperties ( Arrays.asList ( "totalCosts", "differenceToMin" ) )
            .withBooleanFormattingProperties ( Arrays.asList ( "active" ) )
            .withColumnFormatters ( columnFormatters )
            .build ();

        ExportExcelComponentConfiguration componentConfig4 = new ExportExcelComponentConfigurationBuilder ().withGrid ( grid2 )
            .withVisibleProperties ( grid2.getColumns ().stream ().filter ( column -> !column.isHidden () ).map ( Column::getPropertyId )
                .toArray ( Object []::new ) )
            .withHeaderConfigs ( Arrays.asList ( new ComponentHeaderConfigurationBuilder ()
                .withColumnHeaderKeys ( grid1.getColumns ().stream ().filter ( column -> !column.isHidden () ).map ( Column::getHeaderCaption )
                    .toArray ( String []::new ) )
                .build () ) )
            .withIntegerFormattingProperties ( Arrays.asList ( "counter" ) )
            .withFloatFormattingProperties ( Arrays.asList ( "totalCosts", "differenceToMin" ) )
            .withBooleanFormattingProperties ( Arrays.asList ( "active" ) )
            .withColumnFormatters ( columnFormatters )
            .build ();

        /* Configuring Sheets */
        ExportExcelSheetConfiguration sheetConfig1 = new ExportExcelSheetConfigurationBuilder ().withIsDefaultGeneratedByRequired ( Boolean.FALSE )
            .withComponentConfigs ( Arrays.asList ( componentConfig1, componentConfig2 ) )
            .withIsDefaultSheetTitleRequired ( Boolean.FALSE )
            .withIsHeaderSectionRequired ( Boolean.FALSE )
            .withDateFormat ( "dd-MMM-yyyy" )
            .build ();

        ExportExcelSheetConfiguration sheetConfig2 = new ExportExcelSheetConfigurationBuilder ().withReportTitle ( "Exported Tree Table" )
            .withSheetName ( "Excel Tree Table" )
            .withComponentConfigs ( Arrays.asList ( componentConfig3 ) )
            .withIsHeaderSectionRequired ( Boolean.TRUE )
            .build ();

        ExportExcelSheetConfiguration sheetConfig3 = new ExportExcelSheetConfigurationBuilder ().withReportTitle ( "Exported Grid" )
            .withSheetName ( "Excel Grid" )
            .withComponentConfigs ( Arrays.asList ( componentConfig4, componentConfig5 ) )
            .withIsHeaderSectionRequired ( Boolean.TRUE )
            .build ();

        /* Configuring Excel */
        ExportExcelConfiguration exportExcelConfiguration = new ExportExcelConfigurationBuilder ().withGeneratedBy ( "Kartik Suba" )
            .withSheetConfigs ( Arrays.asList ( sheetConfig1, sheetConfig2, sheetConfig3 ) )
            .build ();

        return new ExportToExcelUtility<> ( UI.getCurrent (), exportExcelConfiguration, DataModel.class );
    }

    /**
     * The UI.
     *
     * @return UI with correct type
     */
    public static DemoUI get () {
        return (DemoUI) UI.getCurrent ();
    }

    /**
     * The Servlet.
     */
    @WebServlet ( urlPatterns = "/*", name = "DemoUIServlet", asyncSupported = true )
    @VaadinServletConfiguration ( ui = DemoUI.class, productionMode = false )
    public static class DemoUIServlet extends SpringVaadinServlet {
    }

    /**
     * CORS Filter.
     */
    @WebFilter ( urlPatterns = "/*", initParams = @WebInitParam ( name = "cors.allowOrigin", value = "*" ) )
    public static class DemoUICORSFilter extends CORSFilter {
    }
}
