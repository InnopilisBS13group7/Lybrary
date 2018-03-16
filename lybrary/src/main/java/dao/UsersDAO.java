package dao;

import Models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public User get(int id) throws HibernateException {
        return (User) session.get(User.class, id);
    }

    public int getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return ((User) criteria.add(eq("name", name)).uniqueResult()).getId();
    }

    public List<User> getListOfUsers(String fieldName1,String value1,String fieldName2,String value2) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return ((List<User>) criteria.add(Restrictions.and(eq(fieldName1, value1),eq(fieldName2, value2))).list());
    }

    public List<User> getAll() throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return ((List<User>) criteria.list());
    }

    public List<User> getSpecialUsers() throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return ((List<User>) criteria.add(Restrictions.sqlRestriction("email = 'Simon'")).list());
    }

    public int insertNew(String email, String password, String name, String surname, String cookieId, String status, int fine, String address, String phone) throws HibernateException {
        return (int) session.save(new User (email, password, name, surname, cookieId, status, fine, address, phone));
    }

    public void update(User user) throws HibernateException {
        session.update(user);
    }
}
