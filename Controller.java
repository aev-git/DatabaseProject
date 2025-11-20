import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {

    public Controller() {
    }

    public Controller(Model m) {
        this.model = m;
    }

    private Model model;

    public void printOptions() {
        System.out.println("1) Add or update record\n" + "2) Delete record\n"
                + "3) Search for record\n" + "4) Rent equipment\n"
                + "5) Return equipment\n" + "6) Equipment delivery\n"
                + "7) Equipment pickup\n" + "8) Reports\n" + "Other) Quit program\n");
    }

    public void processOptions(String option, Scanner in) {
        switch (option) {
            case "1":
                this.addMenu(in);
                break;
            case "2":
                this.deleteMenu(in);
                break;
            case "3":
                this.searchMenu(in);
                break;

            case "4":
                this.rentEquipment(in);
                break;
            case "5":
                this.returnEquipment(in);
                break;
            case "6":
                this.equipmentDelivery(in);
                break;
            case "7":
                this.equipmentPickup(in);
                break;
            case "8":
                this.reports(in);
                break;
            default:
                break;
        }
    }

    private void searchMenu(Scanner in) {
        String userAction = "";
        System.out.println("1) Warehouses\n2) Members\n3) Employees\n");
        System.out.print("Enter option number: ");
        userAction = in.nextLine();
        switch (userAction) {
            case "1":
                this.searchWarehouse(in);
                break;
            case "2":
                this.searchMember(in);
                break;
            case "3":
                this.searchEmployee(in);
                break;
        }
    }

    private void deleteMenu(Scanner in) {
        String userAction = "";
        System.out.println("1) Warehouses\n2) Members\n3) Employees\n");
        System.out.print("Enter option number: ");
        userAction = in.nextLine();
        switch (userAction) {
            case "1":
                this.deleteWarehouse(in);
                break;
            case "2":
                this.deleteMember(in);
                break;
            case "3":
                this.deleteEmployee(in);
                break;
            default:
                break;
        }
    }

    private void addMenu(Scanner in) {
        String userAction = "";
        System.out.println("1) Warehouses\n2) Members\n3) Employees\n");
        System.out.print("Enter option number: ");
        userAction = in.nextLine();
        switch (userAction) {
            case "1":
                this.addWarehouse(in);
                break;
            case "2":
                this.addMember(in);
                break;
            case "3":
                this.addEmployee(in);
                break;
            default:
                break;
        }
    }

    private void addEmployee(Scanner in) {
        System.out.println("Enter employee id: ");
        int empID = 0;
        try {
            empID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println("Enter employee position: ");
        String position = this.cleanStringOrEmpty(in.nextLine());
        this.model.addOrUpdateEmployee(empID, position);

    }

    private void addMember(Scanner in) {
        System.out.println("Enter member user id: ");
        int userID = 0;
        try {
            userID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println("Enter member warehouse address: ");
        String warehouseAddress = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member first name: ");
        String firstName = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member last name: ");
        String lastName = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member address: ");
        String address = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member phone number: ");
        long phoneNumber = 0L;
        try {
            phoneNumber = Long.parseLong(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println("Enter member email: ");
        String email = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member start date (yyyy-MM-dd): ");
        String startDate = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter member warehouse distance: ");
        int warehouseDistance = 0;
        try {
            warehouseDistance = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        this.model.addOrUpdateMember(userID, warehouseAddress, firstName, lastName,
                address, phoneNumber, email, startDate, warehouseDistance);
    }

    private void addWarehouse(Scanner in) {
        System.out.println("Enter warehouse address: ");
        String address = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter warehouse city: ");
        String city = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter warehouse phone number: ");
        long phoneNumber = 0L;
        try {
            phoneNumber = Long.parseLong(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println("Enter warehouse manager name: ");
        String managerName = this.cleanStringOrEmpty(in.nextLine());
        System.out.println("Enter warehouse storage capacity: ");
        int storageCapacity = 0;
        try {
            storageCapacity = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println("Enter warehouse drone capacity: ");
        int droneCapacity = 0;
        try {
            droneCapacity = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        this.model.addOrUpdateWarehouse(address, city, phoneNumber, managerName,
                storageCapacity, droneCapacity);
    }

    private void deleteEmployee(Scanner in) {
        System.out.println("Enter employee id to delete: ");
        int empID = 0;
        try {
            empID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out.println(
                "Are you sure you want to delete employee #" + empID + "? (y/N)");
        String response = in.nextLine();
        if (response.equals("y") || response.equals("Y")) {
            this.model.deleteEmployee(empID);
        }
    }

    private void deleteMember(Scanner in) {
        System.out.println("Enter member id to delete: ");
        int userID = 0;
        try {
            userID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        System.out
                .println("Are you sure you want to delete member #" + userID + "? (y/N)");
        String response = in.nextLine();
        if (response.equals("y") || response.equals("Y")) {
            this.model.deleteMember(userID);
        }
    }

    private void deleteWarehouse(Scanner in) {
        System.out.println("Enter warehouse address to delete: ");
        String addr = this.cleanStringOrEmpty(in.nextLine());
        System.out.println(
                "Are you sure you want to delete warehouse at " + addr + "? (y/N)");
        String response = in.nextLine();
        if (response.equals("y") || response.equals("Y")) {
            this.model.deleteWarehouse(addr);
        }
    }

    private void searchEmployee(Scanner in) {
        System.out.println("Enter employee id to search for: ");
        int empID = 0;
        try {
            empID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        this.model.searchEmployee(empID);
    }

    private void searchMember(Scanner in) {
        System.out.println("Enter member id to search for: ");
        int userID = 0;
        try {
            userID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        this.model.searchMember(userID);
    }

    private void searchWarehouse(Scanner in) {
        System.out.println("Enter warehouse address to search for: ");
        String addr = in.nextLine();
        this.model.searchWarehouse(addr);
    }

    private void rentEquipment(Scanner in) {
        System.out.println("Enter customer id: ");
        int userID = 0;
        try {
            userID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        // find equipment customer wants in warehouse
        // create rent entity
        System.out.println("Equipment rented");
    }

    private void returnEquipment(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = 0;
        try {
            rentalID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        //get rent entity
        // assign equipment rented to warehouse
        // update warehouse inventory

        System.out.println("Equipment returned");
    }

    private void equipmentDelivery(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = 0;
        try {
            rentalID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        // get rent entity
        // find next available drone from warehouse
        // update warehouse inventory
    }

    private void equipmentPickup(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = 0;
        try {
            rentalID = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        // get rent entity
        // find next available drone from warehouse
        // update warehouse inventory
    }

    private void reports(Scanner cin) {
        System.out.println("\n=== USEFUL REPORTS ===");
        System.out.println("1. Total rentals by a member");
        System.out.println("2. Most popular item");
        System.out.println("3. Most popular manufacturer");
        System.out.println("4. Most used drone");
        System.out.println("5. Member with most rentals");
        System.out.println("6. Equipment by type released before a year");
        System.out.print("Enter choice (1-6): ");

        int choice = cin.nextInt();
        cin.nextLine();

        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter Member UserID: ");
                    String userID = cin.nextLine();
                    ps = this.model.conn.prepareStatement(
                            "SELECT COUNT(*) AS TotalRentals FROM Rent WHERE UserID = ?");
                    ps.setString(1, userID);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("Total Rentals: " + rs.getInt("TotalRentals"));
                    }
                    break;
                case 2:
                    stmt = this.model.conn.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT SerialNumber, COUNT(*) AS TimesRented, SUM(RunningTime) AS TotalTime "
                                    + "FROM Rent GROUP BY SerialNumber ORDER BY SUM(RunningTime) DESC LIMIT 1");
                    if (rs.next()) {
                        System.out
                                .println("SerialNumber: " + rs.getString("SerialNumber"));
                        System.out.println("Times Rented: " + rs.getInt("TimesRented"));
                        System.out
                                .println("Total Running Time: " + rs.getInt("TotalTime"));
                    }
                    break;
                case 3:
                    stmt = this.model.conn.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT Equipment.Manufacturer, COUNT(*) AS TotalRented "
                                    + "FROM Rent JOIN Equipment ON Rent.SerialNumber = Equipment.SerialNo "
                                    + "GROUP BY Equipment.Manufacturer ORDER BY COUNT(*) DESC LIMIT 1");
                    if (rs.next()) {
                        System.out
                                .println("Manufacturer: " + rs.getString("Manufacturer"));
                        System.out.println("Total Rented: " + rs.getInt("TotalRented"));
                    }
                    break;
                case 4:
                    stmt = this.model.conn.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT Drone.SerialNo, Drone.MilesFlown, COUNT(*) AS Deliveries "
                                    + "FROM Drone JOIN Equipment ON Equipment.DroneSN = Drone.SerialNo "
                                    + "GROUP BY Drone.SerialNo, Drone.MilesFlown ORDER BY COUNT(*) DESC LIMIT 1");
                    if (rs.next()) {
                        System.out.println("Drone SerialNo: " + rs.getString("SerialNo"));
                        System.out.println("Miles Flown: " + rs.getInt("MilesFlown"));
                        System.out.println("Deliveries: " + rs.getInt("Deliveries"));
                    }
                    break;
                case 5:
                    stmt = this.model.conn.createStatement();
                    rs = stmt.executeQuery(
                            "SELECT Members.FirstName, Members.LastName, COUNT(*) AS TotalRentals "
                                    + "FROM Members JOIN Rent ON Members.UserID = Rent.UserID "
                                    + "GROUP BY Members.UserID ORDER BY COUNT(*) DESC LIMIT 1");
                    if (rs.next()) {
                        System.out.println("Member: " + rs.getString("FirstName") + " "
                                + rs.getString("LastName"));
                        System.out.println("Total Rentals: " + rs.getInt("TotalRentals"));
                    }
                    break;
                case 6:
                    System.out.print("Enter year: ");
                    int year = cin.nextInt();
                    cin.nextLine();
                    ps = this.model.conn.prepareStatement(
                            "SELECT Type, Manufacturer, YearManufactured FROM Equipment WHERE YearManufactured < ?");
                    ps.setInt(1, year);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.println("Type: " + rs.getString("Type"));
                        System.out
                                .println("Manufacturer: " + rs.getString("Manufacturer"));
                        System.out.println("Year: " + rs.getInt("YearManufactured"));
                        System.out.println("--------------------");
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (SQLException e) {
            System.out.println("Error executing report: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ignored) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ignored) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignored) {
            }
        }
    }

    private String cleanStringOrEmpty(String input) {
        return (Pattern.matches("[A-z0-9 ]", input)) ? input : "";
    }
}
