package br.com.pabloferreira.lmsapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "disciplina")
class Disciplina : Serializable {

    @PrimaryKey
    var id: Long = 0
    var name = ""
    var description = ""


    override fun toString(): String {
        return "Disciplina(name='$name')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}