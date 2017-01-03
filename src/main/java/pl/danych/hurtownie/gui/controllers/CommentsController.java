package pl.danych.hurtownie.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.danych.hurtownie.objects.Comment;
import pl.danych.hurtownie.objects.Product;
import pl.danych.hurtownie.services.CsvWriter;
import pl.danych.hurtownie.services.etl.Loader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentsController {

    private Stage stage;
    private List<Product> productList;
    private ObservableList<ProductFx> productData;
    private ObservableList<CommentFX> commentData;
    List<Comment> rawComments ;
    private Product selected;
    CsvWriter writer = new CsvWriter() ;

    @FXML
    private TableView<ProductFx> productTable;

    @FXML
    private TableColumn<ProductFx, String> typeColumn;

    @FXML
    private TableColumn<ProductFx, String> brandColumn;

    @FXML
    private TableColumn<ProductFx, String> modelColumn;

    @FXML
    private TableView<CommentFX> commentsTable;

    @FXML
    private TableColumn<CommentFX, String> authorColumn;

    @FXML
    private TableColumn<CommentFX, String> dateColumn;

    @FXML
    private TableColumn<CommentFX, String> summaryColumn;

    @FXML
    private TableColumn<CommentFX, String> rateColumn;


    @FXML
    public void initialize(){
        productData  = FXCollections.observableArrayList();


        typeColumn.setCellValueFactory(cellData-> cellData.getValue().typeProperty());
        brandColumn.setCellValueFactory(cellData-> cellData.getValue().brandProperty());
        modelColumn.setCellValueFactory(cellData-> cellData.getValue().modelProperty());

        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        dateColumn.setCellValueFactory(cellData-> cellData.getValue().dateProperty());
        summaryColumn.setCellValueFactory(cellData-> cellData.getValue().summaryProperty());
        rateColumn.setCellValueFactory(cellData-> cellData.getValue().rateProperty());


        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)-> showComments(newValue)
        );
    }

    @FXML
    private void exportToCsv(ActionEvent event){
        if(selected != null){
            writer.writeToFile(selected);
        }
    }

    private void showComments(ProductFx newValue) {
        commentData = FXCollections.observableArrayList();
        rawComments = null;
        productList.stream()
                .filter(product -> product.getId() == newValue.getId())
                .forEach(product -> {
                    rawComments = product.getComments();
                    selected = product;
                });

        System.out.println(selected.getId());
            commentData.addAll(rawComments
                    .stream()
                    .map(comment -> new CommentFX(comment.getAuthor(), comment.getCreateDate(),
                            comment.getSummary(), comment.getRate()))
                    .collect(Collectors.toList())
            );

        commentsTable.setItems(commentData);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        addProductsToShow();
        productTable.setItems(productData);
    }

    private void addProductsToShow() {
        Set<Product> set = new HashSet<>();
        set.addAll(productList);
        productList.clear();
        productList.addAll(set);
        productData.addAll(productList
                .stream()
                .map(product -> new ProductFx(product.getId(), product.getType(), product.getBrand(), product.getModel()))
                .collect(Collectors.toList()));
    }


    private static class ProductFx{
        private int id;
        private final SimpleStringProperty type;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty model;


        public ProductFx(int id, String type, String brand, String model) {
            this.id = id;
            this.type = new SimpleStringProperty(type);
            this.brand =new SimpleStringProperty(brand);
            this.model = new SimpleStringProperty(model);
        }

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public String getBrand() {
            return brand.get();
        }

        public SimpleStringProperty brandProperty() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public String getModel() {
            return model.get();
        }

        public SimpleStringProperty modelProperty() {
            return model;
        }

        public void setModel(String model) {
            this.model.set(model);
        }

        public int getId(){
            return id;
        }


    }

    private static class CommentFX {
        private final SimpleStringProperty author;
        private final SimpleStringProperty date;
        private final SimpleStringProperty summary;
        private final SimpleStringProperty rate;

        public CommentFX(String author, String date, String summary, float rate) {
            this.author = new SimpleStringProperty(author);
            this.date = new SimpleStringProperty(date);
            this.summary = new SimpleStringProperty(summary);
            this.rate = new SimpleStringProperty(String.valueOf(rate));
        }

        public StringProperty authorProperty(){
            return author;
        }

        public StringProperty summaryProperty(){
            return summary;
        }
        public StringProperty dateProperty(){
            return date;
        }
        public StringProperty rateProperty(){
            return rate;
        }

    }
}
