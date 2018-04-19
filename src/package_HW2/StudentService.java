/*
 Name- Saurabh Singh
 SWE 645
 Assignment number 2
 Professor Vinod Dubey
 
 Description :- This is a class which is having the business logic to calculate the mean and the standard
 deviation of the 10 numbers stored in the field raffle. When the user submits the survey form, the values wil
 be set in to the managed beans named StudentInfo, then this class will use the raffle field and will
 calculate the mean and the standard deviation of those 10 numbers.
 
 */

package package_HW2;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class StudentService {

	public static ArrayList<StudentInfo> studData = new ArrayList<StudentInfo>();
	
	public static void addStu(StudentInfo stu){
		studData.add(stu);
	}
	/*
	 * Description:- This is one of the two methods inside this class, It is
	 * taking in the value of the raffle which is in String, then this method
	 * will split the values based on the "," and then it would convert all
	 * those comma separated values in to integer and then it would calculate
	 * the mean of it and return it to the calling method.
	 * 
	 * @Param- It is taking in a String parameter named ss , which is actually
	 * the String raffle
	 * 
	 * @Return - It returns a double value that is the calculated mean of the 10
	 * numbers stored inside the raffle
	 */
	public double calMean(String ss) throws IOException, NullPointerException {

		String s = ss;// s1.getRaffle();
		String values[] = s.split(",");
		int len = values.length;
		int numArray[] = new int[len];
		int sum = 0;
		double mean = 0.0;
		System.out.println("Calculating mean");

		try {

			for (int i = 0; i < len; i++) {
				numArray[i] = Integer.parseInt(values[i]);
				sum = sum + numArray[i];
			}
			mean = sum / len;
			return mean;
		} catch (Exception e) {
			System.out.println("Exception occured.");
		}
		System.out.println("Mean is   :  "+mean);

		return mean;

	}

	
	
	/* Description:- This is one of the two methods inside this class, It is takinging in
	 * 				the value of the raffle which is in String, then this method will split the
	 * 				values based on the "," and then it would convert all those comma saperated values in to
	 *				integer and then it would calculate the mean  and standard deviation of it and 
	 *				return the standeard deviation to the calling method.
	 * 
	 * @Param- It is taking in a String parameter named ss , which is actually the String raffle
	 * @Return - It returns a double value that is the calculated standard deviation of the 10 numbers stored inside 
	 * the raffle
	 * */
	
	public double calStd(String ss) {

		String s = ss;// s1.getRaffle();
		String values[] = s.split(",");
		int len = values.length;
		int numArray[] = new int[len];
		int sum = 0;
		double std = 0.0, mean = 0.0;

		try {

			for (int i = 0; i < len; i++) {
				numArray[i] = Integer.parseInt(values[i]);
				sum = sum + numArray[i];
			}
			mean = sum / len;

		} catch (Exception e) {
			System.out.println("Exception occured.");

		}

		for (int i = 0; i < len; i++) {
			std = std + Math.pow(numArray[i] - mean, 2);
		}
		System.out.println("std is : "+std);

		return std;
	}

	// Method to add the Information inside the database

	public void addToDatabase() throws java.io.IOException {
		File file = new File("member.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write("test");
		} finally {
			if (writer != null)
				writer.close();
		}
		System.out.printf("File is located at %s%n", file.getAbsolutePath());

	}

	// Method to find which page to show to the user depending on the standard
	// deviation.

}
