package org.vaadin.addons;

import java.util.ArrayList;

import javax.annotation.Generated;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;

/**
 * The {@link ExportExcelComponentConfigurationBuilder} is a Builder for {@link ExportExcelComponentConfiguration} objects.
 *
 * <p>
 * This class has been generated. Please DO NOT MODIFIY this file since it will be overwritten by the PojoBuilder generator.
 * </p>
 * 
 * @author Kartik Suba
 */
@Generated("PojoBuilder")
public class ExportExcelComponentConfigurationBuilder implements Cloneable {
    protected ExportExcelComponentConfigurationBuilder self;
    protected Object[] value$visibleProperties$java$lang$Object$;
    protected boolean isSet$visibleProperties$java$lang$Object$ = false;

    protected XSSFCellStyle value$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected XSSFCellStyle value$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected Grid value$grid$com$vaadin$ui$Grid;
    protected boolean isSet$grid$com$vaadin$ui$Grid = false;

    protected Table value$table$com$vaadin$ui$Table;
    protected boolean isSet$table$com$vaadin$ui$Table = false;

    protected TreeTable value$treeTable$com$vaadin$ui$TreeTable;
    protected boolean isSet$treeTable$com$vaadin$ui$TreeTable = false;

    protected ArrayList<String> value$integerFormattingProperties$java$util$ArrayList;
    protected boolean isSet$integerFormattingProperties$java$util$ArrayList = false;

    protected Integer value$colRowFreeze$java$lang$Integer;
    protected boolean isSet$colRowFreeze$java$lang$Integer = false;

    protected XSSFCellStyle value$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected ArrayList<String> value$dateFormattingProperties$java$util$ArrayList;
    protected boolean isSet$dateFormattingProperties$java$util$ArrayList = false;

    protected ArrayList<String> value$floatFormattingProperties$java$util$ArrayList;
    protected boolean isSet$floatFormattingProperties$java$util$ArrayList = false;

    protected XSSFCellStyle value$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle;
    protected boolean isSet$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = false;

    protected String[] value$columnHeaderKeys$java$lang$String$;
    protected boolean isSet$columnHeaderKeys$java$lang$String$ = false;

    /**
     * Creates a new {@link ExportExcelComponentConfigurationBuilder}.
     */
    public ExportExcelComponentConfigurationBuilder() {
        this.self = this;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#visibleProperties} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withVisibleProperties(final Object[] value) {
        this.value$visibleProperties$java$lang$Object$ = value;
        this.isSet$visibleProperties$java$lang$Object$ = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#rTableContentStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withRTableContentStyle(final XSSFCellStyle value) {
        this.value$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#rTableHeaderStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withRTableHeaderStyle(final XSSFCellStyle value) {
        this.value$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#grid} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withGrid(final Grid value) {
        this.value$grid$com$vaadin$ui$Grid = value;
        this.isSet$grid$com$vaadin$ui$Grid = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#table} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withTable(final Table value) {
        this.value$table$com$vaadin$ui$Table = value;
        this.isSet$table$com$vaadin$ui$Table = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#treeTable} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withTreeTable(final TreeTable value) {
        this.value$treeTable$com$vaadin$ui$TreeTable = value;
        this.isSet$treeTable$com$vaadin$ui$TreeTable = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#integerFormattingProperties} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withIntegerFormattingProperties(final ArrayList<String> value) {
        this.value$integerFormattingProperties$java$util$ArrayList = value;
        this.isSet$integerFormattingProperties$java$util$ArrayList = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#colRowFreeze} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withColRowFreeze(final Integer value) {
        this.value$colRowFreeze$java$lang$Integer = value;
        this.isSet$colRowFreeze$java$lang$Integer = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#tableHeaderStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withTableHeaderStyle(final XSSFCellStyle value) {
        this.value$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#dateFormattingProperties} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withDateFormattingProperties(final ArrayList<String> value) {
        this.value$dateFormattingProperties$java$util$ArrayList = value;
        this.isSet$dateFormattingProperties$java$util$ArrayList = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#floatFormattingProperties} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withFloatFormattingProperties(final ArrayList<String> value) {
        this.value$floatFormattingProperties$java$util$ArrayList = value;
        this.isSet$floatFormattingProperties$java$util$ArrayList = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#tableContentStyle} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withTableContentStyle(final XSSFCellStyle value) {
        this.value$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = value;
        this.isSet$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle = true;
        return this.self;
    }

    /**
     * Sets the default value for the {@link ExportExcelComponentConfiguration#columnHeaderKeys} property.
     * 
     * @param value the default value
     * @return this builder
     */
    public ExportExcelComponentConfigurationBuilder withColumnHeaderKeys(final String[] value) {
        this.value$columnHeaderKeys$java$lang$String$ = value;
        this.isSet$columnHeaderKeys$java$lang$String$ = true;
        return this.self;
    }

    /**
     * Returns a clone of this builder.
     * 
     * @return the clone
     */
    @Override
    public Object clone() {
        try {
            ExportExcelComponentConfigurationBuilder result = (ExportExcelComponentConfigurationBuilder) super.clone();
            result.self = result;
            return result;
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    /**
     * Returns a clone of this builder.
     * 
     * @return the clone
     */
    public ExportExcelComponentConfigurationBuilder but() {
        return (ExportExcelComponentConfigurationBuilder) clone();
    }

    /**
     * Creates a new {@link ExportExcelComponentConfiguration} based on this builder's settings.
     * 
     * @return the created ExportExcelComponentConfiguration
     */
    public ExportExcelComponentConfiguration build() {
        ExportExcelComponentConfiguration result = new ExportExcelComponentConfiguration();

        if (this.isSet$visibleProperties$java$lang$Object$) {
            result.setVisibleProperties(this.value$visibleProperties$java$lang$Object$);
        }
        if (this.isSet$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrTableContentStyle(this.value$rTableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setrTableHeaderStyle(this.value$rTableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$grid$com$vaadin$ui$Grid) {
            result.setGrid(this.value$grid$com$vaadin$ui$Grid);
        }
        if (this.isSet$table$com$vaadin$ui$Table) {
            result.setTable(this.value$table$com$vaadin$ui$Table);
        }
        if (this.isSet$treeTable$com$vaadin$ui$TreeTable) {
            result.setTreeTable(this.value$treeTable$com$vaadin$ui$TreeTable);
        }
        if (this.isSet$integerFormattingProperties$java$util$ArrayList) {
            result.setIntegerFormattingProperties(this.value$integerFormattingProperties$java$util$ArrayList);
        }
        if (this.isSet$colRowFreeze$java$lang$Integer) {
            result.setColRowFreeze(this.value$colRowFreeze$java$lang$Integer);
        }
        if (this.isSet$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setTableHeaderStyle(this.value$tableHeaderStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$dateFormattingProperties$java$util$ArrayList) {
            result.setDateFormattingProperties(this.value$dateFormattingProperties$java$util$ArrayList);
        }
        if (this.isSet$floatFormattingProperties$java$util$ArrayList) {
            result.setFloatFormattingProperties(this.value$floatFormattingProperties$java$util$ArrayList);
        }
        if (this.isSet$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle) {
            result.setTableContentStyle(this.value$tableContentStyle$org$apache$poi$xssf$usermodel$XSSFCellStyle);
        }
        if (this.isSet$columnHeaderKeys$java$lang$String$) {
            result.setColumnHeaderKeys(this.value$columnHeaderKeys$java$lang$String$);
        }

        return result;
    }

}