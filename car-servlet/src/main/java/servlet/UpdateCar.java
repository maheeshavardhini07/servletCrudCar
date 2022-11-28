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

@WebServlet("/updateCar")
public class UpdateCar extends HttpServlet {

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
			Connection con = DriverManager.getConnection(
					"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true",
					"", "");
		System.out.println("Connected Success");
		System.out.println(req.getParameter("id"));
			int id = Integer.parseInt(req.getParameter("id"));
			String brand = req.getParameter("brand");
			String model = req.getParameter("model");
			int price = Integer.parseInt(req.getParameter("price"));
			Car cars = new Car(id, brand, model, price);
		System.out.println("Before Prepared Statement Executed");
//			String sql = "UPDATE car SET brand = ?, model = ?, price = ? WHERE id = ?";
			// sql += " ";
			PreparedStatement statement = con.prepareStatement("UPDATE car SET brand = ?, model = ?, price = ? WHERE id = ?");
		System.out.println("Connection process completed Update");
//			statement.setInt(1, cars.getId());
			statement.setString(1, cars.getBrand());
			statement.setString(2, cars.getModel());
			statement.setInt(3, cars.getPrice());
			statement.setInt(4, cars.getId());
			statement.executeUpdate();
			statement.close();
			con.close();
		System.out.println("Update Called with connection disclose");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("carList.jsp");
	}
}
