package com.dlogan.android.tvmaze.data

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class EpgDatabaseTest{

//    lateinit var instrumentationContext: Context
//
//    @Before
//    fun setup() {
//        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
//    }

    //val context = androidx.test.InstrumentationRegistry.getTargetContext()


    @Test
    fun insert() {
        val db: EpgDatabase = EpgDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().context)
        db.scheduledShowDao().nukeTable()

        db.scheduledShowDao().insert(createDummyScheduleItem(1, 10))

        var count = db.scheduledShowDao().count()
        assertEquals(1, count)

    }

    private fun createDummyScheduleItem(id: Long, showId: Long) :ScheduledShow  {
        return ScheduledShow(id, null, null, null, null, null, null, null, null, null, null, showId)
    }

}
