package jcc.api.javafx.css;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
	public static String BOOTSTRAP_4 = "bootstrap-4.css";
	public static String BOOTSTRAP_5 = "bootstrap-5.css";
	
	public static URL get(String resource) {
		return ResourceLoader.class.getResource(resource);
	}
	
	public static InputStream getAsStream(String resource) {
		return ResourceLoader.class.getResourceAsStream(resource);
	}
}
