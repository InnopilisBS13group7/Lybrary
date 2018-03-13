package DateBase;


import Models.User;
import dao.UsersDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class DBService {
    private static final String hibernate_show_sql = "false";
    private static final String hibernate_hbm2ddl_auto = "validate";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/itpdb");
        configuration.setProperty("hibernate.connection.serverTimezone", "UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }


    public User getUser(int id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            User dataSet = dao.get(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }


    //IT DOES NOT WORK, BECAUSE IT  RETURNS MORE THEN ONE OBJECT
    /*public User getUser(String name) throws DBException{
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            User dataSet= dao.get(dao.getUserId(name));
            session.close();
            return dataSet;
        } catch (HibernateException e){
            throw new DBException(e);
        }
    }*/

    public int insertNewUser(String email, String password, String name, String surname, String cookieId, String status, int fine, String address, String phone) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            int id = dao.insertNewUser(email, password, name, surname, cookieId, status, fine, address, phone);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void updateUser(User user) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            dao.updateUser(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<User> getListOfUsers(String fieldName1,String value1,String fieldName2,String value2) throws DBException{
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            List<User> dataSet= dao.getListOfUsers(fieldName1, value1,fieldName2, value2);
            session.close();
            return dataSet;
        } catch (HibernateException e){
            throw new DBException(e);
        }
    }

    public List<User> getAllUsers() throws DBException{
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            List<User> dataSet= dao.getAllUsers();
            session.close();
            return dataSet;
        } catch (HibernateException e){
            throw new DBException(e);
        }
    }

    public List<User> getSpecialUsers() throws DBException{
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            List<User> dataSet= dao.getSpecialUsers();
            session.close();
            return dataSet;
        } catch (HibernateException e){
            throw new DBException(e);
        }
    }


    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
