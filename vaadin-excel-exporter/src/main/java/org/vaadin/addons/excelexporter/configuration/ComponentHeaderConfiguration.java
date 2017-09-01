/*
 * 
 */
package org.vaadin.addons.excelexporter.configuration;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.vaadin.ui.components.grid.HeaderRow;

/**
 * The Class ComponentHeaderConfiguration is used to configure footers for the
 * Excel component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(intoPackage = "*.builder")
public class ComponentHeaderConfiguration extends AbstractComponentHeaderFooterConfiguration<HeaderRow> {

	private boolean autoFilter = false;

	public boolean isAutoFilter() {
		return this.autoFilter;
	}

	public void setAutoFilter(boolean autoFilter) {
		this.autoFilter = autoFilter;
	}

}