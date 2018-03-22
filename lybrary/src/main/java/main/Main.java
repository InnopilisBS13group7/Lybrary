package main;

import DateBase.DBException;
import DateBase.DBService;
import controllers.Controller;

public class Main {
    public static void main(String[] args) {
//        DBService dbService = new DBService();
//        dbService.printConnectInfo();

        try {
//
            System.out.println(Controller.getAllOrders());

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
