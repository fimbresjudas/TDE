package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Contacto;


public class Data {
   private static ObservableList<Contacto> registro = FXCollections.observableArrayList();

    public static void eliminar(Contacto c) {
        registro.removeAll(c);
    }

    public static void agregarNuevoContacto(Contacto c){
        registro.add(c);
        System.out.println(registro);
    }

    public static ObservableList<Contacto> getRegistro(){
        return registro;
    }

}
