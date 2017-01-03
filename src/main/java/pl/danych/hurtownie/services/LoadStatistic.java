package pl.danych.hurtownie.services;


public class LoadStatistic {

    private int productsAdded;
    private int commentsAdded;
    private int remarksAdded;
    private int commentsDeleted;

    public void addingProducts(int number){
        productsAdded += number;
    }

    public void addingComments(int number){
        commentsAdded += number;
    }

    public void deletingComments(int number){
        commentsDeleted += number;
    }

    public void addingRemarks(int number){
        remarksAdded += number;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        if(productsAdded > 0)
            result.append(productsAdded).append(" Product loaded to database\n");
        if(commentsDeleted > 0)
            result.append(commentsDeleted).append(" Comments deleted from database\n");
        if(commentsAdded > 0 )
            result.append(commentsAdded).append((commentsAdded > 1) ? " Comments" : " Comment").append(" loaded\n");
        if(remarksAdded > 0)
            result.append(remarksAdded).append(" Remarks loaded\n");
        if(result.toString().equals(""))
            result.append("All is up to date");

        productsAdded = commentsAdded = remarksAdded = commentsDeleted = 0;
        return result.toString();
    }


}
