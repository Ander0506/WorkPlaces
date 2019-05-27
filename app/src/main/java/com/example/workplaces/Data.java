package com.example.workplaces;

import com.example.workplaces.Place;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Data  {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference RefPlaces = database.getReference("places");
    private static DatabaseReference RefGlobal = database.getReference("globalVar");

    private static ArrayList<Place> Places = new ArrayList<>();
    private static ArrayList<User>  Users = new ArrayList<>();



    public static void Save(Place p){
        RefPlaces.child(p.getId()).setValue(p);
    }

    public static void LLenarVec(Place p){
        Places.add(p);
    }

    public static ArrayList<Place> Get() {
        return Places;
    }

    public static void RemoveAll(){
        Places.clear();
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

    public static void ChangeAvailable(Place p){

        /*for (int i = 0;i<Places.size();i++){
            Place Actual = Places.get(i);
            if (Actual.getId().equals(p.getId())){
                if (Actual.getDisponible()){
                    Actual.setDisponible(false);
                }else{
                    Actual.setDisponible(true);
                }
            }
        }*/

        if (p.getDisponible()){
            RefPlaces.child(p.getId()).child("disponible").setValue("false");
        }else{
            RefPlaces.child(p.getId()).child("disponible").setValue("true");
        }

    }

}
