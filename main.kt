import data.StudentList
import data.Students
import kotlinx.html.js.h1
import kotlinx.html.js.li
import kotlinx.html.js.ol
import kotlinx.html.js.onClickFunction
import kotlinx.html.dom.append
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.clear
import org.w3c.dom.HTMLElement
import kotlinx.html.js.*
import kotlinx.html.InputType
import kotlinx.html.*

var ascending = true

fun main() {
    document.getElementById("root")!!

        .append {
            h1 {
                +"Students"
                onClickFunction = onCLickFunction()
            }
            ol {
                attributes += "id" to "listStudents"
                StudentList.map {
                    li {
                        attributes += "id" to it.firstname
                        +"${it.firstname} ${it.surname}"
                        onClickFunction = onClickFunctionCheck(it)
                    }
                }
            }
            input(options = arrayListOf("white","tomato","pink"))
        }
}


private fun LI.onClickFunctionCheck(Student :Students): (Event) -> Unit {
    return{
        val student = document.getElementById(Student.firstname)!!
        if (Student.in_place) {
            student.setAttribute("style", "color:grey")
            Student.in_place = false
        }
        else {
            student.setAttribute("style", "color:newColor")
            Student.in_place = true
        }
    }
}

private fun H1.onCLickFunction (): (Event) -> Unit {
    return {
        val listStudents = document.getElementById("listStudents")!!
        listStudents.clear()
        listStudents.append {
            if (ascending)
                StudentList.sortBy { it.firstname }
            else
                StudentList.sortByDescending { it.firstname }

            ascending = !ascending

            StudentList.map {
                li {
                    attributes += "id" to it.firstname
                    +"${it.firstname} ${it.surname}"
                    onClickFunction = onClickFunctionCheck(it)
                }
            }
        }
    }
}

fun TagConsumer<HTMLElement>.input(options : List<String>,
                                   classes : String? = null,
                                   block : P.() -> Unit = {})
        : HTMLElement = p(classes) {
    options.forEach {
        +it
        input (InputType.radio, name = "colors"){
            value = it
            onClickFunction = {
                val color =  document.getElementById("root")!!
                color.setAttribute("style","color: ${value}")
            }
        }
    }
    block()
}

