package hucid2.hucid;

import java.net.MalformedURLException;
import java.net.URL;

public class DomainChecker {

	public static void domainChecker(Website website) {

		URL url;
		try {
			String urlCheck = website.getUrl();
			if (urlCheck != null && urlCheck != "") {
				url = new URL(urlCheck);

				String protocol = url.getProtocol();
				String host = url.getHost();
				int port = url.getPort();
				String path = url.getPath();

				String hostTrimmmed = host;
				String hostEnding = null;
				if (hostTrimmmed != null && hostTrimmmed.startsWith("www.")) {
					hostTrimmmed = hostTrimmmed.substring(4);
				}
				if (hostTrimmmed != null && hostTrimmmed.endsWith(".com")) {
					hostEnding = hostTrimmmed.substring(hostTrimmmed.length() - 4);
					hostTrimmmed = hostTrimmmed.substring(0, hostTrimmmed.length() - 4);

				} else if (hostTrimmmed != null && hostTrimmmed.endsWith(".co.uk")) {
					hostEnding = hostTrimmmed.substring(hostTrimmmed.length() - 6);
					hostTrimmmed = hostTrimmmed.substring(0, hostTrimmmed.length() - 6);
				}

				if (hostTrimmmed != null) {
					website.setHost(hostTrimmmed);
				}
				if (hostEnding != null) {
					website.setHostEnding(hostEnding);
				}

				if (hostTrimmmed.equalsIgnoreCase("theguardian")) {
					try {
						GuardianApi.guardianExtract(path, website);
						ContentChecker.guardianExtract(website);
					} catch (Exception e) {

					}
				} else if (hostTrimmmed.equalsIgnoreCase("bbc")) {
					ContentChecker.bbcExtract(path, website);
				} else if (hostTrimmmed.equalsIgnoreCase("independent")) {
					ContentChecker.independentExtract(website);
				} else {
					ContentChecker.contentChecker(website);
				}

			}

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
