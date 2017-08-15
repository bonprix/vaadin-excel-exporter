package org.vaadin.addons.excelexporter.demo.model;

import java.io.Serializable;


/**
 * Example data model to demonstrate export functionalities.
 *
 * @author Kartik Suba
 *
 */
@SuppressWarnings ( "serial" )
public class DataModel implements Serializable {

    private String country;
    private String productType;
    private String catalogue;
    private String plannedPrinter;
    private String cheapest;
    private String contractor;
    private Double totalCosts;
    private Double differenceToMin;
    private String comment;
    private String coverPartner;
    private String contentProduction;
    private String contentPaper;
    private String contentFinishing;
    private String contentSender;
    private Double contentPrintCost;
    private Double contentPaperCost;
    private Double finishingCost;
    private Double finishingTruckCost;
    private Double senderCost;
    private Double frightCoverToFinishingCost;
    private Double diffToMinTransportContent;
    private Boolean active;
    private Integer counter;

    public String getCountry () {
        return country;
    }

    public void setCountry ( String country ) {
        this.country = country;
    }

    public String getProductType () {
        return productType;
    }

    public void setProductType ( String productType ) {
        this.productType = productType;
    }

    public String getCatalogue () {
        return catalogue;
    }

    public void setCatalogue ( String catalogue ) {
        this.catalogue = catalogue;
    }

    public String getPlannedPrinter () {
        return plannedPrinter;
    }

    public void setPlannedPrinter ( String plannedPrinter ) {
        this.plannedPrinter = plannedPrinter;
    }

    public String getCheapest () {
        return cheapest;
    }

    public void setCheapest ( String cheapest ) {
        this.cheapest = cheapest;
    }

    public String getContractor () {
        return contractor;
    }

    public void setContractor ( String contractor ) {
        this.contractor = contractor;
    }

    public Double getTotalCosts () {
        return totalCosts;
    }

    public void setTotalCosts ( Double totalCosts ) {
        this.totalCosts = totalCosts;
    }

    public Double getDifferenceToMin () {
        return differenceToMin;
    }

    public void setDifferenceToMin ( Double differenceToMin ) {
        this.differenceToMin = differenceToMin;
    }

    public String getComment () {
        return comment;
    }

    public void setComment ( String comment ) {
        this.comment = comment;
    }

    public String getCoverPartner () {
        return coverPartner;
    }

    public void setCoverPartner ( String coverPartner ) {
        this.coverPartner = coverPartner;
    }

    public String getContentProduction () {
        return contentProduction;
    }

    public void setContentProduction ( String contentProduction ) {
        this.contentProduction = contentProduction;
    }

    public String getContentPaper () {
        return contentPaper;
    }

    public void setContentPaper ( String contentPaper ) {
        this.contentPaper = contentPaper;
    }

    public String getContentFinishing () {
        return contentFinishing;
    }

    public void setContentFinishing ( String contentFinishing ) {
        this.contentFinishing = contentFinishing;
    }

    public String getContentSender () {
        return contentSender;
    }

    public void setContentSender ( String contentSender ) {
        this.contentSender = contentSender;
    }

    public Double getContentPrintCost () {
        return contentPrintCost;
    }

    public void setContentPrintCost ( Double contentPrintCost ) {
        this.contentPrintCost = contentPrintCost;
    }

    public Double getContentPaperCost () {
        return contentPaperCost;
    }

    public void setContentPaperCost ( Double contentPaperCost ) {
        this.contentPaperCost = contentPaperCost;
    }

    public Double getFinishingCost () {
        return finishingCost;
    }

    public void setFinishingCost ( Double finishingCost ) {
        this.finishingCost = finishingCost;
    }

    public Double getFinishingTruckCost () {
        return finishingTruckCost;
    }

    public void setFinishingTruckCost ( Double finishingTruckCost ) {
        this.finishingTruckCost = finishingTruckCost;
    }

    public Double getSenderCost () {
        return senderCost;
    }

    public void setSenderCost ( Double senderCost ) {
        this.senderCost = senderCost;
    }

    public Double getFrightCoverToFinishingCost () {
        return frightCoverToFinishingCost;
    }

    public void setFrightCoverToFinishingCost ( Double frightCoverToFinishingCost ) {
        this.frightCoverToFinishingCost = frightCoverToFinishingCost;
    }

    public Double getDiffToMinTransportContent () {
        return diffToMinTransportContent;
    }

    public void setDiffToMinTransportContent ( Double diffToMinTransportContent ) {
        this.diffToMinTransportContent = diffToMinTransportContent;
    }

    public Boolean getActive () {
        return active;
    }

    public void setActive ( Boolean active ) {
        this.active = active;
    }

    public Integer getCounter () {
        return counter;
    }

    public void setCounter ( Integer counter ) {
        this.counter = counter;
    }

}
