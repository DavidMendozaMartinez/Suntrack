package com.davidmendozamartinez.sunrating.domain.work.model

sealed interface Work {
    val uniqueName: String

    enum class Periodic(override val uniqueName: String) : Work {
        EventSynchronizer(uniqueName = "work_event_synchronizer")
    }
}
