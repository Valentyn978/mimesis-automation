package com.helpers;

import org.apache.commons.lang.StringUtils;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoader {

	private static final String ERROR_MESSAGE = "Attention! \"%s\" property has empty value or not set in property file";
	protected final Properties properties = new Properties();

	private File configFile = new File("base.properties");

	public PropertiesLoader() {
		try {
			FileReader reader = new FileReader(configFile);
			properties.load(reader);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public String getProperty(String key) {
		String propertyValue = getPropertyFromHolder(properties, key);
		if (propertyValue != null) {
			return replaceLinks(key, propertyValue);
		}
		throw new NullPointerException(String.format(ERROR_MESSAGE, key));
	}

	protected String getPropertyFromHolder(Properties holder, String key) {
		return holder.getProperty(key);
	}

	protected String replaceLinks(String key, String sval) {
		Integer index = 0;
		Integer start;
		Integer finish;
		Set<String> paramsNames = new HashSet<>();
		if (sval == null) {
			throw new NullPointerException(String.format(ERROR_MESSAGE, key));
		}
		while (sval.indexOf("${", index) != -1) {
			index = sval.indexOf("${", index);
			start = index + 2;
			index = sval.indexOf("}", index);
			finish = index;
			try {
				paramsNames.add(sval.substring(start, finish));
			} catch (Exception ex) {
				continue;
			}
		}
		return replaceLinksByName(key, sval, paramsNames);
	}

	protected String replaceLinksByName(String key, String sval, Set<String> paramsNames) {
		for (String paramName : paramsNames) {
			sval = sval.replaceAll("\\$\\{" + paramName + "\\}", getProperty(paramName));
		}
		if (StringUtils.isEmpty(sval)) {
			throw new NullPointerException(String.format(ERROR_MESSAGE, key));
		}
		return sval.trim();
	}
}
