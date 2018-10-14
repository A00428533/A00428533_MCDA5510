import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class A00428533_MainMethod {

	public static final Logger logger = Logger.getLogger(A00428533_MainMethod.class.getName());
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		FileHandler fh = null;
		SimpleFormatter formatterTxt = new SimpleFormatter();
		TransactionAccessDA0 dao = new TransactionAccessDA0();
		PrintWriter pw = null;
		int count = 0;
		pw = new PrintWriter(new FileOutputStream(new File("/home/student_2018_fall/a_gupta/Output.txt"), true));
		try {

			fh = new FileHandler("/home/student_2018_fall/a_gupta/MyLogFile.log", true);
			logger.addHandler(fh);
			fh.setFormatter(formatterTxt);
			while (count == 0) {
				int type = 0;
				System.out.println(
						"Please enter the operation to be done:\n\n 1) *To CREATE THE RECORD\t 2) *To SELECT THE RECORD\n 3) *To UPDATE THE RECORD\t 4) *To DELETE THE RECORD\n 5) To Exit");
				type = Integer.parseInt(br.readLine());
				switch (type) {
				case 1:
					pw.println("Creating the record in database");
					pw.close();
					dao.inputFromUserMethod(type);
					break;
				case 2:
					dao.inputFromUserMethod(type);
					break;
				case 3:
					pw.println("Updating the record in database");
					dao.inputFromUserMethod(type);
					break;
				case 4:
					pw.println("Deleting the record in database");
					dao.inputFromUserMethod(type);
					break;
				case 5:
					count = 1;
					break;
				default:
					System.out.println("Enter valid option");
				}
			}
			dao.close();

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Please enter the valid path of log file");
		} finally {

			if (pw != null) {
				pw.close();
			}
			if (fh != null) {
				fh.close();
			}
		}
	}
}
