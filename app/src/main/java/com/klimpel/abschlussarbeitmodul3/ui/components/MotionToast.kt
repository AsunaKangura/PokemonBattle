package com.klimpel.abschlussarbeitmodul3.ui.components

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

/**
 * Zeigt eine Erfolgsmeldung als Toast an.
 *
 * @param context Der Kontext der Anwendung.
 * @param messageText Der Text der Erfolgsmeldung.
 */
fun messageDialogSuccess(
    context: Context,
    messageText: String,
){
    MotionToast.darkColorToast(
        context as Activity,
        title = "Erfolgreich",
        message = messageText,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

/**
 * Zeigt eine Fehlermeldung als Toast an.
 *
 * @param context Der Kontext der Anwendung.
 * @param messageText Der Text der Fehlermeldung.
 */
fun messageDialogError(
    context: Context,
    messageText: String,
){
    MotionToast.darkColorToast(
        context as Activity,
        title = "Fehlermeldung",
        message = messageText,
        MotionToastStyle.ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

/**
 * Zeigt eine Informationsmeldung als Toast an.
 *
 * @param context Der Kontext der Anwendung.
 * @param messageText Der Text der Informationsmeldung.
 */
fun messageDialogInfo(
    context: Context,
    messageText: String,
){
    MotionToast.darkColorToast(
        context as Activity,
        title = "Info",
        message = messageText,
        MotionToastStyle.INFO,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}


/**
 * Zeigt eine Warnungsmeldung als Toast an.
 *
 * @param context Der Kontext der Anwendung.
 * @param messageText Der Text der Warnungsmeldung.
 */
fun messageDialogWarning(
    context: Context,
    messageText: String,
){
    MotionToast.darkColorToast(
        context as Activity,
        title = "Warnung",
        message = messageText,
        MotionToastStyle.WARNING,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}
