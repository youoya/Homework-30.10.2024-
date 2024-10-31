package com.example.calc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonClear.setOnClickListener {
            binding.input.text = " "
            binding.output.text = " "
        }
        binding.buttonBracketLeft.setOnClickListener {
            addToInputText("(")
        }
        binding.buttonBracketRight.setOnClickListener {
            addToInputText(")")
        }
        binding.button0.setOnClickListener {
            addToInputText("0")
        }
        binding.button1.setOnClickListener {
            addToInputText("1")
        }
        binding.button2.setOnClickListener {
            addToInputText("2")
        }
        binding.button3.setOnClickListener {
            addToInputText("3")
        }
        binding.button4.setOnClickListener {
            addToInputText("4")
        }
        binding.button5.setOnClickListener {
            addToInputText("5")
        }
        binding.button6.setOnClickListener {
            addToInputText("6")
        }
        binding.button7.setOnClickListener {
            addToInputText("7")
        }
        binding.button8.setOnClickListener {
            addToInputText("8")
        }
        binding.button9.setOnClickListener {
            addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            addToInputText(".")
        }
        binding.buttonDivision.setOnClickListener {
            addToInputText("÷")
        }
        binding.buttonMultiply.setOnClickListener {
            addToInputText("x")
        }
        binding.buttonSubtraction.setOnClickListener {
            addToInputText("-")
        }
        binding.buttonAddition.setOnClickListener {
            addToInputText("+")
        }
        binding.buttonEquals.setOnClickListener {
            showResult()
        }
        binding.buttonPercent.setOnClickListener {
            addToInputText("%")
        }
    }

    // Функция для добавления текста к полю ввода
    fun addToInputText(value: String) {
        binding.input.append(value)// Добавляем переданное значение в конец поля ввода
    }

    // Функция для получения строки ввода
    fun getInputExpression(): String {
        return binding.input.text.toString()// Возвращаем текст из поля ввода
    }

    // Функция для показа результата вычислений
    private fun showResult() {
        try {
            val expression = getInputExpression().replace("%", "/100")
            //Заменяем % на соответствующее значение для деления
            val result = ExpressionBuilder(expression).build().evaluate()
            binding.output.text =
                DecimalFormat("0.######").format(result).toString()
            binding.output.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.neon_green
                )
            )
        } catch (e: Exception) {
            binding.output.text = "Ошибка"
            binding.output.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.red
                )
            )
        }
    }
}