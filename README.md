# Lab_8

## Задание

1. Доработайте приложение из видеоуроков;
2. Разработайте компоненты, отвечающий за редактирование названия занятия и имени и фамилии студента;
3. Hазработайте компонент, отвечающий за редактирование списка элементов (с возможностью добавить или удалить элемент);
4. В качестве аргументов этому компоненту передаются компоненты для отображения и для редактирования элемента списка;
5. Добавьте в приложение страницы для редактирования списка студентов и списка занятий. 

## Ход работы 

1. Добавил компоненты в ComponentForLesson.

   ![СomponentForLesson](https://i.ibb.co/Kmgt0BD/1.jpg)

```
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
            })
    {
    }
```

   ![ComponentForStudent](https://i.ibb.co/1KWqYFR/2.jpg)

```
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
        })
        {
        }
```

1. Добавил в app функции "DeleteStudent","AddStudent","DeleteLesson","AddLesson"

    ![DeleteStudent](https://i.ibb.co/Q6S4BBk/3.jpg)

```
    fun Addstudent():(Event) -> Unit = {
        val objectnew = document.getElementById("Students") as HTMLInputElement
        val value = objectnew.value.split(" ")
        setState {
            students += Student(value[0],value[1])
            presents += arrayOf(Array(state.students.size){false}) } }
    fun Deletestudent() :(Int) -> Unit = {
        val redactStud = state.students.toMutableList().apply {
            removeAt(it) }
            .toTypedArray()
        val editedPresents = state.presents.toMutableList().apply {
            removeAt(it)}
            .toTypedArray()
        setState{
            students = redactStud
            presents= editedPresents
        }
    }
}
```
2.  Добавил в app функции "DeleteLesson","AddLesson"

    ![DeleteLesson](https://i.ibb.co/TBjSCmn/4.jpg)

```
    fun Addlesson    ():(Event) -> Unit = {
        val objectnew = document.getElementById("Lessons") as HTMLInputElement
        val redactLess = state.presents.mapIndexed { index, _ ->
            state.presents[index].plus(arrayOf(false))
        }.toTypedArray()
        setState {
            less += Lesson(objectnew.value)
            presents = redactLess } }
    fun Deletelesson() :(Int) -> Unit = {
        val redactLess = state.less.toMutableList().apply {
            removeAt(it) }
            .toTypedArray()
        val editedPresents = state.presents.mapIndexed { index, _ ->
            state.presents[index].toMutableList().apply {
                removeAt(it)
            }.toTypedArray()
        }.toTypedArray()
        setState{
            less = redactLess
            presents= editedPresents } }
```

3. Исправил ComponentForLesson

   ![ComponentForLesson](https://i.ibb.co/7Jc88mf/5.jpg)

```
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
            })
    {

    }
```

4. Исправил AnyList

   ![AnyList](https://i.ibb.co/S68YFYz/6.jpg)

```
package component

import hoc.withDisplayName
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink

interface AnyListProps<O> : RProps {
    var Object: Array<O>
    var delete: (Int) -> Unit
}

fun <T> fAnyList(name: String, path: String) =
    functionalComponent<AnyListProps<T>> {props ->
        h2 { +name }
        ul {
            props.Object.mapIndexed{ index, obj ->
                li {
                    button {
                        +"delete"
                        attrs.onClickFunction = {
                            props.delete(index)}
                    }
                    navLink("$path/$index"){
                        +obj.toString()
                    }
                }
            }
        }
    }

fun <T> RBuilder.anyList(
    anys: Array<T>,
    name: String,
    path: String,
    delete: (Int) -> Unit
) = child(
    withDisplayName(name, fAnyList<T>(name, path))
){
    attrs.Object = anys
    attrs.delete = delete
}

```

## Работа программы

1. Запуск

      ![Запуск](https://i.ibb.co/bXMcFTj/7.jpg)

2. Лекции 
   
      ![Лекции](https://i.ibb.co/XXt4PMg/8.jpg)

3. Редактрование Лекций

      ![Редактрование Лекций](https://i.ibb.co/93ymWrD/9.jpg)

4. Студенты
   
      ![Студенты](https://i.ibb.co/x3Zbtth/10.jpg)

5. Редактрование списка студентов
   
      ![Редактрование списка](https://i.ibb.co/2qzNCB9/11.jpg)
