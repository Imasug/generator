package jp.imanaga.generator.processor;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.imanaga.generator.data.XmlElement;

@Component
public class CamelXmlProcessor implements Processor {

	@Autowired
	private ResourceBundle bundle;

	// TODO どっかのドキュメントからタグ説明を拾えるか？
	public List<XmlElement> process(List<XmlElement> input) {
		input.forEach(x -> {
			String key = x.getName();
			// メッセージバンドルに要素名に対応する値があれば変換
			if (this.bundle.containsKey(key)) {
				x.setName(this.bundle.getString(x.getName()));
			}
			process(x.getChilds());
		});
		return input;
	}

}
