package com.example.myapp

fun main() {
    // Employee Demo
    val tempStaff = TemporaryStaff("John", 101, 20.0, 160)
    tempStaff.displayDetails()
    println("Monthly Salary: $${tempStaff.calculateMonthlySalary()}\n")

    val permStaff = PermanentStaff("Alice", 201, 5000.0, "Health Insurance")
    permStaff.displayDetails()
    permStaff.applyBonus()

    println("\n---\n")

    // Shape Demo
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 6.0)

    circle.setup()
    circle.draw()

    println()  // Spacer

    rectangle.setup()
    rectangle.draw()
}