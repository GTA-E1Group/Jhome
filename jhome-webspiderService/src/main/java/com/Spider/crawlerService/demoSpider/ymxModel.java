//package com.Spider.crawlerService.demoSpider;
//
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.model.AfterExtractor;
//import us.codecraft.webmagic.model.annotation.ExtractBy;
//import us.codecraft.webmagic.model.annotation.HelpUrl;
//import us.codecraft.webmagic.model.annotation.TargetUrl;
//
//@TargetUrl("https://www.hao123.com/\\w+")
//@HelpUrl("https://www.hao123.com/\\w+")
//public class ymxModel implements AfterExtractor {
//
//	@ExtractBy(value = "//a[@class='hao123']/text()")
//	private String name;
///*	@ExtractByUrl("https://github\\.com/(\\w+)/.*")
//    private String author;
//	@ExtractBy("//div[@id='readme']/tidyText()")
//    private String readme;*/
//
//	public void afterProcess(Page page) {
//		// TODO Auto-generated method stub
//		System.out.println("爬取成功`");
//		//System.out.println(page);
//
//	}
//}
