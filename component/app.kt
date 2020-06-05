package component
import data.*
import hoc.withDisplayName
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*
import kotlin.browser.document
interface AppProps : RProps {}
interface AppState : RState {
    var less: Array<Lesson>
    var students: Array<Student>
    var presents: Array<Array<Boolean>> }
interface Route : RProps {
    var number: String
}
class App : RComponent<AppProps, AppState>() {
    override fun componentWillMount() {
        state.less = lessonsList
        state.students = studentList
        state.presents = Array(state.less.size) {
            Array(state.students.size) { false }
        } }
    override fun RBuilder.render() {
        header {
            h1 { +"APP " }
            nav {
                ul {
                    li { navLink("/lessons") { +"Lessons" } }
                    li { navLink("/editLessons") { +"Editing the list of lesson" } }
                    li { navLink("/students") { +"Students" } }
                    li { navLink("/editStudents") { +"Editing the list of students" } }
                } } }
        switch {
            route("/lessons",
                exact = true,
                render = {
                    anyList(state.less, "Lessons", "/lessons", deleteforlesson())
                })
            route("/students",
                exact = true,
                render = {
                    anyList(state.students, "Students", "/students", deleteforstudent())
                })
            route("/lessons/:number",
                render = { route_props: RouteResultProps<Route> ->
                    val number = route_props.match.params.number.toIntOrNull() ?: -1
                    val less = state.less.getOrNull(number)
                    if (less != null)
                        anyFull(
                            RBuilder::student, less, state.students,
                            state.presents[number]
                        ) { onClick(number, it) }
                    else
                        p { +"No lesson here" }
                })
            route("/students/:number",
                render = { route_props: RouteResultProps<Route> ->
                    val number = route_props.match.params.number.toIntOrNull() ?: -1
                    val student = state.students.getOrNull(number)
                    if (student != null)
                        anyFull(
                            RBuilder::lesson, student, state.less,
                            state.presents.map {
                                it[number]
                            }.toTypedArray()
                        ) { onClick(it, number) }
                    else
                        p { +"No student here" } })
            route("/editStudents",
                render = {
                    console.log(state.less)
                    console.log(state.presents)
                    console.log(state.students)
                    function(
                        RBuilder::anyList,
                        RBuilder::StudentAdd,
                        state.students,
                        addforstudent(),
                        deleteforstudent(),
                        "Students",
                        "/students"
                    )
                })
            route("/editLessons",
                render = {
                    function(
                        RBuilder::anyList,
                        RBuilder::LessonAdd,
                        state.less,
                        addforlesson(),
                        deleteforlesson(),
                        "Lessons",
                        "/lessons"
                    )
                }) } }
    fun onClick(indexLesson: Int, indexStudent: Int) =
        { _: Event ->
            setState {
                presents[indexLesson][indexStudent] =
                    !presents[indexLesson][indexStudent]
            } }
    fun addforlesson    ():(Event) -> Unit = {
        val objectnew = document.getElementById("Lessons") as HTMLInputElement
        val redactLess = state.presents.mapIndexed { index, _ ->
            state.presents[index].plus(arrayOf(false))
        }.toTypedArray()
        setState {
            less += Lesson(objectnew.value)
            presents = redactLess } }
    fun deleteforlesson() :(Int) -> Unit = {
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
    fun addforstudent():(Event) -> Unit = {
        val objectnew = document.getElementById("Students") as HTMLInputElement
        val value = objectnew.value.split(" ")
        setState {
            students += Student(value[0],value[1])
            presents += arrayOf(Array(state.students.size){false}) } }
    fun deleteforstudent() :(Int) -> Unit = {
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
    fun RBuilder.app() =
        child(
            withDisplayName("AppHoc", App::class)
        ) {
        }


