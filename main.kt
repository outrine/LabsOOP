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
import org.w3c.dom.HTMLSelectElement
import kotlinx.html.js.*
import kotlinx.html.InputType

var ascending = true

fun main() {
    document.getElementById("root")!!

        .append {
            h1 {
                attributes += "id" to "cap"
                +"List students"
                onClickFunction = onCLickFunctionSort()
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
            select {
                attributes += "id" to "selector"
                option {
                    attributes += "value" to "darkslategray"
                    +"Темно-серый"
                }
                option {
                    attributes += "value" to "cyan"
                    +"Циан"
                }
                onClickFunction = {
                    val selectColor =
                        document.getElementById("selector")!!
                                as HTMLSelectElement
                    val cap =
                        document.getElementById("cap")!!
                    cap.setAttribute("style", "color:${selectColor.value}")
                }
            }

            input(InputType.radio) {
                attributes += "id" to "tomato"
                attributes += "name" to "contact"
                attributes += "value" to "tomato"
                onClickFunction = function("tomato")
            }
            label {
                +"Томатный"
            }

            input(InputType.radio) {
                attributes += "id" to "orange"
                attributes += "name" to "contact"
                attributes += "value" to "orange"
                onClickFunction = function("orange")

            }
            label {
                +"Апельсин"
            }

            input(InputType.radio) {
                attributes += "id" to "navajowhite"
                attributes += "name" to "contact"
                attributes += "value" to "navajowhite"
                onClickFunction = function("navajowhite")
            }
            label {
                +"Кремовый"
            }
        }
}
private fun function(newColor: String): (Event) -> Unit {
    return {
        val root = document.getElementById("root")!!
        root.setAttribute("style", "color:${newColor}")
    }
}

private fun onClickFunctionCheck(Student :Students): (Event) -> Unit {
    return{
        val student = document.getElementById(Student.firstname)!!
        if (Student.in_place) {
            student.setAttribute("style", "color:gray")
            Student.in_place = false
        }
        else {
            student.setAttribute("style", "color:newColor")
            Student.in_place = true
        }
    }
}

private fun onCLickFunctionSort(): (Event) -> Unit {
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

