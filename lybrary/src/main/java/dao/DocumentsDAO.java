package dao;

import Models.Document;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;

public class DocumentsDAO {

    private Session session;

    public DocumentsDAO(Session session) {
        this.session = session;
    }

    public Document get(int id) throws HibernateException {
        return (Document) session.get(Document.class, id);
    }
    public int insertNew(String title, String author, String status, int amount, String description, String teg, String type, int year, String publisher, String edition) throws HibernateException {
        return (int) session.save(new Document(title, author, status, amount, description, teg, type, year, publisher, edition));
    }

    public void update(Document document) throws HibernateException {
        session.update(document);
    }

    public List<Document> getAll() throws HibernateException {
        Criteria criteria = session.createCriteria(Document.class);
        List<Document> list = (List<Document>) criteria.list();
        System.out.println(list);
        return list;
    }

    public List<Document> getSpecialSet() throws HibernateException {
        Criteria criteria = session.createCriteria(Document.class);
        return ((List<Document>) criteria.add(Restrictions.sqlRestriction("title = 'smth'")).list());
    }

    public List<Document> getListOfDocuments(String fieldName1, String value1, String fieldName2, String value2) throws HibernateException {
        Criteria criteria = session.createCriteria(Document.class);
        return ((List<Document>) criteria.add(Restrictions.and(eq(fieldName1, value1), eq(fieldName2, value2))).list());
    }
}
