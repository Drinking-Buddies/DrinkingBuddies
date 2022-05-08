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
	public String editProfilePage(@RequestParam(defaultValue = "") String newBio,
								  @RequestParam(defaultValue = "") String birthDate,
								  @RequestParam(defaultValue = "") String gender,
								  @RequestParam(defaultValue = "") String weight,
								  @RequestParam(defaultValue = "") String phone,
								  @RequestParam(defaultValue = "") String emergencyNum,
								  ModelMap map, HttpServletRequest request){
		if(request.getCookies()!=null) {
			String email = "";
			try {
				email = readCookie("userEmail", request).get();
			} catch (Exception e) {
				return "profileRemind";
			}
			if(!newBio.equals("")){util.updateBio(email,newBio);}
			if(!birthDate.equals("")){util.updateBirthday(email,birthDate);}
			if(!gender.equals("")){util.updateGender(email,gender);}
			if(!weight.equals("")){
				if(isNumber(weight)){
					util.updateWeight(email,Integer.parseInt(weight));
				}else{
					map.put("weightMsg","Invalid weight");
				}
			}
			if(!phone.equals("")){util.updatePhone(email,phone);}
			if(!emergencyNum.equals("")){util.updateEmergencyPhone(email,emergencyNum);}
		}
		map.put("editMsg","change recorded");
		return "editProfile";
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
