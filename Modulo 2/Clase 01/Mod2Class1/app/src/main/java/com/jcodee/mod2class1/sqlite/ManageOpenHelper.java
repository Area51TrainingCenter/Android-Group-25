package com.jcodee.mod2class1.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: johannfjs
 * Date: 27/8/16
 * Time: 10:50
 */
public class ManageOpenHelper extends SQLiteOpenHelper {
    public static final String NAME = "mod2class1.db";
    public static final int VERSION = 1;

    public ManageOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    //Creación por primera vez de la base de datos en el móvil
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación de las tablas
        sqLiteDatabase.execSQL(
                "create table tb_genero (" +
                        "id integer primary key autoincrement," +
                        "descripcion varchar(50)" +
                        ")"
        );
        sqLiteDatabase.execSQL(
                "create table tb_datos (" +
                        "id integer primary key autoincrement," +
                        "correo varchar(100)," +
                        "nombre_completo varchar(200)," +
                        "id_genero integer," +
                        "contrasena varchar(100)," +
                        "foreign key (id_genero) references tb_genero (id)" +
                        ")"
        );
        sqLiteDatabase.execSQL(
                "insert into tb_genero (descripcion) values('Masculino')"
        );
        sqLiteDatabase.execSQL(
                "insert into tb_genero (descripcion) values ('Femenino')"
        );
    }

    //Actualización de la base de datos según versiones
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            //Ejecutamos las sentencias nuevas
            sqLiteDatabase.execSQL("create table tb_productos(descripcion varchar(100))");
        }
    }
}
