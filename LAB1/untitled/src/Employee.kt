package com.example.myapp

// Abstract class Employee
abstract class Employee(val name: String, val empId: Int) {
    abstract fun displayDetails()
}

// TemporaryStaff (extends Employee)
class TemporaryStaff(
    name: String,
    empId: Int,
    val hourlyRate: Double,
    val hoursWorked: Int
) : Employee(name, empId) {

    fun calculateMonthlySalary(): Double {
        return hourlyRate * hoursWorked
    }

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

    fun applyBonus() {
        println("$name received a 10% bonus! New salary: $${monthlySalary * 1.1}")
    }

    override fun displayDetails() {
        println("Permanent Staff Details:")
        println("Name: $name")
        println("ID: $empId")
        println("Monthly Salary: $$monthlySalary")
        println("Benefits: $benefits")
    }
}
