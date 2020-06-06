package component
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
interface Props3<O> :RProps{
    var function : (Int) -> Unit
    var Info : (Event) -> Unit
    var way : String
    var name : String
    var subject : Array<O>}
fun <O> function(
    function :RBuilder.() -> ReactElement,
    Component: RBuilder.(Array<O>, String, String,(Int) -> Unit)->ReactElement
) = functionalComponent<Props3<O>> {props ->
        h2{+"In this window, you can edit (delete or add) lectures or students"}
        h3{+"Try typing something"}
        p {
            +"Add ${props.name}"
            function()
            button {
                +"Add"
                attrs.onClickFunction = props.Info
        }
            Component(props.subject,props.name,props.way,props.function)
        }
}
fun <O> RBuilder.function(
    rComponent: RBuilder.(Array<O>, String, String,(Int) -> Unit)->ReactElement,
    AddendObject :RBuilder.() -> ReactElement,
    subject : Array<O>,
    AddNewInfo: (Event) -> Unit,
    Delite: (Int) -> Unit,
    name : String,
    way: String
) =  child(function<O>(AddendObject,rComponent)){
    attrs.Info=AddNewInfo
    attrs.function=Delite
    attrs.subject= subject
    attrs.name = name
    attrs.way = way
}
