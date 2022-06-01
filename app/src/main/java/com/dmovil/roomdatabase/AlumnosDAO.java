package com.dmovil.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dmovil.roomdatabase.entidades.Alumno;

import java.util.List;

//Esta interfaz define los metodos y consultas que se utilizaran para acceder a la base de datos
@Dao
public interface AlumnosDAO {

    @Query("SELECT * FROM Alumnos")
    List<Alumno> getAll();

    @Query("SELECT * FROM Alumnos WHERE noControl = (:noControl)")
    List<Alumno> getById (String noControl);

    @Query("SELECT * FROM Alumnos WHERE noControl IN (:control)")
    List<Alumno> loadAllByIds (String control);

    @Query("SELECT * FROM Alumnos WHERE nombre LIKE :nombre AND " +
            "apellidos LIKE :apellidos LIMIT 1")
    Alumno findByName (String nombre, String apellidos);

    @Query("SELECT * FROM Alumnos WHERE noControl LIKE :noControl LIMIT 1")
    Alumno buscarControl (String noControl);

    @Insert
    void insert (Alumno alumno);
    @Update
    void update (Alumno alumno);
    @Delete
    void delete (Alumno alumno);
}
