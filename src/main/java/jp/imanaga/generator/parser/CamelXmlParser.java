package jp.imanaga.generator.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jp.imanaga.generator.data.XmlElement;

@Component
public class CamelXmlParser extends DefaultHandler {

	private Stack<XmlElement> cache;
	private List<XmlElement> routes;

	public List<XmlElement> getRoutes() {
		return this.routes;
	}

	@Override
	public void startDocument() throws SAXException {
		this.routes = new ArrayList<>();
		this.cache = new Stack<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// 属性をマップへ変換
		Map<String, String> attrs = new HashMap<>();
		for (int i = 0; i < attributes.getLength(); i++) {
			attrs.put(attributes.getQName(i), attributes.getValue(i));
		}

		// コンポーネント情報作成
		XmlElement comopnent = new XmlElement(qName, attrs, new ArrayList<>());

		if ("route".equals(qName)) {
			this.routes.add(comopnent);
		}

		if (this.cache.size() > 0) {
			this.cache.peek().getChilds().add(comopnent);
		}

		// キャッシュへ記録
		this.cache.add(comopnent);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		this.cache.peek().setText(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.cache.pop();
	}

	@Override
	public void endDocument() throws SAXException {
	}

}
