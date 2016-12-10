package services.etl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import objects.ExtractedData;
import objects.Product;
import objects.Comment;
import objects.Remark;

public class Transformator {
	
	public Product transform(ExtractedData extractedData) {
		Product product = null;
		List<Comment> comments = null;
		
		product = this.transformProductData(extractedData.getNext());
		extractedData.reset();
		comments = this.transformAllCommentsData(extractedData);
		product.setComments(comments);
		
		return product;
	}
	
	public Product transformProductData(Document page) {
		Product product = new Product();

		product.setType(this.extractType(page));
		product.setBrand(this.extractBrand(page));
		product.setModel(this.extractModel(page));
		product.setRemarks(this.extractRemarks(page));
		
		return product;
	}
	
	private String extractType(Document page) {
		return page
				.select(".breadcrumbs")
				.select(".breadcrumb")
				.last()
				.select("a")
				.select("span")
				.text();
	}
	
	private String extractBrand(Document page) {
		return page
				.select("head")
				.select("meta")
				.select("[property=og:brand]")
				.attr("content");
	}

	private String extractModel(Document page) {
		return page
				.select(".main-content")
				.select(".product")
				.select(".product-content")
				.select(".product-name")
				.text();
	}
	
	private List<Remark> extractRemarks(Document page) {
		List<Remark> remarks = new ArrayList<Remark>();
		Elements remarks_elements = page
										.select("#productTechSpecs")
										.select(".specs-group")
										.select("tr");
		
		for(Element remark : remarks_elements) {
			String remark_key = remark
									.select("th")
									.text();
			String remark_value = remark
									.select(".attr-value")
									.text();
			remarks.add(new Remark(remark_key, remark_value));
		}
		
		return remarks;
	}
	
	private List<Comment> transformCommentsData(Document page) {
		List<Comment> comments = new ArrayList<Comment>();
		Elements comments_elements = page
										.select(".main-content")
										.select(".screening-wrapper")
										.select(".product-reviews")
										.select(".product-review");
		
		for(Element comment_element : comments_elements) {
			Comment comment = new Comment();
			comment.setPros(this.extractPros(comment_element));
			comment.setCons(this.extractCons(comment_element));
			comment.setSummary(this.extractSummary(comment_element));
			comment.setRate(this.extractRate(comment_element));
			comment.setAuthor(this.extractAuthor(comment_element));
			comment.setCreateDate(this.extractCreateDate(comment_element));
			comment.setRecommend(this.extractRecomendet(comment_element));
			comment.setHelpful(this.extractHelpfull(comment_element));
			comment.setUnhelpful(this.extractUnhelpfull(comment_element));
			comments.add(comment);
		}
		
		return comments;
	}
	
	private List<String> extractPros(Element page) {
		List<String> pross = new ArrayList<String>();
		Elements pros_elements = page
									.select(".pros-cell")
									.select("li");
		
		for (Element pros : pros_elements) {
			pross.add(pros.text());
		}
		
		return pross;
	}
	
	private List<String> extractCons(Element page) {
		List<String> conss = new ArrayList<String>();
		Elements cons_elements = page
									.select(".cons-cell")
									.select("li");
		
		for (Element cons : cons_elements) {
			conss.add(cons.text());
		}
		
		return conss;
	}
	
	private String extractSummary(Element page) {
		return page
				.select(".product-review-body")
				.text();
	}
	
	private float extractRate(Element page) {
		String count = page
							.select(".review-score-count")
							.text();
		count = count.substring(0, count.indexOf('/'));
		count = count.replace(',', '.');
		return Float.parseFloat(count);
	}

	private String extractAuthor(Element page) {
		return page
				.select(".product-reviewer")
				.text();
	}
	
	private String extractCreateDate(Element page) {
		return page
				.select(".review-time")
				.select("time")
				.attr("datetime");
	}
	
	private boolean extractRecomendet(Element page) {
		if (page
				.select(".product-recommended")
				.text()
				.equals("Polecam")) {
			return true;
		}
		return false;
	}
	
	private int extractHelpfull(Element page) {
		String quantity = page
							.select(".js_product-review-usefulness.vote")
							.select(".vote-yes")
							.select("span")
							.text();
		return Integer.parseInt(quantity);
	}
	
	private int extractUnhelpfull(Element page) {
		String quantity = page
				.select(".js_product-review-usefulness.vote")
				.select(".vote-no")
				.select("span")
				.text();
		return Integer.parseInt(quantity);
	}
	
	public List<Comment> transformAllCommentsData(ExtractedData extractedData) {
		List<Comment> comments = new ArrayList<Comment>();
		
		while(extractedData.hasNext()) {
			Document page = extractedData.getNext();
			List<Comment> tmp_comments = this.transformCommentsData(page);
			comments.addAll(tmp_comments);
		}
		
		return comments;
	}
	
}
