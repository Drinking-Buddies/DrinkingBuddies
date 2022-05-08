package com.drinkingbuddies.drinkingbuddies.controller;

import com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EditProfileController {
	private dbUtility util = new dbUtility();
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editProfilePage(ModelMap map, HttpServletRequest request) {
//		String userName = readCookie("userName", request).get();
//		map.put("userName", userName);
		return "editProfile";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String editProfilePage(@RequestParam(defaultValue = "XXX") String newBio, 
								  @RequestParam(defaultValue = "") String birthDate,
								  @RequestParam(defaultValue = "") String gender,
								  @RequestParam(defaultValue = "") String weight,
								  @RequestParam(defaultValue = "") String phone,
								  @RequestParam(defaultValue = "") String emergency,
								  ModelMap map, HttpServletRequest request){

		if(request.getCookies()!=null) {
			
			String email = "";
			try {
				email = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "profileRemind";
			}
			
			// deal with bio
			// meaning we did'nt change bio
			if (!newBio.equals("XXX")) {
				errorCheckBio(newBio, email, map);
				errorCheck(email, map, birthDate, gender, weight, phone, emergency);
			}
			// meaning we changed bio
			else {
				errorCheck(email, map, birthDate, gender, weight, phone, emergency);
			}
			
		}
	
		return "editProfile";
	}
	
	private void errorCheckBio(String newBio, String email, ModelMap map) {
		if(!newBio.equals("Enter your new bio here...") && !newBio.isBlank()){
			util.updateBio(email,newBio);
		}else if (newBio.isBlank()) {
			map.put("bioError", "empty bio...");
		}else {
			map.put("bioError", "please input something else than the placeholder...");
		}
	}
	
	private void errorCheck(String email, ModelMap map, String birthDate, String gender, String weight, String phone, String emergency) {
		Map<String, String> errorMap = new HashMap<>();
		int emptyCnt = 0;
		if(!birthDate.isBlank()){
			util.updateBirthday(email,birthDate);
		}
		
		if(!gender.isBlank()){
			util.updateGender(email,gender);
		}
		
		if(!weight.isBlank()){
			if(isNumber(weight)){
				util.updateWeight(email,Integer.parseInt(weight));
			}else{
				errorMap.put("weightError","Invalid weight");
			}
		}else emptyCnt++;
		
		if(!phone.isBlank()){
			if (isValidNumber(phone)) {
				util.updatePhone(email,phone);
			}else {
				errorMap.put("phoneError","Invalid phone number");
			}
		}else emptyCnt++;
		
		if(!emergency.isBlank()){
			if (isValidNumber(emergency)) {
				util.updateEmergencyPhone(email,emergency);
			}else {
				errorMap.put("emergencyError","Invalid emergency phone number");
			}
		}else emptyCnt++;
		
		for (Entry<String, String> e : errorMap.entrySet()) {
			map.put(e.getKey(), e.getValue());
		}
		if (errorMap.isEmpty()) {
			if (emptyCnt < 3) {
				map.put("editMsg","Change recorded");
			}else {
				map.put("editMsg","Please fill out all three empty fields here");
			}
			
		}
	}
	

	public static boolean isValidNumber(String s)
	{
		Pattern tenPattern = Pattern.compile("^\\d{10}$");
		Matcher tenMatch = tenPattern.matcher(s);
		if(tenMatch.matches() == false)
		{
			return false;
		}
		Pattern hypPattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
		Matcher hypMatch = hypPattern.matcher(s);
		if(hypMatch.matches() == false)
		{
			return false;
		}
		Pattern parenPattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
		Matcher parenMatch = parenPattern.matcher(s);
		if(parenMatch.matches() == false)
		{
			return false;
		}
		return true;
	}

	public Optional<String> readCookie(String key, HttpServletRequest request) {
		return Arrays.stream(request.getCookies())
				.filter(c -> key.equals(c.getName()))
				.map(Cookie::getValue)
				.findAny();
	}

	public static boolean isNumber(String s){
		Pattern pattern = Pattern.compile("[0-9]+\\.?[0-9]*");
		Matcher isNum = pattern.matcher(s);
		if(!isNum.matches()){
			return false;
		}
		return true;
	}
}
