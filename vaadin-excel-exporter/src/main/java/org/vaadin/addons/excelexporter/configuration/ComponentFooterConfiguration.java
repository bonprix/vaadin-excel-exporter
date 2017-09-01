/*
 * 
 */
package org.vaadin.addons.excelexporter.configuration;

import net.karneim.pojobuilder.GeneratePojoBuilder;

import com.vaadin.ui.components.grid.FooterRow;

/**
 * The Class ComponentFooterConfiguration is used to configure headers for the
 * Excel component
 *
 * @author Kartik Suba
 */
@GeneratePojoBuilder(intoPackage = "*.builder")
public class ComponentFooterConfiguration extends AbstractComponentHeaderFooterConfiguration<FooterRow> {

}