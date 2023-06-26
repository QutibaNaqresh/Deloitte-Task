package com.deloitte.deloittetask.common

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import android.widget.TimePicker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateHelper(private val listener: DateHelperListener) : OnDateSetListener, OnTimeSetListener {

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var savedDay = 0
    private var savedMonth = 0
    private var savedYear = 0
    private var savedHour = 0
    private var savedMinute = 0

    init {
        refreshDateTimeCalender()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.savedYear = year
        this.savedMonth = month
        this.savedDay = dayOfMonth
        view?.let {
            pickTime(it.context)
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.savedHour = hourOfDay
        this.savedMinute = minute

        listener?.onDatePicked(savedYear, savedMonth, savedDay, savedHour, savedMinute)

    }

    private fun refreshDateTimeCalender() {
        val cal = Calendar.getInstance()
        cal.apply {
            day = get(Calendar.DAY_OF_MONTH)
            month = get(Calendar.MONTH)
            year = get(Calendar.YEAR)
            hour = get(Calendar.HOUR)
            minute = get(Calendar.MINUTE)
        }
    }

    private fun pickDate(ctx: Context) {
        DatePickerDialog(ctx, this, year, month, day).show()
    }

    private fun pickTime(ctx: Context) {
        TimePickerDialog(ctx, this, hour, minute, false).show()
    }

    fun showDateTimeDialog(ctx: Context) {
        pickDate(ctx)
    }

    interface DateHelperListener {
        fun onDatePicked(year: Int, month: Int, dayOfMonth: Int, hourOfDay: Int, minute: Int)
    }
    companion object {
        fun calculateTimeDifference(dateString: String): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val givenDate = LocalDate.parse(dateString, formatter)
            val currentDate = LocalDate.now()
            val difference = ChronoUnit.DAYS.between(givenDate, currentDate)

            return when {
                difference < 1 -> "Today"
                difference < 2 -> "Yesterday"
                difference < 7 -> "$difference days ago"
                difference < 30 -> "${difference / 7} weeks ago"
                difference < 365 -> "${difference / 30} months ago"
                else -> "${difference / 365} years ago"
            }
        }
    }
}