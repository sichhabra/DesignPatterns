//target-interface
interface WebRequester {
	public int request(Object request);
}

// adaptee
class WebService {

	private String host;

	public WebService(String host) {
		this.host = host;
	}

	public Json request(Json result) {
		return result;
	}

}

class Json {

}

// adapter
class WebAdapter implements WebRequester {

	// adaptee
	private WebService service;

	public void connect(WebService service) {
		this.service = service;
	}

	@Override
	public int request(Object request) {
		Json result = this.toJson(request);
		Json response = service.request(result);
		if (response != null)
			return 200;
		else
			return 500;
	}

	private Json toJson(Object object) {
		return (Json) object;
	}

}

class WebClient {

	private WebRequester adapter;

	public WebClient(WebRequester adapter) {
		this.adapter = adapter;
	}

	public void doWork() {
		System.out.println("YOLO!");
	}
}

public class AdapterPattern {

	public AdapterPattern() {
		String host = "http://google.com";
		WebService service = new WebService(host);
		WebAdapter adapter = new WebAdapter();
		adapter.connect(service);
		WebClient client = new WebClient(adapter);
		client.doWork();
	}

	public static void main(String[] args) {
		new AdapterPattern();
	}
}
