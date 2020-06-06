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
