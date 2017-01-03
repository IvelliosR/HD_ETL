package pl.danych.hurtownie.services;

import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Arek on 03.01.2017.
 */
public class CsvWriter {

    private final String fileType = ".csv";
    private String fileName;
    private String quot = "\"";
    private String quotEnd = "\",";

    public void writeToFile(Product product){
        createFileName(product);
        int counter = 1;
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))){
            writer.write("ID,Author,Create_Date,Summary,Rate,Recommend,Helpful,Unhelpful\n");
            for(Comment comment : product.getComments()){
                writer.write(counter+",");
                writer.write(quot+ comment.getAuthor()+quotEnd);
                writer.write(comment.getCreateDate()+",");
                writer.write(quot+comment.getSummary()+quotEnd);
                writer.write(String.valueOf(comment.getRate())+",");
                writer.write(String.valueOf(comment.isRecommend())+",");
                writer.write(String.valueOf(comment.getHelpful())+",");
                writer.write(String.valueOf(comment.getUnhelpful())+"\n");
                writer.flush();
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String createFileName(Product product){
        fileName = product.getModel().replaceAll("[^A-Za-z0-9]", "");
        fileName = fileName+fileType;
        return fileName;
    }

}
