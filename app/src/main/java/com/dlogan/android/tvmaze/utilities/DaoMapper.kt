package com.dlogan.android.tvmaze.utilities

import com.dlogan.android.tvmaze.data.epg.ScheduledShow
import com.dlogan.android.tvmaze.proxy.dto.ScheduleItemDto

object DaoMapper {


    fun convert(dto: ScheduleItemDto): ScheduledShow {
        val startTime = dto.getStartTime()
        val scheduledShow = ScheduledShow(
                dto.id,
                dto.embedded.show.name,
                dto.name,
                dto.season,
                dto.number,
                startTime,
                dto.getEndTime(startTime),
                dto.embedded.show.image?.medium,
                dto.embedded.show.image?.original,
                dto.embedded.show.summary,
                dto.embedded.show.getCountryCode(),
                dto.embedded.show.id
        )
        //LogUtil.debug("DaoMapper", scheduledShow.toString())
        return scheduledShow
    }

}