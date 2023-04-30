package com.ogya.noteapp.data

data class CourseInfo(val courseId: String,
                      val title: String)


data class NoteInfo(var course: CourseInfo,
                    var title: String,
                    var text: String)
