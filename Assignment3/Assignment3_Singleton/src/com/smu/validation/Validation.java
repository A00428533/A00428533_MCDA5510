package com.smu.validation;

import java.util.Scanner;

public class Validation {
	public Validation() {
	}

	public String getInteger(int value) {
		String checkValue = String.valueOf(value);
		if (checkInteger(checkValue))
			return checkValue;
		if (!checkInteger(checkValue)) {
			System.out.println("Please enter the valid integer!");
		}
		return checkValue;
	}

	public String getUnitPrice(Scanner sc) {
		String unitPrice = null;
		unitPrice = sc.next();
		if (checkDouble(unitPrice))
			return unitPrice;
		if (!checkDouble(unitPrice)) {
			System.out.println("Please enter the valid integer!");
			unitPrice = getUnitPrice(sc);
		}
		return unitPrice;
	}

	public String getName(Scanner sc) {
		String name = null;
		name = sc.next();
		if (checkString(name))
			return name;
		if (!checkString(name)) {
			System.out.println("Please enter the chrachters other then ; : ! @ # $ % ^ * + ? < >");
			name = getName(sc);
		}
		return name;
	}

	public String checkCardNumber(Scanner sc, String cardType) throws Exception {
		String cardNumber = null;
		cardNumber = sc.next();
		if (validCC(cardNumber, cardType))
			return cardNumber;
		if (!validCC(cardNumber, cardType)) {
			System.out.println("Please enter the valid card number!");
			cardNumber = checkCardNumber(sc, cardType);
		}
		return cardNumber;
	}

	public String getCheckedDate(Scanner sc) {
		String date = null;
		date = sc.next();
		if (checkDate(date))
			return date;
		if (!checkDate(date)) {
			System.out.println(
					"Error date format:The format of the expiration date should be MM/YYYY, where MM means month and YYYY means year. The range of MM is between 01 and 12, and YYYY is between 2016 and 2031 (inclusively). Please enter the date:");
			date = getCheckedDate(sc);
		}
		return date;
	}

	public static boolean checkInteger(String num) {
		String regex = "[+-]?[0-9][0-9]*";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
		if (pattern.matcher(num).matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkDouble(String num) {
		String regex = "^[\\\\+\\\\-]{0,1}[0-9]+[\\\\.\\\\,][0-9]+$";
		String regex1 = "[+-]?[0-9][0-9]*";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
		java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile(regex1);
		if ((pattern.matcher(num).matches()) || (pattern1.matcher(num).matches())) {
			return true;
		}
		return false;
	}

	public static boolean checkString(String name) {
		if ((!name.contains(";")) && (!name.contains(":")) && (!name.contains("!")) && (!name.contains("@"))
				&& (!name.contains("#")) && (!name.contains("$")) && (!name.contains("%")) && (!name.contains("^"))
				&& (!name.contains("*")) && (!name.contains("+")) && (!name.contains("?")) && (!name.contains("<"))
				&& (!name.contains(">"))) {
			return true;
		}
		return false;
	}

	public static boolean validCC(String number, String cardType) throws Exception {
		String CardID = getCardID(number, cardType);
		if (!CardID.equals("INVALID"))
			return true;
		return false;
	}

	public static String getCardID(String number, String cardType) {
		String valid = "INVALID";

		String digit1 = number.substring(0, 1);
		String digit2 = number.substring(0, 2);

		if (isNumber(number)) {
			if ((digit1.equals("4")) && (cardType.equals("2"))) {
				if (number.length() == 16) {
					valid = "VISA";
				}
			} else if ((digit2.compareTo("51") >= 0) && (digit2.compareTo("55") <= 0) && (cardType.equals("1"))) {
				if (number.length() == 16) {
					valid = "MASTERCARD";
				}
			} else if (((digit2.equals("34")) || (digit2.equals("37"))) && (cardType.equals("3"))
					&& (number.length() == 15)) {
				valid = "AMERICAN_EXPRESS";
			}
		}

		return valid;
	}

	public static boolean checkDate(String date) {
		String digit1 = date.substring(0, 2);
		String digit2 = date.substring(6, 9);
		if ((isNumber(date)) && (date.matches("([0-9]{2})/([0-9]{4})")) && (digit1.compareTo("01") >= 0)
				&& (digit1.compareTo("12") <= 0) && (digit2.compareTo("2016 ") >= 0)
				&& (digit2.compareTo("2031") <= 0)) {
			return true;
		}

		return false;
	}

	public static boolean isNumber(String n) {
		try {
			String.valueOf(n).toString();
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
}
