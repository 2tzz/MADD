// Main.kt
fun main() {
    // Create TemporaryStaff object
    val tempStaff = TemporaryStaff("John", 101, 20.0, 160)
    tempStaff.displayDetails()
    println("Monthly Salary: $${tempStaff.calculateMonthlySalary()}\n")

    // Create PermanentStaff object
    val permStaff = PermanentStaff("Alice", 201, 5000.0, "Health Insurance")
    permStaff.displayDetails()
    permStaff.applyBonus()
}

// Abstract class Employee
abstract class Employee(val name: String, val empId: Int) {
    abstract fun displayDetails()  // Must be implemented by subclasses
}

// TemporaryStaff (extends Employee)
class TemporaryStaff(
    name: String,
    empId: Int,
    val hourlyRate: Double,
    val hoursWorked: Int
) : Employee(name, empId) {

    // Unique method for TemporaryStaff
    fun calculateMonthlySalary(): Double {
        return hourlyRate * hoursWorked
    }

    // Overriding abstract method
    override fun displayDetails() {
        println("Temporary Staff Details:")
        println("Name: $name")
        println("ID: $empId")
        println("Hourly Rate: $$hourlyRate")
        println("Hours Worked: $hoursWorked")
    }
}

// PermanentStaff (extends Employee)
class PermanentStaff(
    name: String,
    empId: Int,
    val monthlySalary: Double,
    val benefits: String
) : Employee(name, empId) {

    // Unique method for PermanentStaff
    fun applyBonus() {
        println("$name received a 10% bonus! New salary: $${monthlySalary * 1.1}")
    }

    // Overriding abstract method
    override fun displayDetails() {
        println("Permanent Staff Details:")
        println("Name: $name")
        println("ID: $empId")
        println("Monthly Salary: $$monthlySalary")
        println("Benefits: $benefits")
    }
}