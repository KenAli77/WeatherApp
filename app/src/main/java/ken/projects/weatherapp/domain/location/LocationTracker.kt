package ken.projects.weatherapp.domain.location

import android.location.Location

// location tracker abstraction
interface LocationTracker {

    suspend fun getCurrentLocation(): Location?

}