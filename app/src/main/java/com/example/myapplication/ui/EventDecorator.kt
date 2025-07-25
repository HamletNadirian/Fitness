package com.example.myapplication.ui

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator(context: Context, dates: Collection<CalendarDay>) : DayViewDecorator {
    private val dates: Collection<CalendarDay> = dates
    private val drawable =
        ContextCompat.getDrawable(context, R.drawable.calendar_event_background)!!

    //override fun shouldDecorate(day: CalendarDay): Boolean = dates.contains(day)
    override fun shouldDecorate(day: CalendarDay): Boolean {
        val match = dates.contains(day)
        Log.d("Decorator", "Checking: ${day} => $match")
        Log.d("Decorator", "Dates: $dates")
        return match
    }

    override fun decorate(view: DayViewFacade) {
        view.setBackgroundDrawable(drawable)
    }
}