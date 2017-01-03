package pl.danych.hurtownie.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.danych.hurtownie.gui.ETLApplication;
import pl.danych.hurtownie.objects.ExtractedData;
import pl.danych.hurtownie.objects.Product;
import pl.danych.hurtownie.services.etl.Extractor;
import pl.danych.hurtownie.services.etl.Loader;
import pl.danych.hurtownie.services.etl.Transformator;

import java.io.IOException;

public class AppController {

    private Extractor extractor = new Extractor();
    private ExtractedData extractedData;
    private Transformator transformator;
    private Product product;
    private Loader loader = new Loader();

    private ETLApplication mainApp;

    public void setMainApp(ETLApplication mainApp){
        this.mainApp = mainApp;
    }



    @FXML
    private Button extractButton;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputField;

    @FXML
    private Button transformButton;

    @FXML
    private Button loadButton;

    @FXML
    private void handleExtractButton(ActionEvent action){
        if(extract()){
            transformButton.setDisable(false);
        }
    }

    private boolean extract(){
        String code = inputField.getText();
        if(!code.equals("")){
            try {
                extractedData = extractor.extract(code);
                outputField.setDisable(false);
                outputField.setText("Number of downloaded pages: "+String.valueOf(extractedData.getPagesQuantity()));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                inputField.setPromptText("Type correct code");
                return false;
            }
        }
        else{
            inputField.setPromptText("Type correct code");
            return false;
        }

    }

    @FXML
    private void handleTransformButton(ActionEvent action){
        transform();
        transformButton.setDisable(true);
        loadButton.setDisable(false);
    }

    private void transform(){
        transformator = new Transformator();
        product = transformator.transform(extractedData);
        outputField.setText(outputField.getText()+"\nObtained "+product.countComments()+" Comments\n"
                +"Obtained "+product.countRemarks()+" Remarks");
    }

    @FXML
    private void handleLoadButton(ActionEvent action){
        load();
        loadButton.setDisable(true);
    }

    private void load(){
        loader.loadToDatabase(product);
        outputField.setText(outputField.getText()+ "\n" + loader.getStatistic());
    }

    @FXML
    private void handleEtlButton(ActionEvent action){
        if(extract()){
            transform();
            load();
        }
        else
            inputField.setText("Type correct product code");
    }

    @FXML
    private void deleteButton(){
        loader.deleteAllComments();
        outputField.setDisable(false);
        outputField.setText(outputField.getText() + "\n" + loader.getStatistic());
    }

    @FXML
    private void commentsOverview(ActionEvent event){

        mainApp.showCommentsWindow(loader.getAllProducts(), this.loader);

    }


}
