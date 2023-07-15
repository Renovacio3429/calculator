package scr.main

import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextField

class Calculator : ActionListener {

    private var frame: JFrame = JFrame("Calculator")
    private var textField: JTextField = JTextField()
    private var addButton: JButton = JButton("+")
    private var subtractionButton: JButton = JButton("-")
    private var multiplyButton: JButton = JButton("*")
    private var divisionButton: JButton = JButton("/")
    private var decimalButton: JButton = JButton(".")
    private var equalsButton: JButton = JButton("=")
    private var deleteButton: JButton = JButton("D")
    private var clearButton: JButton = JButton("C")
    private var negativeButton: JButton = JButton("-/+")
    private var numberButtons: Array<JButton>
    private var functionButtons: Array<JButton>
    private var panel: JPanel = JPanel()

    private var num1: Double = 0.0
    private var num2: Double = 0.0
    private var result: Double = 0.0
    private var operator: Char = Char.MIN_VALUE

    private var font: Font = Font("Ink Free", Font.BOLD, 30)

    init {
        frame.setSize(420, 550)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.layout = null

        textField.setBounds(50, 25, 300, 50)
        textField.font = font
        textField.isEditable = false

        functionButtons = arrayOf(
            addButton, subtractionButton, multiplyButton,
            divisionButton, decimalButton, equalsButton,
            deleteButton, clearButton, negativeButton
        )

        numberButtons = Array(10) { i -> initNumberButton(i) }

        (0..8).forEach {
            functionButtons[it].addActionListener(this)
            functionButtons[it].font = font
            functionButtons[it].isFocusable = false
        }

        negativeButton.setBounds(50, 430, 100, 50)
        deleteButton.setBounds(150, 430, 100, 50)
        clearButton.setBounds(250, 430, 100, 50)

        panel.setBounds(50, 100, 300, 300)
        panel.layout = GridLayout(4, 4, 10, 10)

        panel.add(numberButtons[1])
        panel.add(numberButtons[2])
        panel.add(numberButtons[3])
        panel.add(addButton)
        panel.add(numberButtons[4])
        panel.add(numberButtons[5])
        panel.add(numberButtons[6])
        panel.add(subtractionButton)
        panel.add(numberButtons[7])
        panel.add(numberButtons[8])
        panel.add(numberButtons[9])
        panel.add(multiplyButton)
        panel.add(decimalButton)
        panel.add(numberButtons[0])
        panel.add(equalsButton)
        panel.add(divisionButton)

        frame.add(panel)
        frame.add(negativeButton)
        frame.add(deleteButton)
        frame.add(clearButton)
        frame.add(textField)
        frame.isVisible = true
    }

    private fun initNumberButton(number: Int): JButton {
        val numberButton = JButton(number.toString())
        numberButton.addActionListener(this)
        numberButton.font = font
        numberButton.isFocusable = false
        return numberButton
    }

    override fun actionPerformed(e: ActionEvent?) {
        (0..9).forEach {
            if (e?.source == numberButtons[it]) {
                textField.text += it.toString()
            }
        }

        when (e?.source) {
            decimalButton -> {
                var text = textField.text
                val dotChar = "."
                if (text != null && !text.endsWith(dotChar)) {
                    text += "."
                }
            }

            addButton -> {
                num1 = textField.text.toDouble()
                operator = '+'
                textField.text = ""
            }

            subtractionButton -> {
                num1 = textField.text.toDouble()
                operator = '-'
                textField.text = ""
            }

            multiplyButton -> {
                num1 = textField.text.toDouble()
                operator = '*'
                textField.text = ""
            }

            divisionButton -> {
                num1 = textField.text.toDouble()
                operator = '/'
                textField.text = ""
            }

            equalsButton -> {
                num2 = textField.text.toDouble()
                when (operator) {
                    '+' -> {
                        result = num1 + num2
                    }

                    '-' -> {
                        result = num1 - num2
                    }

                    '*' -> {
                        result = num1 * num2
                    }

                    '/' -> {
                        result = num1 / num2
                    }
                }
                num1 = result
                textField.text = result.toString()
            }

            clearButton -> {
                textField.text = ""
                num1 = 0.00
                result = 0.00
            }

            deleteButton -> {
                val text = textField.text
                if (text != null && text.isNotEmpty()) {
                    textField.text = text.substring(0, text.length - 1)
                }
            }

            negativeButton -> {
                var text = textField.text.toDouble()
                text *= -1
                textField.text = text.toString()
            }
        }
    }
}