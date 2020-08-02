//package com.Spider.crawlerService.YMXSpider;
//
//import com.Spider.crawlerService.demoSpider.ymxModel;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.processor.PageProcessor;
//
///**
// * 网络蜘蛛
// * @author Administrator
// */
//public class ymxSpider implements PageProcessor {
//    //爬虫处理程序
//	public void process(Page page) {
//		// TODO Auto-generated method stub
//		ymxModel yModel=new ymxModel();
//
//		String html=page.getHtml().get();
//		String url=page.getUrl().get();
//		try {
//			//抽取部分
//
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	public Site getSite() {
//		// TODO Auto-generated method stub
//		return Site.me().setRetrySleepTime(1000).setRetryTimes(3);
//	}
//
//}
