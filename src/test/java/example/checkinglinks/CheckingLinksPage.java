package example.checkinglinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinksPage {

	WebDriver driver;

	public CheckingLinksPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean OkLinks() {

		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> okLinks = new ArrayList<String>();
		List<String> brokenLinks = new ArrayList<String>();

		HttpURLConnection con = null;
		int code = 200;

		for (WebElement link : links) {
			url = link.getAttribute("href");

			if (url == null || url.isEmpty()) {
				System.out.println(url + " is empty or not configured");
				continue;
			}
			try {
				// abrir la conexión
				con = (HttpURLConnection) new URL(url).openConnection();

				// solicitar el head del html ya que es donde aparece el código de estado
				con.setRequestMethod("HEAD");

				con.connect();

				code = con.getResponseCode();

				if (code >= 400) {
					brokenLinks.add(url);
				} else {
					okLinks.add(url);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				brokenLinks.add(url);
				e.printStackTrace();
			}
		}

		System.out.println("total of broken links: " + brokenLinks.size());
		System.out.println("total of valid links: " + okLinks.size());

		if (brokenLinks.size() > 0) {
			for (int i = 0; i < brokenLinks.size(); i++) {
				System.out.println("broken url: " + brokenLinks.get(i));
			}
			return false;
		} else {
			return true;
		}
	}
}
