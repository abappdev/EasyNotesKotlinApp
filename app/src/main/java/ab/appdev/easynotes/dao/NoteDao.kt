package ab.appdev.easynotes.dao

import ab.appdev.easynotes.entities.Notes
import androidx.room.*

@Dao
interface NoteDao {
    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Notes)

    @Delete
    fun deleteNote(note: Notes)
}