package org.vaadin.addons.excelexporter.demo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.vaadin.addons.excelexporter.demo.DataModel;

public class DataModelGenerator {

	private DataModelGenerator() {
	}

	private static final String[] CATALOGUES = { "ABC", "DEF", "GHI", "JKL", "MNO", "PQR", "STU", "VWX", "YZ" };
	private static final String[] COUNTRIES = { "India", "Germany", "America", "Italy", "Spain", "Portugal" };
	private static final String[] PRODUCTTYPES = { "Car", "Motorcycle", "Bicycle" };
	private static final String[] COMPANIES = { "Audi", "VW", "Fiat", "Lexus", "Suzuki" };
	private static final String[] QUALITYTYPES = { "Luxury", "Premium", "Sports", "Racing", "Standard" };

	public static Collection<DataModel> generate(int cnt) {

		ArrayList<DataModel> result = new ArrayList<>();
		for (int i = 1; i <= cnt; i++) {
			result.add(getDataModel(CATALOGUES[new Random().nextInt(CATALOGUES.length)],
									COUNTRIES[new Random().nextInt(COUNTRIES.length)],
									PRODUCTTYPES[new Random().nextInt(PRODUCTTYPES.length)],
									COMPANIES[new Random().nextInt(COMPANIES.length)], new Random().nextInt() + "$",
									"contractor" + i, new Random().nextDouble(), new Random().nextDouble(),
									new Random().nextBoolean(), new Random().nextInt(),
									QUALITYTYPES[new Random().nextInt(QUALITYTYPES.length)]));
		}

		return result;
	}

	private static DataModel getDataModel(String catalogue, String country, String productType, String plannedPrinter,
			String cheapest, String contractor, Double totalCosts, Double differenceToMin, Boolean active,
			Integer counter, String comment) {
		DataModel dataModel = new DataModel();
		dataModel.setCatalogue(catalogue);
		dataModel.setCountry(country);
		dataModel.setProductType(productType);
		dataModel.setPlannedPrinter(plannedPrinter);
		dataModel.setCheapest(cheapest);
		dataModel.setContractor(contractor);
		dataModel.setTotalCosts(totalCosts);
		dataModel.setDifferenceToMin(differenceToMin);
		dataModel.setActive(active);
		dataModel.setCounter(counter);
		dataModel.setComment(comment);
		return dataModel;
	}

}
