package pl.danych.hurtownie.hibernate.dao;

import org.hibernate.*;
import pl.danych.hurtownie.hibernate.utils.HibernateUtils;
import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;

import java.util.List;

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
    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        Criteria cr = getCurrentSession().createCriteria(Product.class);
         return (List<Product>) cr.list();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> findAllComments() {
        Criteria cr = getCurrentSession().createCriteria(Comment.class);
        return (List<Comment>) cr.list();
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
