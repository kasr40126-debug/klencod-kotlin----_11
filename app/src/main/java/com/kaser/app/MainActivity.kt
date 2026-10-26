
package com.kaser.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import android.content.Intent
import android.os.Handler
import android.os.Looper

class MainActivity : Activity() {

    private val jokes = arrayOf(
        "واحد كسول حلم أنه يشتغل، قام من النوم تعبان 😂",
        "مدرس رياضيات تزوج مدرسة رياضيات، خلفوا مسائل 😂",
        "بخيل دخل مطعم قرأ قائمة الطعام وشبع 😂",
        "واحد اشترى ساعة ضد الماء، عطشها وسقاها 😂",
        "طالب قال للمدرس نسيت القلم، قال اكتب بإصبعك 😂",
        "ليش الكمبيوتر بردان؟ لأنه فاتح الويندوز 😂",
        "واحد نام متأخر، فاته الحلم 😂",
        "واحد راح للدكتور قال أنسى كثير، قال من متى؟ قال من متى إيش؟ 😂",
        "واحد اشترى سرير دورين، نام فوق عشان يحلم من فوق 😂",
        "طالب كتب في الاختبار الله أعلم، المدرس كتب الله ناجح وأنت راسب 😂",
        "واحد سأل صاحبه جوالك ذكي؟ قال نعم، كل ما أشحنه ينام 😂",
        "واحد دخل المكتبة يسأل عن كتاب تقوية الذاكرة، نسي اسمه 😂",
        "واحد فتح الثلاجة شاف الجيلي يرجف، قال لا تخاف جاي آخذ الماء 😂",
        "واحد قال أنا سريع، رجع يثبت لهم بعد يومين 😂",
        "واحد سأل صاحبه كم الساعة؟ قال الساعة بخير الحمد لله 😂",
        "واحد راح يشتري نظارة، شاف الأسعار قال خلوني أعيش أعمى 😂",
        "واحد قال لصاحبه عندي ذاكرة قوية، قال من متى؟ قال نسيت 😂",
        "واحد اشترى باب جديد، جلس ينتظر البيت يفتح 😂",
        "واحد سأل الكمبيوتر تحبني؟ قال له: يعتمد على الاتصال 😂",
        "واحد راح ينام بدري، لقى النوم مقفل 😂"
    )

    private var index = 0
    private lateinit var jokeText: TextView
    private val handler = Handler(Looper.getMainLooper())

    private val timer = object : Runnable {
        override fun run() {
            nextJoke()
            handler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(8, 65, 48)
        window.navigationBarColor = Color.rgb(18, 27, 34)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(22, 24, 22, 20)
            setBackgroundColor(Color.rgb(18, 27, 34))
        }

        val title = TextView(this).apply {
            text = "😂 الكاسر آب 😂"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(50, 220, 150))
            setTypeface(null, 1)
        }
        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "ضحكتك علينا والباقي على الله!"
            textSize = 17f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(0, 14, 0, 24)
        }
        root.addView(subtitle)

        jokeText = TextView(this).apply {
            text = jokes[0]
            textSize = 23f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(18, 20, 18, 20)
            setBackgroundColor(Color.rgb(35, 58, 48))
        }

        root.addView(
            jokeText,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
            )
        )

        val next = Button(this).apply {
            text = "😂 نكتة ثانية"
            isAllCaps = false
            setOnClickListener { nextJoke() }
        }
        root.addView(next)

        val share = Button(this).apply {
            text = "📲 مشاركة النكتة"
            isAllCaps = false
            setOnClickListener {
                val send = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        jokes[index] + "\n\nالكاسر آب 😂"
                    )
                }
                startActivity(Intent.createChooser(send, "مشاركة النكتة"))
            }
        }
        root.addView(share)

        val footer = TextView(this).apply {
            text = "صناعة كاسر الضاعني"
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.LTGRAY)
            setPadding(0, 18, 0, 0)
        }
        root.addView(footer)

        setContentView(root)
        handler.postDelayed(timer, 5000)
    }

    private fun nextJoke() {
        index = (index + 1) % jokes.size
        jokeText.text = jokes[index]
    }

    override fun onDestroy() {
        handler.removeCallbacks(timer)
        super.onDestroy()
    }
}
