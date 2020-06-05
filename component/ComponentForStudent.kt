package component
import kotlinx.html.InputType
import kotlinx.html.id
import react.RBuilder
import react.RProps
import react.child
import react.dom.input
import react.functionalComponent
interface Props2 : RProps {
}
fun RBuilder.StudentAdd () =
    child(functionalComponent<Props2> {
        input(InputType.text) {
            attrs {
                placeholder = "Write student name"
                id = "Students"
            }
        }
    }){

    }
