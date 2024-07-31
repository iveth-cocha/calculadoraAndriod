package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    private lateinit var txtNum: TextView
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnSum: Button
    private lateinit var btnMin: Button
    private lateinit var btnMul: Button
    private lateinit var btnDiv: Button
    private lateinit var btnPunto: Button
    private lateinit var btnIgual: Button
    private lateinit var btnClear: Button
    private lateinit var btnTan: Button
    private lateinit var btnCos: Button
    private lateinit var btnSen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNum = findViewById(R.id.txtNum)
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnSum = findViewById(R.id.btnSum)
        btnMin = findViewById(R.id.btnMin)
        btnMul = findViewById(R.id.btnMul)
        btnDiv = findViewById(R.id.btnDiv)
        btnPunto = findViewById(R.id.btnPunto)
        btnIgual = findViewById(R.id.btnIgual)
        btnClear = findViewById(R.id.bntDelete)
        btnTan = findViewById(R.id.btnTan)
        btnCos = findViewById(R.id.btnCos)
        btnSen = findViewById(R.id.btnSen)

        viewModel.display.observe(this, Observer {
            txtNum.text = it
        })

        btn0.setOnClickListener { viewModel.updateDisplay("0") }
        btn1.setOnClickListener { viewModel.updateDisplay("1") }
        btn2.setOnClickListener { viewModel.updateDisplay("2") }
        btn3.setOnClickListener { viewModel.updateDisplay("3") }
        btn4.setOnClickListener { viewModel.updateDisplay("4") }
        btn5.setOnClickListener { viewModel.updateDisplay("5") }
        btn6.setOnClickListener { viewModel.updateDisplay("6") }
        btn7.setOnClickListener { viewModel.updateDisplay("7") }
        btn8.setOnClickListener { viewModel.updateDisplay("8") }
        btn9.setOnClickListener { viewModel.updateDisplay("9") }

        btnSum.setOnClickListener { viewModel.updateDisplay("+") }
        btnMin.setOnClickListener { viewModel.updateDisplay("-") }
        btnMul.setOnClickListener { viewModel.updateDisplay("*") }
        btnDiv.setOnClickListener { viewModel.updateDisplay("/") }
        btnPunto.setOnClickListener { viewModel.updateDisplay(".") }

        btnIgual.setOnClickListener { viewModel.calculate() }
        btnClear.setOnClickListener { viewModel.clear() }

        btnTan.setOnClickListener { viewModel.calculateTrigonometric("tan") }
        btnCos.setOnClickListener { viewModel.calculateTrigonometric("cos") }
        btnSen.setOnClickListener { viewModel.calculateTrigonometric("sin") }
    }
}
