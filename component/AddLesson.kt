package component

import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.*
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import kotlin.browser.document

interface lessonProps :RProps{
    var clicks : (String) ->  Unit
}

    fun RBuilder.fAddLesson(click :(String) -> Unit ) =
        child(functionalComponent<lessonProps> {props ->
                input(InputType.text) {
                    attrs {
                       id = "lesson"
                    }
                }
                button {
                    +"Add"
                    attrs.onClickFunction = {
                        val nameLesson = document.getElementById("lesson") as HTMLInputElement
                        props.clicks(nameLesson.value)
                    }
                }
        }){
            attrs.clicks=click
        }