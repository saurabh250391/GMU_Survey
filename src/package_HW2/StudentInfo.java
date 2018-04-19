/*
 Name- Saurabh Singh
 SWE 645
 Assignment number 2
 Professor Vinod Dubey
 
 Description :- This is a wrapper class for the student survey form, which is having
 the getters and setters for all the survey form variables. This is JSF so we will call it
 Managed bean class, It has a request scope and we will also make instances of other two classes i.e.
 studentService and WinningResult in to this class so that we can set the value of mean and Standard deviation.
 
 */

package package_HW2;


//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import package_HW2.WinningResult;
import static package_HW2.StudentService.studData;
import package_HW2.IOException;

@ManagedBean
@RequestScoped
public class StudentInfo {
	
	private static final String MasonRec = "VeryLikely,Likely,UnLikely";
	private static final String[] MasonRecArray = MasonRec.split(",");

	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private Date date;
	private String email;
	private Date semDate;
	private List<String> liking;
	private String interested;
	private String recommendation;
	private String raffle;
	private String comment;
	
	//private double mean;
	//private double sdev;
	private WinningResult w1;
	private StudentService s1;
	
	/*Public constructor for the Managed Bean class, when it will be instantiated it will
	also make the objects for class WinningResult and StudentService 
	@param - It is not taking in an parameter
	@return - It is not returning anything as it is a constructor
	*/
	public StudentInfo(){
		
		 w1 = new WinningResult();
		 s1 = new StudentService();
	}
	
	
	
	/*
	 The below is the list of getters and setters that we will be using to set the for variables
	 and to get them when required.
	 
	 @param - All the setters are taking in String values and are setting them to the respective 
	 variable, While getters are not taking in any argument.
	 
	 @return - getters are returning the String while setters are of void type as they are just used to
	 set the variable.*/

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
		
		cityState();
		// method new method

	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public Date getSemDate() {
		return semDate;
	}



	public void setSemDate(Date semDate) {
		this.semDate = semDate;
	}



	public List<String> getLiking() {
		return liking;
	}


	public void setLiking(List<String> liking) {
		this.liking = liking;
	}

	public String getInterested() {
		return interested;
	}

	public void setInterested(String interested) {
		this.interested = interested;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getRaffle() {
		return raffle;
	}

	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	public double getMean() {
		return w1.getMean();
	}


	public double getSdev() {
		return w1.getSdev();
	}


	/*Description - This is the main method that would be called on the submission of the survey form.
	 * When user clicks the submit button the controller will perform action of calling this method.
	 * This method is passing the raffle string in to methods named calMean and calStd in to the
	 * StudentService class, and then sets the value of these two computations in to the variable named
	 * mean and std in the WinningResult class 68.75
	 * */
	public String doProcess() throws NullPointerException, IOException, java.io.IOException{
		
		if(semDate.before(date)){
			
			System.out.println("Inside do process .....");
			System.out.println("survey date is : "+date+" Sem start date is : "+semDate);

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage("The semester start date is not less than the survey date ");
			context.addMessage("myForm:semDate",message);
			System.out.println("end of semester start date");
			return null;
		}
		
		
		w1.setMean(s1.calMean(getRaffle()));
		w1.setSdev(s1.calStd(getRaffle()));
		studData.add(this);
		
		if(w1.getMean()<90){
			return "thankyou";
		}
		else{
			return "winner";
		}
	}

	public ArrayList<StudentInfo> getstudData(){
		return studData;
	}
	
	/* Method to autocomplete the dropdown list*/
	public List<String> NameSuggestion(String Suggestion){
		List<String> matches = new ArrayList<String>();
		
		for(String s : MasonRecArray){
			if(s.toUpperCase().startsWith(Suggestion.toUpperCase())){
				matches.add(s);
			}
			
		}
		return matches;
	}
	
	/*Method  that act as a REST client and calls the rest API defined in the
	 * java class named updateCityStateRest.java  */
	public void cityState(){
		System.out.println("inside city state");
		
		String strRes="";
		Client cl = ClientBuilder.newClient();
		
		Response response = cl
				.target("http://ec2-34-202-161-199.compute-1.amazonaws.com/swe-645-hw3/webresources/updateRest")
				.path(zip)
				.request()
				.get();
		
		strRes= response.readEntity(String.class);
		
		String[] SSS = strRes.split(":");
		this.setCity(SSS[0]);
		this.setState(SSS[1]);
		
	}
}
