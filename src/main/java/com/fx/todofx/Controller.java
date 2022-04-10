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

    // all the frontend elements that will be used

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
    private Label noSelectedTaskLabel;
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
    private MenuItem editTaskContextMenu;
    @FXML
    private MenuItem editTaskMenuBar;
    @FXML
    private TextField taskName;

    @FXML
    public void about(ActionEvent event) {
        viewTaskButton.setVisible(true);
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5cbf3");

        viewTask.setText("About Application");
        taskNameLabel.setText("Created for: ");
        dueDateLabel.setText("");

        taskNameLabelField.setText("IB Computer Science IA");
        dueDateLabelField.setText("");

        noSelectedTaskLabel.setVisible(false);
    }

    @FXML
    public void addTask(ActionEvent event) {
        // verifies that both the task name text field and the date picker are not empty before adding the task
        if ((taskName.getText() != null && !taskName.getText().isBlank()) && datePicker.getValue() != null) {
            tasks.add(new Task(taskName.getText(), String.valueOf(datePicker.getValue())));
            taskNames.add(taskName.getText());
        }
        taskName.clear();
        datePicker.setValue(null);

        refreshList(event);
    }

    @FXML
    public void clear(ActionEvent event) {
        tasks.clear();
        taskNames.clear();
        refreshList(event);
    }

    @FXML
    public void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void deleteTasks(ActionEvent event) {
        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        if (itemIndex >= 0) {
            tasks.remove(itemIndex);
            taskNames.clear();
            for (Task task : tasks) {
                taskNames.add(task.getName());
            }
            refreshList(event);
        }
    }

    @FXML
    public void doubleClickViewTask(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                int itemIndex = listView.getSelectionModel().getSelectedIndex();
                if (itemIndex >= 0) {
                    taskNameLabel.setText("Task Name:");
                    dueDateLabel.setText("Due Date:");
                    taskNameLabelField.setText(tasks.get(itemIndex).getName());
                    dueDateLabelField.setText(tasks.get(itemIndex).getDueDate());
                    bottomAnchorPane.setStyle("-fx-background-color: " + "#9fbded");
                    viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5e5a1");

                    viewTaskButton.setVisible(true);
                    noSelectedTaskLabel.setVisible(false);
                }
            }
        }
    }

    @FXML
    public void editTask(ActionEvent event) {
        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        if (itemIndex >= 0) {
            createTask.setText("Edit Task");
            bottomAnchorPane.setStyle("-fx-background-color: " + "#bdb7f1");
            viewTaskAnchorPane.setStyle("-fx-background-color: " + "#88cf9b");

            String nameOfTask = tasks.get(itemIndex).getName();
            String dueDate = tasks.get(itemIndex).getDueDate();

            taskName.setText(nameOfTask);
            datePicker.setValue(LocalDate.parse(dueDate));

            tasks.remove(itemIndex);
            taskNames.remove(itemIndex);
            listView.getItems().remove(itemIndex);
        }
    }

    @FXML
    public void refreshList(ActionEvent event) {
        listView.getSelectionModel().clearSelection();
        bottomAnchorPane.setStyle("-fx-background-color: " + "#9fbded");
        viewTaskAnchorPane.setStyle("-fx-background-color: " + "#88cf9b");
        createTask.setText("Create a Task");
        viewTask.setText("View Task");
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

            taskNameLabel.setText("");
            dueDateLabel.setText("");
            taskNameLabelField.setText("");
            dueDateLabelField.setText("");

            noSelectedTaskLabel.setVisible(true);
            noSelectedTaskLabel.setText("Right-click a task to view it.");
        }
    }

    @FXML
    public void viewTask(ActionEvent event) {
        int itemIndex = listView.getSelectionModel().getSelectedIndex();
        if (itemIndex >= 0) {
            taskNameLabel.setText("Task Name:");
            dueDateLabel.setText("Due Date:");
            noSelectedTaskLabel.setVisible(false);
            viewTaskButton.setVisible(true);
            bottomAnchorPane.setStyle("-fx-background-color: " + "#9fbded");
            viewTaskAnchorPane.setStyle("-fx-background-color: " + "#f5e5a1");

            taskNameLabelField.setText(tasks.get(itemIndex).getName());
            dueDateLabelField.setText(tasks.get(itemIndex).getDueDate());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewTaskButton.setVisible(false);
        noTasks();

        taskNameLabel.setText("");
        dueDateLabel.setText("");
        taskNameLabelField.setText("");
        dueDateLabelField.setText("");

        noSelectedTaskLabel.setText("Create a task to view it here.");

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

        taskNameLabel.setText("");
        dueDateLabel.setText("");
        taskNameLabelField.setText("");
        dueDateLabelField.setText("");

        noSelectedTaskLabel.setVisible(true);
        noSelectedTaskLabel.setText("Create a task to view it here.");
    }
}

/*
// all of the frontend elements that will be used
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
    private Label noSelectedTaskLabel;

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
    private MenuItem editTaskContextMenu;

    @FXML
    private MenuItem editTaskMenuBar;

    @FXML
    private TextField taskName;
 */
