package pl.danych.hurtownie.test;

import pl.danych.hurtownie.objects.Comment;

/**
 * Created by Arek on 20.12.2016.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Test");

        Comment comment = new Comment();
        comment.setAuthor("asdfasdf");
        comment.setRate(1.23f);
        comment.setSummary("very good");

        System.out.println(comment);
    }
}
