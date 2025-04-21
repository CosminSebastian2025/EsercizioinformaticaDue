package com.example.esercizioinformaticadue;

// Importa le classi necessarie per creare un'applicazione JavaFX, caricare file FXML e gestire la scena e lo stage
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// La classe HelloApplication estende la classe Application di JavaFX
public class HelloApplication extends Application {

    // Override del metodo `start`, che è il punto di ingresso principale per un'applicazione JavaFX
    @Override
    public void start(Stage stage) throws IOException {
        // Carica il file FXML che definisce l'interfaccia utente
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        
        // Crea una nuova scena utilizzando il file FXML caricato, con dimensioni di larghezza 400 e altezza 600
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        
        // Imposta il titolo della finestra (stage) principale
        stage.setTitle("Stringhe");
        
        // Imposta la scena appena creata sullo stage principale
        stage.setScene(scene);
        
        // Mostra lo stage, rendendo visibile la finestra dell'applicazione
        stage.show();
    }

    // Metodo main: il punto di ingresso dell'applicazione Java
    public static void main(String[] args) {
        // Lancia l'applicazione JavaFX
        launch();
    }
}
