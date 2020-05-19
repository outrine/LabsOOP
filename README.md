# Лабораторная работа 2.5

## Работа программы

1. Общий вид программы
<img src="https://i.ibb.co/883Vgqw/image.jpg">

> Сексембаев присутствует на паре
<img src="https://i.ibb.co/234mLmY/2.jpg">

1. Смена цвета текста в списке студентов
<img src="https://i.ibb.co/9yfPgkT/image.jpg">
<img src="https://i.ibb.co/SP3gRwX/image.jpg">
<img src="https://i.ibb.co/92Tcx63/image.jpg">

1. Смена цвета заголовка - шапки списка  
<img src="https://i.ibb.co/p3ktJww/1.jpg">
<img src="https://i.ibb.co/CnFSCb1/2.jpg">

> Настройка радиокнопок не позволяет в текущей версии возвращаться к пепрвозданному виду бело-серой палитре.
1. Проверка возрата цвета после восстановления элемента списка
<img src="https://i.ibb.co/MPWRWMj/image.jpg">
<img src="https://i.ibb.co/q7cGMnD/2.jpg">
> Всё работает как надо.


## Оценка программы
- Нельзя вернуться к старой палитре, после выбора радиокнопки.
+ Программа выполняет свои функции; смена цвета в тексте не мешает читаемости текста.

## Код программы
<img src="https://i.ibb.co/bmrFRNb/1.jpg">
<img src="https://i.ibb.co/rZhbvG7/2.jpg">
<img src="https://i.ibb.co/mHBgNKh/3.jpg">

## Листинг

```import data.StudentList
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
```

## Дополнительные материалы
<img src="https://i.ibb.co/vqvtmcp/image.jpg">

Список студентов

