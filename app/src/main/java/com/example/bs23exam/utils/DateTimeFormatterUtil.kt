package com.example.bs23exam.utils

import com.crowncoder.mowall.common.other.Constant
import java.text.SimpleDateFormat

class DateTimeFormatterUtil {

    companion object{
        fun formatDateTime(date: String):String{
            val parser = SimpleDateFormat(Constant.DATE_FORMATTER_PARSER)
            val formatter = SimpleDateFormat(Constant.DATE_FORMATTER)
            return formatter.format(parser.parse(date))
        }
    }

}