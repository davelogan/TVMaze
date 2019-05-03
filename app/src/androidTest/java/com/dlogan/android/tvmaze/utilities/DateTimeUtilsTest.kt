package com.dlogan.android.tvmaze.utilities

import org.junit.Test

class DateTimeUtilsTest
{

    @Test
    fun parse() {
        var strDate = "2019-04-30T04:00:00+00:00"
        //var odt = OffsetDateTime.parse( strDate )
        //System.out.println(odt)
        var date = DateTimeUtils.dateFormatter.parse(strDate)
    }
}