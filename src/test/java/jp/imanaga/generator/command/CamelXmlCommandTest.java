package jp.imanaga.generator.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false" })
public class CamelXmlCommandTest {

	@Autowired
	private Shell shell;

	@Test
	public void test() throws Exception {
		String path = "C:\\Users\\SI\\workspace\\generator\\src\\test\\resources\\camel-context.xml";
		System.out.println(shell.evaluate(() -> String.format("generate %s", path)));
	}

}