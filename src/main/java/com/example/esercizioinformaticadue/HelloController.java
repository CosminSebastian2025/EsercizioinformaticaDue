package com.example.esercizioinformaticadue;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HelloController {
    private List<String> parole = new ArrayList<>();
    private List<String> originalOrder = new ArrayList<>();

    // Riferimenti FXML
    @FXML private VBox mainContainer;
    @FXML private Label titleLabel;
    @FXML private TextArea welcomeText;
    @FXML private Button invia;
    @FXML private Button elimina;
    @FXML private TextArea area;
    @FXML private ChoiceBox<String> menuOrdinamento;
    @FXML private RadioButton lightTheme;
    @FXML private RadioButton darkTheme;
    @FXML private ToggleGroup themeGroup;
    @FXML
    public void initialize() {
        // Configura il menu a tendina
        menuOrdinamento.getItems().addAll(
                "Ordine alfabetico (A-Z)",
                "Ordine alfabetico inverso (Z-A)",
                "Ordine di inserimento"
        );
        menuOrdinamento.setValue("Ordine di inserimento");

        menuOrdinamento.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                switch(newVal) {
                    case "Ordine alfabetico (A-Z)":
                        parole.sort(String.CASE_INSENSITIVE_ORDER);
                        break;
                    case "Ordine alfabetico inverso (Z-A)":
                        parole.sort(Comparator.reverseOrder());
                        break;
                    case "Ordine di inserimento":
                        parole = new ArrayList<>(originalOrder);
                        break;
                }
                aggiornaLabel();
            }
        });
    }

    @FXML
    protected void click() {
        String testo = area.getText().trim();
        if (!testo.isEmpty()) {
            parole.add(testo);
            originalOrder.add(testo);
            aggiornaLabel();
            area.clear();
        }
    }

    @FXML
    private void applyLightTheme() {
        // Stile chiaro
        mainContainer.setStyle("-fx-background-color: #f8f9fa;");
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");

        // Applica stile a tutti i pannelli
        mainContainer.lookupAll(".vbox").forEach(node ->
                node.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0;")
        );

        // Stile pulsanti
        invia.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        elimina.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
    }

    @FXML
    private void applyDarkTheme() {
        // Stile scuro
        mainContainer.setStyle("-fx-background-color: #2d3436;");
        titleLabel.setStyle("-fx-text-fill: #dfe6e9;");

        // Applica stile a tutti i pannelli
        mainContainer.lookupAll(".vbox").forEach(node ->
                node.setStyle("-fx-background-color: #3d4547; -fx-border-color: #4b5658;")
        );

        // Stile pulsanti
        invia.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");
        elimina.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");
    }

    @FXML
    public void elimina() {
        if (!parole.isEmpty()) {
            String removed = parole.remove(0);
            originalOrder.remove(removed);
            aggiornaLabel();
        }
    }

    private void aggiornaLabel() {
        StringBuilder sb = new StringBuilder();
        for (String parola : parole) {
            sb.append("• ").append(parola).append("\n");
        }
        welcomeText.setText(sb.toString());
    }
}