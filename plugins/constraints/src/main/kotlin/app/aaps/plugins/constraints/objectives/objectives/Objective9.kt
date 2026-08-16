package app.aaps.plugins.constraints.objectives.objectives

import app.aaps.core.data.time.T
import app.aaps.core.interfaces.resources.ResourceHelper
import app.aaps.core.interfaces.utils.DateUtil
import app.aaps.core.keys.interfaces.Preferences
import app.aaps.plugins.constraints.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Objective9 @Inject constructor(
    preferences: Preferences,
    rh: ResourceHelper,
    dateUtil: DateUtil,
) : Objective(preferences, rh, dateUtil, "auto", R.string.objectives_auto_objective, R.string.objectives_auto_gate) {

    init {
        // Drop the final 28-day requirement down to 0 milliseconds
        tasks.add(
            MinimumDurationTask(this, 0L)
                .learned(Learned(R.string.objectives_auto_learned))
        )
    }
}
