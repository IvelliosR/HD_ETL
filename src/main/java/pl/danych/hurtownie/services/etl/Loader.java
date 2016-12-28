package pl.danych.hurtownie.services.etl;

import pl.danych.hurtownie.hibernate.dao.EntityDao;
import pl.danych.hurtownie.hibernate.utils.HibernateUtils;
import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 22.12.2016.
 */
public class Loader {
    private static EntityDao manager;
    private List<Comment> toDelete;

    public Loader(){
        manager = new EntityDao();
    }

    public String loadToDatabase(Product product){
        manager.openCurrentSessionwithTransaction();

        persistOrUpdate(product);

        manager.closeCurrentSessionWithTransaction();
        HibernateUtils.closeSessionFactory();

        //// TODO: 24.12.2016 zwrocic dane statystyczne
        return null;
    }

    private void persistOrUpdate(Product toSave){
        Product equivalent;
        if((equivalent = isInDatabase(toSave)) != null){
            updateInDatabase(toSave, equivalent);
        }
        else
            saveToDatabase(toSave);
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
    }

    private void addNewComments(Product toSave, Product fromDatabase) {
        fromDatabase.getComments().addAll(findCommentsToAdd(toSave, fromDatabase));
        manager.update(fromDatabase);

    }

    private List<Comment> findCommentsToAdd(Product toSave, Product fromDatabase) {
        List<Comment> toAdd = new ArrayList<>();
        toAdd.addAll(toSave.getComments());
        toAdd.removeAll(fromDatabase.getComments());
        return toAdd;
    }

    private void findCommentsToDelete(Product toSave, Product fromDatabase) {
        toDelete = new ArrayList<>();
        toDelete.addAll(fromDatabase.getComments());
        toDelete.removeAll(toSave.getComments());
    }

    private void saveToDatabase(Product toSave) {
        manager.persist(toSave);
        //// TODO: 24.12.2016 dodac dane statystyczne na tamat save
    }


    private Product isInDatabase(Product toSave){
        List<Product> products = manager.findAll();

        for(Product p: products)
            if(toSave.equals(p))
                return p;

        return null;
    }


}
