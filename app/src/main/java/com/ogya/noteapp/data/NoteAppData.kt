package com.ogya.noteapp.data

data class CourseInfo(val courseId: String,
                      val title: String)


data class NoteInfo(val course: CourseInfo,
                    var title: String)