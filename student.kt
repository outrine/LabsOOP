package data

data class Students (
    val firstname: String,
    val surname: String
)

val studentList =
    arrayListOf(
        Students("Sheldon", "Cooper"),
        Students("Howard", "Wolowitz"),
        Students("Leonard ", "Hofstadter"),
        Students("Leslie", "Winkle"),
        Students("Emily ", "Sweeney"),
        Students("George", "Cooper"),
        Students("Timur", "Seksembaev")
    )