package com;

import org.testng.TestNG;

import java.util.Arrays;
import java.util.List;

public class TestingApplication {
	public static void main(String[] args) {
		TestNG testNG = new TestNG();
		if (args.length == 0) {
			System.out.printf("Parameter is required.");
			System.exit(1);
		}

		List<String> suite = Arrays.asList(args[0]);
		testNG.setTestSuites(suite);
		testNG.run();
	}
}
