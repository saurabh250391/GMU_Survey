/*
 Name- Saurabh Singh
 SWE 645
 Assignment number 2
 Professor Vinod Dubey
 
 Description :- This is a REST web service which will be called on the blured event 
 of the zip field. 
 
 */

package package_HW2;

//import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("updateRest")
public class UpdateCityStateRest {
	
	@GET
	@Path("{zip}")
	public Response getCityState(@PathParam("zip") int zip){
		System.out.println("Hey");
		
		String strRespose = "";
		
		// matching the zip code and returning city and state delimited by colon
		if(zip==22312){
			strRespose="Alexandria:VA";
			
		}
		else if(zip==22030){
			strRespose="Fairfax:VA";
			
		}
		else if(zip==22301){
			strRespose="Tysons Corner:MD";
			
		}
		else if(zip==20148){
			strRespose="Ashburn:VA";
			
		}
		else{
			strRespose="invalid:invlaid";

		}
		
		return Response.ok(strRespose).build();
	} 

}
