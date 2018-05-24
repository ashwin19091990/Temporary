package DataLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InputServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		out.println("The page is getting printed");
		/*String name = request.getParameter("name");
		String assetid = request.getParameter("Asset Id");
		String course = request.getParameter("Course completion");*/
		
		FeederMechanism fm = new FeederMechanism();
		
		
		BufferedReader bufferedreader = new BufferedReader(new FileReader("sample.txt"));
		String line = null;
		ArrayList<String> namelist = new ArrayList<String>();
		while ((line = bufferedreader.readLine()) != null){
			
			namelist.add(request.getParameter(line));
	
		}
		
		int validitycheck=fm.tochecktheemployeeidvalidity(namelist);
		if(validitycheck != -1){
		fm.toEnterDataIntoExcel(namelist);
		request.setAttribute("list", namelist);
		}
		ArrayList<String> employeeidlist = fm.toRetriveDefaulter();
		request.setAttribute("employeeidlist", employeeidlist);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("input.jsp");
		dispatcher.forward(request,response);
		
		//request.setAttribute("testvalue", name);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
