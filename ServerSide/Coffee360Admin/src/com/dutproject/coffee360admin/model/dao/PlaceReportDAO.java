package com.dutproject.coffee360admin.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dutproject.coffee360.model.bean.PrimitiveType;
import com.dutproject.coffee360.model.bean.PlaceReport;

public class PlaceReportDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/report/place");
	private Client client = ClientBuilder.newClient();

	public int getNumberOfReport() {
		Response response = client
				.target(PATH)
				.path("/count")
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get(Response.class);
		
		if (isSuccessful(response.getStatus())) {
			String result = response .readEntity(String.class);
			return new JSONObject(result).getInt("value");
		}
		return 0;
	}

	private List<PlaceReport> toReports(String jsonString) {
		JSONArray array = new JSONArray(jsonString);
		int length = array.length();
		List<PlaceReport> reports = new ArrayList<PlaceReport>();
		for (int i = 0; i < length; ++i) {
			JSONObject jsonObject = array.getJSONObject(i);
			reports.add(toReport(jsonObject));
		}
		return reports;
	}
	
	private PlaceReport toReport(JSONObject jsonObject) {
		int accountId = jsonObject.getInt("accountId");
		String caption = jsonObject.getString("caption");
		String dateTime = jsonObject.getString("dateTime");
		int id = jsonObject.getInt("id");
		String palceName = jsonObject.getString("palceName");
		int placeId = jsonObject.getInt("placeId");
		String state = jsonObject.getString("state");
		return new PlaceReport(id, accountId, placeId, caption, dateTime);
	}

	public PlaceReport getReport(int reportId) {
		Response response = client
				.target(String.format(PATH + "/getone?id=%d", reportId))
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.accept(MediaType.APPLICATION_JSON)
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			PlaceReport report = toPlaceReport(new JSONObject(jsonString));
			return report;
		}
		return null;
	}

	private PlaceReport toPlaceReport(JSONObject jsonObject) {
		int accountId = jsonObject.getInt("accountId");
		String caption = jsonObject.getString("caption");
		String dateTime = jsonObject.getString("dateTime");
		int id = jsonObject.getInt("id");
		String palceName = jsonObject.getString("palceName");
		int placeId = jsonObject.getInt("placeId");
		String state = jsonObject.getString("state");
		PlaceReport report = new PlaceReport(id, accountId, placeId, caption, dateTime);
		return report;
	}

	public List<PlaceReport> getListReport(int fromIndex, int toIndex) {
		Response response = client
				.target(String
						.format(PATH + "/get?fromIndex=%d&toIndex=%d",
								fromIndex, toIndex))
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			List<PlaceReport> reports = toReports(jsonString);
			return reports;
		}
		return null;
	}
	
	public int getReportQuantity(int placeId) {
		Response response = client
				.target(String.format(PATH + "/quantity?id=%d", placeId))
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response
					.readEntity(String.class);
			int quantity = new JSONObject(jsonString).getInt("value");
			return quantity;
		}
		return 0;
	}
	
}
