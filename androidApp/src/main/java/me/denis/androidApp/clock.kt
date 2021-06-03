package me.denis.androidApp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class clock : AppCompatActivity(), View.OnClickListener {
    private val notificationId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        // Set onClick Listener
        findViewById<View>(R.id.setBtn).setOnClickListener(this)
        findViewById<View>(R.id.cancelBtn).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)

        // Intent
        val intent = Intent(this@clock, AlarmReceiver::class.java)
        intent.putExtra("notificationId", notificationId)
        intent.putExtra("message", editText.text.toString())

        // PendingIntent
        val pendingIntent = PendingIntent.getBroadcast(
            this@clock, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        // AlarmManager
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        when (view.id) {
            R.id.setBtn -> {
                val hour = timePicker.currentHour
                val minute = timePicker.currentMinute

                // Create time.
                val startTime = Calendar.getInstance()
                startTime[Calendar.HOUR_OF_DAY] = hour
                startTime[Calendar.MINUTE] = minute
                startTime[Calendar.SECOND] = 0
                val alarmStartTime = startTime.timeInMillis

                // Set Alarm
                alarmManager[AlarmManager.RTC_WAKEUP, alarmStartTime] = pendingIntent
                Toast.makeText(this, "Готово!", Toast.LENGTH_SHORT).show()
            }
            R.id.cancelBtn -> {
                alarmManager.cancel(pendingIntent)
                Toast.makeText(this, "Отменено.", Toast.LENGTH_SHORT).show()
            }
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
            R.id.action_alarm -> {
                val intent = Intent(this, alarm::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}