import java.util.HashMap;
import java.util.Map;

public class Model {
    class Warehouse {
        String address; // primary key
        String city;
        long phoneNumber;
        String managerName;
        int storageCapacity;
        int droneCapacity;

        protected Warehouse() {
            this.address = "";
            this.city = "";
            this.phoneNumber = 0;
            this.managerName = "";
            this.storageCapacity = 0;
            this.droneCapacity = 0;
        }

        protected Warehouse(String address, String city, long phoneNumber,
                String managerName, int storageCapacity, int droneCapacity) {
            this.address = address;
            this.city = city;
            this.phoneNumber = phoneNumber;
            this.managerName = managerName;
            this.storageCapacity = storageCapacity;
            this.droneCapacity = droneCapacity;
        }

        @Override
        public String toString() {
            return "address: " + this.address + "\ncity: " + this.city
                    + "\nphone number: " + this.phoneNumber + "\nmanager name: "
                    + this.managerName + "\nstorage capacity: " + this.storageCapacity
                    + "\ndrone capacity: " + this.droneCapacity;
        }
    }

    class Member {
        int userID; // primary key
        String warehouseAddress; // foreign key
        String firstName;
        String lastName;
        String address;
        long phoneNumber;
        String email;
        String startDate; // will be handled better in the database
        int warehouseDistance;

        protected Member() {
            this.userID = 0;
            this.warehouseAddress = "";
            this.firstName = "";
            this.lastName = "";
            this.address = "";
            this.phoneNumber = 0;
            this.email = "";
            this.startDate = "";
            this.warehouseDistance = 0;
        }

        protected Member(int userID, String warehouseAddress, String firstName,
                String lastName, String address, long phoneNumber, String email,
                String date, int warehouseDistance) {
            this.userID = userID;
            this.warehouseAddress = warehouseAddress;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.startDate = date;
            this.warehouseDistance = warehouseDistance;
        }

        @Override
        public String toString() {
            return "userID: " + this.userID + "\nwarehouse address: "
                    + this.warehouseAddress + "\nname: " + this.firstName + " "
                    + this.lastName + "\naddress" + this.address + "\nemail: "
                    + this.email + "\nstart date: " + this.startDate
                    + "\nwarehouse distance: " + this.warehouseDistance + "km";
        }
    }

    class Employee {
        int empID; // primary key
        float salary;
        String position;

        protected Employee() {
            this.empID = 0;
            this.salary = (float) 0.0;
            this.position = "";
        }

        protected Employee(int empID, float salary, String position) {
            this.empID = empID;
            this.salary = salary;
            this.position = position;
        }

        @Override
        public String toString() {
            return "employee id: " + this.empID + "\nsalary: $" + this.salary
                    + "\nposition: " + this.position;
        }
    }

    Map<String, Warehouse> warehouses = new HashMap<String, Warehouse>();
    Map<Integer, Member> members = new HashMap<Integer, Member>();
    Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

    public void addOrUpdateWarehouse(String address, String city, long phoneNumber,
            String managerName, int storageCapacity, int droneCapacity) {
        this.warehouses.put(address, new Warehouse(address, city, phoneNumber,
                managerName, storageCapacity, droneCapacity));
    }

    public void addOrUpdateMember(int userID, String warehouseAddress, String firstName,
            String lastName, String address, long phoneNumber, String email,
            String startDate, int warehouseDistance) {
        this.members.put(userID, new Member(userID, warehouseAddress, firstName, lastName,
                address, phoneNumber, email, startDate, warehouseDistance));
    }

    public void addOrUpdateEmployee(int empID, float salary, String position) {
        this.employees.put(empID, new Employee(empID, salary, position));
    }

    public void deleteWarehouse(String address) {
        this.warehouses.remove(address);
    }

    public void deleteMember(int userID) {
        this.members.remove(userID);
    }

    public void deleteEmployee(int empID) {
        this.employees.remove(empID);
    }

    public Warehouse searchWarehouse(String address) {
        if (!this.warehouses.containsKey(address)) {
            return new Warehouse();
        }
        return this.warehouses.get(address);
    }

    public Member searchMember(int userID) {
        if (!this.members.containsKey(userID)) {
            return new Member();
        }
        return this.members.get(userID);
    }

    public Employee searchEmployee(int empID) {
        if (!this.employees.containsKey(empID)) {
            return new Employee();
        }
        return this.employees.get(empID);
    }
}
