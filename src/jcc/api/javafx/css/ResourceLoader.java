package jcc.api.javafx.css;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {
	public static URL get(String resource) {
		return ResourceLoader.class.getResource(resource);
	}
	
	public static InputStream getAsStream(String resource) {
		return ResourceLoader.class.getResourceAsStream(resource);
	}
}
