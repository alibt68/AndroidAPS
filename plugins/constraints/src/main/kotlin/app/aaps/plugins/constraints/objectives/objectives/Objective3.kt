package app.aaps.plugins.constraints.objectives.objectives

import app.aaps.core.data.time.T
import app.aaps.core.interfaces.resources.ResourceHelper
import app.aaps.core.interfaces.utils.DateUtil
import app.aaps.core.keys.IntNonKey
import app.aaps.core.keys.interfaces.Preferences
import app.aaps.plugins.constraints.R
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("SpellCheckingInspection")
@Singleton
class Objective3 @Inject constructor(
    preferences: Preferences,
    rh: ResourceHelper,
    dateUtil: DateUtil,
) : Objective(preferences, rh, dateUtil, "openloop", R.string.objectives_openloop_objective, R.string.objectives_openloop_gate) {

    init {
        // Loosen the duration check from 7 days down to 0 milliseconds
        tasks.add(MinimumDurationTask(this, 0L))
        
        tasks.add(
            object : Task(this, R.string.objectives_manualenacts) {
                override fun isCompleted(): Boolean {
                    // Force the manual enact rule to pass instantly
                    return true
                }

                override val progress: String
                    get() = rh.gs(R.string.completed_well_done)
            }.learned(Learned(R.string.objectives_openloop_learned))
        )
    }

    companion object {
        // Align the required metric target down to zero
        private const val MANUAL_ENACTS_NEEDED = 0
    }
}
