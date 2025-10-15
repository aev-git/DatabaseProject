import java.util.Scanner;
import java.util.regex.Pattern;

public final class DatabaseProject {
    private DatabaseProject() {
    }

    public static void main(String args[]) {
        Model model = new Model();
        Controller controller = new Controller(model);
        dummyData(model);

        Scanner in = new Scanner(System.in);
        String userAction = "";
        while (Pattern.matches("[1-8]", userAction) || userAction == "") {
            controller.printOptions();
            System.out.print("Enter option number: ");
            userAction = in.nextLine();
            controller.processOptions(userAction, in);
        }
        in.close();
    }

    private static void dummyData(Model model) {
        model.addOrUpdateEmployee(1000, 55000, "Grunt");
        model.addOrUpdateEmployee(1001, 45000, "Manager");
        model.addOrUpdateEmployee(1002, 155000, "Manager");
        model.addOrUpdateEmployee(1003, 65789, "Grunt");
        model.addOrUpdateMember(1001, "123 Neil Ave", "Johnny", "Hamm", "124 Neil Ave",
                6146146114L, "hamm@osu.edu", "2025/4/17", 1);
        model.addOrUpdateMember(1002, "2173 Glyn Ct", "Mauricio", "Vasquez",
                "677 Ford Way", 2000000000L, "vasquez@osu.edu", "2023/9/17", 15);
        model.addOrUpdateMember(1003, "9 Lane Ave", "Sophia", "Smith", "124 Neil Ave",
                8927368874L, "smith@osu.edu", "2025/9/06", 7);
        model.addOrUpdateMember(1004, "888 Dummy St", "Ryan", "Letourneau",
                "8009 Main St", 1234567890L, "letourneau@osu.edu", "2024/10/01", 20);
        model.addOrUpdateWarehouse("124 Neil Ave", "Columbus", 1234567899L, "Harris",
                3000, 5);
        model.addOrUpdateWarehouse("8009 Main St", "Columbus", 1000000000L, "Harris",
                1500, 2);
        model.addOrUpdateWarehouse("677 Ford Way", "Newark", 7408881111L, "Cassidy", 2500,
                7);
    }

}
