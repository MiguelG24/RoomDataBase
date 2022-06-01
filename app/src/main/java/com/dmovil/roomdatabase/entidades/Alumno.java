package com.dmovil.roomdatabase.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Se crea la entidad Alumno para la tabla alumnos y se utilizan las anotaciones de lombook para
// definir los constructores y los metodos setter y getter
//con las anotaciones de room se define el nombre de la tabla y su llave primaria, además de que
// toma como campos los atributos de la entidad.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (tableName = "Alumno")
public class Alumno {

    @PrimaryKey
    @NonNull
    private String noControl;


    private String nombre;
    private String apellidos;
    private String carrera;
    private String telefono;
    private String email;
    private String direccion;


}
