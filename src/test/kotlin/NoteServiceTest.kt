import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {
    @Test
    fun addNoteIfListEmpty() {
        val service = NoteService()
        val note = Note(1, "First Note", "First Text")
        service.addNote(note)
        val result = note.id
        assertEquals(1, result)
    }

    @Test
    fun deleteNoteWithExistId() {
        val service = NoteService()
        val note = Note(1, "First Title", "First Text",)
        service.addNote(note)
        service.deleteNote(1)
        val result = service.notes.size
        assertEquals(0, result)
    }

    @Test
    fun addCommentIfCommentsListIsEmpty() {
        val service = NoteService()
        val comment = Comment(1,"First Comment")
        val note = Note(1,"First Title","First Text",)
        service.addNote(note)
        service.createComments(1, comment)
        val result = note.comments.size
        assertEquals(1, result)
    }

    @Test
    fun addCommentIfCommentsListIsNotEmpty() {
        val service = NoteService()
        val commentFirst = Comment(1,"First Comment")
        val commentSecond = Comment(2,"Second Comment")
        val note = Note(1,"First Title","First Text")

        service.addNote(note)
        service.createComments(1, commentFirst)
        service.createComments(4, commentSecond)

        val result = commentSecond.id

        assertEquals(2, result)
    }
}

