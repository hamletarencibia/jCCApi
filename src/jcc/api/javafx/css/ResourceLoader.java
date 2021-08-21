package jcc.api.javafx.css;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
	public static URL BOOTSTRAP_4 = get("bootstrap-4.css");
	public static URL BOOTSTRAP_5 = get("bootstrap-5.css");
	
	public static URL get(String resource) {
		return ResourceLoader.class.getResource(resource);
	}
	
	public static InputStream getAsStream(String resource) {
		return ResourceLoader.class.getResourceAsStream(resource);
	}
}
