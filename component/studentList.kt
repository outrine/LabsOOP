package component

import data.Students
import react.*
import react.dom.*
import react.router.dom.*

interface StudentListProps : RProps {
    var students: Array<Students>
}

val fStudentList =
    functionalComponent<StudentListProps> {
        h2 { +"Students" }
        ul {
            it.students.mapIndexed { index, student ->
                li {
                    navLink("/students/$index") {
                        +"${student.firstname} ${student.surname}"
                    }
                }
            }
        }
    }

fun RBuilder.studentList(students: Array<Students>) =
    child(fStudentList) { attrs.students = students }