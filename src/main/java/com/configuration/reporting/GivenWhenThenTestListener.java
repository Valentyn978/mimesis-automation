package com.configuration.reporting;

import org.apache.commons.lang.StringUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.lang.reflect.Field;

public class GivenWhenThenTestListener extends TestListenerAdapter {

	private static final String TEST = "test";
	private static final String GIVEN = "Given";
	private static final String WHEN = "When";
	private static final String THEN = "Then";
	private static final String SEVEN_SPACES = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	private static final String BR = "<br/>";
	private int count = 1;

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		setTestNameInHtml(tr);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		setTestNameInHtml(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		setTestNameInHtml(tr);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		super.onTestFailedButWithinSuccessPercentage(tr);
		setTestNameInHtml(tr);
	}

	protected void setTestNameInHtml(ITestResult result) {

		try {
			String methodName = result.getName();
			if (methodName.contains(TEST)
					&& methodName.contains(GIVEN)
					&& methodName.contains(WHEN)
					&& methodName.contains(THEN)) {
				methodName = getGivenWhenThenFormat(methodName, result.getMethod().getDescription(), result);
				// Add bottom border
				methodName = String.format("<span style=\"display: block; width:221px;\">%s</span>", methodName);
				Field f = result.getClass().getDeclaredField("m_name");
				f.setAccessible(true);
				f.set(result, methodName);
			}
		} catch (IllegalAccessException | NoSuchFieldException ignored) {
		}
	}

	private String getGivenWhenThenFormat(String methodName, String description, ITestResult result) {
		String given = methodName.split(GIVEN)[1].split(WHEN)[0];
		String when = methodName.split(WHEN)[1].split(THEN)[0];
		String then = methodName.split(THEN)[1];

		String startPart = "Test step " + String.format("%4d", count++) + ":";
		String baseMessage = bold(startPart) +
				BR + SEVEN_SPACES + bold("Given ") + addSpaces(given) +
				BR + SEVEN_SPACES + bold("When ") + addSpaces(when) +
				BR + SEVEN_SPACES + bold("Then ") + addSpaces(then);

		// Switch color in ordered to result
		int status = result.getStatus();
		String color = "#44aa44";
		if (status == 2) color = "red";
		if (status == 3) color = "#ffaa00";

		String descriptionMessage = "";
		if (!StringUtils.isEmpty(description))
			descriptionMessage = String.format("%s%s%s<span style=\"border-bottom:2px solid %s;\">%s</span>",
					BR, bold("Description:"), BR, color, description);
		return baseMessage + descriptionMessage + BR + BR + BR;
	}

	private String addSpaces(String s) {
		return s.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
	}

	private String bold(String s) {
		return String.format("<b>%s</b>", s);
	}
}
