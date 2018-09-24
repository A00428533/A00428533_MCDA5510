import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Assignmnet_MSCDA5510_A00428533 {

	public static int Valid = 0;
	public static int inValid = 0;
	public static final Logger logger = Logger
			.getLogger(Assignmnet_MSCDA5510_A00428533.class.getName());

	public static void main(String[] args) {

		final long startTime = System.currentTimeMillis();
		PrintWriter pw = null;
		FileHandler fh=null;  
        SimpleFormatter formatterTxt = new SimpleFormatter();  
        Scanner reader1 =null;
        Scanner reader2 =null;
		try {
			fh = new FileHandler("/home/student_2018_fall/a_gupta/MyLogFile.log");
           logger.addHandler(fh);
           fh.setFormatter(formatterTxt);  
			try {
				pw = new PrintWriter(new FileOutputStream(new File(
						"/home/student_2018_fall/a_gupta/Data_A00428533.csv")));
				pw.close();

			} catch (FileNotFoundException e) {
				logger.log(Level.SEVERE, "File not found thrown" + e);

			}
			SimpleCsvParser SCsvParser = new SimpleCsvParser();
			reader1 = new Scanner(System.in);
			reader2 = new Scanner(System.in);
			String path = null;
			int inputValue = 0;
			System.out
					.println("There are two options to select:\n 1) Enter the path manually \n 2) The path is predefined \n Do you want to select options 1 or 2 ?");
			inputValue = reader1.nextInt();
			
			System.out.println();
			if (inputValue == 1) {
				System.out.println("Please enter the path to proceed: ");
				path = reader2.nextLine();
				File root = new File(path);
				if (!root.isDirectory()) {
					logger.log(Level.SEVERE, "Please enter the valid path");
				} else {
					logger.log(Level.INFO,"Start time:" + startTime);
					SCsvParser.dirWaker(path);
					}
				}
			 else if (inputValue == 2) {
			    logger.log(Level.INFO,"Start time:" + startTime);
				SCsvParser.dirWaker("/home/student_2018_fall/a_gupta/Sample Data/Sample Data/");

			} else {
				System.out.println("Please enter some value");
			}
			final long endTime = System.currentTimeMillis();
			logger.log(Level.INFO, "Total valid count:" + Assignmnet_MSCDA5510_A00428533.Valid +" Total invalid count:"+ Assignmnet_MSCDA5510_A00428533.inValid);
			logger.log(Level.INFO, "End Time:" + endTime);
			logger.log(Level.INFO, "End Time:" + "Total execution time: " + (endTime - startTime)+ " ms");
		}
		catch(SecurityException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, "File Exception thrown" + e);

		} finally {
			if(reader1!=null)
			{
				reader1.close();
			}
			if(reader2!=null)
			{
				reader2.close();
			}
			if(fh!=null)
			{
				fh.close();
			}
			if(pw!=null)
			{
			pw.close();
			}

		}
	}

}
