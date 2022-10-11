class NoteService {

    var notes = mutableListOf<Note>()

    fun addNote (note: Note) {
        if (notes.isEmpty()){note.id = 1} else {note.id = notes.last().id + 1}
        notes += note
    }

    fun createComments (idNote: Int, comment: Comment) {
        for (note in notes) {
            if (note.id == idNote) {
                if (note.comments.isEmpty()) {comment.id = 1} else {comment.id = note.comments.last().id + 1}
            }
            note.comments += comment
        }
    }

    fun deleteNote (idNote: Int) {
        val iterator = notes.iterator()
        while (iterator.hasNext()) {
            if (idNote == iterator.next().id) {iterator.remove()}
        }
    }

    fun editNote (idNote: Int, title: String, text: String) {
        for (note in notes) {
            if (note.id == idNote) {
                note.title = title
                note.text = text
            }
        }
    }

    fun editComments (idNote: Int, idCom: Int, text: String) {
        for (note in notes) {
            if (note.id == idNote) {
                for (comment in note.comments) {
                    if (comment.id == idCom && !comment.isDelete) {
                        comment.text = text
                    }
                }
            }
        }
    }

    fun getAll(): List<Note>? {
        return notes.ifEmpty { null }
    }

    fun getById (idNote: Int): Note {
        for (note in notes) {
            if (idNote == note.id) {
                return note
            }
        }
        throw PostNotFoundException("Такая заметка не найдена")
    }

    fun getComments (idNote: Int): List<Comment> {
        for (note in notes) {
            if (idNote == note.id) {
                return note.comments
            }
        }
        throw PostNotFoundException("У заметки нет комментариев")
    }

    fun deleteComment (idCom: Int) {
        for (note in notes) {
            for (comments in note.comments) {
                if (idCom == comments.id && !comments.isDelete) {
                    comments.isDelete = true
                }
            }
        }
    }

    fun restoreComments (idCom: Int) {
        for (note in notes) {
            for (comments in note.comments) {
                if (idCom == comments.id && comments.isDelete) {
                    comments.isDelete = false
                }
            }
        }
    }
}