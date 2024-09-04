package com.packages.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import com.packages.LogicClasses.GenerateOtp;
import com.packages.LogicClasses.GetCurrentTime;
import com.packages.Model.tasks;
import com.packages.Model.users;
import com.packages.ServiceLayer.userService;

@Controller
public class MainController {

	@Autowired
	private GenerateOtp otp ;
	
	@Autowired
	private userService userservice; 
	
	@Autowired
	private GetCurrentTime currentdate;
	
	
	
	
	
	@RequestMapping("/")
	public String fun()
	{
		return "redirect:/loginPage";
	}
	
	
	
	 // Opening login page 
	 @RequestMapping("/loginPage")
	 public String loginPage()
	 {      
		 return "loginPage";              
	 }
	
	 
	 
	 
	 
	 
	 
	 
	 
	 // Opening sign in page
	 @RequestMapping("/signupPage")
	 public String signupPage()
	 {
		 return "signupPage";
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // HANDLING SIGN UP PAGE DATA 
	 @PostMapping("/getUserDetail")
	 public String getUserDetail(@ModelAttribute users userdetail , HttpSession session,Model model)
	 {
		 
	      String dateandtime=currentdate.currentTime();
	      userdetail.setCreated_at(dateandtime);
	      
	      
	      
	      String email=userdetail.getEmail();
	      
	      Long conformationofemail = userservice.CheckEmialExistOrNot(email);
	      
	      if(conformationofemail!=null)
	      {
	    	  model.addAttribute("messageaboutemail", "Account Already Exist");
	    	   return "loginPage";
	      }
	      else {
	      
	      
	      session.setAttribute("userdetail", userdetail);
	      
	      
	      String generatedcode= otp.generateotp();
	      
	      session.setAttribute("generatedcode", generatedcode);
	      
	      otp.SendOtp(userdetail.getEmail(), generatedcode);
	      
		  return "verifyEmailPage";        // email opt verification page
	      }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // HANDLING EMAIL VERIFICATION 
	 
	 @PostMapping("/verifyEmail")
	 public String verifyEmail(@RequestParam("otp") String otp,HttpSession session,Model model)
	 {
		 users userdetail_on_signin=(users) session.getAttribute("userdetail"); 
		 if( userdetail_on_signin !=null)
		 {
		 String sendotp = (String) session.getAttribute("generatedcode");	 
		 users userdetail = (users) session.getAttribute("userdetail");
		 
		 if(otp.equals(sendotp))
		 {
			 session.removeAttribute("generatedcode");
			  
			 userservice.saveUser(userdetail);
			 
			 return "loginPage";
		 }
		 else {
			 
			 model.addAttribute("errorMessage","wrong otp! please try again ");
			 return "verifyEmailPage";
		 }
		 
		 }else {
			 
			 return "signupPage";
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // HANDLING LOGIN PAGE DATA
	 @PostMapping("/getlogindetail")
	 public String loginPage(@RequestParam("email") String email,
			                  @RequestParam("password") String password,
			                  HttpSession session,
			                  Model model) {
		 
		if( userservice.CheckCredentialForLogin(email, password)) {
			session.setAttribute("emailofuser_afterlogedin", email);	
			return "redirect:/openhomepage";
		}else
		{
		 model.addAttribute("login_error_message","Wrong Password! Please Try Again");
		 return "loginPage";
		}
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //OPENING HOME PAGE
	 
	 @RequestMapping("/openhomepage")
	 public String openHomePage(HttpSession session,
			                    Model model)
	 {
		 String userEmail = (String) session.getAttribute("emailofuser_afterlogedin");
		 
		 if(userEmail != null)
		 {
			 int userid=userservice.getUserId_ThroughEmail(userEmail);
	         List<tasks> UserToDoList=null;
	         UserToDoList=userservice.getUserAllToDOList(userid); 
			 model.addAttribute("AllToDoListOfUser", UserToDoList);
			 return "home";
		 }else
		 {
			 return "loginPage";
		 }
		
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // HANDLING NEW TO DO LIST DATA 
	 @PostMapping("/create_to_do_list_data")
	 public String addDatatolist(@ModelAttribute tasks usertask,
			                      HttpSession session,
			                      Model model)
	 {
		 
		 String userEmail = (String) session.getAttribute("emailofuser_afterlogedin");
		 
		 if(userEmail != null)
		 {
			 int userid=userservice.getUserId_ThroughEmail(userEmail);
			 
			 
			 usertask.setUser_id(userid);
			 usertask.setCreated_at(currentdate.currentTime());
			 usertask.setUpdated_at(currentdate.currentTime());
			 
			 userservice.saveUserTask(usertask);
			 
			/*List<tasks> UserToDoList=null;
			 
			 UserToDoList =userservice.getUserAllToDOList(userid);
			 
			 model.addAttribute("AllToDoListOfUser", UserToDoList );*/
			    
			 return "redirect:/openhomepage";
		 }else
		 {
			 return "loginPage";
		 }
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 //HANDLING THE EDIT LIST BUTTON
	 @PostMapping("/edit_to_do_list")
	 public String editlist(@RequestParam("id") int id,
			                @RequestParam("title") String title,
			                @RequestParam("description") String description,
			                HttpSession session,
			                Model model)
	 {
		 String userEmail = (String) session.getAttribute("emailofuser_afterlogedin");
		 
		 if(userEmail!=null)
		 {
			 if(userservice.editToDOList(id,title,description))
				{
					 return "redirect:/openhomepage";
				}else {
					model.addAttribute("edit_to_do_list_error","Please Try Again ! Error In Editing The Data");
					return "redirect:/openhomepage";
				}
		 }else
		 {
			 return "loginPage";
		 }
		
		
	 }
	 
	 
	 
	 @RequestMapping("/delete_to_do_list_element")
	 public String deleteItemFromList(@RequestParam("id") int id,
			                           Model model,
			                           HttpSession session)
	 {
		 String userEmail = (String) session.getAttribute("emailofuser_afterlogedin");
		 
		 if(userEmail != null)
		 {
			 System.out.println("value of id need to be deleted = " + id);
			 
			 if(userservice.deleteItemFromoDoList(id))
			 {
				 return "redirect:/openhomepage";
			 }else {
				 model.addAttribute("error_message_of_deleting","Data Not Deleted ! Please Try Again ");
				 return "redirect:/openhomepage";
			 }
		 }else
		 {
			 return "loginPage";
		 }
		 
		 
		 
	 }
}


















