fun main() {
    // Task 1: whoAmI function
    println("Task 1: whoAmI")
    println(whoAmI("Hello"))      // String
    println(whoAmI(42))          // Int
    println(whoAmI(3.14))        // Double
    println(whoAmI(true))        // Boolean
    println(whoAmI(listOf(1, 2))) // List
    println()

    // Task 2: Circle calculations
    println("Task 2: Circle Calculations")
    val radius = 5.0
    println("Circumference: ${calculateCircumference(radius)}")
    println("Area: ${calculateArea(radius)}")
    println()

    // Task 3: Greetings with driving license check
    println("Task 3: Greetings")
    greetings("Alice", 25)
    greetings("Bob", 16)
    println()

    // Task 4: Print names with index
    println("Task 4: Print Names")
    val names = listOf("Alice", "Bob", "Charlie", "Diana", "Eve")
    printNames(names)
}

// Task 1: Function to return the type of the parameter
fun whoAmI(param: Any): String {
    return when (param) {
        is String -> "String"
        is Int -> "Int"
        is Double -> "Double"
        is Boolean -> "Boolean"
        is List<*> -> "List"
        else -> "Unknown type"
    }
}

// Task 2: Circle calculations
const val PI = 3.14

fun calculateCircumference(radius: Double): Double {
    return 2 * PI * radius
}

fun calculateArea(radius: Double): Double {
    return PI * radius * radius
}

// Task 3: Greetings with driving license check
fun greetings(name: String, age: Int) {
    println("Hello, $name! You are $age years old.")

    when {
        age >= 18 -> println("You are eligible to apply for a driving license.")
        age > 0 -> println("You need to wait ${18 - age} more years to apply for a driving license.")
        else -> println("Invalid age provided.")
    }
}

// Task 4: Store and print names
fun printNames(names: List<String>) {
    for ((index, name) in names.withIndex()) {
        println("$index: $name")
    }
}