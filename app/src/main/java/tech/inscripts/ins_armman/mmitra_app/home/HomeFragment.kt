package tech.inscripts.ins_armman.mmitra_app.home


import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import tech.inscripts.ins_armman.mmitra_app.R
import kotlinx.android.synthetic.main.fragment_home.*
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class HomeFragment : Fragment() {

    // 1 min = 60000 milliseconds
    private var timeCountInMilliSeconds = (1 * 60000).toLong()
    private val totalDays= 280
    var daysIntoMilliSecs : Long?=null
    var progressbar : ProgressBar ?= null
    var lmpDate ="1/07/2019"
    var daysPassed : Long ?=null
    var daysForCountdown : Int ?=null
    var eddDate : String?=null
    /**
     *  1day = 24 hours * 60 mins(1hour = 60 mins) =1440 mins
     *       = 1440 mins * 60 secs (1 min = 60 secs) = 86400 secs*/
    val day1IntoMillis =86400 * 1000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view =  inflater.inflate(R.layout.fragment_home, container, false)
       progressbar = view.findViewById(R.id.progressBarCircle)
        calculateDays()
        setProgressBarValues()
        startCountDown()
        // Inflate the layout for this fragment
        return view

    }


    private fun setProgressBarValues(){
        progressbar?.max = 120//day1IntoMillis * totalDays //280 days
        progressbar?.progress = daysPassed!!.toInt() * day1IntoMillis
        progressbar?.secondaryProgress = day1IntoMillis * totalDays
    }

    private fun startCountDown(){
       // var countDownTimer =object : CountDownTimer(daysIntoMilliSecs!!,day1IntoMillis.toLong()) {
        var countDownTimer =object : CountDownTimer(120000,1000) {
            override fun onFinish() {
                Toast.makeText(activity,"countdown finished",Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {

                var milliSecToSec = millisUntilFinished/1000
               var secToMin = milliSecToSec/60
                var minToHour = secToMin/60
                var hourToDay = minToHour/24
                textViewTime?.text =secToMin.toString() //"$hourToDay days"
                progressbar?.progress = milliSecToSec.toInt()
                txtEDD?.text=" expected delivery date: $eddDate"

            }

        }.start()

        countDownTimer.start()
    }

    private fun calculateDays(){
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy",Locale.US)
        val today = Date()
        val s = dateFormatter.format(today)
        Log.e("current date ",s)
        Log.e("lmp date ",lmpDate)
        var from = dateFormatter.parse(s)
        var to = dateFormatter.parse(lmpDate)
        var diff = from.time - to.time
         daysPassed = TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS)
        Log.e("days passed ",daysPassed.toString())
        daysForCountdown= totalDays - daysPassed!!.toInt()
        Log.e("days remaining ",daysForCountdown.toString())
        /*** daysForCountdown * day1IntoSecs * timeCountInMilliSeconds = daysForCountdown * 86400 * 1000 */
        daysIntoMilliSecs = daysForCountdown!! * day1IntoMillis.toLong()
        val calendar = Calendar.getInstance()
        calendar.time = dateFormatter.parse(lmpDate)
        calendar.add(Calendar.DAY_OF_MONTH, 280)
        eddDate = dateFormatter.format(calendar.time)
        Log.e("EDD ",eddDate)
    }


}
