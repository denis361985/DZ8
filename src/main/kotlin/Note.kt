data class Note (
    var id: Int ,
    var title: String,
    var text: String,
    var comments: MutableList<Comment> = mutableListOf()
)