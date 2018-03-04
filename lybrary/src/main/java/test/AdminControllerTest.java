package test;

import controllers.AdminController;
import controllers.Controller;
import org.junit.After;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminControllerTest  {


    @org.junit.Test
    public void addDocument() throws SQLException {
        String pattern = "TestBook";
        assertEquals("true",AdminController.addDocument("1",pattern,pattern,pattern,pattern,"1",pattern));

        assertEquals("false",AdminController.addDocument("1",pattern,pattern,pattern,pattern,"1",pattern));

        AdminController.deleteDocumentByParameters(pattern,pattern,pattern);

    }

    @org.junit.Test
    public void modifyDocument() throws Exception {
        String pattern = "TestBook";
        String id = "11223412";

        assertEquals("false",AdminController.modifyDocument(id,pattern,pattern,pattern,pattern,pattern,"1",pattern));
        AdminController.addDocument(pattern,pattern,pattern,pattern,pattern,"1",pattern);
        assertEquals("true",AdminController.modifyDocument(Controller.getDocumentIdByParameters(pattern,pattern,pattern),pattern,pattern,pattern,pattern,pattern,"5",pattern));

        assertEquals("true",AdminController.deleteDocumentById(Controller.getDocumentIdByParameters(pattern,pattern,pattern)));

    }

    @org.junit.Test
    public void modifyUser() {


    }

    @org.junit.Test
    public void deleteUser() {
    }

    @org.junit.Test
    public void closeOrder() {
    }
}