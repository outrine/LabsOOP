# Lab_6

## Задания

1. Доработайте приложение из видео;
2. Перенесите массив lessons из  AppProps в AppState;
3. Добавьте компонент AddLesson, который позволяет добавить урок в массив lessons;
4. Другие компоненты (кроме App и AddLesson) не должны изменяться, но должны корректно работать.

## Ход работы 

1. Перенес lessons из props в Appstate.

    ![lessons](https://i.ibb.co/s5ft1bg/1.jpg)
```
interface AppProps : RProps {
    var students: Array<Students>
}

interface AppState : RState {
    var lessons: Array<Lesson>
    var presents: Array<Array<Boolean>>
}
```

2. Добавил компонент AddLesson.
    ![Add](https://i.ibb.co/1R1tJw0/2.jpg)

```
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
```

3. Отображение в программе
    ![WorkAdd](https://i.ibb.co/4Z5q2g2/7.jpg)

4. Указал название лекции
    ![lessons3](https://i.ibb.co/bsBLMT6/3.jpg)

## Работа программы

1. Работа кода

    ![Работа_кода](https://i.ibb.co/7GMZjrS/10.jpg)
