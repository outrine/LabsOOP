import data.Students
import react.*
import react.dom.li
import react.dom.ol

interface RStudentProps : RProps {
    var student: Students
}

interface RStudentListProps : RProps {
    var students : Array<Students>
}

fun RBuilder.rstudent(student: Students) =
    child(
        functionalComponent<RStudentProps> {
            +"${it.student.firstname} ${it.student.surname}"
        }
    ){
        attrs.student = student
    }

fun RBuilder.rstudentList(students: Array<Students>) =
       child(functionalComponent<RStudentListProps> { props ->
           props.students.map {
               li {
                   rstudent(it)
               }
           }
       }){
           attrs.students = students
       }