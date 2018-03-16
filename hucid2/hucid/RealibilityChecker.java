package hucid2.hucid;

public class RealibilityChecker {

	public static void realibilityChecker(String url) {
		// Goes through each different factor in determining if the source and
		// the news is legit

		/*
		 * Website domain checker
		 * 
		 * Story content
		 * 
		 * Website content
		 * 
		 * Other sources of news
		 */

		Website website = new Website();
		website.setUrl(url);

		WebsiteRating websiteRating = new WebsiteRating();

		DomainChecker.domainChecker(website);

		WebSearch.webSearch(website, websiteRating);

		WebsiteRating.setRatings(website, websiteRating);

		GUI.updatePercentages(website, websiteRating);

	}

}
