package org.vaadin.addons.excelexporter.demo;

/**
 *
 * @author Kartik Suba
 *
 */
public class DataModel {

	private String country = null;
	private String productType = null;
	private String catalogue = null;
	private String plannedPrinter = null;
	private String cheapest = null;
	private String contractor = null;
	private Double totalCosts = null;
	private Double differenceToMin = null;
	private String comment = null;
	private String coverPartner = null;
	private String contentProduction = null;
	private String contentPaper = null;
	private String contentFinishing = null;
	private String contentSender = null;
	private Double contentPrintCost = null;
	private Double contentPaperCost = null;
	private Double finishingCost = null;
	private Double finishingTruckCost = null;
	private Double senderCost = null;
	private Double frightCoverToFinishingCost = null;
	private Double diffToMinTransportContent = null;
	private Boolean active;

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public Integer getCounter() {
		return this.counter;
	}

	public void setCounter(final Integer counter) {
		this.counter = counter;
	}

	Integer counter;

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(final String productType) {
		this.productType = productType;
	}

	public String getCatalogue() {
		return this.catalogue;
	}

	public void setCatalogue(final String catalogue) {
		this.catalogue = catalogue;
	}

	public String getPlannedPrinter() {
		return this.plannedPrinter;
	}

	public void setPlannedPrinter(final String plannedPrinter) {
		this.plannedPrinter = plannedPrinter;
	}

	public String getCheapest() {
		return this.cheapest;
	}

	public void setCheapest(final String cheapest) {
		this.cheapest = cheapest;
	}

	public String getContractor() {
		return this.contractor;
	}

	public void setContractor(final String contractor) {
		this.contractor = contractor;
	}

	public String getCoverPartner() {
		return this.coverPartner;
	}

	public void setCoverPartner(final String coverPartner) {
		this.coverPartner = coverPartner;
	}

	public String getContentProduction() {
		return this.contentProduction;
	}

	public void setContentProduction(final String contentProduction) {
		this.contentProduction = contentProduction;
	}

	public String getContentPaper() {
		return this.contentPaper;
	}

	public void setContentPaper(final String contentPaper) {
		this.contentPaper = contentPaper;
	}

	public String getContentFinishing() {
		return this.contentFinishing;
	}

	public void setContentFinishing(final String contentFinishing) {
		this.contentFinishing = contentFinishing;
	}

	public String getContentSender() {
		return this.contentSender;
	}

	public void setContentSender(final String contentSender) {
		this.contentSender = contentSender;
	}

	public Double getTotalCosts() {
		return this.totalCosts;
	}

	public void setTotalCosts(final Double totalCosts) {
		this.totalCosts = totalCosts;
	}

	public Double getDifferenceToMin() {
		return this.differenceToMin;
	}

	public void setDifferenceToMin(final Double differenceToMin) {
		this.differenceToMin = differenceToMin;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public Double getContentPrintCost() {
		return this.contentPrintCost;
	}

	public void setContentPrintCost(final Double contentPrintCost) {
		this.contentPrintCost = contentPrintCost;
	}

	public Double getContentPaperCost() {
		return this.contentPaperCost;
	}

	public void setContentPaperCost(final Double contentPaperCost) {
		this.contentPaperCost = contentPaperCost;
	}

	public Double getFinishingCost() {
		return this.finishingCost;
	}

	public void setFinishingCost(final Double finishingCost) {
		this.finishingCost = finishingCost;
	}

	public Double getFinishingTruckCost() {
		return this.finishingTruckCost;
	}

	public void setFinishingTruckCost(final Double finishingTruckCost) {
		this.finishingTruckCost = finishingTruckCost;
	}

	public Double getSenderCost() {
		return this.senderCost;
	}

	public void setSenderCost(final Double senderCost) {
		this.senderCost = senderCost;
	}

	public Double getFrightCoverToFinishingCost() {
		return this.frightCoverToFinishingCost;
	}

	public void setFrightCoverToFinishingCost(final Double frightCoverToFinishingCost) {
		this.frightCoverToFinishingCost = frightCoverToFinishingCost;
	}

	public Double getDiffToMinTransportContent() {
		return this.diffToMinTransportContent;
	}

	public void setDiffToMinTransportContent(final Double diffToMinTransportContent) {
		this.diffToMinTransportContent = diffToMinTransportContent;
	}

}
