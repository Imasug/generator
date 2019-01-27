package jp.imanaga.generator.data;

import java.util.List;
import java.util.Map;

public class XmlElement {

	/**
	 * 要素名
	 */
	private String name;

	/**
	 * 属性
	 */
	private Map<String, String> attrs;

	/**
	 * 子要素
	 */
	private List<XmlElement> childs;

	/**
	 * テキスト
	 */
	private String text;

	/**
	 * 自由設定項目
	 */
	private String description;

	public XmlElement(String name, Map<String, String> attrs, List<XmlElement> childs) {
		this.name = name;
		this.attrs = attrs;
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, String> attrs) {
		this.attrs = attrs;
	}

	public List<XmlElement> getChilds() {
		return childs;
	}

	public void setChilds(List<XmlElement> childs) {
		this.childs = childs;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
