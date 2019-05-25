package com.example.workplaces;

import com.example.workplaces.Place;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Place> Places = new ArrayList<>();
    private static ArrayList<User>  Users = new ArrayList<>();

    public static void Save(Place p){
        Places.add(p);
    }

    public static ArrayList<Place> Get() {
        return Places;
    }


    public static void Edit(Place p, String id, String name, String bloque, String piso, boolean disponible){

        p.setId(id);
        p.setNombre(name);
        p.setBloque(bloque);
        p.setPiso(piso);
        p.setDisponible(disponible);

    }

    public static void Remove(Place c){
        Places.remove(c);
    }

    public static void SaveUser(User u){
        Users.add(u);
    }

    public static void RemoveUser(User u){
        Users.remove(u);
    }

}
