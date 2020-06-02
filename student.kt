package data

data class Students (
    val firstname: String,
    val surname: String,
    var in_place: Boolean
)

val StudentList =
    arrayListOf(
        Students("Sheldon", "Cooper",true),
        Students("Howard", "Wolowitz",true),
        Students("Leonard ", "Hofstadter",true),
        Students("Leslie", "Winkle",true),
        Students("Emily ", "Sweeney",true),
        Students("George  ", "Cooper",true),
        Students("Timur  ", "Seksembaev",true)
    )