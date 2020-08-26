package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.Contacto;
import util.Data;

import java.io.IOException;
import java.util.Optional;

public class Controller{
    //View.Sample
    @FXML Button nuevoB,verB, salirB;
    @FXML AnchorPane panePrincipal;

    //View.lista
    @FXML Button editarBtn, eliminarBtn, atrasBtn;
    @FXML AnchorPane paneLista;
    @FXML TableView<Contacto> tableContactos = new TableView<>();
    @FXML TableColumn<Contacto, String> nombreColumn = new TableColumn<>();
    @FXML TableColumn<Contacto, String> numeroColumn = new TableColumn<>();
    @FXML TableColumn<Contacto, String> correoColumn = new TableColumn<>();



    //View.agregar
    @FXML Button aceptarB, cancelarB;
    @FXML JFXTextField nombreTF= new JFXTextField(), correoTF = new JFXTextField(), numeroTF = new JFXTextField();
    @FXML AnchorPane paneAgregar;



    //AGREGAR
    public void aceptarB() throws IOException {
        Contacto contacto = new Contacto();
        if(numeroTF.validate() && nombreTF.validate() && correoTF.validate()) {
            contacto.nombre.set(nombreTF.getText());
            contacto.correo.set(correoTF.getText());
            contacto.numero.set(numeroTF.getText());
            Data.agregarNuevoContacto(contacto);
            cargarPrincipal();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "!Opsss!... No ha ingresado los datos necesarios");
            alert.show();
        }
    }

    public void cancelar() throws IOException{
        cargarPrincipal();
    }


    //LISTA
    @FXML
    private void initialize(){
        tableContactos.setItems(Data.getRegistro());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombre);
        numeroColumn.setCellValueFactory(cellData -> cellData.getValue().numero);
        correoColumn.setCellValueFactory(cellData -> cellData.getValue().correo);


        RequiredFieldValidator  requiredValidator = new RequiredFieldValidator();
        requiredValidator.setMessage("Este campo es obligarotio");

        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Solo numeros");


        RegexValidator emailValidator = new RegexValidator();
        emailValidator.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        emailValidator.setMessage("Email no valido");


        nombreTF.getValidators().add(requiredValidator);

        numeroTF.getValidators().add(requiredValidator);
        numeroTF.getValidators().add(numberValidator);


        correoTF.getValidators().add(requiredValidator);
        correoTF.getValidators().add(emailValidator);


        correoTF.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                correoTF.validate();
            }
        });

        nombreTF.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                nombreTF.validate();
            }
        });
        numeroTF.focusedProperty().addListener((ObservableValue, oldValue, newValue) ->{
            if ( !newValue){
                numeroTF.validate();
            }
        });
    }

    public void delete() {
        if(!tableContactos.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que desea eliminar este contacto?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Data.eliminar(tableContactos.getSelectionModel().getSelectedItem());
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "¡Opsss!...No ha seleccionado ningun contacto");
            alert.show();
        }
    }

    public void atrasB() throws IOException{
        cargarPrincipalLista();
    }

    public void editar() throws IOException{
        if(!tableContactos.getSelectionModel().isEmpty()) {
            cargarEditar();

        }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "¡Opsss!...No ha seleccionado ningun contacto");
                alert.show();
            }
    }




    //Metodos pantallas

    public void salir(){
        System.exit(0);
    }

    @FXML public void cargarAgregar () throws IOException {
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/agregar.fxml"));
        panePrincipal.getChildren().setAll(secondPane);
    }

    @FXML private void cargarPrincipal() throws IOException{
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        Platform.runLater(() -> paneAgregar.getChildren().setAll(secondPane));
    }
    @FXML private void cargarPrincipalLista() throws IOException{
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        Platform.runLater(() -> paneLista.getChildren().setAll(secondPane));
    }

    @FXML private void cargarLista() throws IOException{
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/lista.fxml"));
        Platform.runLater(() -> panePrincipal.getChildren().setAll(secondPane));
    }

    @FXML private void cargarEditar() throws IOException{
        editarController.cargarText(tableContactos.getSelectionModel().getSelectedItem());
        AnchorPane secondPane = FXMLLoader.load(getClass().getResource("../views/edit.fxml"));
        Platform.runLater(() -> paneLista.getChildren().setAll(secondPane));
    }


}
