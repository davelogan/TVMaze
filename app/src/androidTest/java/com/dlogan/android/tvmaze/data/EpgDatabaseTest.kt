package com.dlogan.android.tvmaze.data

import androidx.paging.DataSource
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.dlogan.android.tvmaze.data.epg.EpgDatabase
import com.dlogan.android.tvmaze.data.epg.ScheduledShow
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class EpgDatabaseTest{

//    lateinit var instrumentationContext: Context
//
//    @Before
//    fun setup() {
//        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
//    }

    val context = androidx.test.InstrumentationRegistry.getTargetContext()
    val db: EpgDatabase = EpgDatabase.getDatabase(context)

    @Before
    fun setup() {
        db.scheduledShowDao().insert(createDummyScheduleItem(1, 10, "FAKE"))
    }

    @Test
    fun getShow() {
        val show: ScheduledShow = db.scheduledShowDao().getShow(1)
        assertNotNull(show)
    }

    @Test
    fun allShows() {
        db.scheduledShowDao().insert(createDummyScheduleItem(1, 10, "Hello"))

        val shows: DataSource.Factory<Int, ScheduledShow> = db.scheduledShowDao().allShows("Hello")
        assertNotNull(shows)
    }

    @Test
    fun insert() {
        var count = db.scheduledShowDao().count()

        db.scheduledShowDao().insert(createDummyScheduleItem(Random().nextLong(), 10, "FAKE"))

        var count2 = db.scheduledShowDao().count()

        assertEquals(count + 1, count2)
    }

    private fun createDummyScheduleItem(id: Long, showId: Long, countryCode: String) : ScheduledShow {
        return ScheduledShow(id, "test show", "test", 1, 1, Date(), Date(),
                null, null, null, countryCode, showId)
    }


    //TODO test date range

}
