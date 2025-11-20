import java.util.Scanner;

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
        }
    }

    private void addEmployee(Scanner in) {
        System.out.println("Enter employee id: ");
        int empID = Integer.parseInt(in.nextLine());
        System.out.println("Enter employee position: ");
        String position = in.nextLine();
        this.model.addOrUpdateEmployee(empID, position);

    }

    private void addMember(Scanner in) {
        System.out.println("Enter member user id: ");
        int userID = Integer.parseInt(in.nextLine());
        System.out.println("Enter member warehouse address: ");
        String warehouseAddress = in.nextLine();
        System.out.println("Enter member first name: ");
        String firstName = in.nextLine();
        System.out.println("Enter member last name: ");
        String lastName = in.nextLine();
        System.out.println("Enter member address: ");
        String address = in.nextLine();
        System.out.println("Enter member phone number: ");
        long phoneNumber = Long.parseLong(in.nextLine());
        System.out.println("Enter member email: ");
        String email = in.nextLine();
        System.out.println("Enter member start date: ");
        String startDate = in.nextLine();
        System.out.println("Enter member warehouse distance: ");
        int warehouseDistance = Integer.parseInt(in.nextLine());

        this.model.addOrUpdateMember(userID, warehouseAddress, firstName, lastName,
                address, phoneNumber, email, startDate, warehouseDistance);
    }

    private void addWarehouse(Scanner in) {
        System.out.println("Enter warehouse address: ");
        // assuming all input is valid for now
        String address = in.nextLine();
        System.out.println("Enter warehouse city: ");
        String city = in.nextLine();
        System.out.println("Enter warehouse phone number: ");
        long phoneNumber = Long.parseLong(in.nextLine());
        System.out.println("Enter warehouse manager name: ");
        String managerName = in.nextLine();
        System.out.println("Enter warehouse storage capacity: ");
        int storageCapacity = Integer.parseInt(in.nextLine());
        System.out.println("Enter warehouse drone capacity: ");
        int droneCapacity = Integer.parseInt(in.nextLine());
        this.model.addOrUpdateWarehouse(address, city, phoneNumber, managerName,
                storageCapacity, droneCapacity);
    }

    private void deleteEmployee(Scanner in) {
        System.out.println("Enter employee id to delete: ");
        int empID = Integer.parseInt(in.nextLine());
        if (!this.model.employees.containsKey(empID)) {
            System.out.println("Error: employee not found.");
        } else {
            System.out.println(
                    "Are you sure you want to delete employee #" + empID + "? (y/N)");
            String response = in.nextLine();
            if (response.equals("y") || response.equals("Y")) {
                this.model.deleteEmployee(empID);
            }
        }
    }

    private void deleteMember(Scanner in) {
        System.out.println("Enter member id to delete: ");
        int userID = Integer.parseInt(in.nextLine());
        if (!this.model.members.containsKey(userID)) {
            System.out.println("Error: member not found.");
        } else {
            System.out.println(
                    "Are you sure you want to delete member #" + userID + "? (y/N)");
            String response = in.nextLine();
            if (response.equals("y") || response.equals("Y")) {
                this.model.deleteMember(userID);
            }
        }
    }

    private void deleteWarehouse(Scanner in) {
        System.out.println("Enter warehouse address to delete: ");
        String addr = in.nextLine();
        if (!this.model.warehouses.containsKey(addr)) {
            System.out.println("Error: warehouse not found.");
        } else {
            System.out.println(
                    "Are you sure you want to delete warehouse at " + addr + "? (y/N)");
            String response = in.nextLine();
            if (response.equals("y") || response.equals("Y")) {
                this.model.deleteWarehouse(addr);
            }
        }
    }

    private void searchEmployee(Scanner in) {
        System.out.println("Enter employee id to search for: ");
        int empID = Integer.parseInt(in.nextLine());
        Model.Employee e = this.model.searchEmployee(empID);
        if (e.empID == 0) {
            System.out.println("Employee not found.");
        } else {
            System.out.println(e);
        }
    }

    private void searchMember(Scanner in) {
        System.out.println("Enter member id to search for: ");
        int userID = Integer.parseInt(in.nextLine());
        Model.Member m = this.model.searchMember(userID);
        if (m.userID == 0) {
            System.out.println("Member not found.");
        } else {
            System.out.println(m);
        }
    }

    private void searchWarehouse(Scanner in) {
        System.out.println("Enter warehouse address to search for: ");
        String addr = in.nextLine();
        Model.Warehouse w = this.model.searchWarehouse(addr);
        if (w.address.equals("")) {
            System.out.println("Warehouse not found.");
        } else {
            System.out.println(w);
        }
    }

    private void rentEquipment(Scanner in) {
        System.out.println("Enter customer id: ");
        int userID = Integer.parseInt(in.nextLine());
        Model.Member m = this.model.searchMember(userID);
        Model.Warehouse w = this.model.searchWarehouse(m.warehouseAddress);
        // find equipment customer wants in warehouse
        // create rent entity
        System.out.println("Equipment rented");
    }

    private void returnEquipment(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = Integer.parseInt(in.nextLine());
        //get rent entity
        // assign equipment rented to warehouse
        // update warehouse inventory

        System.out.println("Equipment returned");
    }

    private void equipmentDelivery(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = Integer.parseInt(in.nextLine());
        // get rent entity
        // find next available drone from warehouse
        // update warehouse inventory
    }

    private void equipmentPickup(Scanner in) {
        System.out.println("Enter rental id: ");
        int rentalID = Integer.parseInt(in.nextLine());
        // get rent entity
        // find next available drone from warehouse
        // update warehouse inventory
    }

    private void reports(Scanner in) {
    }

}
