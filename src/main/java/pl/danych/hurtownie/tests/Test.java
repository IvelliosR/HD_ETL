package pl.danych.hurtownie.tests;

import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;
import pl.danych.hurtownie.objects.Remark;
import pl.danych.hurtownie.services.etl.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 20.12.2016.
 */
public class Test {
    public static void main(String[] args) {
        
        //// TODO: 22.12.2016 add dao classes and services and tests
        
        testDataSaving();
//        testLoad();
    }

    private static void testLoad() {
        Comment c1 = new Comment();
        c1.setAuthor("Gal Anonim");
        c1.setRate(12.32f);
        c1.setSummary("Bardzo dobry");
        c1.setCreateDate("2016-03-12");
        c1.setHelpful(1);
        c1.setUnhelpful(12);
        c1.setRecommend(true);
        ArrayList<String> cons1 = new ArrayList<>();
        cons1.add("Tani");
        cons1.add("nie przydatny");
        ArrayList<String> pros1 =new ArrayList<>();
        pros1.add("super wytrzymaly");
        pros1.add("dobra bateria");
        c1.setCons(cons1);
        c1.setPros(pros1);


        Comment c2 = new Comment();
        c2.setAuthor("autor");
        c2.setRate(2.32f);
        c2.setSummary("sumary2");
        c2.setCreateDate("Data2");
        c2.setHelpful(12);
        c2.setUnhelpful(22);
        c2.setRecommend(true);
        ArrayList<String> cons2 = new ArrayList<>();
        cons2.add("Cons21");
        ArrayList<String> pros2 =new ArrayList<>();
        pros2.add("pros21");
        pros2.add("pros22");
        pros2.add("pros23");
        c2.setCons(cons2);
        c2.setPros(pros2);

        List<Comment> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        List<Comment> l2 = new ArrayList<>(list);
        System.out.println(list.equals(l2));

        list.remove(0);

        l2.forEach(System.out::print);
        System.out.println();
        list.forEach(System.out::print);


        System.out.println();

    }

    public static void testDataSaving(){
        Comment c1 = new Comment();
        c1.setAuthor("Gal Anonim");
        c1.setRate(12.32f);
        c1.setSummary("Bardzo dobry");
        c1.setCreateDate("2016-03-12");
        c1.setHelpful(1);
        c1.setUnhelpful(12);
        c1.setRecommend(true);
        ArrayList<String> cons1 = new ArrayList<>();
        cons1.add("Tani");
        cons1.add("nie przydatny");
        ArrayList<String> pros1 =new ArrayList<>();
        pros1.add("super wytrzymaly");
        pros1.add("dobra bateria");
        c1.setCons(cons1);
        c1.setPros(pros1);


        Comment c2 = new Comment();
        c2.setAuthor("autor");
        c2.setRate(2.32f);
        c2.setSummary("sumary2");
        c2.setCreateDate("Data2");
        c2.setHelpful(12);
        c2.setUnhelpful(22);
        c2.setRecommend(true);
        ArrayList<String> cons2 = new ArrayList<>();
        cons2.add("Cons21");
        ArrayList<String> pros2 =new ArrayList<>();
        pros2.add("pros21");
        pros2.add("pros22");
        pros2.add("pros23");
        c2.setCons(cons2);
        c2.setPros(pros2);

        Comment c3 = new Comment();
        c3.setAuthor("autor3");
        c3.setRate(3.32f);
        c3.setSummary("sumary3");
        c3.setCreateDate("Data3");
        c3.setHelpful(33);
        c3.setUnhelpful(34);
        c3.setRecommend(true);
        ArrayList<String> cons3 = new ArrayList<>();
        cons3.add("Cons31");
        ArrayList<String> pros3 =new ArrayList<>();
        pros3.add("pros31");
        pros3.add("pros32");
        pros3.add("pros33");
        c3.setCons(cons3);
        c3.setPros(pros3);


        Remark r1 = new Remark();
        r1.setName("Name1");
        r1.setValue("Value1");

        Remark r2 = new Remark();
        r2.setName("Name2");
        r2.setValue("Value2");

        Remark r3 = new Remark();
        r3.setName("Name3");
        r3.setValue("Value3");

        Product p1 = new Product();
        p1.setComments(new ArrayList<>());
        p1.getComments().add(c1);
//        p1.getComments().add(c2);
        p1.getComments().add(c3);
        p1.setBrand("Brand1");
        p1.setModel("Model1");
        p1.setType("Typ1");
        p1.setRemarks(new ArrayList<>());
        p1.getRemarks().add(r1);
        p1.getRemarks().add(r2);
        p1.getRemarks().add(r3);

        Loader loader = new Loader();
        loader.loadToDatabase(p1);

    }
}
