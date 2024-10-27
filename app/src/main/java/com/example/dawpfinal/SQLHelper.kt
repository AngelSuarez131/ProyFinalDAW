package com.example.dawpfinal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLHelper (context: Context) : SQLiteOpenHelper (context,"pfinal.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion =
            "CREATE TABLE usuarios " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "correo TEXT, pswrd TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS usuarios"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun agnadirDatos(cor: String, pwrd: String) {

        val datos = ContentValues()
        datos.put("correo", cor)
        datos.put("pswrd", pwrd)


        val db = this.writableDatabase
        db.insert("usuarios", null, datos)

    }

    fun verificarUsuario(cor: String, pwrd: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM usuarios WHERE correo = ? AND pswrd = ?"
        val cursor = db.rawQuery(query, arrayOf(cor, pwrd))
        val exists = cursor.count > 0
        cursor.close()
        return exists

    }
}