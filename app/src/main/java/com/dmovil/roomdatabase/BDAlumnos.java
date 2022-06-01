package com.dmovil.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dmovil.roomdatabase.entidades.Alumno;
//Se crea la base de datos y se añade las tablas que la integrarán.
//Se crea un metodo abstracto que devuelve una consulta de la clase AlumnosDAO.
@Database(entities = {Alumno.class}, version = 1)
public abstract class BDAlumnos extends RoomDatabase {

    public abstract AlumnosDAO alumnosDAO();

}
