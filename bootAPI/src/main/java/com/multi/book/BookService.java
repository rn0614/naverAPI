package com.multi.book;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

@Service
public class BookService{

	String clientId = ""; //애플리케이션 클라이언트 아이디값"
    String clientSecret = ""; //애플리케이션 클라이언트 시크릿값"
    
    public List<BookVO> searchBook(String keyword, int display, int start){
    	List<BookVO> list = null;
    	
    	try {
			URL url;
			url = new URL("https://openapi.naver.com/v1/search/"
					+ "book.xml?query="
					+ URLEncoder.encode(keyword, "UTF-8")
					+ (display !=0 ? "&display=" + display : "")
					+ (start != 0 ? "&start=" + start : ""));
			
			URLConnection con = url.openConnection();
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(con.getInputStream()));
			
			int eventType = parser.getEventType();
			
			BookVO book = null;
			
			while(eventType != XmlPullParser.END_DOCUMENT ) {
				switch(eventType) {
				case XmlPullParser.END_DOCUMENT: // 문서의 끝이면
					break;
				case XmlPullParser.START_DOCUMENT: 	list = new ArrayList<BookVO>();
					break;
				case XmlPullParser.START_TAG: {//태그 시작이면
					String tag = parser.getName();  //파서를 통해 태그 이름을 알아 오기
					switch(tag) { //태그 확인해서
					case "item": // item 이면 book 생성
						book = new BookVO(); break;
					case "title": // 제목부터 데이터 가져 오기
						if(book != null) book.setTitle(parser.nextText()); break;
					case "link": if(book != null) book.setLink(parser.nextText()); break;
					case "image": if(book != null) book.setImage(parser.nextText()); break;
					case "author": if(book != null) book.setAuthor(parser.nextText()); break;
					case "price": if(book != null) book.setPrice(parser.nextText()); break;
					case "discount": if(book != null) book.setDiscount(parser.nextText()); break;
					case "publisher": if(book != null) book.setPublisher(parser.nextText()); break;
					case "pubdate": if(book != null) book.setPubdate(parser.nextText()); break;
					case "isbn": if(book != null) book.setIsbn(parser.nextText()); break;
					case "description": if(book != null) book.setDescription(parser.nextText()); break;
					} // tag switch 끝					
				}
				break; //START-TAG 끝
				case XmlPullParser.END_TAG: {// 태그 끝이면
					String tag = parser.getName();
					if(tag.equals("item")) {
						list.add(book);
						book = null;
					}
				} // eventType switch 끝
				
			} // while 끝
			
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	return list;
    }
	
}