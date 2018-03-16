package main;

import DateBase.DBException;
import DateBase.DBService;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
//        dbService.printConnectInfo();

        try {
//            int userId = dbService.addUser("Simon");
//            dbService.updateUser("Simon");
//            System.out.println("Added user id: " + userId);
//

//            for (User u: dbService.getListOfUsers("surname", "SimonUPD","email","Simon WTttt")) {
//                u.setSurname(u.getSurname()+":"+u.getId());
//                dbService.updateUser(u);
//                System.out.println("User data set: " + u);
//            }
            System.out.println(dbService.getAllOrders());

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
