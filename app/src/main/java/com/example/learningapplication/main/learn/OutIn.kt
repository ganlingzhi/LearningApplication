package com.example.learningapplication.main.learn


open class Person(val age: Int, val name: String)
class Student(age: Int, name: String) : Person(age, name)
class Teacher(age: Int, name: String) : Person(age, name)

class Sample<out T>(private var t: T? = null) {

    fun get(): T? {
        return t
    }

    fun set(t: @UnsafeVariance T?) {
        this.t = t
    }
}

fun main() {
    val student = Student(11, "Tom")

    val sampleStu = Sample(student)
    val sampleTea = Sample<Teacher>()

    val listPerson: MutableList<Person> = arrayListOf()
    listPerson.add(student)
    val listStudent: MutableList<Student> = arrayListOf()

    var sampleP = Sample<Person>()
    val sampleS = Sample<Student>()
    sampleP = sampleS

    val list: MutableList<Sample<Person>> = arrayListOf()
    list.add(sampleStu)

    handle(sampleStu)
    handle(sampleTea)
    val emptyList = arrayListOf<String>()

    val testStr = "12345"
    println(testStr.substring(0, testStr.length - 1))

    emptyList.forEachIndexed { index, s ->
        println("我是空列表")
    }
}

fun handle(sample: Sample<Person>) {
    println(sample.get()?.name)
}