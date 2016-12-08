package services.etl;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.*;

public class Extractor {
	private static String source_page = "http://www.ceneo.pl";
	
	public List<Document> extract(String product_number) throws IOException {
		List<Document> pages = new ArrayList<Document>();
		
		String link = Extractor.source_page+"/"+product_number;
		
		Document page = this.getPage(link);
		if (page != null) {
			pages.add(page);
		} else {
			throw new IOException();
		}
		
		while(this.hasNextPage(page)) {
			link = this.getNextPageLink(page);
			page = this.getPage(link);
			if (page != null) {
				pages.add(page);
			}
		}
		
		return pages;
	}
	
	private Document getPage(String link) {
		Document page = null;
        try {
            page = Jsoup.connect(link).userAgent("Mozilla").get();
        } catch(IOException e) {}
        
        return page;
	}
	
	private boolean hasNextPage(Document page) {
		if(page.select(".pagination").select(".page-arrow.arrow-next").isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	private String getNextPageLink(Document page) {
		String link_from_page = page.select(".pagination").select(".page-arrow.arrow-next").select("a").attr("href");
		return Extractor.source_page+link_from_page;
	}
}
