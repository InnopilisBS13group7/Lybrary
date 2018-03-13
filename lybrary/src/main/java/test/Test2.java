/*package test;

import Models.User;
import controllers.BookingController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import controllers.Controller;
import controllers.AdminController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Test2 {

    AdminControllerT ac= new AdminControllerT();
    BookingControllerT bc= new BookingControllerT();

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void tc1() throws SQLException {
        ac.addDocument("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein","book","Publisher: MIT Press.Year: 2009.Edition: Third edition","teg","3","status");
        ac.addDocument("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm","book","Publisher: Addison-Wesley Professional. Year: 2003.Edition: First edition","teg","2","bestseller");
        ac.addDocument("The Mythical Man-month","Brooks,Jr., Frederick P","book","Publisher: Addison-Wesley Longman Publishing Co., Inc.Year: 1995.Edition: Second edition","teg","1","reference");
        ac.addDocument(" Null References: The Billion Dollar Mistake","Tony Hoare","video","","teg","1","status");
        ac.addDocument(" Information Entropy","Claude Shannon","video","","teg","1","status");
        Controller.addNewUserToTheSystemWithStatus("Sergey", "Afonso","sergey@innopolis.ru", "pass","faculty");

        Controller.addNewUserToTheSystemWithStatus("Nadia", "Teixeira","nadira@innopolis.ru", "pass","student");

        Controller.addNewUserToTheSystemWithStatus("Elvira", "Pindosskaya","elvira@innopolis.ru", "pass","student");

        *//*почистить бд или изменить экспектед
        assertEquals(15,controllers.Controller.numberOfUsers());
        assertEquals(15,controllers.Controller.numberOfDocuments());
        *//*
    }

    @Test
    public void tc2() throws SQLException {
        tc1();

        ac.deleteUser(Controller.getUserIdByParameters("Nadia", "Teixeira","student"));
        ac.modifyDocument(Controller.getDocumentIdByParameters("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein","book"),"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein","book","Publisher: MIT Press.Year: 2009.Edition: Third edition","teg","3","status");
        ac.modifyDocument(Controller.getDocumentIdByParameters("The Mythical Man-month","Brooks,Jr., Frederick P","book"),"The Mythical Man-month","Brooks,Jr., Frederick P","book","Publisher: Addison-Wesley Longman Publishing Co., Inc.Year: 1995.Edition: Second edition","teg","0","reference");

         *//* на 1 меньшеб на 2
        assertEquals(15,controllers.Controller.numberOfUsers());
        assertEquals(15,controllers.Controller.numberOfDocuments());
        *//*
    }

    @Test
    public void tc3() throws SQLException {
        tc1();
        List<String> list = new LinkedList<>();List<String> list2 = new LinkedList<>();
        String id0=Controller.getUserIdByParameters("Sergey", "Afonso","faculty");
        String id1=Controller.getUserIdByParameters("Elvira", "Pindosskaya","student");
        list.add(id0);list.add("Sergey");list.add("Afonso");list.add( "sergey@innopolis.ru");list.add("faculty");list.add("0");
        list2.add(id1);list2.add("Elvira");list2.add("Pindosskaya");list2.add("elvira@innopolis.ru");list2.add("student");list2.add("0");
        assertEquals(list,Controller.getUserInfo(Controller.getUserIdByParameters("Sergey", "Afonso","faculty")));
        assertEquals(list2,Controller.getUserInfo(Controller.getUserIdByParameters("Elvira", "Pindosskaya","student")));
    }

    @Test
    public void tc4() throws SQLException {
        tc2();
        List<String> list2 = new LinkedList<>();
        String id1=Controller.getUserIdByParameters("Elvira", "Pindosskaya","student");
        assertEquals(null,Controller.getUserInfo(Controller.getUserIdByParameters("Nadia", "Teixeira","student")));
        list2.add(id1);list2.add("Elvira");list2.add("Pindosskaya");list2.add("elvira@innopolis.ru");list2.add("student");list2.add("0");
        assertEquals(list2,Controller.getUserInfo(Controller.getUserIdByParameters("Elvira", "Pindosskaya","student")));


    }

    @Test
    public void tc5() throws SQLException {
        tc2();
        assertEquals("false",bc.takeItem(Controller.getDocumentIdByParameters("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein","book"),Controller.getCookieFromId(Controller.getUserIdByParameters("Nadia", "Teixeira","student"))));

    }
    */
    /*@Test
    public void tc6() throws SQLException {
        tc2();
    }

    @Test
    public void tc7() throws SQLException {
        tc1();
    }

    @Test
    public void tc8() throws SQLException {
        tc1();
    }


    public void cleanAll() {

    }
}
    */