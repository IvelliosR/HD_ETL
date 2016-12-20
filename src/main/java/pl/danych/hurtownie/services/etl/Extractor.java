package pl.danych.hurtownie.services.etl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.danych.hurtownie.objects.ExtractedData;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Extractor {
	private static String source_page = "http://www.ceneo.pl";
	
	public ExtractedData extract(String product_number) throws IOException {
		List<Document> pages = new ArrayList<Document>();
		
		String link = Extractor.source_page+"/"+product_number;
		
		Date start_date = new Date();
		
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
		
		Date end_date = new Date();
		
		return new  ExtractedData(pages, start_date, end_date);
	}
	
	private Document getPage(String link) {
		Document page = null;
        try {
        	page = Jsoup.connect(link)
        			.header("Accept-Encoding", "gzip, deflate")
        			.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
        		    .maxBodySize(0)
        		    .timeout(600000)
        		    .get();

        } catch(IOException e) {}
        
        return page;
	}
	
	private boolean hasNextPage(Document page) {
		Boolean NotIsButtonNext = page
									.select(".pagination")
									.select(".page-arrow.arrow-next")
									.isEmpty();
		if(NotIsButtonNext) {
			return false;
		} else {
			return true;
		}
	}
	
	private String getNextPageLink(Document page) {
		String link_from_page = page
									.select(".pagination")
									.select(".page-arrow.arrow-next")
									.select("a")
									.attr("href");
		return Extractor.source_page+link_from_page;
	}
}
