package pl.danych.hurtownie.services.etl;

import pl.danych.hurtownie.hibernate.dao.EntityDao;
import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;
import pl.danych.hurtownie.services.LoadStatistic;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static EntityDao manager;
    private List<Comment> toDelete;
    private LoadStatistic statistic;

    public Loader(){
        manager = new EntityDao();
        statistic = new LoadStatistic();
    }

    public void loadToDatabase(Product product){
        manager.openCurrentSessionwithTransaction();
        persistOrUpdate(product);
        manager.closeCurrentSessionWithTransaction();
    }

    private void persistOrUpdate(Product toSave){
        Product equivalent;
        if((equivalent = isInDatabase(toSave)) != null){
            updateInDatabase(toSave, equivalent);
        }
        else
            saveToDatabase(toSave);
    }

    private Product isInDatabase(Product toSave){
        List<Product> products = manager.findAll();

        for(Product p: products)
            if(toSave.equals(p))
                return p;

        return null;
    }

    private void updateInDatabase(Product toSave, Product fromDatabase) {
        findCommentsToDelete(toSave, fromDatabase);
        addNewComments(toSave, fromDatabase);
        deleteOldComments(fromDatabase);
    }

    private void deleteOldComments(Product fromDatabase) {
        fromDatabase.getComments().removeAll(toDelete);
        manager.deleteAll(toDelete);
        manager.update(fromDatabase);
        statistic.deletingComments(toDelete.size());
    }

    private void addNewComments(Product toSave, Product fromDatabase) {
        fromDatabase.getComments().addAll(findCommentsToAdd(toSave, fromDatabase));
        manager.update(fromDatabase);

    }

    private List<Comment> findCommentsToAdd(Product toSave, Product fromDatabase) {
        List<Comment> toAdd = new ArrayList<>();
        toAdd.addAll(toSave.getComments());
        toAdd.removeAll(fromDatabase.getComments());
        statistic.addingComments(toAdd.size());
        return toAdd;
    }

    private void findCommentsToDelete(Product toSave, Product fromDatabase) {
        toDelete = new ArrayList<>();
        toDelete.addAll(fromDatabase.getComments());
        toDelete.removeAll(toSave.getComments());
    }

    private void saveToDatabase(Product toSave) {
        manager.persist(toSave);
        statistic.addingProducts(1);
        statistic.addingComments(toSave.countComments());
        statistic.addingRemarks(toSave.countRemarks());
    }

    public LoadStatistic getStatistic(){
        return statistic;
    }

    public void deleteAllComments(){
        manager.openCurrentSessionwithTransaction();
        List<Comment> toDelete = manager.findAllComments();
        manager.deleteAll(toDelete);
        statistic.deletingComments(toDelete.size());
        manager.closeCurrentSessionWithTransaction();
    }

    public List<Product> getAllProducts(){
        List<Product> allProducts;
        manager.openCurrentSessionwithTransaction();
        allProducts = manager.findAll();
        manager.closeCurrentSessionWithTransaction();
        return allProducts;
    }


}
