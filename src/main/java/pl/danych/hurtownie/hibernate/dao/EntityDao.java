package pl.danych.hurtownie.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.danych.hurtownie.hibernate.utils.HibernateUtils;
import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;

import java.util.List;

/**
 * Created by Arek on 22.12.2016.
 */
public class EntityDao implements EntityDaoInterface<Product, Comment> {

    private Session currentSession;
    private Transaction currentTransaction;

    @Override
    public void persist(Product entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Product entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Product findById(int id) {
        Product product = getCurrentSession().get(Product.class, id);
        return product;
    }

    @Override
    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        Criteria cr = getCurrentSession().createCriteria(Product.class);
         return (List<Product>) cr.list();
    }

    @Override
    public void deleteAll() {
        List<Product> products = findAll();
        products.forEach(this::delete);
    }

    @Override
    public void deleteAll(List<Comment> entities) {
       for(Comment comment: entities)
           getCurrentSession().delete(comment);
    }

    public Session openCurrentSession(){
        currentSession = HibernateUtils.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction(){
        currentSession = HibernateUtils.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession(){
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }
    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


}
