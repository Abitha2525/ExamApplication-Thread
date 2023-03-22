package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Apply
 */
@WebServlet("/Apply")
public class Apply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		
		try {
		
		String aadhar = (String) request.getAttribute("aadhar");
		threadHandling a1 = new threadHandling(new Appliers(aadhar));
//		threadHandling a2 = new threadHandling(new Appliers("123128746529"));
//		threadHandling a3 = new threadHandling(new Appliers("059478642310"));
//		threadHandling a4 = new threadHandling(new Appliers("874567354219"));
//		threadHandling a5 = new threadHandling(new Appliers("657831240945"));
		
		a1.start();
		a1.join();
//		a2.start();
//		a2.join();
//		a3.start();
//		a3.join();
//		a4.start();
//		a4.join();
//		a5.start();
//		a5.join();
		
//		threadHandling obj = new threadHandling();
//		obj.threadAppliers(aadhar);
	   
	   jsonObject.put("statusCode", 200);
	   jsonObject.put("message", "Congrats, You successfully applied to this exam");
	   response.getWriter().append(jsonObject.toString());
	}
	catch(Exception ex) {
		ex.printStackTrace();
		jsonObject.put("statusCode", 200);
		jsonObject.put("message", "Some error occurred");
		response.getWriter().append(jsonObject.toString());
	}
	}
}
