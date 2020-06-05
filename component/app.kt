package component

import data.*
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*

interface AppProps : RProps {
    var students: Array<Student> }
interface AppState : RState {
    var presents: Array<Array<Boolean>>
    var less: Array<Lesson>}

interface Route : RProps {
    var number: String
}
class App : RComponent<AppProps, AppState>() {
    override fun componentWillMount() {
        state.less = lessonsList
        state.presents = Array(state.less.size) {
            Array(props.students.size) { false }
        } }
    override fun RBuilder.render() {
        header {
            h1 { +"App" }
            nav {
                ul {
                    li { navLink("/lessons") { +"Lessons" } }
                    li { navLink("/students") { +"Students" } }
                    li {navLink("/addLesson") {+"Add new lesson"} }      //add
                } } }
        switch {
            route("/lessons",
                exact = true,
                render = {
                    lessonList(state.less)
                })
            route("/students",
                exact = true,
                render = {
                    studentList(props.students)
                })
            route("/addLesson",
                exact = true,
                render={
                    Add (Function())
                })
            route("/lessons/:number",
                render = { route_props: RouteResultProps<Route> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val lesson = state.less.getOrNull(num)
                    if (lesson != null)
                        lessonFull(
                            lesson,
                            props.students,
                            state.presents[num]
                        ) { onClick(num, it) }
                    else
                        p { +"No such lesson" }
                })
            route("/students/:number",
                render = { route_props: RouteResultProps<Route> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val student = props.students.getOrNull(num)
                    if (student != null)
                        studentFull(
                            state.less,
                            student,
                            state.presents.map {
                                it[num]
                            }.toTypedArray()
                        ) { onClick(it, num) }
                    else
                        p { +"No such student" }
                })
        } }
    private fun onClick(indexLesson: Int, indexStudent: Int) =
        { _: Event ->
            setState {
                presents[indexLesson][indexStudent] =
                    !presents[indexLesson][indexStudent]
            } }
    private fun Function():(String) -> Unit = { str->
        setState {
            presents += arrayOf(Array(props.students.size){false})
            less += Lesson(str)
        } }
}
fun RBuilder.app(
    students: Array<Student>
) = child(App::class) {
    attrs.students = students
}