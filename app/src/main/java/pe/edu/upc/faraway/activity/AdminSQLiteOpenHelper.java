package pe.edu.upc.faraway.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cliente(codigo int primary Key AUTOINCREMENT, username text, nombre text, apellidos text, celular text, password text)");


        //db.execSQL("INSERT INTO comments (user, comment) VALUES ('Digital Learning','Esto es un comentario insertado usando el método execSQL()')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
