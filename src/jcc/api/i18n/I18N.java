package jcc.api.i18n;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class I18N {
	private static I18N instance;
	
	public static I18N getInstance() {
		if(instance == null) {
			instance = new I18N();
			translations = new HashMap<String, Object>();
			instance.locales = new HashMap<Locale, Class<? extends I18NLoader>>();
			instance.setLocale(Locale.getDefault());
		}
		return instance;
	}
	public static String t(String text) {
		if(translations.get(text) != null)
			return (String) translations.get(text);
		return text;
	}
	public static String t(String text, I18N.Interpolation ...interpolations) {
		String interpolatedTranslation = t(text);
		for(I18N.Interpolation interpolation : interpolations) {
			interpolatedTranslation = interpolatedTranslation.replaceAll("\\{\\{" + interpolation.key + "\\}\\}", interpolation.value);
		}
		return interpolatedTranslation;
	}
	
	private Locale locale;
	private static HashMap<String, Object> translations;
	private HashMap<Locale, Class<? extends I18NLoader>> locales;

	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
		loadTranslations(locale);
	}
	public Class<? extends I18NLoader> addLocale(Locale locale, Class<? extends I18NLoader> loader) {
		Class<? extends I18NLoader> clazz = locales.put(locale, loader);
		loadTranslations(locale);
		return clazz;
	}
	public Class<? extends I18NLoader> removeLocale(Locale locale) {
		return locales.remove(locale);
	}
	public Set<Locale> locales() {
		return locales.keySet();
	}
	
	@SuppressWarnings("unchecked")
	private void loadTranslations(Locale locale) {
		if(locale == this.locale || (this.locale.getLanguage() == locale.getLanguage() && this.locale.getCountry() == locale.getCountry())) {
			Class<? extends I18NLoader> loader = locales.get(locale);
			if(loader != null)
				try {
					I18NLoader initLoader = loader.getConstructor(new Class<?>[] {}).newInstance(new Object[] {});
					Method method = loader.getMethod("loadTranslations", new Class<?>[] {});
					translations = (HashMap<String, Object>) method.invoke(initLoader, new Object[] {});
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public class Interpolation {
		public String key;
		public String value;
		public Interpolation(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
