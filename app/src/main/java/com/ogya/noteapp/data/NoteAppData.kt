package com.ogya.noteapp.data

data class CourseInfo(val courseId: String,
                      val title: String){
    override fun toString(): String {
        return title
    }
}


data class NoteInfo(var course: CourseInfo? = null,
                    var title: String? = null,
                    var text: String? = null){
    override fun toString(): String {
        return "   --course title= " + course?.title +
                "\n   --note title= " + title +
                "\n   --note test= " + text
    }
}
