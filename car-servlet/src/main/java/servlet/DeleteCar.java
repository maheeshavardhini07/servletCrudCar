package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Car;

@WebServlet("/deleteCar")
public class DeleteCar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Delete Car Called");
		String action = req.getServletPath();
		System.out.println("Action " + action);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true",
					"", "");

			int id = Integer.parseInt(req.getParameter("id"));
			Car car = new Car(id);
			PreparedStatement statement = conn.prepareStatement("DELETE FROM car WHERE id = ?");
			System.out.println("Connection process completed Delete");
			statement.setInt(1, car.getId());
			statement.executeUpdate();
			statement.close();
			conn.close();
			System.out.println("Delete Called with connection disclose");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("carList.jsp");
	}

}
