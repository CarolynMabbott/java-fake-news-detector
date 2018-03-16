package hucid2.hucid;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebSearch {

	public static void webSearch(Website website, WebsiteRating websiteRating) {

		int noOfSites = 0;
		int noOfReliableSites = 0;
		String webAddress = website.getUrl();

		String search = website.getTitle();
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)";
		Elements webResults = null;
		try {
			webResults = Jsoup.connect("https://www.google.co.uk/search?q=" + URLEncoder.encode(search))
					.userAgent(userAgent).get().select(".g");

			for (Element links : webResults) {

				Website webResultsWebsite = new Website();
				WebsiteRating webResultsWebsiteRating = new WebsiteRating();

				Elements subs = links.select(".r>a");
				for (Element link : subs) {
					String title = link.text();
					String url = link.absUrl("href"); // Google returns URLs in
					// Format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
					try {
						url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!url.startsWith("http")) {
						continue;
					}

					webResultsWebsite.setTitle(title);
					webResultsWebsite.setUrl(url);
				}
				Elements train = links.select(".s>.st");
				String date = train.text();
				if (date.contains("...")) {
					date = date.substring(0, date.indexOf("...")).trim();

				}
				if (date != null && date != "") {
					webResultsWebsite.setDate(date);
				}
				DomainChecker.domainChecker(webResultsWebsite);
				WebsiteRating.setRatingsWebSearch(webResultsWebsite, webResultsWebsiteRating);

				String webResultsAddress = webResultsWebsite.getUrl();
				if (webAddress != null && webAddress != "" && webResultsAddress != null
						&& webResultsAddress.equalsIgnoreCase(webAddress)) {
					if (webResultsWebsiteRating.getOverallRating() > 65) {

						noOfReliableSites = noOfReliableSites + 1;
					}
					noOfSites = noOfSites + 1;
				}

				website = null;
				webResultsWebsiteRating = null;
			}

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int results = results(noOfReliableSites, noOfSites);
		websiteRating.setWebSearchResults(results);

	}

	public static int results(int noOfReliableSites, int noOfSites) {

		int x = 0;

		if (noOfSites != 0) {
			x = (noOfReliableSites / noOfSites) * 100;
		}

		if (x > 75) {
			return 100;
		} else if (x > 65) {
			return 80;
		} else if (x > 50) {
			return 60;
		} else if (x > 35) {
			return 40;
		} else {
			return x;
		}

	}

}
