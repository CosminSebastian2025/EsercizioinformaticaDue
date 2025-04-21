Ecco una spiegazione del codice con commenti inline in italiano:

```java
// Package dichiarato per il progetto
package com.example.esercizioinformaticadue;

// Import delle classi necessarie da JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Classe controller per gestire il comportamento dell'interfaccia utente
public class HelloController {
    // Lista per memorizzare le parole inserite dall'utente
    private List<String> parole = new ArrayList<>();
    // Lista per mantenere l'ordine originale delle parole
    private List<String> originalOrder = new ArrayList<>();

    // Collegamenti alle componenti FXML
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

    // Metodo chiamato automaticamente all'inizializzazione del controller
    @FXML
    public void initialize() {
        // Configura le opzioni del menu a tendina per l'ordinamento
        menuOrdinamento.getItems().addAll(
                "Ordine alfabetico (A-Z)",
                "Ordine alfabetico inverso (Z-A)",
                "Ordine di inserimento"
        );
        menuOrdinamento.setValue("Ordine di inserimento"); // Imposta valore predefinito

        // Listener per cambiare l'ordine delle parole in base alla selezione
        menuOrdinamento.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) { // Controlla se è selezionato un valore
                switch(newVal) {
                    case "Ordine alfabetico (A-Z)":
                        // Ordina alfabeticamente
                        parole.sort(String.CASE_INSENSITIVE_ORDER);
                        break;
                    case "Ordine alfabetico inverso (Z-A)":
                        // Ordina alfabeticamente in ordine inverso
                        parole.sort(Comparator.reverseOrder());
                        break;
                    case "Ordine di inserimento":
                        // Ripristina l'ordine originale
                        parole = new ArrayList<>(originalOrder);
                        break;
                }
                aggiornaLabel(); // Aggiorna la visualizzazione
            }
        });
    }

    // Metodo chiamato quando l'utente clicca sul pulsante "Invia"
    @FXML
    protected void click() {
        String testo = area.getText().trim(); // Ottiene il testo dall'area di input
        if (!testo.isEmpty()) { // Controlla se il testo non è vuoto
            parole.add(testo); // Aggiunge la parola alla lista
            originalOrder.add(testo); // Aggiunge la parola alla lista originale
            aggiornaLabel(); // Aggiorna la visualizzazione
            area.clear(); // Pulisce l'area di input
        }
    }

    // Metodo per applicare il tema chiaro
    @FXML
    private void applyLightTheme() {
        mainContainer.setStyle("-fx-background-color: #f8f9fa;"); // Sfondo chiaro
        titleLabel.setStyle("-fx-text-fill: #2c3e50;"); // Testo scuro

        // Cambia lo stile dei pannelli
        mainContainer.lookupAll(".vbox").forEach(node ->
                node.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0;")
        );

        // Cambia lo stile dei pulsanti
        invia.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        elimina.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
    }

    // Metodo per applicare il tema scuro
    @FXML
    private void applyDarkTheme() {
        mainContainer.setStyle("-fx-background-color: #2d3436;"); // Sfondo scuro
        titleLabel.setStyle("-fx-text-fill: #dfe6e9;"); // Testo chiaro

        // Cambia lo stile dei pannelli
        mainContainer.lookupAll(".vbox").forEach(node ->
                node.setStyle("-fx-background-color: #3d4547; -fx-border-color: #4b5658;")
        );

        // Cambia lo stile dei pulsanti
        invia.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");
        elimina.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white;");
    }

    // Metodo chiamato quando l'utente clicca sul pulsante "Elimina"
    @FXML
    public void elimina() {
        if (!parole.isEmpty()) { // Controlla se ci sono parole nella lista
            String removed = parole.remove(0); // Rimuove la prima parola
            originalOrder.remove(removed); // Rimuove anche dall'ordine originale
            aggiornaLabel(); // Aggiorna la visualizzazione
        }
    }

    // Metodo per aggiornare l'etichetta con l'elenco delle parole
    private void aggiornaLabel() {
        StringBuilder sb = new StringBuilder();
        for (String parola : parole) {
            sb.append("• ").append(parola).append("\n"); // Aggiunge ogni parola con un punto elenco
        }
        welcomeText.setText(sb.toString()); // Imposta il testo aggiornato
    }
}
```
