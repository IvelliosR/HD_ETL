package pl.danych.hurtownie.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.danych.hurtownie.gui.controllers.AppController;
import pl.danych.hurtownie.gui.controllers.CommentsController;
import pl.danych.hurtownie.hibernate.utils.HibernateUtils;
import pl.danych.hurtownie.objects.Product;
import pl.danych.hurtownie.services.etl.Loader;

import java.io.IOException;
import java.util.List;

//43223879
public class ETLApplication extends Application {

    private Stage primaryStage;
    private GridPane rootLayout;

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ETL");
        initFirstWindowLayout();

    }

    private void initFirstWindowLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/rootScene.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout, 500, 600);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        AppController controller = loader.getController();
        controller.setMainApp(this);
    }


    public void showCommentsWindow(List<Product> products, Loader myLoader) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/comments.fxml"));
            SplitPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Comments");

            Scene scene = new Scene(page, 500, 600);
            dialogStage.setScene(scene);

            CommentsController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setProductList(products);

            dialogStage.show();

        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtils.closeSessionFactory();
    }


    public Stage getPrimaryStage(){
        return this.primaryStage;
    }


}
