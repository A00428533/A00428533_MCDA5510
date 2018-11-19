package com.smu.dao;

import com.smu.db.DBConnection;
import com.smu.dto.TransactionDTO;
import com.smu.validation.Validation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionDAO {
	Date now = new Date();
	String datePatternFormat = "yyyy/MM/dd HH:mm:ss";
	SimpleDateFormat dateformatter = new SimpleDateFormat(datePatternFormat);
	String newDate = dateformatter.format(now);
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Statement statement = null;
	public static final Logger logger = Logger.getLogger(TransactionDAO.class.getName());
	SimpleFormatter formatterTxt = new SimpleFormatter();
	FileHandler fh = null;

	public TransactionDAO() {
	}

	public TransactionDTO readDataBase(int trnxId) {
		TransactionDTO results = new TransactionDTO();
		try {
			fh = new FileHandler("/home/student_2018_fall/a_gupta/MyLogFile.log", true);
			logger.addHandler(fh);
			fh.setFormatter(formatterTxt);
			Connection conn = DBConnection.getDBConnection();
			preparedStatement = conn.prepareStatement("select * from a_gupta.transaction where ID=?");
			if (trnxId != 0) {
				preparedStatement.setInt(1, trnxId);
			}
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				logger.log(Level.INFO, "Successfully read the record in the table transaction");
				results = createTrxns(resultSet);

			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Exception in select query of read DataBase Method");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in query execution" + e);
		}

		return results;
	}

	private TransactionDTO createTrxns(ResultSet resultSet) throws SQLException, FileNotFoundException {
		TransactionDTO results = new TransactionDTO();
		try {
			if (resultSet.next()) {
				String nameOnCard = resultSet.getString("NameOnCard");
				String cardNumber = resultSet.getString("CardNumber");
				String createdBy = resultSet.getString("CreatedBy");
				Date createdoN = resultSet.getDate("CreatedOn");
				String CreditCardType = resultSet.getString("CreditCardType");
				String ExpDate = resultSet.getString("ExpDate");
				int Id = resultSet.getInt("ID");
				Double UnitPrice = Double.valueOf(resultSet.getDouble("UnitPrice"));
				int Quantity = resultSet.getInt("Quantity");
				Double TotalPrice = Double.valueOf(resultSet.getDouble("TotalPrice"));
				TransactionDTO trxn = new TransactionDTO();
				trxn.setNameOnCard(nameOnCard);
				trxn.setCardNumber(cardNumber);
				trxn.setCreatedBy(createdBy);
				trxn.setCreatedOn(createdoN);
				trxn.setCreditCardType(CreditCardType);
				trxn.setExpDate(ExpDate);
				trxn.setID(Id);
				trxn.setUnitPrice(UnitPrice.doubleValue());
				trxn.setQty(Quantity);
				results.setTotalPrice(TotalPrice.doubleValue());
				results = trxn;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in displaying data in createTrxns method ");
		}

		return results;
	}

	public String createResultSetInTable(int ID, String NameOnCard, String CardNumber, String UnitPrice,
			String Quantity, String ExpDate, String CreditCardType) throws SecurityException, IOException {
		StringBuilder sb = new StringBuilder();
		Statement statement = null;
		String output = null;
		Validation vad = new Validation();
		String createdBy = System.getProperty("user.name");
		boolean CardNumberCheck = Validation.checkInteger(CardNumber);
		boolean NameOnCardCheck = Validation.checkString(NameOnCard);
		boolean UnitPriceCheck = Validation.checkDouble(UnitPrice);
		boolean QuantityCheck = Validation.checkInteger(Quantity);
		boolean CreditCardTypeCheck = Validation.checkString(CreditCardType);
		int Errorcount = 0;

		if (!NameOnCardCheck) {
			sb.append("Not a valid String. Please re-enter the Name On Card.\n");
			Errorcount++;
		}
		if (!UnitPriceCheck) {
			sb.append("Not a valid integer. Please re-enter the Unit Price.\n");
			Errorcount++;
		}
		if (!QuantityCheck) {
			sb.append("Not a valid integer. Please re-enter the Quantity.\n");
			Errorcount++;
		}

		if (!CreditCardTypeCheck) {
			sb.append("Not a valid credit card type. Please re-enter the Credit card type.\n");
			Errorcount++;
		}
		if (Errorcount == 0) {
			try {
				Connection conn = DBConnection.getDBConnection();
				statement = conn.createStatement();
				double totalPrice = Double.parseDouble(UnitPrice) + Double.parseDouble(Quantity);

				resultSet = statement.executeQuery("select * from a_gupta.transaction");
				int id = 0;
				while (resultSet.next()) {
					id = resultSet.getInt("ID");
				}
				if (id == ID) {
					sb.append("SQL-ERROR: PRIMARY KEY VIOLATION. PLEASE ENTER DIFFERENT ID");
					Errorcount++;

					System.out.println("SQL-ERROR: PRIMARY KEY VIOLATION. PLEASE ENTER DIFFERENT ID");
				}
				if (Errorcount == 0) {
					preparedStatement = conn.prepareStatement(
							"INSERT INTO a_gupta.transaction ( ID, NameOnCard,CardNumber,UnitPrice,Quantity,TotalPrice,ExpDate,CreatedOn,CreatedBy,CreditCardType) values(?, ?, ?, ?, ? , ?, ?, ?, ?,?)");
					if (ID != 0) {
						preparedStatement.setInt(1, ID);
					}
					if (NameOnCard != null) {
						preparedStatement.setString(2, NameOnCard);
					}
					if (CardNumber != null) {
						preparedStatement.setString(3, CardNumber);
					}
					if (UnitPrice != null) {
						preparedStatement.setString(4, UnitPrice);
					}
					if (Quantity != null) {
						preparedStatement.setString(5, Quantity);
					}
					if (totalPrice != 0.0D) {
						preparedStatement.setDouble(6, totalPrice);
					}
					if (ExpDate != null) {
						preparedStatement.setString(7, ExpDate);
					}
					preparedStatement.setString(8, newDate);
					preparedStatement.setString(9, createdBy);
					preparedStatement.setString(10, CreditCardType);
					int i = preparedStatement.executeUpdate();
					if ((i > 0) && (Errorcount == 0)) {
						sb.append("Created Successfully in table");
						logger.log(Level.INFO, "Successfully inserted record in the table transaction");
					} else {
						sb.append("Error caused during insertion in the table");
						logger.log(Level.INFO, "Not able to update the record: rolled back");
					}
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "SQL-Error: Exception while inserting data");
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Exception in CreateResultSetInTable method");
			}
		}

		return sb.toString();
	}

	public boolean deleteRecord(int txnID) {
		boolean output = false;
		try {
			Connection conn = DBConnection.getDBConnection();
			int cnt = 0;
			preparedStatement = conn.prepareStatement("select * from a_gupta.transaction where ID=?");
			if (txnID != 0) {
				preparedStatement.setInt(1, txnID);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cnt++;
				System.out.println("Transaction id exists in the table.");
			}
			if (cnt > 0) {
				preparedStatement = conn.prepareStatement(" delete from a_gupta.transaction where ID=? ");
				if (txnID != 0) {
					preparedStatement.setInt(1, txnID);
				}
				int i = preparedStatement.executeUpdate();
				if (i > 0) {
					output = true;
					logger.log(Level.INFO, " Record successfully deleted for the given ID" + txnID);
				} else {
					output = false;
					logger.log(Level.INFO, "No data found to delete the row");
				}
				statement = conn.createStatement();
				resultSet = statement.executeQuery("select * from a_gupta.transaction");
			} else {
				logger.log(Level.INFO, "No data found to delete the row");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL-Error: Exception while executing the delete query");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in deleteRecord method");
		}
		return output;
	}

	public String updateRecord(int ID, String nameOnCard) {
		StringBuilder sb = new StringBuilder();

		try {
			Connection conn = DBConnection.getDBConnection();
			int cnt = 0;
			preparedStatement = conn.prepareStatement("select * from a_gupta.transaction where ID=?");
			if (ID != 0) {
				preparedStatement.setInt(1, ID);
			}
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cnt++;
			}
			if (cnt > 0) {
				preparedStatement = conn.prepareStatement(" update  a_gupta.transaction set NameOnCard=? where ID=?");
				if (nameOnCard != null) {
					preparedStatement.setString(1, nameOnCard);
				}

				if (ID != 0) {
					preparedStatement.setInt(2, ID);
				}
				int i = preparedStatement.executeUpdate();
				if (i > 0) {
					sb.append("Updated Successfully in table");
					logger.log(Level.INFO, "Successfully updated record in the table transaction");
				} else {
					sb.append("Error caused during updation in the table");
					logger.log(Level.INFO, "Not able to update the record: rolled back");
				}

			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL-Error: Exception in updating the record ");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception in updateRecord method");
		}
		return sb.toString();
	}
}
