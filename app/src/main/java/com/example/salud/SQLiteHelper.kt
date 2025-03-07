package com.example.salud


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper

// Clase para manejar la base de datos SQLite
class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla en la base de datos
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_CORREO TEXT UNIQUE,
                $COLUMN_EDAD INTEGER,
                $COLUMN_PESO REAL,
                $COLUMN_ALTURA REAL
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar la tabla si ya existe y crearla de nuevo
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Función para insertar un nuevo registro
    fun insertUser(nombre: String, correo: String, edad: Int, peso: Double, altura: Double): Boolean {
        val db = writableDatabase
        val values = android.content.ContentValues()

        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE correo = ?", arrayOf(correo))
        if (cursor.count > 0) {
            cursor.close()
            return false  // Si el correo ya existe, no insertamos
        }
        val contentValues = ContentValues().apply {
            values.put(COLUMN_NOMBRE, nombre)
            values.put(COLUMN_CORREO, correo)
            values.put(COLUMN_EDAD, edad)
            values.put(COLUMN_PESO, peso)
            values.put(COLUMN_ALTURA, altura)
        }
        val result= db.insert(TABLE_NAME, null, values)
        return result != -1L
    }

    // Función para obtener todos los registros
    fun getAllUsers(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }


    // Función para actualizar un registro
    fun updateUser(id: Int, nombre: String, correo: String, edad: Int, peso: Double, altura: Double): Int {
        val db = writableDatabase
        val values = android.content.ContentValues()
        values.put(COLUMN_NOMBRE, nombre)
        values.put(COLUMN_CORREO, correo)
        values.put(COLUMN_EDAD, edad)
        values.put(COLUMN_PESO, peso)
        values.put(COLUMN_ALTURA, altura)

        return db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    // Función para eliminar un registro
    fun deleteUser(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    companion object {
        const val DATABASE_NAME = "usuarios.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CORREO = "correo"
        const val COLUMN_EDAD = "edad"
        const val COLUMN_PESO = "peso"
        const val COLUMN_ALTURA = "altura"
    }
}
