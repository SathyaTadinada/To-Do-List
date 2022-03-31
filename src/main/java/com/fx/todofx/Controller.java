package com.fx.todofx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Controller implements Initializable {

    private List<String> taskNames = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    @FXML
    private AnchorPane bottomAnchorPane;

    @FXML
    private AnchorPane viewTaskAnchorPane;

    @FXML
    private Button viewTaskButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label createTask;

    @FXML
    private Label viewTask;

    @FXML
    private Label taskNameLabelField;

    @FXML
    private Label taskNameLabel;

    @FXML
    private Label dueDateLabelField;

    @FXML
    private Label dueDateLabel;

    @FXML
    private TextField taskName;

    @FXML
    private ListView<String> listView;

    @FXML
    private MenuItem viewTaskContextMenu;

    @FXML
    private MenuItem viewTaskMenuBar;

    @FXML
    private MenuItem deleteContextMenu;

    @FXML
    private MenuItem deleteMenuBar;

    @FXML
    private MenuItem clearMenuBar;

    @FXML
    private MenuItem editTaskMenuBar;

    @FXML
    private MenuItem editTaskContextMenu;


    @FXML
    void about(ActionEvent event) {
        //TODO change the detailed task window to a summary of the creation of this service, etc.

        viewTaskButton.setVisible(true);
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5cbf3");

        viewTask.setText("About Application");
        taskNameLabel.setText("Created by: ");
        dueDateLabel.setText("");

        taskNameLabelField.setText("Sathya Tadinada");
        dueDateLabelField.setText("");

    }

    @FXML
    void addTask(ActionEvent event) {
        System.out.println(taskName.getText());
        System.out.println(datePicker.getValue());

        if ((taskName.getText() != null && !taskName.getText().isBlank()) && datePicker.getValue() != null) {
            tasks.add(new Task(taskName.getText(), String.valueOf(datePicker.getValue())));
            taskNames.add(taskName.getText());
        }

//        List<String> strings = new ArrayList<>();
//        ObservableList<String> observableList = FXCollections.observableList(strings);
//        strings.add(datePicker.getValue() + taskName.getText());
//
//        listView.edit(listViewMenuIndex);
//        listView.setItems(observableList);
//        listViewMenuIndex++;
//        System.out.println(listViewMenuIndex);

        taskName.clear();
        datePicker.setValue(null);

        refreshList(event);
    }

//    @FXML
//    public void onPrintButtonClicked(MouseEvent event) {
//        String message = "";
//        ObservableList<String> movies;
//        movies = listView.getSelectionModel().getSelectedItems();
//
//        for (String m : movies) {
//            message += m + "\n";
//        }
//        System.out.println(message);
//    }

    @FXML
    public void clear(ActionEvent event) {
        tasks.clear();
        taskNames.clear();
        refreshList(event);
    }

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void deleteTasks(ActionEvent event) {
//        ObservableList<String> toBeRemoved = listView.getSelectionModel().getSelectedItems();
//        listView.getItems().removeAll(toBeRemoved);

        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        tasks.remove(itemIndex);
        taskNames.clear();
        for (Task task : tasks) {
            taskNames.add(task.getName());
        }
        refreshList(event);

//        if (tasks.size() < 1) {
//            deleteContextMenu.setDisable(true);
//            deleteMenuBar.setDisable(true);
//        } else {
//            int itemIndex = listView.getSelectionModel().getSelectedIndex();
//            tasks.remove(itemIndex);
//            refreshList(event);
//        }
    }

    @FXML
    void doubleClickViewTask(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                int itemIndex = listView.getSelectionModel().getSelectedIndex();
                taskNameLabelField.setText(tasks.get(itemIndex).getName());
                dueDateLabelField.setText(tasks.get(itemIndex).getDueDate());
                bottomAnchorPane.setStyle("-fx-background-color: " + "#9fbded");
                viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5e5a1");
                viewTaskButton.setVisible(true);
            }
        }
    }

    @FXML
    void editTask(ActionEvent event) {
        createTask.setText("Edit Task");
        bottomAnchorPane.setStyle("-fx-background-color: " + "#bdb7f1");
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#88cf9b");

        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        String nameOfTask = tasks.get(itemIndex).getName();
        String dueDate = tasks.get(itemIndex).getDueDate();

        taskName.setText(nameOfTask);
        datePicker.setValue(LocalDate.parse(dueDate));

        tasks.remove(itemIndex);
        taskNames.remove(itemIndex);
        listView.getItems().remove(itemIndex);
//        itemIndex = listView.getSelectionModel().getSelectedIndex();
//        nameOfTask = event.getNewValue();
//        tasks.add(new Task(nameOfTask, tasks.get(itemIndex).getDueDate()));
//        tasks.add(new Task(" ", " "));
//        listView.getItems().remove(itemIndex);
//        System.out.println(nameOfTask);

//        refreshList(event);

    }

    @FXML
    void refreshList(ActionEvent event) {
        listView.getSelectionModel().clearSelection();
        bottomAnchorPane.setStyle("-fx-background-color: " + "#9fbded");
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#88cf9b");
        createTask.setText("Create a Task");
        viewTask.setText("View Task");
        taskNameLabel.setText("Task Name:");
        dueDateLabel.setText("Due Date:");
        taskNameLabelField.setText("Right-click a task to view");
        dueDateLabelField.setText("Right-click a task to view");
        viewTaskButton.setVisible(false);

        listView.getItems().clear();
        if (tasks.size() == 0) {
            noTasks();
        } else {
            listView.getItems().addAll(taskNames);

            viewTaskContextMenu.setDisable(false);
            viewTaskMenuBar.setDisable(false);

            deleteContextMenu.setDisable(false);
            deleteMenuBar.setDisable(false);

            clearMenuBar.setDisable(false);

            editTaskContextMenu.setDisable(false);
            editTaskMenuBar.setDisable(false);
        }

    }

    @FXML
    void viewTask(ActionEvent event) {
        viewTaskButton.setVisible(true);
        bottomAnchorPane.setStyle("-fx-background-color: #" + "9fbded");
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5e5a1");
        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        taskNameLabelField.setText(tasks.get(itemIndex).getName());
        dueDateLabelField.setText(tasks.get(itemIndex).getDueDate());

//        if (tasks.size() > 0) {
//            int itemIndex = listView.getSelectionModel().getSelectedIndex();
//            nameOfTaskLabel.setText(tasks.get(itemIndex).getName());
//            dueDateLabel.setText(tasks.get(itemIndex).getDueDate());
//        }
//        else {
//            viewTaskContextMenu.setDisable(true);
//            viewTaskMenuBar.setDisable(true);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewTaskButton.setVisible(false);
//        listView.setEditable(true);
//        listView.setCellFactory(TextFieldListCell.forListView());

        noTasks();

//        listView.getItems().addAll("Iron Man", "Spider-Man", "Captain America", "Thor");
//        listView.getItems().addAll(String.valueOf(tasks));
//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        taskNameLabelField.setText("Right-click a task to view");
        dueDateLabelField.setText("Right-click a task to view");

    }

    private void noTasks() {
        listView.getItems().addAll("Nothing here yet... Add your first task!");
        viewTaskContextMenu.setDisable(true);
        viewTaskMenuBar.setDisable(true);

        deleteContextMenu.setDisable(true);
        deleteMenuBar.setDisable(true);

        clearMenuBar.setDisable(true);

        editTaskContextMenu.setDisable(true);
        editTaskMenuBar.setDisable(true);
    }
}
