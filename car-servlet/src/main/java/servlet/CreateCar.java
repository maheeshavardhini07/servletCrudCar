package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Car;

@WebServlet("/insertCar")
public class CreateCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Insert Car Called");
		String action = req.getServletPath();
		System.out.println("Action " + action);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true",
					"", "");

//			int id = Integer.parseInt(req.getParameter("id"));
			String brand = req.getParameter("brand");
			String model = req.getParameter("model");
			int price = Integer.parseInt(req.getParameter("price"));
			Car car = new Car(brand, model, price);
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO car (brand, model, price) VALUES (?, ?, ?)");
			System.out.println("Connection process completed Insert");
//			statement.setInt(1, car.getId());
			statement.setString(1, car.getBrand());
			statement.setString(2, car.getModel());
			statement.setInt(3, car.getPrice());
			statement.executeUpdate();
			statement.close();
			conn.close();
			System.out.println("Insert Called with connection disclose");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("carList.jsp");
	}

}
