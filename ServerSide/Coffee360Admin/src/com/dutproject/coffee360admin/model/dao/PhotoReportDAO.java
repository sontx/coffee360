package com.dutproject.coffee360admin.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dutproject.coffee360.model.bean.PhotoReport;
import com.dutproject.coffee360.model.bean.Report;
import com.dutproject.coffee360.model.bean.ReportState;
import com.dutproject.coffee360admin.util.Converter;

public class PhotoReportDAO extends BaseDAO {
	private static final String PATH = getPath("Coffee360Service/rest/v1/report/photo");
	private WebTarget target = ClientBuilder.newClient().target(PATH);

	public int getCountReport() {
		Response response = target
				.path("/count")
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			int count = toCountReport(new JSONObject(jsonString));
			return count;
		}
		return 0;
	}

	private int toCountReport(JSONObject jsonObject) {
		return jsonObject.getInt("value");
	}

	public PhotoReport getReportById(int reportId) {
		Response response = target
				.path("/getone")
				.queryParam("id", reportId)
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			PhotoReport report = toReport(new JSONObject(jsonString));
			return report;
		}
		return null;
	}

	public List<PhotoReport> getListReports(int fromIndex, int toIndex) {
		Response response = target
				.path("/get")
				.queryParam("fromIndex", fromIndex)
				.queryParam("toIndex", toIndex)
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			List<PhotoReport> reports = toReports(new JSONArray(jsonString));
			return reports;
		}
		return null;
	}

	private List<PhotoReport> toReports(JSONArray jsonArray) {
		int length = jsonArray.length();
		List<PhotoReport> reports = new ArrayList<PhotoReport>();
		for (int i = 0; i < length; ++i) {
			PhotoReport report = toReport(jsonArray.getJSONObject(i));
			reports.add(report);
		}
		return reports;
	}

	private PhotoReport toReport(JSONObject jsonObject) {
		String dateTime = jsonObject.getString("dateTime");
		int accountId = jsonObject.getInt("accountId");
		String caption = jsonObject.getString("caption");
		int uploadedPhotoId = jsonObject.getInt("uploadedPhotoId");
		int id = jsonObject.getInt("id");
		String state = jsonObject.getString("state");
		
		Report report = new Report(id, accountId, caption,
				Converter.toTimestamp(dateTime), ReportState.valueOf(state));
		PhotoReport photoReport = new PhotoReport(report, uploadedPhotoId);
		return photoReport;
	}

	public int getQuantity(int uploadedPhotoId) {
		Response response = target
				.path("/quantity")
				.queryParam("id", uploadedPhotoId)
				.request(MediaType.APPLICATION_JSON)
				.header("Authorization", getAuthorizationString())
				.get();
		if (isSuccessful(response.getStatus())) {
			String jsonString = response.readEntity(String.class);
			int quantity = toQuantity(new JSONObject(jsonString));
			return quantity;
		}
		return 0;
	}

	private int toQuantity(JSONObject jsonObject) {
		return jsonObject.getInt("value");
	}

}
