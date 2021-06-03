package me.denis.androidApp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class calendar : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        val calendarView: CalendarView = findViewById(R.id.calendar_view)
        val textview6: TextView = findViewById(R.id.textView6)
        val textview8: TextView = findViewById(R.id.textView8)
        calendarView.firstDayOfWeek = 2
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            val selectedDate = calendarView.date
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDate
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            val calenda = Calendar.getInstance()
            calenda[year, month] = dayOfMonth
            val dayOfWeek = calenda[Calendar.DAY_OF_WEEK]
            when (dayOfWeek)
            {
                2 -> textview8.text = "Понедельник"
                3 -> textview8.text = "Вторник"
                4 -> textview8.text = "Среда"
                5 -> textview8.text = "Четверг"
                6 -> textview8.text = "Пятница"
                7 -> textview8.text = "Суббота"
                1 -> textview8.text = "Воскресенье"
            }
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            textview6.text = currentDate
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_calendar -> {
                val intent = Intent(this, calendar::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_alarm-> {
                val intent = Intent(this, alarm::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}