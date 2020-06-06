package component
import kotlinx.html.InputType
import kotlinx.html.id
import react.*
import react.dom.*
interface Props1 :RProps{
}
fun RBuilder.LessonAdd( ) =
    child(functionalComponent<Props1> {
        input(InputType.text) {
            attrs {
                placeholder = "Write lesson name"
                id = "Lessons"
            }
            }
    }){
    }
