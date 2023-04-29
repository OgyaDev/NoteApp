package com.ogya.noteapp.datamanager

import com.ogya.noteapp.data.CourseInfo
import com.ogya.noteapp.data.NoteInfo

class DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
    }
    private fun initializeCourses(){
        var course = CourseInfo("course1", "Android Programming")
        courses.set(course.courseId, course)

        course = CourseInfo("course2", "Java Programming")
        courses.set(course.courseId, course)

        course = CourseInfo("course3", "Kotlin Programming")
        courses.set(course.courseId, course)

        course = CourseInfo("course4", "IOS Programming")
        courses.set(course.courseId, course)


    }

}