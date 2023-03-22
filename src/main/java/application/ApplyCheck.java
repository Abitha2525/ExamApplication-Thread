package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet Filter implementation class ApplyCheck
 */
@WebFilter(filterName = "ApplyCheck", urlPatterns = {"/Apply"})
public class ApplyCheck extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public ApplyCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		String input = "";
		String jsonInput = "";
		BufferedReader reader = request.getReader();
		JSONObject jsonObject = new JSONObject();
		while((input=reader.readLine())!=null) {
			jsonInput += input;
		}
	   System.out.println(jsonInput);
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(jsonInput);
			System.out.println(json);
		} 
		catch (ParseException e2) {
			System.out.println(e2.getMessage());
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Some error occurred");
			response.getWriter().append(jsonObject.toString());
		}

		String name = (String)json.get("name");
		String guardian = (String)json.get("guardian");
		String qualification = (String)json.get("qualification");
		String exam = (String)json.get("exam");
		String mobileNo = (String)json.get("mobile");
		String email = (String)json.get("email");
		String address = (String)json.get("address");
		String dob = (String)json.get("dob");
		String district = (String)json.get("district");
		String country = (String)json.get("country");
		String state = (String)json.get("state");
		String aadhar = (String)json.get("aadhar");
		
	
		
		try {
		if(!name.equals("")) {
			if(!guardian.equals("")) {
				String regex = "[0-9]*$";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(mobileNo);
				if(mobileNo.length() == 10 && m.matches()) {
					String regex1 = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
					Pattern p1 = Pattern.compile(regex1);
					Matcher m1 = p1.matcher(email);
					if(m1.matches() && !email.equals("")) {
						if(!address.equals("")) {
							if(!dob.equals("")) {
							SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = date.parse(dob);
							System.out.println(date);
							long dateSeconds = date1.getTime();
							
						    Date date2 = new Date();
						    long currentSeconds = date2.getTime();
						    
						    long value=currentSeconds-dateSeconds;
							int answer=(int)((value/86400000)/365);
							if(answer >= 18 && answer < 36) {
								System.out.println(answer);
								if(country.equals("India")) {
									if(state.replace(" ", "").toLowerCase().equals("tamilnadu")){
										String regex2 = "[0-9]*$";
										Pattern p2 = Pattern.compile(regex2);
										Matcher m2 = p2.matcher(aadhar);
										if(aadhar.length() == 12 && !aadhar.equals("") && m2.matches()) {
											boolean check = false;
											File file = new File("/home/abitha-zstk276/GroupExamApplications/src/index.txt");
											FileReader fileReader = new FileReader(file);
											BufferedReader buffer = new BufferedReader(fileReader);
											String str = "";
											while((str = buffer.readLine()) != null) {
												if(str.equals(aadhar)) {
													check = true;
													break;
												}
												System.out.println(str);
											}
											fileReader.close();
											buffer.close();
											if(!check) {
												request.setAttribute("aadhar", aadhar);
												chain.doFilter(request, response);
											}
											else {
												jsonObject.put("statusCode", 402);
												jsonObject.put("message", "You already applied for this exam");
												response.getWriter().append(jsonObject.toString());
											}
										}
										else {
											jsonObject.put("statusCode", 400);
											jsonObject.put("message", "Please check your aadhar no ");
											response.getWriter().append(jsonObject.toString());
										}
									}
									else {
										jsonObject.put("statusCode", 400);
										jsonObject.put("message", "You are in Tamil Nadu");
										response.getWriter().append(jsonObject.toString());
									}
								}
								else {
									jsonObject.put("statusCode", 400);
									jsonObject.put("message", "You are in India");
									response.getWriter().append(jsonObject.toString());
								}
							}
							else {
								jsonObject.put("statusCode", 400);
								jsonObject.put("message", "You didn't have privillege age to attend this exam");
								response.getWriter().append(jsonObject.toString());
							}
							}
							else {
								jsonObject.put("statusCode", 400);
								jsonObject.put("message", "Please give your correct date of birth");
								response.getWriter().append(jsonObject.toString());
							}
						}
						else {
							jsonObject.put("statusCode", 400);
							jsonObject.put("message", "Please give your correct addresss");
							response.getWriter().append(jsonObject.toString());
						}
					}
					else {
						jsonObject.put("statusCode", 400);
						jsonObject.put("message", "Please give a valid email id");
						response.getWriter().append(jsonObject.toString());
					}
				}
				else {
					jsonObject.put("statusCode", 400);
					jsonObject.put("message", "Mobile Number must have 10 digits");
					response.getWriter().append(jsonObject.toString());
				}
			}
			else {
				jsonObject.put("statusCode", 400);
				jsonObject.put("message", "Please fill the candidate's guardian name");
				response.getWriter().append(jsonObject.toString());
			}
		}
		else {
			jsonObject.put("statusCode", 400);
			jsonObject.put("message", "Please fill the candidate's name");
			response.getWriter().append(jsonObject.toString());
		}
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
