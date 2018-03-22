interface WebPage {
	public void display();
}

class BasicWebPage implements WebPage {

	private String html, styleSheets, scripts;

	@Override
	public void display() {
		System.out.println("Basic Web Page!");
	}
}

// decorator-class
abstract class WebPageDecorator implements WebPage {

	protected WebPage page;

	public WebPageDecorator(WebPage page) {
		this.page = page;
	}

	public void display() {
		this.page.display();
	}
}

class AuthorizedWebPage extends WebPageDecorator {

	public AuthorizedWebPage(WebPage page) {
		super(page);
	}

	public void authorizedUser() {
		System.out.println("Authorizing User!");
	}

	public void display() {
		super.display();
		this.authorizedUser();
	}
}

class AuthenticatedWebPage extends WebPageDecorator {

	public AuthenticatedWebPage(WebPage page) {
		super(page);
	}

	public void authenticatedUser() {
		System.out.println("Authenticating User!");
	}

	public void display() {
		super.display();
		this.authenticatedUser();
	}
}

public class DecoratorPattern {
	public DecoratorPattern() {
		WebPage webpage = new BasicWebPage();
		webpage = new AuthenticatedWebPage(webpage);
		webpage = new AuthorizedWebPage(webpage);
		webpage.display();
	}

	public static void main(String[] args) {
		new DecoratorPattern();
	}
}
