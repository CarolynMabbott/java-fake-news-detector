package hucid2.hucid;

public class WebsiteRating {
	private int overallRating;
	private int hostRealibility;
	private int hostEndingPercentage;
	private int containsBasicInfo;
	private int webSearchResults;

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public int getHostRealibility() {
		return hostRealibility;
	}

	public void setHostRealibility(int hostRealibility) {
		this.hostRealibility = hostRealibility;
	}

	public int getHostEndingPercentage() {
		return hostEndingPercentage;
	}

	public void setHostEndingPercentage(int hostEndingPercentage) {
		this.hostEndingPercentage = hostEndingPercentage;
	}

	public int getContainsBasicInfo() {
		return containsBasicInfo;
	}

	public void setContainsBasicInfo(int containsBasicInfo) {
		this.containsBasicInfo = containsBasicInfo;
	}

	public int getWebSearchResults() {
		return webSearchResults;
	}

	public void setWebSearchResults(int webSearchResults) {
		this.webSearchResults = webSearchResults;
	}

	public static void setRatings(Website website, WebsiteRating websiteRating) {

		hostPercentage(website, websiteRating);
		hostEndingPercentage(website, websiteRating);
		containsBasicInfo(website, websiteRating);
		overallPercentage(website, websiteRating);
	}

	public static void hostPercentage(Website website, WebsiteRating websiteRating) {

		String host = website.getHost();

		if (host != null) {
			if (host.equalsIgnoreCase("bbc") || host.equalsIgnoreCase("theguardian") || host.equalsIgnoreCase("ITV")
					|| host.equalsIgnoreCase("channel4") || host.equalsIgnoreCase("independent")) {
				websiteRating.setHostRealibility(100);
			} else if (host.equalsIgnoreCase("sun") || host.equalsIgnoreCase("dailymail")
					|| host.equalsIgnoreCase("mailonsunday")) {
				websiteRating.setHostRealibility(50);
			} else if (host.equalsIgnoreCase("onion")) {
				websiteRating.setHostRealibility(20);
			} else {
				websiteRating.setHostRealibility(0);
			}
		} else {
			websiteRating.setHostRealibility(0);
		}

	}

	public static void hostEndingPercentage(Website website, WebsiteRating websiteRating) {
		String hostEnding = website.getHostEnding();

		if (hostEnding != null) {
			if (hostEnding.equalsIgnoreCase(".co.uk") || hostEnding.equalsIgnoreCase(".com")
					|| hostEnding.equalsIgnoreCase(".no") || hostEnding.equalsIgnoreCase(".se")
					|| hostEnding.equalsIgnoreCase(".fi") || hostEnding.equalsIgnoreCase(".dk")
					|| hostEnding.equalsIgnoreCase(".nl") || hostEnding.equalsIgnoreCase(".ch")
					|| hostEnding.equalsIgnoreCase(".be")) {
				websiteRating.setHostEndingPercentage(100);
			} else if (hostEnding.equalsIgnoreCase(".ru")) {
				websiteRating.setHostEndingPercentage(20);
			} else if (hostEnding.equalsIgnoreCase(".cn") || hostEnding.equalsIgnoreCase(".cu")
					|| hostEnding.equalsIgnoreCase(".dj") || hostEnding.equalsIgnoreCase(".kp")
					|| hostEnding.equalsIgnoreCase(".sy") || hostEnding.equalsIgnoreCase(".tm")
					|| hostEnding.equalsIgnoreCase(".sd")) {
				websiteRating.setHostEndingPercentage(0);
			} else {
				websiteRating.setHostRealibility(0);
			}
		} else {
			websiteRating.setHostEndingPercentage(0);
		}
	}

	public static void containsBasicInfo(Website website, WebsiteRating websiteRating) {
		int points = 0;
		String date = website.getDate();
		String author = website.getAuthor();
		String title = website.getTitle();

		if (date != null) {
			points = points + 40;
		}
		if (title != null) {
			points = points + 40;
		}
		if (author != null) {
			points = points + 20;
		}

		websiteRating.setContainsBasicInfo(points);

	}

	private static void overallPercentage(Website website, WebsiteRating websiteRating) {
		float score = 0;

		int host = websiteRating.getHostRealibility();
		int hostEnd = websiteRating.getHostEndingPercentage();
		int basicInfo = websiteRating.getContainsBasicInfo();
		int webSearchResults = websiteRating.getWebSearchResults();

		// Weightings
		host = host * 40;
		hostEnd = hostEnd * 10;
		basicInfo = basicInfo * 10;
		webSearchResults = webSearchResults * 40;

		score = ((host + hostEnd + basicInfo + webSearchResults) / 100);

		websiteRating.setOverallRating((int) score);
	}

	public static void setRatingsWebSearch(Website website, WebsiteRating websiteRating) {

		hostPercentage(website, websiteRating);
		hostEndingPercentage(website, websiteRating);
		containsBasicInfo(website, websiteRating);
		overallPercentageWebSearch(website, websiteRating);
	}

	private static void overallPercentageWebSearch(Website website, WebsiteRating websiteRating) {
		float score = 0;

		int host = websiteRating.getHostRealibility();
		int hostEnd = websiteRating.getHostEndingPercentage();
		int basicInfo = websiteRating.getContainsBasicInfo();

		// Weightings
		host = host * 60;
		hostEnd = hostEnd * 20;
		basicInfo = basicInfo * 20;

		score = ((host + hostEnd + basicInfo) / 100);

		websiteRating.setOverallRating((int) score);
	}

}