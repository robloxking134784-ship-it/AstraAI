package com.astraai

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*
import com.astraai.ai.AstraAI

class MainActivity : Activity() {

    private lateinit var messagesContainer: LinearLayout
    private lateinit var inputField: EditText

    private val astraAI = AstraAI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE

        createInterface()
    }

    private fun createInterface() {

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(24, 20, 24, 20)
        }

        val title = TextView(this).apply {
            text = "AstraAI"
            textSize = 24f
            setTextColor(Color.rgb(30, 30, 30))
            setTypeface(null, Typeface.BOLD)
        }

        header.addView(
            title,
            LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        )

        val status = TextView(this).apply {
            text = "● Онлайн"
            textSize = 14f
            setTextColor(Color.rgb(244, 67, 54))
        }

        header.addView(status)

        val divider = View(this).apply {
            setBackgroundColor(Color.rgb(230, 230, 230))
        }

        root.addView(header)

        root.addView(
            divider,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
            )
        )

        val scrollView = ScrollView(this)

        messagesContainer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
        }

        scrollView.addView(messagesContainer)

        root.addView(
            scrollView,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val inputContainer = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(12, 10, 12, 12)
        }

        inputField = EditText(this).apply {
            hint = "Напишите сообщение..."
            textSize = 16f
            singleLine = true
            setPadding(20, 12, 20, 12)
        }

        inputContainer.addView(
            inputField,
            LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        )

        val sendButton = Button(this).apply {
            text = "➤"
            textSize = 20f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(244, 67, 54))

            setOnClickListener {
                sendMessage()
            }
        }

        inputContainer.addView(
            sendButton,
            LinearLayout.LayoutParams(60, 60)
        )

        root.addView(inputContainer)

        setContentView(root)

        addAIMessage(
            "Привет! 👋 Я AstraAI. Задавай мне вопросы."
        )
    }

    private fun sendMessage() {

        val question = inputField.text.toString().trim()

        if (question.isEmpty()) {
            return
        }

        addUserMessage(question)

        inputField.text.clear()

        val answer = astraAI.ask(question)

        addAIMessage(answer)
    }

    private fun addUserMessage(message: String) {

        val text = TextView(this).apply {
            text = message
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(244, 67, 54))
            setPadding(18, 14, 18, 14)
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.END
            setMargins(60, 8, 0, 8)
        }

        messagesContainer.addView(text, params)
    }

    private fun addAIMessage(message: String) {

        val text = TextView(this).apply {
            text = message
            textSize = 16f
            setTextColor(Color.rgb(30, 30, 30))
            setBackgroundColor(Color.rgb(245, 245, 245))
            setPadding(18, 14, 18, 14)
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.START
            setMargins(0, 8, 60, 8)
        }

        messagesContainer.addView(text, params)
    }
}
