package dao;

import Models.Document;
import Models.Order;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class OrdersDAO {

    private Session session;

    public OrdersDAO(Session session) {
        this.session = session;
    }

    public Order get(int id) throws HibernateException {
        return (Order) session.load(Order.class, id);
    }

    public int insertNew(int userId, int itemId, long startTime, long finishTime, String status) throws HibernateException {
        return (int) session.save(new Order(userId, itemId, startTime, finishTime, status));
    }

    public void update(Order document) throws HibernateException {
        session.update(document);
    }

    public List<Order> getAll() throws HibernateException {
        Criteria criteria = session.createCriteria(Order.class);
        List<Order> list = (List<Order>) criteria.list();
        System.out.println(list);
        return list;
    }

    public List<Order> getSpecialSet(String sqlCondition) throws HibernateException {
        Criteria criteria = session.createCriteria(Order.class);
        return ((List<Order>) criteria.add(Restrictions.sqlRestriction(sqlCondition)).list());
    }

    public List<Order> getListOfOrders(String fieldName1, String value1, String fieldName2, String value2) throws HibernateException {
        Criteria criteria = session.createCriteria(Order.class);
        return ((List<Order>) criteria.add(Restrictions.and(eq(fieldName1, value1), eq(fieldName2, value2))).list());
    }
}
