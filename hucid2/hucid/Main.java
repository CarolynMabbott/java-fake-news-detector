package hucid2.hucid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

	public static void main(String[] args) throws MalformedURLException {

		GUI.setGUI();

	}

	public static void startingProgram() {

		String website = "https://www.google.co.uk/";

		boolean internetConnection = testWebsite(website);
		if (internetConnection == true) {
			System.out.println("There is a internet connection");

			String websiteToBeChecked = GUI.urlEnter.getText().trim();

			if (websiteToBeChecked != null) {
				boolean websiteChecked = testWebsite(websiteToBeChecked);
				if (websiteChecked == true) {
					RealibilityChecker.realibilityChecker(websiteToBeChecked);
				} else {
					GUI.urlError();
				}

			}

		} else {
			System.out.println("No internet connection");
			GUI.webConnectvityError();
		}

	}

	public static boolean testWebsite(String website) {
		// Check that there is internet connection. Checks google as it is a reliable
		// site with short loading times.

		try {
			URL url = new URL(website);

			URLConnection connection = url.openConnection();
			connection.connect();

			return true;

		} catch (MalformedURLException e) {
			System.out.println("Malformed URL Exception for " + e);

		} catch (IOException e) {
			System.out.println("IO Exception for " + e);
		} catch (Exception e) {

		}

		return false;
	}
}