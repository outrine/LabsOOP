package data

data class Students (
    val firstname: String,
    val surname: String
)

val studentList =
    arrayOf(
        Students("Sheldon", "Cooper"),
        Students("Leonard", "Hofstadter"),
        Students("Howard", "Wolowitz"),
        Students("Leslie", "Winkle"),
        Students("Emily ", "Sweeney"),
        Students("George", "Cooper"),
        Students("Timur", "Seksembaev")
    )