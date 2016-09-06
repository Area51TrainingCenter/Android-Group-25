package com.jcodee.mod2class1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jcodee.mod2class1.modelos.Combo;
import com.jcodee.mod2class1.modelos.Datos;

import java.util.ArrayList;

/**
 * Author: johannfjs
 * Date: 27/8/16
 * Time: 11:06
 */
public class Querys {
    private ManageOpenHelper dbConexion;

    public Querys(Context context) {
        dbConexion = new ManageOpenHelper(context);
    }

    public boolean validarUsuario(String correo, String contrasena) {
        //Asignamos el permiso necesario para la acción a realizar, en este caso solamente de lectura
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        //Obtenemos los datos de nuestra consulta de sql
        Cursor cursor = db.rawQuery(
                "select * from tb_datos where correo='" + correo +
                        "' and contrasena='" + contrasena + "'",
                null
        );
        //Verificamos si nuestra consulta ha traído los datos
        return cursor != null ? cursor.moveToFirst() ? true : false : false;
    }

    public boolean registrarDatos(Datos datos) {
        boolean resultado = false;
        //Asignar el permiso de escritura
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        //Creamos el contenedor de las variables con los datos
        ContentValues contentValues = new ContentValues();
        //Enviamos las variables al contenedor
        contentValues.put("correo", datos.getCorreo());
        contentValues.put("contrasena", datos.getContrasena());
        contentValues.put("nombre_completo", datos.getNombre());
        //Registramos los datos a la bd
        db.insert("tb_datos", null, contentValues);
        resultado = true;
        return resultado;
    }

    public ArrayList<Combo> obtenerGenero() {
        ArrayList<Combo> lista = new ArrayList<>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select id, descripcion from tb_genero",
                null
        );
        //Validamos que haya datos dentro de la respuesta
        if (cursor.moveToFirst()) {
            do {
                //Obtenemos los datos
                Combo item = new Combo();
                item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                item.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                //Agregamos los datos a la lista
                lista.add(item);
            } while (cursor.moveToNext());
        }
        //Retornamos a la lista con los datos
        return lista;
    }

    public ArrayList<Datos> obtenerDatos() {
        ArrayList<Datos> lista = new ArrayList<>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from tb_datos",
                null
        );
        if (cursor.moveToFirst()) {
            do {
                Datos datos = new Datos();
                datos.setNombre(cursor.getString(cursor.getColumnIndex("nombre_completo")));
                datos.setCorreo(cursor.getString(cursor.getColumnIndex("correo")));
                datos.setIdGenero(cursor.getInt(cursor.getColumnIndex("id_genero")));
                lista.add(datos);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void eliminarDato(int id) {
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        db.delete("tb_datos", "id=" + id, null);
    }

    public void actualizarDato(Datos datos) {
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (datos.getCorreo().trim().length() > 0)
            contentValues.put("correo", datos.getCorreo());
        if (datos.getContrasena().trim().length() > 0)
            contentValues.put("contrasena", datos.getContrasena());
        if (datos.getNombre().trim().length() > 0)
            contentValues.put("nombre_completo", datos.getNombre());
        db.update("tb_datos", contentValues, "id=" + datos.getId(), null);
    }

    public void insertarSesion(String correo) {
        //Obtener IdDato
        //Insertar en la tabla de sesion
        String idDato = "";
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from tb_datos where correo='" + correo + "'"
                , null);
        if (cursor.moveToFirst()) {
            do {
                idDato = cursor.getString(cursor.getColumnIndex("id"));
            } while (cursor.moveToNext());
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("existe", "1");
        contentValues.put("id_dato", idDato);
        db.insert("tb_sesion", null, contentValues);
    }

    public String existeSesion() {
        String nombreCompleto = "";
        //Consultar la tabla de sesion
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from tb_sesion a " +
                        "inner join tb_datos b on a.id_dato=b.id",
                null
        );
        if (cursor.moveToFirst()) {
            do {
                nombreCompleto = cursor.getString(cursor.getColumnIndex("nombre_completo"));
            } while (cursor.moveToNext());
        }
        return nombreCompleto;
    }

    public void eliminarSesion() {
        //Delete a la tabla de sesion
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        db.delete("tb_sesion", "1=1", null);
    }
}
