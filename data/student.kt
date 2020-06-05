package data

data class Student (
    val firstname: String,
    val surname: String
)

val studentList =
    arrayOf(
        Student("Sheldon", "Cooper"),
        Student("Leonard", "Hofstadter"),
        Student("Howard", "Wolowitz"),
        Student("Leslie", "Winkle"),
        Student("Emily ", "Sweeney"),
        Student("George", "Cooper"),
        Student("Timur", "Seksembaev")
    )