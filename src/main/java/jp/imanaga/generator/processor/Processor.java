package jp.imanaga.generator.processor;

import jp.imanaga.generator.data.XmlElement;

import java.util.List;

public interface Processor {

	List<XmlElement> process(List<XmlElement> input);
}
