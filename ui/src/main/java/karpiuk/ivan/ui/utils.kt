package karpiuk.ivan.ui

import android.content.Context
import androidx.compose.ui.unit.IntSize

fun String.formattedDate(context: Context): String {
    val dateComponents = split('-')
    val year = dateComponents[0]
    val month =
        when (dateComponents[1]) {
            "01" -> context.getString(R.string.month_january)
            "02" -> context.getString(R.string.month_february)
            "03" -> context.getString(R.string.month_march)
            "04" -> context.getString(R.string.month_april)
            "05" -> context.getString(R.string.month_may)
            "06" -> context.getString(R.string.month_june)
            "07" -> context.getString(R.string.month_july)
            "08" -> context.getString(R.string.month_august)
            "09" -> context.getString(R.string.month_september)
            "10" -> context.getString(R.string.month_october)
            "11" -> context.getString(R.string.month_november)
            "12" -> context.getString(R.string.month_december)
            else -> dateComponents[1]
        }
    val day = dateComponents[2].removePrefix("0")
    return "$month $day, $year"
}

fun String.applySize(size: IntSize?): String {
    return size?.let { replace("100x100", "${size.width}x${size.height}") } ?: this
}