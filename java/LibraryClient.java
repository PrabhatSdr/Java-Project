package com.example.javaprojectlib;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryClient extends Application {
    private LibraryServer server;
    private ObservableList<Book> bookData;

    @Override
    public void start(Stage primaryStage) {
        server = new LibraryServer();
        bookData = FXCollections.observableArrayList(server.listBooks());

        primaryStage.setTitle("Library Management System");

        TableView<Book> table = new TableView<>();
        table.setItems(bookData);

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Boolean> availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        table.getColumns().addAll(idColumn, titleColumn, authorColumn, availableColumn);

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            if (!title.isEmpty() && !author.isEmpty()) {
                server.addBook(title, author);
                bookData.setAll(server.listBooks());
                titleField.clear();
                authorField.clear();
            }
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setVgap(10);
        inputGrid.setHgap(10);

        inputGrid.add(new Label("Title:"), 0, 0);
        inputGrid.add(titleField, 1, 0);
        inputGrid.add(new Label("Author:"), 0, 1);
        inputGrid.add(authorField, 1, 1);
        inputGrid.add(addButton, 1, 2);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(table, inputGrid);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
