import data.Students
import data.studentList
import org.w3c.dom.events.Event
import react.*
import react.dom.h2
import react.dom.ol

interface Rlesson : RProps {
    var lesson : String
    var listStudent :Array<Students>
}
interface RStudentListState :RState{
    var present: Array<Boolean>
}
class RStudentlesson : RComponent<Rlesson,RStudentListState>(){
    override fun componentWillMount() {
        state.apply {
            present = Array(props.listStudent.size){false}
        }
    }
    override fun RBuilder.render() {
        h2 {
            +props.lesson
        }
        ol {
                rstudentList(props.listStudent,state.present, onIndex())
        }
    }
    private fun onIndex(): (Int) -> (Event) -> Unit = {
        onClick(it)
    }
    private fun onClick(index: Int) :(Event) -> Unit = {
        setState {
            present[index] = !present[index]
        }
    }
}
    fun RBuilder.lesson()=
        child(RStudentlesson::class){
            attrs.lesson = "OOP"
            attrs.listStudent = studentList.toTypedArray()
        }
