package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
// import javax.servlet.ServletRequest;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Car;


// @WebFilter(urlPatterns = "/uppercase")
// public class EmptyParamFilter implements Filter {
//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//       FilterChain filterChain) throws IOException, ServletException {
//         String inputString = servletRequest.getParameter("input");

//         if (inputString != null && inputString.matches("[A-Za-z0-9]+")) {
//             filterChain.doFilter(servletRequest, servletResponse);
//         } else {
//             servletResponse.getWriter().println("Missing input parameter");
//         }
//     }

//     // implementations for other methods
// }




@WebServlet("/car")
public class CarServlet extends HttpServlet {
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
        System.out.println("Car Controller doGet");
        String action = req.getServletPath();
         System.out.println(action);

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    System.out.println("Insert Method");
                    insertCar(req, resp);
                    break;
                case "/delete":
                    deleteCar(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateCar(req, resp);
                    break;
                default:
                    listCar(req, resp);
                    break;
            }
            System.out.println(action);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    private void listCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("List Car Called");
        try {
            Connection conn = DriverManager.getConnection(
            		"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true", "",
					"");

            ArrayList<Car> crlist = new ArrayList<Car>();
            java.sql.Statement stmt = conn.createStatement();
            String strSelect = "select id, brand, model, price from car";
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) { // Repeatedly process each row
                int id = rset.getInt("id");
                String brand = rset.getString("brand"); // retrieve a 'String'-cell in the row
                String model = rset.getString("model"); // retrieve a 'double'-cell in the row
                int price = rset.getInt("price"); // retrieve a 'int'-cell in the row
                System.out.println(id + ", " + brand + ", " + model + ", " + price);
                crlist.add(new Car(id, brand, model, price));
            }
            crlist.forEach(System.out::println);
            req.setAttribute("crlist", crlist);
            RequestDispatcher dis = req.getRequestDispatcher("carList.jsp");
            dis.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("New Form Called");
        RequestDispatcher dis = req.getRequestDispatcher("carForm.jsp");
        dis.forward(req, resp);
    }

    private void insertCar(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Insert Car Called");
        try {
            Connection conn = DriverManager.getConnection(
            		"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true", "",
					"");{
                String brand = req.getParameter("brand");
                String model = req.getParameter("model");
                int price = Integer.parseInt(req.getParameter("price"));
                Car car = new Car(brand, model, price);
                String sql = "INSERT INTO car (brand, model, price) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, car.getBrand());
                statement.setString(2, car.getModel());
                statement.setInt(3, car.getPrice());
                statement.executeUpdate();
                statement.close();
                conn.close();
                resp.sendRedirect("/list");}
          } catch (Exception e) {
            e.printStackTrace();
          }
    }

    private void updateCar(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try (Connection con = DriverManager.getConnection(
        		"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true", "",
				"")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            int price = Integer.parseInt(req.getParameter("price"));
            Car cars = new Car(id, brand, model, price);
            String sql = "UPDATE car SET brand = ?, model = ?, price = ? WHERE id = ?";
            // sql += " ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, cars.getBrand());
            statement.setString(2, cars.getModel());
            statement.setFloat(3, cars.getPrice());
            statement.setInt(4, cars.getId());
            statement.executeUpdate();
            statement.close();
            con.close();
            resp.sendRedirect("/list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection con = DriverManager.getConnection(
        		"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true", "",
				"")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Car cars = null;
            String sql = "SELECT * FROM car WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int price = resultSet.getInt("price");
                cars = new Car(id, brand, model, price);
            }
            resultSet.close();
            statement.close();
            con.close();
            RequestDispatcher dispatcher = req.getRequestDispatcher("carForm.jsp");
            req.setAttribute("cars", cars);
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection con = DriverManager.getConnection(
        		"jdbc:ucanaccess://C:\\Users\\7000033011\\Maheesha\\java\\ms access\\Car.accdb;openExclusive=false;ignoreCase=true", "",
				"")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Car cars = new Car(id, null, null, 0);
            String sql = "DELETE FROM car where id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, cars.getId());
            statement.executeUpdate();
            statement.close();
            con.close();
            resp.sendRedirect("/list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
