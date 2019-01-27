package jp.imanaga.generator.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import jp.imanaga.generator.data.XmlElement;
import jp.imanaga.generator.processor.Processor;
import jp.imanaga.generator.service.CamelXmlSerivce;

@ShellComponent
public class CamlXmlCommand {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Processor processor;

	@Autowired
	private CamelXmlSerivce camelXmlSerivce;

	private static final String TEMPLATE_FILE_DIRECTORY = "./velocity-template";
	private static final String OUTPUT_FILE_DIRECTORY = "./velocity-output/";
	// TODO ファイル名は変換元のXMLファイル名を用いたほうが良さそう
	private static final String FILE_NAME = "doc.txt";
	private static final String CHARSET = "UTF-8";
	private static final String TEMPLATE_EXTENSION = ".vm";

	// TODO 名前を変えたい
	@ShellMethod("Generate a design document from a camel xml.")
	public int generate(String path) {

		try (StringWriter writer = new StringWriter();
				FileOutputStream fileOutputStream = new FileOutputStream(new File(OUTPUT_FILE_DIRECTORY + FILE_NAME));
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, CHARSET);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {

			// テンプレートが格納されているディレクトリを登録
			Velocity.setProperty("file.resource.loader.path", TEMPLATE_FILE_DIRECTORY);
			Velocity.init();

			// VelocityContext：テンプレートにセットする値を管理するためのオブジェクト
			VelocityContext context = new VelocityContext();
			List<XmlElement> content = this.camelXmlSerivce.getContent(path);
			context.put("routes", processor.process(content));

			// バインディング
			Template template = Velocity.getTemplate(FILE_NAME + TEMPLATE_EXTENSION, CHARSET);
			template.merge(context, writer);

			// ファイル出力
			bufferedWriter.write(writer.toString());

		} catch (Exception ex) {
			logger.error("異常終了", ex);
			return -1;
		}

		logger.info("正常終了");
		return 0;
	}

}
