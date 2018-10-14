import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class TransactionAccessDA0 {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	Random rand = new Random();
	Date now = new Date();
	String datePatternFormat = "yyyy/MM/dd HH:mm:ss";
	SimpleDateFormat dateformatter = new SimpleDateFormat(datePatternFormat);
	String newDate = dateformatter.format(now);
	public static final int INVALID = -1;
	public static final int VISA = 0;
	public static final int MASTERCARD = 1;
	public static final int AMERICAN_EXPRESS = 2;
	public static final Logger logger = Logger.getLogger(A00428533_MainMethod.class.getName());

	public String getInteger(Scanner sc) {
		String id = null;
		id = sc.next();
		if (checkInteger(id) == true) {
			return id;
		} else if (checkInteger(id) == false) {
			System.out.println("Please enter the valid integer!");
			id = getInteger(sc);
		}
		return id;
	}

	public String getUnitPrice(Scanner sc) {
		String unitPrice = null;
		unitPrice = sc.next();
		if (checkDouble(unitPrice) == true) {
			return unitPrice;
		} else if (checkDouble(unitPrice) == false) {
			System.out.println("Please enter the valid integer!");
			unitPrice = getUnitPrice(sc);
		}
		return unitPrice;
	}

	public String getName(Scanner sc) {
		String name = null;
		name = sc.next();
		if (checkString(name) == true) {
			return name;
		} else if (checkString(name) == false) {
			System.out.println("Please enter the chrachters other then ; : ! @ # $ % ^ * + ? < >");
			name = getName(sc);
		}
		return name;
	}

	public String checkCardNumber(Scanner sc, String cardType) throws Exception {
		String cardNumber = null;
		cardNumber = sc.next();
		if (validCC(cardNumber, cardType) == true) {
			return cardNumber;
		} else if (validCC(cardNumber, cardType) == false) {
			System.out.println("Please enter the valid card number!");
			cardNumber = checkCardNumber(sc, cardType);
		}
		return cardNumber;
	}

	public String getCheckedDate(Scanner sc) {
		String date = null;
		date = sc.next();
		if (checkDate(date) == true) {
			return date;
		} else if (checkDate(date) == false) {
			System.out.println(
					"Error date format:The format of the expiration date should be MM/YYYY, where MM means month and YYYY means year. The range of MM is between 01 and 12, and YYYY is between 2016 and 2031 (inclusively). Please enter the date:");
			date = getCheckedDate(sc);
		}
		return date;
	}

	public boolean validation(TransactionDTO custDao) {
		if (custDao.getCardNumber() == null) {
			return false;
		}
		if (custDao.getID() == 0) {
			return false;
		}
		if (custDao.getNameOnCard() == null) {
			return false;
		}
		if (custDao.getExpDate() == null) {
			return false;
		}
		return true;
	}

	public static boolean checkInteger(String num) {
		String regex = "[+-]?[0-9][0-9]*";
		Pattern pattern = Pattern.compile(regex);
		if (pattern.matcher(num).matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkDouble(String num) {
		String regex = "^[\\\\+\\\\-]{0,1}[0-9]+[\\\\.\\\\,][0-9]+$";
		String regex1 = "[+-]?[0-9][0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Pattern pattern1 = Pattern.compile(regex1);
		if (pattern.matcher(num).matches() || pattern1.matcher(num).matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkString(String name) {
		if (!(name.contains(";") || name.contains(":") || name.contains("!") || name.contains("@") || name.contains("#")
				|| name.contains("$") || name.contains("%") || name.contains("^") || name.contains("*")
				|| name.contains("+") || name.contains("?") || name.contains("<") || name.contains(">"))) {
			return true;
		}
		return false;
	}

	public static boolean validCC(String number, String cardType) throws Exception {
		String CardID = getCardID(number, cardType);
		if (!(CardID).equals("INVALID"))
			return true;
		return false;
	}

	public static String getCardID(String number, String cardType) {
		String valid = "INVALID";

		String digit1 = number.substring(0, 1);
		String digit2 = number.substring(0, 2);

		if (isNumber(number)) {

			if (digit1.equals("4") && cardType.equals("2")) {
				if (number.length() == 16)
					valid = "VISA";
			}

			else if ((digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) && cardType.equals("1")) {
				if (number.length() == 16)
					valid = "MASTERCARD";
			}

			else if ((digit2.equals("34") || digit2.equals("37")) && cardType.equals("3")) {
				if (number.length() == 15)
					valid = "AMERICAN_EXPRESS";
			}

		}
		return valid;
	}

	public static boolean checkDate(String date) {

		String digit1 = date.substring(0, 2);
		String digit2 = date.substring(3, 7);
		if (isNumber(date)) {
			if (date.matches("([0-9]{2})/([0-9]{4})")) {
				if ((digit1.compareTo("01") >= 0 && digit1.compareTo("12") <= 0)
						&& (digit2.compareTo("2016 ") >= 0 && digit2.compareTo("2031") <= 0)) {
					return true;
				}
			}

		}
		return false;
	}

	public static boolean isNumber(String n) {
		try {
			String.valueOf(n).toString();
			return true;
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "Error: Number format exception");
			return false;
		}
	}

	public Connection Conector() throws Exception {

		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB

			connect = DriverManager.getConnection("jdbc:mysql://dev.cs.smu.ca/a_gupta?" // DTP I spelled transactoins //
																						// wrong oops
					+ "user=a_gupta&password=A00428533" // Creds
					+ "&useSSL=false&AllowPublicKeyRetrieval=True" // b/c localhost
					+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"); // timezone

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in connection the MySQL server");
		}
		return connect;
	}

	public void readDataBase(TransactionDTO custDtO) {
		try {

			TransactionAccessDA0 a = new TransactionAccessDA0();
			connect = a.Conector();
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from a_gupta.transaction");
			int id = 0;
			boolean exists = false;

			if (custDtO.getID() != 0) {
				while (resultSet.next()) {
					id = resultSet.getInt("ID");
				}
				if (id == custDtO.getID()) {
					exists = true;
					logger.log(Level.SEVERE, "SQL-ERROR: PRIMARY KEY VIOLATION. PLEASE ENTER DIFFERENT ID");

				} else if (custDtO.getValue() == 1 && exists == false) {
					CreateResultSetInTable(custDtO);
					CreateResultSet(resultSet);
				} else if (custDtO.getValue() == 2 && exists == false) {
					CreateResultSet(resultSet);
				}
			} else {
				CreateResultSet(resultSet);
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception in select query of read DataBase Method");
		}

		catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in query execution");
		}
	}

	private void CreateResultSetInTable(TransactionDTO custDtO) {

		try {
			preparedStatement = connect.prepareStatement(
					"INSERT INTO a_gupta.transaction ( ID, NameOnCard,CardNumber,UnitPrice,Quantity,TotalPrice,ExpDate,CreatedOn,CreatedBy,CreditCardType) values(?, ?, ?, ?, ? , ?, ?, ?, ?,?)");
			if (custDtO.getID() != 0) {
				preparedStatement.setInt(1, custDtO.getID());
			}
			if (custDtO.getNameOnCard() != null) {
				preparedStatement.setString(2, custDtO.getNameOnCard());
			}
			if (custDtO.getCardNumber() != null) {
				preparedStatement.setString(3, custDtO.getCardNumber());
			}
			if (custDtO.getUnitPrice() != 0) {
				preparedStatement.setDouble(4, custDtO.getUnitPrice());
			}
			if (custDtO.getQty() != 0) {
				preparedStatement.setInt(5, custDtO.getQty());
			}
			if (custDtO.getTotalPrice() != 0) {
				preparedStatement.setDouble(6, custDtO.getTotalPrice());
			}
			if (custDtO.getExpDate() != null) {
				preparedStatement.setString(7, custDtO.getExpDate());
			}
			preparedStatement.setString(8, newDate);
			preparedStatement.setString(9, custDtO.getCreatedBy());
			preparedStatement.setString(10, custDtO.getCreditCardType());
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				logger.log(Level.INFO, "Successfully inserted record in the table transaction");
			} else {
				logger.log(Level.INFO, "Not able to update the record: rolled back");

			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL-Error: Exception while inserting data");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in CreateResultSetInTable method");
		}
	}

	private void CreateResultSet(ResultSet resultSet)
			throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = null;

		pw = new PrintWriter(new FileOutputStream(new File("/home/student_2018_fall/a_gupta/Output.txt"), true));
		try {
			// ResultSet is initially before the first data set
			pw.println("Selecting all the records from database");
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);
				System.out.println("------------------------------------------------");
				pw.println("------------------------------------------------");
				String ID = resultSet.getString("ID");
				String NameOnCard = resultSet.getString("NameOnCard");
				String CardNumber = resultSet.getString("CardNumber");
				String ExpDate = resultSet.getString("CardNumber");
				String UnitPrice = resultSet.getString("UnitPrice");
				Date qty = resultSet.getDate("Quantity");
				String totalPrice = resultSet.getString("TotalPrice");
				String createdOn = resultSet.getString("CreatedOn");
				String createdBy = resultSet.getString("CreatedBy");
				String CreditCardType = resultSet.getString("CreditCardType");
				System.out.println("ID: " + ID);
				System.out.println("NameOnCard: " + NameOnCard);
				System.out.println("CardNumber: " + CardNumber);
				System.out.println("ExpDate: " + ExpDate);
				System.out.println("UnitPrice: " + UnitPrice);
				System.out.println("Qty: " + qty);
				System.out.println("TotalPrice: " + totalPrice);
				System.out.println("CreatedOn: " + createdOn);
				System.out.println("CreatedBy: " + createdBy);
				System.out.println("Card Type: " + CreditCardType);
				pw.println("ID: " + ID);
				pw.println("NameOnCard: " + NameOnCard);
				pw.println("ExpDate: " + ExpDate);
				pw.println("UnitPrice: " + UnitPrice);
				pw.println("Qty: " + qty);
				pw.println("TotalPrice: " + totalPrice);
				pw.println("CreatedOn: " + createdOn);
				pw.println("CreatedBy: " + createdBy);
				pw.println("Card Type: " + CreditCardType);
			}
			pw.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in displaying data in CreateResultSet method ");
		}
	}

	private void deleteRecord(TransactionDTO custDtO) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = null;
		pw = new PrintWriter(new FileOutputStream(new File("/home/student_2018_fall/a_gupta/Output.txt"), true));
		try {
			TransactionAccessDA0 a = new TransactionAccessDA0();
			connect = a.Conector();
			int cnt = 0;
			preparedStatement = connect.prepareStatement("select * from a_gupta.transaction where ID=?");
			if (custDtO.getID() != 0) {
				preparedStatement.setInt(1, custDtO.getID());
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cnt++;
			}
			if (cnt > 0) {
				preparedStatement = connect.prepareStatement(" delete from a_gupta.transaction where ID=? ");
				if (custDtO.getID() != 0) {
					preparedStatement.setInt(1, custDtO.getID());
				}
				preparedStatement.executeUpdate();
				pw.println("----------------------------------------------------");
				pw.println("Record deleted for id:" + custDtO.getID());
				pw.println("Displaying Records present in databse after deleting");
				statement = connect.createStatement();
				resultSet = statement.executeQuery("select * from a_gupta.transaction");
				CreateResultSet(resultSet);
				logger.log(Level.INFO, " Record successfully deleted for the given ID");
				pw.close();
			} else {
				logger.log(Level.INFO, "No data found to delete the row");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL-Error: Exception while executing the delete query");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in deleteRecord method");
		} finally {
			pw.close();
		}
	}

	private void updateRecord(TransactionDTO custDtO) throws FileNotFoundException, UnsupportedEncodingException {

		String NameOnCard = null;
		String CardNumber = null;
		String ExpDate = null;
		double UnitPrice = 0;
		int qty = 0;
		double totalPrice = 0;
		Date createdOn = null;
		String createdBy = null;
		TransactionAccessDA0 a = new TransactionAccessDA0();
		PrintWriter pw = null;
		pw = new PrintWriter(new FileOutputStream(new File("/home/student_2018_fall/a_gupta/Output.txt"), true));

		try {
			connect = a.Conector();
			int cnt = 0;
			preparedStatement = connect.prepareStatement("select * from a_gupta.transaction where ID=?");
			if (custDtO.getID() != 0) {
				preparedStatement.setInt(1, custDtO.getID());
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				NameOnCard = resultSet.getString("NameOnCard");
				CardNumber = resultSet.getString("CardNumber");
				ExpDate = resultSet.getString("ExpDate");
				UnitPrice = resultSet.getDouble("UnitPrice");
				qty = resultSet.getInt("Quantity");
				totalPrice = resultSet.getDouble("TotalPrice");
				createdOn = resultSet.getDate("CreatedOn");
				createdBy = resultSet.getString("CreatedBy");
				cnt++;
			}
			if (cnt > 0) {
				if (custDtO.getValue() == 1) {
					preparedStatement = connect
							.prepareStatement(" update  a_gupta.transaction set NameOnCard=? where id=?; ");
					if (custDtO.getNameOnCard() != null) {
						preparedStatement.setString(1, custDtO.getNameOnCard());
					} else {
						preparedStatement.setString(1, NameOnCard);
					}
					if (custDtO.getID() != 0) {
						preparedStatement.setInt(2, custDtO.getID());
					}
					preparedStatement.executeUpdate();
					pw.println("-----------------------------------------------------");
					pw.println("Record updated for the id:" + custDtO.getID() + "Updated name now :"
							+ custDtO.getNameOnCard());
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select * from a_gupta.transaction");
					CreateResultSet(resultSet);
					logger.log(Level.INFO, "Record successfully updated for the record");
				} else if (custDtO.getValue() == 2) {
					preparedStatement = connect
							.prepareStatement(" update  a_gupta.transaction set CardNumber=? where id=? ");
					if (custDtO.getCardNumber() != null) {
						preparedStatement.setString(1, custDtO.getCardNumber());
					} else {
						preparedStatement.setString(1, CardNumber);
					}
					if (custDtO.getID() != 0) {
						preparedStatement.setInt(2, custDtO.getID());
					}
					preparedStatement.executeUpdate();
					pw.println("-----------------------------------------------------");
					pw.println("Record updated for the id:" + custDtO.getID() + "Updated card number now :"
							+ custDtO.getCardNumber());
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select * from a_gupta.transaction");
					CreateResultSet(resultSet);
					logger.log(Level.INFO, "Record successfully updated for the record");
				} else if (custDtO.getValue() == 3) {
					preparedStatement = connect
							.prepareStatement(" update  a_gupta.transaction set UnitPrice=?,TotalPrice=? where id=?");
					if (custDtO.getUnitPrice() != 0) {
						preparedStatement.setDouble(1, custDtO.getUnitPrice());
						preparedStatement.setDouble(2, custDtO.getUnitPrice() * qty);
					} else {
						preparedStatement.setDouble(1, UnitPrice);
					}
					if (custDtO.getID() != 0) {
						preparedStatement.setInt(3, custDtO.getID());
					}
					preparedStatement.executeUpdate();
					pw.println("-----------------------------------------------------");
					pw.println("Record updated for the id:" + custDtO.getID() + "Updated Unit price:"
							+ custDtO.getUnitPrice() + "and quantity" + custDtO.getUnitPrice() * qty);
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select * from a_gupta.transaction");
					CreateResultSet(resultSet);
					logger.log(Level.INFO, "Record successfully updated for the record");
				} else if (custDtO.getValue() == 4) {
					preparedStatement = connect
							.prepareStatement(" update  a_gupta.transaction set Quantity=?,TotalPrice=? where id=? ");
					if (custDtO.getQty() != 0) {
						preparedStatement.setInt(1, custDtO.getQty());
						preparedStatement.setDouble(2, custDtO.getQty() * UnitPrice);
					} else {
						preparedStatement.setInt(1, qty);
					}
					if (custDtO.getID() != 0) {
						preparedStatement.setInt(3, custDtO.getID());
					}
					preparedStatement.executeUpdate();
					pw.println("-----------------------------------------------------");
					pw.println("Record updated for the id:" + custDtO.getID() + "Updated Unit price:" + custDtO.getQty()
							+ "and quantity" + custDtO.getQty() * UnitPrice);
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select * from a_gupta.transaction");
					CreateResultSet(resultSet);
					logger.log(Level.INFO, "Record successfully updated for the record");
				} else if (custDtO.getValue() == 5) {
					preparedStatement = connect
							.prepareStatement(" update  a_gupta.transaction set ExpDate=? where id=?");
					if (custDtO.getExpDate() != null) {
						preparedStatement.setString(1, custDtO.getExpDate());
					} else {
						preparedStatement.setString(1, ExpDate);
					}
					if (custDtO.getID() != 0) {
						preparedStatement.setInt(2, custDtO.getID());
					}
					preparedStatement.executeUpdate();
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select * from a_gupta.transaction");
					CreateResultSet(resultSet);
					logger.log(Level.INFO, "Record successfully updated for the record");
				}
			} else {
				logger.log(Level.INFO, "No data found to update the row");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL-Error: Exception in updating the ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in updateRecord method");
		} finally {
			pw.close();
		}
	}

	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while closing the connection");
		}
	}

	public void inputFromUserMethod(int type) {

		TransactionAccessDA0 dao = new TransactionAccessDA0();
		Scanner sc = new Scanner(System.in);
		String creditCardType = null;
		TransactionDTO custDao = new TransactionDTO();
		try {
			if (type == 1) {
				System.out.println("Please Enter the id:");
				String id = sc.next();
				if (checkInteger(id) == true) {
					custDao.setID(Integer.parseInt(id));
				} else {
					System.out.println("Please enter the valid integer:");
					id = getInteger(sc);
					custDao.setID(Integer.parseInt(id));
				}
				System.out.println("Please Enter the name on card:");
				String name = sc.next();
				if (!(name.contains(";") || name.contains(":") || name.contains("!") || name.contains("@")
						|| name.contains("#") || name.contains("$") || name.contains("%") || name.contains("^")
						|| name.contains("*") || name.contains("+") || name.contains("?") || name.contains("<")
						|| name.contains(">"))) {
					custDao.setNameOnCard(name);
				} else {
					System.out.println("Please enter the chrachters other then ; : ! @ # $ % ^ * + ? < >");
					name = getName(sc);
					custDao.setNameOnCard(name);
				}
				System.out.println(
						"Please Select the credit card type:\n\n1) MasterCard\t\t 2) Visa\t\t 3) American Express");
				creditCardType = sc.next();
				if (creditCardType.equals("1")) {
					custDao.setCreditCardType("MasterCard");
				} else if (creditCardType.equals("2")) {
					custDao.setCreditCardType("Visa");
				} else if (creditCardType.equals("3")) {
					custDao.setCreditCardType("American Express");
				}
				System.out.println("Please Enter the card number:");
				String number = sc.next();
				if (validCC(number, creditCardType) == true) {
					custDao.setCardNumber(number);
				} else {
					System.out.println("Please Enter the valid card number");
					number = checkCardNumber(sc, creditCardType);
					custDao.setCardNumber(number);
				}
				System.out.println("Please Enter the unit price:");
				String unitPrice = sc.next();
				if (checkDouble(unitPrice) == true) {
					custDao.setUnitPrice(Double.valueOf(unitPrice));
				} else {
					System.out.println("Please enter the valid integer!");
					unitPrice = getUnitPrice(sc);
					custDao.setUnitPrice(Double.valueOf(unitPrice));
				}
				System.out.println("Please Enter the Quantity:");
				String qty = sc.next();
				if (checkInteger(qty) == true) {
					custDao.setQty(Integer.valueOf(qty));
				} else {
					System.out.println("Please enter the valid integer!");
					qty = getInteger(sc);
					custDao.setQty(Integer.valueOf(qty));
				}
				System.out.println(" Total Price:" + (custDao.getUnitPrice()) * (custDao.getQty()));
				custDao.setTotalPrice((custDao.getUnitPrice()) * (custDao.getQty()));

				System.out.println("Please Enter the Expiry date:");
				String date = sc.next();
				if (checkDate(date) == true) {
					custDao.setExpDate(date);
				} else {
					System.out.println(
							"Error date format:The format of the expiration date should be MM/YYYY, where MM means month and YYYY means year. The range of MM is between 01 and 12, and YYYY is between 2016 and 2031 (inclusively). Please enter the date: ");
					date = getCheckedDate(sc);
					custDao.setExpDate(date);
				}

				custDao.setCreatedBy(System.getProperty("user.name"));
				custDao.setValue(type);
				if (dao.validation(custDao) == true) {
					dao.readDataBase(custDao);
				}
			}

			if (type == 2) {

				dao.readDataBase(custDao);
			}
			if (type == 3) {

				System.out.println("Please Enter the id for which you want to update the record:");
				String id = sc.next();
				if (checkInteger(id) == true) {
					custDao.setID(Integer.parseInt(id));
				} else {
					System.out.println("Please enter the valid integer:");
					id = getInteger(sc);
					custDao.setID(Integer.parseInt(id));
				}
				System.out.println("-----------------------------------------------------");
				System.out.println(
						"What value you want to update:\n 1) Name on card\t 2) Card Number\n 3) Unit Price\t\t 4) Quantity\n 5) Expiry Date");
				custDao.setValue(sc.nextInt());
				if (custDao.getValue() == 1) {
					System.out.println("Please Enter the name on card:");
					String name = sc.next();
					if (!(name.contains(";") || name.contains(":") || name.contains("!") || name.contains("@")
							|| name.contains("#") || name.contains("$") || name.contains("%") || name.contains("^")
							|| name.contains("*") || name.contains("+") || name.contains("?") || name.contains("<")
							|| name.contains(">"))) {
						custDao.setNameOnCard(name);
						dao.updateRecord(custDao);
					} else {
						System.out.println("Please enter the chrachters other then ; : ! @ # $ % ^ * + ? < >");
						name = getName(sc);
						custDao.setNameOnCard(name);
						dao.updateRecord(custDao);
					}
				} else if (custDao.getValue() == 2) {
					System.out.println(
							"Please Select the credit card type:\n\n1) MasterCard\t\t 2) Visa\t\t 3) American Express");
					creditCardType = sc.next();
					System.out.println("Please Enter the card number:");
					String number = sc.next();
					if (validCC(number, creditCardType) == true) {
						custDao.setCardNumber(number);
						dao.updateRecord(custDao);
					} else {
						System.out.println("Please enter the chrachters other then ; : ! @ # $ % ^ * + ? < > ");
						number = checkCardNumber(sc, creditCardType);
						custDao.setCardNumber(number);
						dao.updateRecord(custDao);
					}
				} else if (custDao.getValue() == 3) {
					System.out.println("Please Enter the unit price:");
					String unitPrices = sc.next();
					if (checkDouble(unitPrices) == true) {
						custDao.setUnitPrice(Double.valueOf(unitPrices));
						dao.updateRecord(custDao);
					} else {
						System.out.println("Please enter the valid integer");
						unitPrices = getUnitPrice(sc);
						custDao.setUnitPrice(Double.valueOf(unitPrices));
						dao.updateRecord(custDao);
					}
				} else if (custDao.getValue() == 4) {
					System.out.println("Please Enter the quantity:");
					String quantity = sc.next();
					if (checkInteger(quantity) == true) {
						custDao.setQty(Integer.valueOf(quantity));
						dao.updateRecord(custDao);
					} else {
						System.out.println("Please enter the valid integer");
						quantity = getInteger(sc);
						custDao.setQty(Integer.valueOf(quantity));
						dao.updateRecord(custDao);
					}
				} else if (custDao.getValue() == 5) {
					System.out.println("Please Enter the expiry date:");
					String date1 = sc.next();
					if (checkDate(date1) == true) {
						custDao.setExpDate(date1);
						dao.updateRecord(custDao);
					} else {
						System.out.println("Error: Please enter the valid date format! ");
						date1 = getCheckedDate(sc);
						custDao.setExpDate(date1);
						dao.updateRecord(custDao);
					}
				}
			}
			if (type == 4) {

				try {
					System.out.println("Enter the id for which you want to delete the record:");
					String id = sc.next();
					if (checkInteger(id) == true) {
						custDao.setID(Integer.parseInt(id));
					} else {
						System.out.println("Please enter the valid integer:");
						id = getInteger(sc);
						custDao.setID(Integer.parseInt(id));
					}
					dao.deleteRecord(custDao);
				} catch (InputMismatchException e) {
					logger.log(Level.SEVERE, "Error: Please enter the valid integer!");
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in inputFromUserMethod method");
		}
	}
}
