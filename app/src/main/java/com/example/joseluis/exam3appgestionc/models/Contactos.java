package com.example.joseluis.exam3appgestionc.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Contactos implements Parcelable{
    public Integer id;
    private String nombre;
    private String numero;
    private String email;
    private String perfil;

    public Contactos(String nombre, String numero, String email, String perfil) {
        this.nombre = nombre;
        this.numero = numero;
        this.email = email;
        this.perfil = perfil;
    }

    protected Contactos(Parcel in) {
        nombre = in.readString();
        numero = in.readString();
        email = in.readString();
        perfil = in.readString();
    }


    public static final Creator<Contactos> CREATOR = new Creator<Contactos>() {
        @Override
        public Contactos createFromParcel(Parcel in) {
            return new Contactos(in);
        }

        @Override
        public Contactos[] newArray(int size) {
            return new Contactos[size];
        }
    };

    @Override
    public String toString() {
        return "Contactos{" +
                "nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", email='" + email + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(nombre);
        dest.writeString(numero);
        dest.writeString(email);
        dest.writeString(perfil);
    }
}
