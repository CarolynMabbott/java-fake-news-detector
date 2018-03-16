package hucid2.hucid;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ContentChecker {

	public static void contentChecker(Website website) {

		try {
			Document doc = Jsoup.connect(website.getUrl()).get();

			Elements title = doc.select("title");
			String titleCorrected = title.text().trim();

			if (titleCorrected.endsWith("- BBC News")) {
				titleCorrected = titleCorrected.substring(0, titleCorrected.length() - 10).trim();
			}
			if (titleCorrected != null && titleCorrected != "") {
				website.setTitle(titleCorrected);
			}

			Elements date = doc
					.select("#content > div.region.region-content > article > div.meta-wrapper > ul > li:nth-child(2)");

			String dateCorrected = date.text().trim();

			Elements author = doc.select(".author [title]");
			String authorString = author.text().trim();

			if (authorString.contains("title=")) {
				authorString = authorString.substring(authorString.indexOf("title="));
			}

			if (authorString != null && authorString.trim() != "") {
				website.setAuthor(authorString);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void bbcExtract(String path, Website website) {

		try {
			Document doc = Jsoup.connect(website.getUrl()).get();

			Elements title = doc.select("title");
			String titleCorrected = title.text().trim();

			if (titleCorrected.endsWith("- BBC News")) {
				titleCorrected = titleCorrected.substring(0, titleCorrected.length() - 10).trim();
			}
			if (titleCorrected != null && titleCorrected != "") {
				website.setTitle(titleCorrected);
			}

			Elements date = doc.select(
					"#page > div > div.container > div > div.column--primary > div.story-body > div.with-extracted-share-icons > div > div.story-body__mini-info-list-and-share-row > div.mini-info-list-wrap > ul > li:nth-child(1) > div");

			String dateCorrected = date.text().trim();
			if (dateCorrected != null && dateCorrected.trim() != "") {
				website.setDate(dateCorrected);
			}

			Elements author = doc.select(
					"#page > div > div.container > div > div.column--primary > div.story-body > div.byline > span.byline__name");
			String authorString = author.text().trim();
			if (authorString != null && authorString.trim() != "") {
				website.setAuthor(authorString);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void independentExtract(Website website) {

		try {
			Document doc = Jsoup.connect(website.getUrl()).get();

			Elements title = doc.select("title");
			String titleCorrected = title.text().trim();

			if (titleCorrected.endsWith("- BBC News")) {
				titleCorrected = titleCorrected.substring(0, titleCorrected.length() - 10).trim();
			}
			if (titleCorrected != null && titleCorrected != "") {
				website.setTitle(titleCorrected);
			}

			Elements date = doc
					.select("#content > div.region.region-content > article > div.meta-wrapper > ul > li:nth-child(2)");

			String dateCorrected = date.text().trim();
			// Correct date here

			Elements author = doc.select(".author [title]");
			String authorString = author.text().trim();

			if (authorString.contains("title=")) {
				authorString = authorString.substring(authorString.indexOf("title="));
			}

			if (authorString != null && authorString.trim() != "") {
				website.setAuthor(authorString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void guardianExtract(Website website) {
		try {
			Document doc = Jsoup.connect(website.getUrl()).get();

			Elements author = doc.select(
					"#article > div.content__main.tonal__main.tonal__main--tone-news > div > div.content__main-column.content__main-column--article.js-content-main-column > header > div.content__meta-container.js-content-meta.js-football-meta.u-cf > div.meta__contact-wrap > p.byline > span > a > span");

			String authorString = author.text().trim();
			if (authorString != null && authorString.trim() != "") {
				website.setAuthor(authorString);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
