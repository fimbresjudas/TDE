package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Contacto;
import util.Data;

import java.io.IOException;

public class editarController{

    //View.editar
    @FXML Button cancelarBtn, aceptarBtn;;
    @FXML JFXTextField nombreAdd, correoAdd , numeroAdd;
    @FXML AnchorPane paneEditar;
    static Contacto contacto = new Contacto();


    //EDITAR

    @FXML
    private void initialize(){
        nombreAdd.setText(contacto.nombre.getValue());
        numeroAdd.setText(contacto.numero.getValue());
        correoAdd.setText(contacto.correo.getValue());


        RequiredFieldValidator  requiredValidator = new RequiredFieldValidator();
        requiredValidator.setMessage("Este campo es obligarotio");

        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Solo numeros");


        RegexValidator emailValidator = new RegexValidator();
        emailValidator.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        emailValidator.setMessage("Email no valido");

        nombreAdd.getValidators().add(requiredValidator);

        numeroAdd.getValidators().add(requiredValidator);
        numeroAdd.getValidators().add(numberValidator);


        correoAdd.getValidators().add(requiredValidator);
        correoAdd.getValidators().add(emailValidator);

        correoAdd.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                correoAdd.validate();
            }
        });

        nombreAdd.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                nombreAdd.validate();
            }
        });
        numeroAdd.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                numeroAdd.validate();
            }
        });
    }

    public static  void cargarText(Contacto c){
        contacto = c;
    }

    public void aceptar() throws IOException{
        if (nombreAdd.validate() && numeroAdd.validate() && correoAdd.validate()){
            contacto.nombre.setValue(nombreAdd.getText());
            contacto.numero.setValue(numeroAdd.getText());
            contacto.correo.setValue(correoAdd.getText());
            cargarListaEditar();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "!Opsss!... No ha ingresado los datos necesarios");
            alert.show();
        }
    }

    public void cancelarEditar() throws IOException{
        cargarListaEditar();
    }


    @FXML private void cargarListaEditar() throws IOException{
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/lista.fxml"));
        Platform.runLater(() -> paneEditar.getChildren().setAll(secondPane));
    }

}
