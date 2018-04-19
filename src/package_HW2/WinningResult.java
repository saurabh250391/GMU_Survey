/*
 Name- Saurabh Singh
 SWE 645
 Assignment number 2
 Professor Vinod Dubey
 
 Description :- This is a wrapper class for the mean and standard deviation.
 When the user will submit the form the values of eavh fiel will get set in to the Managed Bean named
 StudentInfo, After that a method will extract the Raffle field and will calculate the mean and standard deviation
 of those 10 numbers inside the raffle and will will store them inside the variables named mean and sdev.
 
 This is also a bean class,we can access the variables inside this class using their getters while
 we can set those variables using the public setters of those variables.
 
 */



package package_HW2;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class WinningResult {
	
	/*Private variables to store the calculated mean and standard deviation of the
	 * 10 numbers stored inside the raffle*/
	private double mean;
	private double sdev;
	
	
	
	/**
	 * @return the mean
	 */
	public double getMean() {
		return mean;
	}
	/**
	 * @param mean the mean to set
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}
	/**
	 * @return the sdev
	 */
	public double getSdev() {
		return sdev;
	}
	/**
	 * @param sdev the sdev to set
	 */
	public void setSdev(double sdev) {
		this.sdev = sdev;
	}
	
	

}
