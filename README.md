# Lab_7

## Задание

1. Доработайте приложение, добавив компонент, разработанный в последнем задании прошлого урока на отдельную страницу приложения и прописав маршруты к нему.

## Ход работы 

1. Добавил новую функцию в switch.

    ![switch](https://i.ibb.co/Ry2wQ19/1.jpg)

```
      route("/addLesson",
            exact = true,
            render={
                  Add (Function())
             })
```

2. Добавил в header "/addlesson li {navLink("/addLesson") {+"add more Lesson"} }.

    ![addlesson](https://i.ibb.co/YLV4D3j/2.jpg)

```
override fun RBuilder.render() {
        header {
            h1 { +"App" }
            nav {
                ul {
                    li { navLink("/lessons") { +"Lessons" } }
                    li { navLink("/students") { +"Students" } }
                    li {navLink("/addLesson") {+"Add new lesson"} }
                } } }
```

3. Исправил main.kt

    ![main](https://i.ibb.co/pdh9TMn/3.jpg)

```
import component.app
import data.*
import react.dom.render
import react.router.dom.browserRouter
import kotlin.browser.document
fun main() {
    render(document.getElementById("root")!!) {
        browserRouter {
            app(studentList) 
        } }
}
```

## Работа программы

1. Запуск

      ![Запуск](https://i.ibb.co/pLvHCNd/4.jpg)

2. Лекции 
   
      ![Лекции](https://i.ibb.co/QH9Vb10/5.jpg)

3. Студенты

      ![Студенты](https://i.ibb.co/SJCxQsm/6.jpg)

4. Окно добавления лекции
   
      ![Окно добавления лекции](https://i.ibb.co/nQktbWw/7.jpg)

5. Открытая лекция
   
      ![Открытая лекция](https://i.ibb.co/ZH2bYv1/8.jpg)   
