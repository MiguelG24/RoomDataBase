package com.dmovil.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dmovil.roomdatabase.entidades.Alumno;

public class MainActivity extends AppCompatActivity {

    //Se declaran las variables de instancia para la base de datos y los EditText
    BDAlumnos bdAlumnos;
    EditText control, nombre, apellidos, carrera, telefono, email, direccion;

    //Inicializan las variables de instancia
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = findViewById(R.id.edt_noControl);
        nombre = findViewById(R.id.edt_nombre);
        apellidos = findViewById(R.id.edt_apellidos);
        carrera = findViewById(R.id.edt_carrera);
        telefono = findViewById(R.id.edt_telefono);
        email = findViewById(R.id.edt_email);
        direccion = findViewById(R.id.edt_direccion);
        control.requestFocus();

        bdAlumnos = Room.databaseBuilder(getApplicationContext(),
                BDAlumnos.class, "App_ITSH").allowMainThreadQueries().build();
    }

    //Metodo para registrar wn la base de datos, primero se validan los campos,
    // se crea el objeto alumno y se inserta en la base de datos
    public void registrar (View view){
        if (validaciones()) {
            Alumno alumno = new Alumno(control.getText().toString(), nombre.getText().toString(),
                    apellidos.getText().toString(), carrera.getText().toString(), telefono.getText().toString(),
                    email.getText().toString(), direccion.getText().toString());

            bdAlumnos.alumnosDAO().insert(alumno);
            Toast.makeText(this, "Registro exitoso...!", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    //Se busca un registro en la base de datos,
    // primero se valida que el campo de busqueda no se encuentre vacio
    //se hace la busqueda en la base de datos y el objeto devuelto se pasa a los EditText
    public void buscar (View view){
        if (validarConsulta()){
            Alumno alumno = bdAlumnos.alumnosDAO().buscarControl(control.getText().toString());

            control.setText(alumno.getNoControl());
            nombre.setText(alumno.getNombre());
            apellidos.setText(alumno.getApellidos());
            carrera.setText(alumno.getCarrera());
            telefono.setText(alumno.getTelefono());
            email.setText(alumno.getEmail());
            direccion.setText(alumno.getDireccion());
        }
    }

    //Se edita uno de los registros existentes, primero se validan los campos y se crea el objeto
    // alumno para actualizarlo en la base de datos
    public void editar (View view) {
        if (validaciones()) {
            Alumno alumno = new Alumno(control.getText().toString(), nombre.getText().toString(),
                    apellidos.getText().toString(), carrera.getText().toString(), telefono.getText().toString(),
                    email.getText().toString(), direccion.getText().toString());

            bdAlumnos.alumnosDAO().update(alumno);
            Toast.makeText(this, "Registro actualizado...!", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    //Elimina un registro
    //Primero valida el campo de busqueda y despues crea la instancia y la elimina.
    public void eliminar (View view) {
        if (validarConsulta()){
            Alumno alumno = new Alumno(control.getText().toString(), nombre.getText().toString(),
                    apellidos.getText().toString(), carrera.getText().toString(),telefono.getText().toString(),
                    email.getText().toString(), direccion.getText().toString());

            bdAlumnos.alumnosDAO().delete(alumno);
            Toast.makeText(this, "Registro eliminado...!", Toast.LENGTH_SHORT).show();
            limpiar();
            control.requestFocus();
        }
    }

    //Limpia los EditText y enfoca al EditText de control
    private void limpiar (){
        control.setText("");
        nombre.setText("");
        apellidos.setText("");
        carrera.setText("");;
        telefono.setText("");
        email.setText("");
        direccion.setText("");
        control.requestFocus();
    }

    //Método para validar los editText, devuelve un valor booleano,
    // verdadero si ningun campo esta vacío y con la estructura correcta o falso si alguna
    // condicion no se cumple y muestra un mensaje de error con instrucciones.
    public boolean validaciones(){
        boolean bandera = true;
        if(control.getText().toString().isEmpty()){
            control.setError("Dato requerido");
            bandera = false;
        }
        if(nombre.getText().toString().isEmpty()){
            nombre.setError("Dato requerido");
            bandera = false;
        }
        if(apellidos.getText().toString().isEmpty()){
            apellidos.setError("Dato requerido");
            bandera = false;
        }
        if(carrera.getText().toString().isEmpty()){
            carrera.setError("Dato requerido");
            bandera = false;
        }
        if (email.getText().toString().isEmpty()){
            email.setError("Dato requerido");
            bandera = false;
        } else if(!email.getText().toString().matches("[a-zA-Z0-9]+[-_.]*[a-zA-Z0-9]+\\@[a-zA-Z]+\\.[a-zA-Z]+")){
            email.setError("Introduzca una dirección de correo electrónico válida");
            bandera = false;
        }
        if (telefono.getText().toString().isEmpty()){
            telefono.setError("Dato requerido");
            bandera = false;
        } else if(!telefono.getText().toString().matches("(\\+?[0-9]{2,3}\\-)?([0-9]{10})")){
            telefono.setError("El telefono debe contener 10 digitos");
            bandera = false;
        }
        if(direccion.getText().toString().isEmpty()){
            direccion.setError("Este campo es obligatorio");
            bandera = false;
        }
        return bandera;
    }

    //Valida que el campo control se encuentre lleno y manda un mensaje de advertencia si no es así.
    public boolean validarConsulta() {
        boolean bandera = true;
        if (control.getText().toString().isEmpty()) {
            control.setError("Se requiere un criterio de busqueda");
            bandera = false;
        }
        return bandera;
    }
}