import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class SimpleCsvParser {

	private static final Logger logger = Logger.getLogger(Assignmnet_MSCDA5510_A00428533.class.getName());
	Reader in;
	String fileName = null;
	String filePath = null;
	String date = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public void dirWaker(String path) {

		SimpleCsvParser sCSVParser = new SimpleCsvParser();
		try {

			File root = new File(path);
			File[] list = root.listFiles();
			if (list == null) return ;

			for (File f : list) {
				if (f.isDirectory()) {
					dirWaker(f.getAbsolutePath());

				} else {
					if (f.getName().contains(".csv")) {
						filePath = f.getAbsoluteFile().toString();
						fileName = filePath.substring(filePath.lastIndexOf("/"));
						filePath = pathLength(filePath, fileName);

						String dd = filePath.substring(filePath.lastIndexOf("/"));
						filePath = pathLength(filePath, dd);

						String mm = filePath.substring(filePath.lastIndexOf("/"));
						filePath = pathLength(filePath, mm);

						String yy = filePath.substring(filePath.lastIndexOf("/")+1);
						String Date = yy + mm + dd;
						filePath = f.getAbsoluteFile().toString();
						//date = dateFormat.format(f.lastModified());
						sCSVParser.path(filePath, fileName, Date);
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "File parsing exception" + e);
		}
	}

	public void path(String path, String name, String date) {

		try {
			int start = 0;
			in = new FileReader(path);
			ArrayList<CustomerDTO> sucessCustomerList = new ArrayList<CustomerDTO>();
			CustomerDTO cDTO = null;
			SimpleCsvParser sCSVParser1 = new SimpleCsvParser();
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			try {
				for (CSVRecord record : records) {

					if (!(record.get(0).trim().isEmpty())) { // Taken First name as mandatory.

						if (Assignmnet_MSCDA5510_A00428533.Valid == 0 && start == 0) {
							cDTO = new CustomerDTO();
							cDTO.setFirstName(record.get(0).trim());
							cDTO.setLastName(record.get(1));
							cDTO.setStreetNumber(record.get(2).toString());
							cDTO.setStreet(record.get(3));
							cDTO.setCity(record.get(4));
							cDTO.setProvince(record.get(5));
							cDTO.setPostalCode(record.get(6));
							cDTO.setCountry(record.get(7));
							cDTO.setPhoneNumber(record.get(8));
							cDTO.setEmailAddress(record.get(9));
							cDTO.setDate("FileDate");
							cDTO.setFileName("FileName");
							cDTO.setFilePath("FilePath");
							sucessCustomerList.add(cDTO);
							sCSVParser1.writeToCSV(sucessCustomerList);
							start = 1;
						} else if (Assignmnet_MSCDA5510_A00428533.Valid > 0 && start == 0) {
							record.iterator().next();
							start = 1;
						} else {
							sucessCustomerList.clear();
							cDTO = new CustomerDTO();
							cDTO.setFirstName(record.get(0).trim().toString()
									.replace(",", "-"));
							cDTO.setLastName(record.get(1).toString()
									.replace(",", "-"));
							cDTO.setStreetNumber(record.get(2).toString()
									.toString().replace(",", "-"));
							cDTO.setStreet(record.get(3).toString()
									.replace(",", "-"));
							cDTO.setCity(record.get(4).toString()
									.replace(",", "-"));
							cDTO.setProvince(record.get(5).toString()
									.replace(",", "-"));
							cDTO.setPostalCode(record.get(6).toString()
									.replace(",", "-"));
							cDTO.setCountry(record.get(7).toString()
									.replace(",", "-"));
							cDTO.setPhoneNumber(record.get(8).toString()
									.replace(",", "-"));
							cDTO.setEmailAddress(record.get(9).toString()
									.replace(",", "-"));
							cDTO.setFileName(name);
							cDTO.setFilePath(path);
							cDTO.setDate(date.toString());
							sucessCustomerList.add(cDTO);
							if(sucessCustomerList!=null)
							{
								sCSVParser1.writeToCSV(sucessCustomerList);
								Assignmnet_MSCDA5510_A00428533.Valid++;
							}
						}
					}
					else {
						Assignmnet_MSCDA5510_A00428533.inValid++;
						logger.log(Level.INFO, "First name not found for "+name+" . Skipping this row.");
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				logger.log(Level.SEVERE,
						"Handling array out of bount exception" + e);

			} catch (Exception e) {
				logger.log(Level.SEVERE, "File parsing exception" + e);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "File reading exception" + e);
		}
	}
	public String pathLength(String filePath, String fname) {
		filePath = filePath.substring(0, filePath.length() - fname.length());
		return filePath;
	}

	public void writeToCSV(ArrayList<CustomerDTO> sucessCustomerList) {
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(new FileOutputStream(new File("/home/student_2018_fall/a_gupta/Data_A00428533.csv"), true));
			for (CustomerDTO a : sucessCustomerList) {
				{
					StringBuilder sb = new StringBuilder();
					sb.append(a.getFirstName());
					sb.append(',');
					sb.append(a.getLastName());
					sb.append(',');
					sb.append(a.getStreetNumber());
					sb.append(',');
					sb.append(a.getStreet());
					sb.append(',');
					sb.append(a.getCity());
					sb.append(',');
					sb.append(a.getProvince());
					sb.append(',');
					sb.append(a.getPostalCode());
					sb.append(',');
					sb.append(a.getCountry());
					sb.append(',');
					sb.append(a.getPhoneNumber());
					sb.append(',');
					sb.append(a.getEmailAddress());
					sb.append(',');
					sb.append(a.getFilePath());
					sb.append(',');
					sb.append(a.getFileName());
					sb.append(',');
					sb.append(a.getDate());
					sb.append('\n');
					pw.write(sb.toString());
				}
				pw.close();
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "File not found thrown" + e);
		} finally {
			if(pw!=null){
				pw.close();
			}
		}

	}
}
