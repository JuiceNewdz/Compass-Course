// AdmissionTestModel.kt
package com.examination.compasscourse.model

data class AdmissionTestModel(
    var title: String,
    var questions: MutableList<Question> = mutableListOf()  // List of questions for each test
)

// TestModel.kt
data class TestModel(
    var testType: String,
    var testName: String,
    var questions: MutableList<Question> = mutableListOf() // List of questions for each test type
)

// Question.kt
data class Question(
    var questionText: String,
    var choices: MutableList<String>,  // List of possible answer choices
    var correctAnswer: String          // The correct answer from the choices
)
