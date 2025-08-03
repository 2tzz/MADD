package com.example.myapp

// Shape Interface
interface Shape {
    fun setup()
    fun draw()
}

// Circle (implements Shape)
class Circle(private val radius: Double) : Shape {
    override fun setup() {
        println("Setting up Circle with radius $radius")
    }

    override fun draw() {
        println("Drawing Circle (Area: ${3.14 * radius * radius})")
    }
}

// Rectangle (implements Shape)
class Rectangle(private val width: Double, private val height: Double) : Shape {
    override fun setup() {
        println("Setting up Rectangle (Width: $width, Height: $height)")
    }

    override fun draw() {
        println("Drawing Rectangle (Area: ${width * height})")
    }
}