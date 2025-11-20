import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    Connection conn = null;

    public void addOrUpdateWarehouse(String address, String city, long phoneNumber,
            String managerName, int storageCapacity, int droneCapacity) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Warehouse WHERE Address=?");
            ps.setString(1, address);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = this.conn.prepareStatement(
                        "INSERT INTO Warehouse VALUES (?, ?, ?, ?, ?, ?)");
                ps.setString(1, address);
                ps.setString(2, city);
                ps.setLong(3, phoneNumber);
                ps.setString(4, managerName);
                ps.setInt(5, storageCapacity);
                ps.setInt(6, droneCapacity);
            } else {
                ps = this.conn.prepareStatement("UPDATE Warehouse SET City = ?, "
                        + "Phone = ?, Manager = ?, EquipCap = ?, DroneCap = ? "
                        + " WHERE Address = ?");
                ps.setString(1, city);
                ps.setLong(2, phoneNumber);
                ps.setString(3, managerName);
                ps.setInt(4, storageCapacity);
                ps.setInt(5, droneCapacity);
                ps.setString(6, address);
            }
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addOrUpdateMember(int userID, String warehouseAddress, String firstName,
            String lastName, String address, long phoneNumber, String email,
            String startDate, int warehouseDistance) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Members WHERE UserID=?");
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = this.conn.prepareStatement(
                        "INSERT INTO Members VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, userID);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, address);
                ps.setLong(5, phoneNumber);
                ps.setString(6, email);
                ps.setDate(7, java.sql.Date.valueOf(startDate));
                ps.setInt(8, warehouseDistance);
                ps.setString(9, warehouseAddress);
            } else {
                ps = this.conn.prepareStatement("UPDATE Members SET FirstName = ?, "
                        + "LastName = ?, Address = ?, PhoneNo = ?, Email = ?, "
                        + "StartDate = ?, WarehouseDist = ?, WarehouseAddr = ?"
                        + " WHERE UserID = ?");
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, address);
                ps.setLong(4, phoneNumber);
                ps.setString(5, email);
                ps.setDate(6, java.sql.Date.valueOf(startDate));
                ps.setInt(7, warehouseDistance);
                ps.setString(8, warehouseAddress);
                ps.setInt(9, userID);
            }
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addOrUpdateEmployee(int empID, String position) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Employee WHERE EmpID=?");
            ps.setInt(1, empID);
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = this.conn.prepareStatement("INSERT INTO Employee VALUES (?, ?)");
                ps.setInt(1, empID);
                ps.setString(2, position);
            } else {
                ps = this.conn.prepareStatement(
                        "UPDATE Employee SET Position = ? WHERE EmpID = ?");
                ps.setString(1, position);
                ps.setInt(2, empID);
            }
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteWarehouse(String address) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement("DELETE FROM Warehouse WHERE Address = ?");
            ps.setString(1, address);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMember(int userID) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement("DELETE FROM Members WHERE UserID = ?");
            ps.setInt(1, userID);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteEmployee(int empID) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement("DELETE FROM Employee WHERE empID = ?");
            ps.setInt(1, empID);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchWarehouse(String address) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Warehouse WHERE Address = ?");
            ps.setString(1, address);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Address: " + rs.getString("Address") + "\nCity: "
                        + rs.getString("City") + "\nPhone: " + rs.getString("Phone")
                        + "\nManager: " + rs.getString("Manager")
                        + "\nEquipment Capacity: " + rs.getString("EquipCap")
                        + "\nDrone Capacity: " + rs.getString("DroneCap") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchMember(int userID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Members WHERE UserID = ?");
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("UserID: " + rs.getString("UserID") + "\n"
                        + "FirstName: " + rs.getString("FirstName") + "\n" + "LastName: "
                        + rs.getString("LastName") + "\n" + "Address: "
                        + rs.getString("Address") + "\n" + "Phone Number: "
                        + rs.getString("PhoneNo") + "\n" + "Email: "
                        + rs.getString("Email") + "\n" + "Start Date: "
                        + rs.getString("StartDate") + "\n" + "Warehouse Address: "
                        + rs.getString("WarehouseAddr") + "\n" + "Warehouse Distance: "
                        + rs.getString("WarehouseDist") + "\n");

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchEmployee(int empID) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement("SELECT * FROM Employee WHERE EmpID = ?");
            ps.setInt(1, empID);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("\nEmpID: " + rs.getInt("EmpID") + "\nPosition: "
                        + rs.getString("Position") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

}
