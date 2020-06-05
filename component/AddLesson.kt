package component
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.*
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import kotlin.browser.document

interface Props :RProps{
    var clicks : (String) ->  Unit }

fun RBuilder.Add(click :(String) -> Unit ) =
    child(functionalComponent<Props> {props ->
        div{
            h1{"Lesson NEW"} }
        input(InputType.text) {
            attrs {
                id = "NewLesson" } }
        button {
            +"Add"
            attrs.onClickFunction = {
                val nameLesson = document.getElementById("NewLesson") as HTMLInputElement
                props.clicks(nameLesson.value) } }
    }){
        attrs.clicks=click
    }