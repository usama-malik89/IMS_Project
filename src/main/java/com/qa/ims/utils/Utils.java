package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {
	private static Logger LOGGER = LogManager.getLogger();

	private final Scanner scanner;

	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Utils() {
		scanner = new Scanner(System.in);
	}

	public Long getLong() {
		Long longInput = null;
		do {
			String input = getString();
			try {
				longInput = Long.parseLong(input);
			} catch (Exception e) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		return scanner.nextLine();
	}

	public Double getDouble() {
		Double doubleInput = null;
		do {
			String input = getString();
			try {
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}
	
	public Integer getInt() {
		Integer intInput = null;
		do {
			String input = getString();
			try {
				intInput = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (intInput == null);
		return intInput;
	}
	
	public Long getOrderItemAction() {
		Long longInput = null;
		do {
			String input = getString();
			if(input.equals("q")) {
				break;
			}
			else {
				try {
					longInput = Long.parseLong(input);
				} catch (Exception e) {
					LOGGER.info("Error - Please enter a number");
				}
			}
		} while (longInput == null);
		return longInput;
	}

}
