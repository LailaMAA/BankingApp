package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private SavingsAccount savings = new SavingsAccount("Alice", 500);
    private CheckingAccount checking = new CheckingAccount("Bob", 1000);
    private BusinessAccount business = new BusinessAccount("Charlie", 1500);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking Operations");

        // Labels to display balances
        Label savingsBalanceLabel = new Label("Savings Balance: $" + savings.getBalance());
        Label checkingBalanceLabel = new Label("Checking Balance: $" + checking.getBalance());
        Label businessBalanceLabel = new Label("Business Balance: $" + business.getBalance());

        // Text fields and buttons for deposit and withdraw actions
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");

        // Dropdown to select account type
        ComboBox<String> accountSelect = new ComboBox<>();
        accountSelect.getItems().addAll("Savings", "Checking", "Business");
        accountSelect.setValue("Savings");

        // Action for Deposit Button
        depositButton.setOnAction(e -> {
            String selectedAccount = accountSelect.getValue();
            double amount = Double.parseDouble(amountField.getText());
            if (selectedAccount.equals("Savings")) {
                savings.deposit(amount);
                savingsBalanceLabel.setText("Savings Balance: $" + savings.getBalance());
            } else if (selectedAccount.equals("Checking")) {
                checking.deposit(amount);
                checkingBalanceLabel.setText("Checking Balance: $" + checking.getBalance());
            } else if (selectedAccount.equals("Business")) {
                business.deposit(amount);
                businessBalanceLabel.setText("Business Balance: $" + business.getBalance());
            }
        });

        // Action for Withdraw Button
        withdrawButton.setOnAction(e -> {
            try {
                String selectedAccount = accountSelect.getValue();
                double amount = Double.parseDouble(amountField.getText());
                if (selectedAccount.equals("Savings")) {
                    savings.withdraw(amount);
                    savingsBalanceLabel.setText("Savings Balance: $" + savings.getBalance());
                } else if (selectedAccount.equals("Checking")) {
                    checking.withdraw(amount);
                    checkingBalanceLabel.setText("Checking Balance: $" + checking.getBalance());
                } else if (selectedAccount.equals("Business")) {
                    business.withdraw(amount);
                    businessBalanceLabel.setText("Business Balance: $" + business.getBalance());
                }
            } catch (InsufficientFundsException ex) {
                showAlert("Insufficient Funds", ex.getMessage());
            }
        });

        // Layout setup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                accountSelect,
                amountField,
                depositButton,
                withdrawButton,
                savingsBalanceLabel,
                checkingBalanceLabel,
                businessBalanceLabel
        );

        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
