package jp.imanaga.generator.command;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import jp.imanaga.generator.common.helper.MessageHelper;

@ShellComponent
public class DocCommand {

	private static final String TEMPLATE_FILE_DIRECTORY = "./velocity-template";

	// TODO 何をどうするかが明確ではないため、名前を要修正
	@ShellMethod("generate")
	public int generate() {

		try (StringWriter writer = new StringWriter();) {

			// テンプレートが格納されているディレクトリを登録
			Velocity.setProperty("file.resource.loader.path", TEMPLATE_FILE_DIRECTORY);
			Velocity.init();

			// VelocityContext：テンプレートにセットする値を管理するためのオブジェクト
			VelocityContext context = new VelocityContext();
			context.put("bean", new SampleBean(1, "Suguru"));

			// 出力
			Template template = Velocity.getTemplate("SampleTemplete.vm", "UTF-8");
			template.merge(context, writer);

			// TODO ファイル出力
			System.out.print(writer.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}

		return 0;
	}

}
