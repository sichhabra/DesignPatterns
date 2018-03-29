import java.util.ArrayList;
import java.util.List;

class Document {
	private String content;
	private String clipboard;

	public Document(String content) {
		this.content = content;
		this.clipboard="";
	}

	public void insertText(int position, String text) {
		content = new StringBuilder(content).insert(position, text).toString();
	}

	public void deleteText(int position, int len) {
		content = new StringBuilder(content).delete(position, position + len).toString();
	}

	public String getClipboard() {
		return this.clipboard;
	}

	public void setClipboard(String temp) {
		this.clipboard = temp;
	}

	public String getText(int position, int len) {
		return this.content.substring(position, position + len);
	}

	public void print() {
		System.out.println(this.content);
	}

}

interface Command {
	public void execute();

	public void unexecute();

	public boolean isReversible();
}

class PasteCommand implements Command {

	private Document document;
	private String text;
	private int position;

	public PasteCommand(Document document, int position) {
		this.document = document;
		this.text = document.getClipboard();
		this.position = position;
	}

	@Override
	public void execute() {
		document.insertText(position, text);
	}

	@Override
	public void unexecute() {
		document.deleteText(position, text.length());
	}

	@Override
	public boolean isReversible() {
		return true;
	}
}

class CopyCommand implements Command {

	private Document document;
	private int position;
	private int length;

	public CopyCommand(Document document, int position, int length) {
		this.document = document;
		this.position = position;
		this.length = length;
	}

	@Override
	public void execute() {
		document.setClipboard(document.getText(position, position + length));
	}

	@Override
	public void unexecute() {
		// Nothing like uncopy.
	}

	@Override
	public boolean isReversible() {
		return false;
	}

}

class CommandManager {

	private List<Command> history = new ArrayList<Command>();

	public void invokeCommand(Command command) {
		this.history.add(command);
		command.execute();
	}
}

public class CommandPattern {
	public CommandPattern() {
		CommandManager commandManager = new CommandManager();
		Document document = new Document("abcd");

		Command copyCommand = new CopyCommand(document, 0, 2);
		Command pasteCommand = new PasteCommand(document, 0);
		commandManager.invokeCommand(copyCommand);
		commandManager.invokeCommand(pasteCommand);
		document.print();
	}

	public static void main(String[] args) {
		new CommandPattern();
	}
}
