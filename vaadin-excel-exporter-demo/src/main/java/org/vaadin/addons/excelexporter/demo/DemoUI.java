package org.vaadin.addons.excelexporter.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import org.vaadin.addons.excelexporter.formatter.BooleanColumnFormatter;
import org.vaadin.addons.excelexporter.formatter.ColumnFormatter;
import org.vaadin.addons.excelexporter.formatter.SuffixColumnFormatter;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Kartik Suba
 *
 */
@Theme("demo")
@Title("vaadin-excel-exporter Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    List<DataModel> orderList;
    private com.vaadin.ui.MenuBar exportToolBar = null;
    private Table tableWithBeanItemContainer;
    private BeanItemContainer<DataModel> beanItemContainer;
    private Table tableWithIndexedContainer;
    private IndexedContainer indexedContainer;
    TreeTable treeTable = new TreeTable();
    Grid gridWithIndexedContainer = new Grid();
    Grid gridWithBeanItemContainer = new Grid();
    Object[] visibleColumns = new Object[] { "country", "productType", "catalogue", "plannedPrinter", "cheapest", "contractor", "totalCosts",
            "differenceToMin", "comment", "active", "counter" };
    String[] columnHeaders = new String[] { "COUNTRY", "PRODUCT_TYPE", "CATALOGUE", "PLANNED_PRINTER", "CHEAPEST", "CONTRACTOR", "TOTAL_COST",
            "DIFFERENCE_TO_MIN", "COMMENT", "ACTIVE", "COUNTER" };

    @WebServlet(
            value = "/*",
            asyncSupported = true)
    @VaadinServletConfiguration(
            productionMode = false,
            ui = DemoUI.class,
            widgetset = "org.vaadin.addons.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void init(final VaadinRequest request) {

        // Creating the Export Tool Bar
        createToolBar();

        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        // Adding the Export Tool Bar to the Layout
        layout.addComponent(this.exportToolBar);

        // Initializing container to be used by various components
        this.indexedContainer = new IndexedContainer();
        this.indexedContainer.addContainerProperty("country", String.class, "");
        this.indexedContainer.addContainerProperty("productType", String.class, "");
        this.indexedContainer.addContainerProperty("catalogue", String.class, "");
        this.indexedContainer.addContainerProperty("plannedPrinter", String.class, "");
        this.indexedContainer.addContainerProperty("cheapest", String.class, "");
        this.indexedContainer.addContainerProperty("contractor", String.class, "");
        this.indexedContainer.addContainerProperty("totalCosts", Double.class, "");
        this.indexedContainer.addContainerProperty("differenceToMin", Double.class, "");
        this.indexedContainer.addContainerProperty("active", Boolean.class, "");
        this.indexedContainer.addContainerProperty("counter", Integer.class, "");
        this.indexedContainer.addContainerProperty("comment", String.class, "");

        this.beanItemContainer = new BeanItemContainer<DataModel>(DataModel.class);

        /********* Adding Components to the Layout namely Tables, Grids and Tree Table *******/
        layout.addComponent(getExportTableWithBeanItemContainer());
        layout.addComponent(getExportTableWithIndexedContainer());
        layout.addComponent(getExportTreeTable());
        layout.addComponent(getExportGridWithIndexedContainer());
        layout.addComponent(getExportGridWithBeanItemContainer());

        layout.setExpandRatio(this.exportToolBar, 10f);
        layout.setExpandRatio(this.tableWithBeanItemContainer, 18f);
        layout.setExpandRatio(this.tableWithIndexedContainer, 18f);
        layout.setExpandRatio(this.treeTable, 18f);
        layout.setExpandRatio(this.gridWithIndexedContainer, 18f);
        layout.setExpandRatio(this.gridWithBeanItemContainer, 18f);

        /********* Adding Components to the Layout namely Tables, Grids and Tree Table *******/

        // Loading the data for the above added components
        getDataForTheComponents();

        /********* Adding the above data to the containers or components *******/
        this.beanItemContainer.removeAllItems();
        this.beanItemContainer.addAll(this.orderList.subList(0, 2));

        this.indexedContainer.removeAllItems();
        for (DataModel orderPlacementReport : this.orderList) {
            Object itemId = this.indexedContainer.addItem();
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("country")
                    .setValue(orderPlacementReport.getCountry());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("productType")
                    .setValue(orderPlacementReport.getProductType());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("catalogue")
                    .setValue(orderPlacementReport.getCatalogue());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("plannedPrinter")
                    .setValue(orderPlacementReport.getPlannedPrinter());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("cheapest")
                    .setValue(orderPlacementReport.getCheapest());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("contractor")
                    .setValue(orderPlacementReport.getContractor());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("totalCosts")
                    .setValue(orderPlacementReport.getTotalCosts());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("differenceToMin")
                    .setValue(orderPlacementReport.getDifferenceToMin());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("comment")
                    .setValue(orderPlacementReport.getComment());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("active")
                    .setValue(orderPlacementReport.getActive());
            this.indexedContainer.getItem(itemId)
                    .getItemProperty("counter")
                    .setValue(orderPlacementReport.getCounter());

        }

        this.treeTable.addItem(this.orderList.get(0));
        this.treeTable.addItem(this.orderList.get(1));
        this.treeTable.addItem(this.orderList.get(2));
        this.treeTable.addItem(this.orderList.get(3));
        this.treeTable.addItem(this.orderList.get(4));
        this.treeTable.setParent(this.orderList.get(1), this.orderList.get(0));
        this.treeTable.setParent(this.orderList.get(2), this.orderList.get(0));
        this.treeTable.setParent(this.orderList.get(4), this.orderList.get(3));
        this.treeTable.setChildrenAllowed(this.orderList.get(1), false);
        this.treeTable.setChildrenAllowed(this.orderList.get(2), false);
        this.treeTable.setChildrenAllowed(this.orderList.get(4), false);
        /********* Adding the above data to the containers or components *******/

        setContent(layout);
    }

    /**
     * This method creates the tool bar with options XLSX and XLS.
     */
    private void createToolBar() {

        this.exportToolBar = new MenuBar();
        this.exportToolBar.addItem("XLSX", FontAwesome.DOWNLOAD, new Command() {

            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ExportToExcelUtility<DataModel> exportToExcelUtility = customizeExportExcelUtility();
                exportToExcelUtility.setSourceUI(UI.getCurrent());
                exportToExcelUtility.setResultantExportType(ExportType.XLSX);
                exportToExcelUtility.export();
            }
        });

        this.exportToolBar.addItem("XLS", FontAwesome.DOWNLOAD, new Command() {

            @Override
            public void menuSelected(final MenuItem selectedItem) {
                ExportToExcelUtility<DataModel> exportToExcelUtility = customizeExportExcelUtility();
                exportToExcelUtility.setSourceUI(UI.getCurrent());
                exportToExcelUtility.setResultantExportType(ExportType.XLS);
                exportToExcelUtility.export();
            }
        });
    }

    /**
     * Configuring ExportToExcel Utility This configuration allows the end user-developer to \ Add multiple sheets and configure them separately \ Configure
     * components to be added in each sheet and their properties
     *
     * @return ExportToExcelUtility
     */
    private ExportToExcelUtility<DataModel> customizeExportExcelUtility() {

        HashMap<Object, ColumnFormatter> columnFormatters = new HashMap<>();

        // Suffix Formatter provided
        columnFormatters.put("totalCosts", new SuffixColumnFormatter("$"));
        columnFormatters.put("differenceToMin", new SuffixColumnFormatter("$"));
        columnFormatters.put("cheapest", new SuffixColumnFormatter("-quite cheap"));

        // Boolean Formatter provided
        columnFormatters.put("active", new BooleanColumnFormatter("Yes", "No"));

        // Custom Formatting also possible
        columnFormatters.put("catalogue", new ColumnFormatter() {

            @Override
            public Object generateCell(final Object value, final Object itemId, final Object columnId) {
                return (value != null) ? ((String) value).toLowerCase() : null;
            }
        });

        /* Configuring Components with Headers and Footers */
        ExportExcelComponentConfiguration componentConfig1 = new ExportExcelComponentConfigurationBuilder().withTable(this.tableWithBeanItemContainer)
                .withVisibleProperties(this.tableWithBeanItemContainer.getVisibleColumns())
                .withHeaderConfigs(Arrays.asList(new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.tableWithBeanItemContainer.getColumnHeaders())
                                                         .withMergedCells(Arrays.asList(new MergedCellBuilder().withStartProperty("cheapest")
                                                                 .withEndProperty("totalCosts")
                                                                 .withHeaderKey("Costs")
                                                                 .build(), new MergedCellBuilder().withStartProperty("productType")
                                                                 .withEndProperty("plannedPrinter")
                                                                 .withHeaderKey("Types")
                                                                 .build(), new MergedCellBuilder().withStartProperty("differenceToMin")
                                                                 .withEndProperty("counter")
                                                                 .withHeaderKey("Remaining")
                                                                 .build()))
                                                         .build(),
                                                 new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.tableWithBeanItemContainer.getColumnHeaders())
                                                         .build()))
                .withIntegerFormattingProperties(Arrays.asList("counter"))
                .withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
                .withBooleanFormattingProperties(Arrays.asList("active"))
                .withColumnFormatters(columnFormatters)
                .build();

        ExportExcelComponentConfiguration componentConfig2 = new ExportExcelComponentConfigurationBuilder().withTable(this.tableWithIndexedContainer)
                .withVisibleProperties(this.tableWithIndexedContainer.getVisibleColumns())
                .withHeaderConfigs(Arrays.asList(new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.tableWithIndexedContainer.getColumnHeaders())
                                           .build()))
                .withIntegerFormattingProperties(Arrays.asList("counter"))
                .withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
                .withBooleanFormattingProperties(Arrays.asList("active"))
                .withColumnFormatters(columnFormatters)
                .build();

        ExportExcelComponentConfiguration componentConfig3 = new ExportExcelComponentConfigurationBuilder().withTreeTable(this.treeTable)
                .withVisibleProperties(this.treeTable.getVisibleColumns())
                .withHeaderConfigs(Arrays.asList(new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.treeTable.getColumnHeaders())
                        .build()))
                .withColRowFreeze(3)
                .withIntegerFormattingProperties(Arrays.asList("counter"))
                .withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
                .withBooleanFormattingProperties(Arrays.asList("active"))
                .withColumnFormatters(columnFormatters)
                .build();

        ExportExcelComponentConfiguration componentConfig4 = new ExportExcelComponentConfigurationBuilder().withGrid(this.gridWithIndexedContainer)
                .withVisibleProperties(this.visibleColumns)
                .withHeaderConfigs(Arrays.asList(new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.columnHeaders)
                        .build()))
                .withIntegerFormattingProperties(Arrays.asList("counter"))
                .withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
                .withBooleanFormattingProperties(Arrays.asList("active"))
                .withColumnFormatters(columnFormatters)
                .build();

        ExportExcelComponentConfiguration componentConfig5 = new ExportExcelComponentConfigurationBuilder().withGrid(this.gridWithBeanItemContainer)
                .withVisibleProperties(this.visibleColumns)
                .withHeaderConfigs(Arrays.asList(new ComponentHeaderConfigurationBuilder().withHeaderRow(this.gridWithBeanItemContainer.getDefaultHeaderRow())
                        .withMergedCells(Arrays.asList(new MergedCellBuilder().withStartProperty("cheapest")
                                .withEndProperty("totalCosts")
                                .withHeaderKey("Costs")
                                .build()))
                        .build(), new ComponentHeaderConfigurationBuilder().withColumnHeaderKeys(this.columnHeaders)
                        .build()))
                .withIntegerFormattingProperties(Arrays.asList("counter"))
                .withFloatFormattingProperties(Arrays.asList("totalCosts", "differenceToMin"))
                .withBooleanFormattingProperties(Arrays.asList("active"))
                .withColumnFormatters(columnFormatters)
                .build();

        /* Configuring Sheets */
        ExportExcelSheetConfiguration sheetConfig1 = new ExportExcelSheetConfigurationBuilder().withIsDefaultGeneratedByRequired(Boolean.FALSE)
                .withComponentConfigs(Arrays.asList(componentConfig1, componentConfig2))
                .withIsDefaultSheetTitleRequired(Boolean.FALSE)
                .withIsHeaderSectionRequired(Boolean.FALSE)
                .withDateFormat("dd-MMM-yyyy")
                .build();

        ExportExcelSheetConfiguration sheetConfig2 = new ExportExcelSheetConfigurationBuilder().withReportTitle("Exported Tree Table")
                .withSheetName("Excel Tree Table")
                .withComponentConfigs(Arrays.asList(componentConfig3))
                .withIsHeaderSectionRequired(Boolean.TRUE)
                .build();

        ExportExcelSheetConfiguration sheetConfig3 = new ExportExcelSheetConfigurationBuilder().withReportTitle("Exported Grid")
                .withSheetName("Excel Grid")
                .withComponentConfigs(Arrays.asList(componentConfig4, componentConfig5))
                .withIsHeaderSectionRequired(Boolean.TRUE)
                .build();

        /* Configuring Excel */
        ExportExcelConfiguration config1 = new ExportExcelConfigurationBuilder().withGeneratedBy("Kartik Suba")
                .withSheetConfigs(Arrays.asList(sheetConfig1, sheetConfig2, sheetConfig3))
                .build();

        return new ExportToExcelUtility<DataModel>(this.tableWithBeanItemContainer.getUI(), config1, DataModel.class);
    }

    public Table getExportTableWithBeanItemContainer() {

        this.tableWithBeanItemContainer = new Table("TableWithBeanItemContainer", this.beanItemContainer);
        this.tableWithBeanItemContainer.setVisibleColumns(new Object[] { "country", "productType", "catalogue", "plannedPrinter", "cheapest", "contractor",
                "totalCosts", "differenceToMin", "comment", "active", "counter" });
        this.tableWithBeanItemContainer.setColumnHeaders(new String[] { "COUNTRY", "PRODUCT_TYPE", "CATALOGUE", "PLANNED_PRINTER", "CHEAPEST", "CONTRACTOR",
                "TOTAL_COST", "DIFFERENCE_TO_MIN", "COMMENT", "ACTIVE", "COUNTER" });
        this.tableWithBeanItemContainer.setWidth(100, Unit.PERCENTAGE);
        this.tableWithBeanItemContainer.setHeight(100, Unit.PERCENTAGE);

        return this.tableWithBeanItemContainer;
    }

    private Component getExportTableWithIndexedContainer() {

        this.tableWithIndexedContainer = new Table("TableWithIndexedContainer", this.indexedContainer);
        this.tableWithIndexedContainer.setVisibleColumns(new Object[] { "country", "productType", "catalogue", "plannedPrinter", "cheapest", "contractor",
                "totalCosts", "differenceToMin", "comment", "active", "counter" });
        this.tableWithIndexedContainer.setColumnHeaders(new String[] { "COUNTRY", "PRODUCT_TYPE", "CATALOGUE", "PLANNED_PRINTER", "CHEAPEST", "CONTRACTOR",
                "TOTAL_COST", "DIFFERENCE_TO_MIN", "COMMENT", "ACTIVE", "COUNTER" });
        this.tableWithIndexedContainer.setWidth(100, Unit.PERCENTAGE);
        this.tableWithIndexedContainer.setHeight(100, Unit.PERCENTAGE);

        return this.tableWithIndexedContainer;
    }

    public TreeTable getExportTreeTable() {
        this.treeTable.setCaption("TreeTable");
        this.treeTable.addContainerProperty("country", String.class, "");
        this.treeTable.addContainerProperty("productType", String.class, "");
        this.treeTable.addContainerProperty("catalogue", String.class, "");
        this.treeTable.addContainerProperty("plannedPrinter", String.class, "");
        this.treeTable.addContainerProperty("cheapest", String.class, "");
        this.treeTable.addContainerProperty("contractor", String.class, "");
        this.treeTable.addContainerProperty("totalCosts", Double.class, "");
        this.treeTable.addContainerProperty("differenceToMin", Double.class, "");
        this.treeTable.addContainerProperty("comment", String.class, "");
        this.treeTable.addContainerProperty("active", Boolean.class, "");
        this.treeTable.addContainerProperty("counter", Integer.class, "");
        this.treeTable.setVisibleColumns(new Object[] { "country", "productType", "catalogue", "plannedPrinter", "cheapest", "contractor", "totalCosts",
                "differenceToMin", "comment", "active", "counter" });
        this.treeTable.setColumnHeaders(new String[] { "COUNTRY", "PRODUCT_TYPE", "CATALOGUE", "PLANNED_PRINTER", "CHEAPEST", "CONTRACTOR", "TOTAL_COST",
                "DIFFERENCE_TO_MIN", "COMMENT", "ACTIVE", "COUNTER" });

        this.treeTable.addGeneratedColumn("country", getNonEditableColumnGenerator("country"));
        this.treeTable.addGeneratedColumn("productType", getNonEditableColumnGenerator("productType"));
        this.treeTable.addGeneratedColumn("catalogue", getNonEditableColumnGenerator("catalogue"));
        this.treeTable.addGeneratedColumn("plannedPrinter", getNonEditableColumnGenerator("plannedPrinter"));
        this.treeTable.addGeneratedColumn("cheapest", getNonEditableColumnGenerator("cheapest"));
        this.treeTable.addGeneratedColumn("contractor", getNonEditableColumnGenerator("contractor"));
        this.treeTable.addGeneratedColumn("totalCosts", getModelValueColumnGenerator("totalCosts"));
        this.treeTable.addGeneratedColumn("differenceToMin", getModelValueColumnGenerator("differenceToMin"));
        this.treeTable.addGeneratedColumn("comment", getNonEditableColumnGenerator("comment"));
        this.treeTable.addGeneratedColumn("active", getModelValueColumnGenerator("comment"));
        this.treeTable.addGeneratedColumn("counter", getModelValueColumnGenerator("comment"));

        this.treeTable.setWidth(100, Unit.PERCENTAGE);
        this.treeTable.setHeight(100, Unit.PERCENTAGE);

        return this.treeTable;
    }

    private ColumnGenerator getNonEditableColumnGenerator(final String suffix) {
        return new ColumnGenerator() {

            @Override
            public Object generateCell(final Table source, final Object itemId, final Object columnId) {

                Label label = new Label();
                Object obj = getValue(source, itemId, columnId);
                if (obj != null) {
                    label.setValue(obj.toString());
                }
                return label;

            }
        };
    }

    private ColumnGenerator getModelValueColumnGenerator(final String suffix) {
        return new ColumnGenerator() {

            @Override
            public Object generateCell(final Table source, final Object itemId, final Object columnId) {

                Object obj = getValue(source, itemId, columnId);
                return obj;

            }
        };
    }

    private Object getValue(final Table source, final Object itemId, final Object columnId) {

        try {
            Field field;
            try {
                field = itemId.getClass()
                        .getDeclaredField(columnId.toString());
                field.setAccessible(true);

                Object obj = field.get(itemId);
                return obj;

            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Grid getExportGridWithIndexedContainer() {
        this.gridWithIndexedContainer.setCaption("GridWithIndexedContainer");
        this.gridWithIndexedContainer.setContainerDataSource(this.indexedContainer);
        this.gridWithIndexedContainer.setWidth(100, Unit.PERCENTAGE);
        this.gridWithIndexedContainer.setHeight(100, Unit.PERCENTAGE);

        return this.gridWithIndexedContainer;
    }

    public Grid getExportGridWithBeanItemContainer() {
        this.gridWithBeanItemContainer.setCaption("GridWithBeanItemContainer");
        this.gridWithBeanItemContainer.setContainerDataSource(this.beanItemContainer);
        this.gridWithBeanItemContainer.setWidth(100, Unit.PERCENTAGE);
        this.gridWithBeanItemContainer.setHeight(100, Unit.PERCENTAGE);

        return this.gridWithBeanItemContainer;
    }

    public List<DataModel> getDataForTheComponents() {

        this.orderList = new ArrayList<DataModel>();

        DataModel orderPlacementReport1 = new DataModel();
        orderPlacementReport1.setCatalogue("ABC");
        orderPlacementReport1.setCountry("India");
        orderPlacementReport1.setProductType("Cars");
        orderPlacementReport1.setPlannedPrinter("Audi");
        orderPlacementReport1.setCheapest("112365$");
        orderPlacementReport1.setContractor("contractor1");
        orderPlacementReport1.setTotalCosts(123654.00d);
        orderPlacementReport1.setDifferenceToMin(12361223.12d);
        orderPlacementReport1.setActive(Boolean.TRUE);
        orderPlacementReport1.setCounter(1459622);
        orderPlacementReport1.setComment("Luxury Car");

        DataModel orderPlacementReport2 = new DataModel();
        orderPlacementReport2.setCatalogue("XYZ");
        orderPlacementReport2.setCountry("German");
        orderPlacementReport2.setProductType("Cars");
        orderPlacementReport2.setPlannedPrinter("VW");
        orderPlacementReport2.setCheapest("1125895$");
        orderPlacementReport2.setContractor("contractor2");
        orderPlacementReport2.setTotalCosts(14526.19d);
        orderPlacementReport2.setDifferenceToMin(587499.12d);
        orderPlacementReport2.setActive(Boolean.FALSE);
        orderPlacementReport2.setCounter(2587416);
        orderPlacementReport2.setComment("Premium Car");

        DataModel orderPlacementReport3 = new DataModel();
        orderPlacementReport3.setCatalogue("DEF");
        orderPlacementReport3.setCountry("French");
        orderPlacementReport3.setProductType("Cars");
        orderPlacementReport3.setPlannedPrinter("Fiat");
        orderPlacementReport3.setCheapest("3698541$");
        orderPlacementReport3.setContractor("contractor3");
        orderPlacementReport3.setTotalCosts(25896.25d);
        orderPlacementReport3.setDifferenceToMin(2541896.36d);
        orderPlacementReport3.setActive(Boolean.FALSE);
        orderPlacementReport3.setCounter(999859);
        orderPlacementReport3.setComment("Sports Car");

        DataModel orderPlacementReport4 = new DataModel();
        orderPlacementReport4.setCatalogue("PQR");
        orderPlacementReport4.setCountry("Mexican");
        orderPlacementReport4.setProductType("Cars");
        orderPlacementReport4.setPlannedPrinter("Lexus");
        orderPlacementReport4.setCheapest("3434$");
        orderPlacementReport4.setContractor("contractor4");
        orderPlacementReport4.setTotalCosts(254163.19d);
        orderPlacementReport4.setDifferenceToMin(8574859.19d);
        orderPlacementReport4.setActive(Boolean.FALSE);
        orderPlacementReport4.setCounter(6652416);
        orderPlacementReport4.setComment("Racing Car");

        DataModel orderPlacementReport5 = new DataModel();
        orderPlacementReport5.setCatalogue("GHI");
        orderPlacementReport5.setCountry("Japanese");
        orderPlacementReport5.setProductType("Cars");
        orderPlacementReport5.setPlannedPrinter("Suzuki");
        orderPlacementReport5.setCheapest("23432323$");
        orderPlacementReport5.setContractor("contractor5");
        orderPlacementReport5.setTotalCosts(9639985.20d);
        orderPlacementReport5.setDifferenceToMin(41521.19d);
        orderPlacementReport5.setActive(Boolean.TRUE);
        orderPlacementReport5.setCounter(44521635);
        orderPlacementReport5.setComment("Standard Car");

        this.orderList.add(orderPlacementReport1);
        this.orderList.add(orderPlacementReport2);
        this.orderList.add(orderPlacementReport3);
        this.orderList.add(orderPlacementReport4);
        this.orderList.add(orderPlacementReport5);
        return this.orderList;
    }

}
