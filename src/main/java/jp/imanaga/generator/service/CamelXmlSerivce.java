package jp.imanaga.generator.service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.imanaga.generator.data.XmlElement;
import jp.imanaga.generator.parser.CamelXmlParser;

@Service
public class CamelXmlSerivce {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CamelXmlParser camelXmlParser;

	public List<XmlElement> getContent(String path) throws Exception {
		SAXParserFactory.newInstance().newSAXParser().parse(new File(path), camelXmlParser);
		List<XmlElement> routes = camelXmlParser.getRoutes();
		ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(SerializationFeature.INDENT_OUTPUT);
		logger.info(mapper.writeValueAsString(routes));
		return routes;
	}

}
