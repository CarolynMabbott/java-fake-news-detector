package hucid2.hucid;

import com.mashape.unirest.http.exceptions.UnirestException;

import theGuardianAPI.GuardianContentApi;
import theGuardianAPI.Response;

public class GuardianApi {

	static String apiKey = "d8835733-c394-400b-a634-273ae4507cbc";

	public static void guardianExtract(String path, Website website) throws UnirestException {

		GuardianContentApi api = new GuardianContentApi("d8835733-c394-400b-a634-273ae4507cbc");

		Response response = api.getContent(path);

		String title = response.getContent().getWebPublicationDate();
		System.out.println("Web title is: " + title);

		if (response.getContent().getWebTitle() != null) {
			website.setTitle(response.getContent().getWebTitle());
		}
		if (response.getContent().getWebPublicationDate() != null) {
			website.setDate(response.getContent().getWebPublicationDate());
		}

	}

}
