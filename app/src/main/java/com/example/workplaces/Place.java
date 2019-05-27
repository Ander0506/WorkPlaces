package com.example.workplaces;

import java.security.PublicKey;

public class Place {

    private String Id;
    private String Nombre;
    private String Bloque;
    private String Piso;
    private Boolean Disponible;
    //0=Sala 1=mesadeTrabajo  2=MesaPersonalizada  3=Parqueadero
    private int Type;

    public Place(String id, String nombre, String bloque, String piso, Boolean disponible, int type) {
        Id = id;
        Nombre = nombre;
        Bloque = bloque;
        Piso = piso;
        Disponible = disponible;
        Type = type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getBloque() {
        return Bloque;
    }

    public void setBloque(String bloque) {
        Bloque = bloque;
    }

    public String getPiso() {
        return Piso;
    }

    public void setPiso(String piso) {
        Piso = piso;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public Boolean getDisponible() {
        return Disponible;
    }

    public void setDisponible(Boolean disponible) {
        Disponible = disponible;
    }

    public void añadir( ){
        Data.Save(this);
    }

    public void eliminar(){
       Data.Remove(this);
    }

    public void changeAvailable(){
        Data.ChangeAvailable(this);
    }

}
