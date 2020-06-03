import data.Students
import org.w3c.dom.events.Event
import react.*
import react.dom.li

interface RStudentListProps : RProps {
    var students: Array<Students>
}
fun RBuilder.rstudentList(students: Array<Students>, state : Array<Boolean>, onClick: (Int) -> (Event)->Unit) =
    child(functionalComponent<RStudentListProps> {props ->
        props.students.mapIndexed {index, student ->
            li {
                rstudent(student, state[index],onClick(index))
                }
            }
    }){
        attrs.students = students
    }


