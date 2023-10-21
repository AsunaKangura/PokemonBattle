package com.klimpel.abschlussarbeitmodul3.ui.components

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.core.content.res.ResourcesCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@Composable
fun MessageDialogSuccess(
    context: Context,
    messageText: String,
    messageTitle: String
){
    MotionToast.darkColorToast(
        context as Activity,
        title = messageTitle,
        message = messageText,
        MotionToastStyle.SUCCESS,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

@Composable
fun MessageDialogError(
    context: Context,
    messageText: String,
    messageTitle: String
){
    MotionToast.darkColorToast(
        context as Activity,
        title = messageTitle,
        message = messageText,
        MotionToastStyle.ERROR,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

@Composable
fun MessageDialogInfo(
    context: Context,
    messageText: String,
    messageTitle: String
){
    MotionToast.darkColorToast(
        context as Activity,
        title = messageTitle,
        message = messageText,
        MotionToastStyle.INFO,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}

@Composable
fun MessageDialogWarning(
    context: Context,
    messageText: String,
    messageTitle: String
){
    MotionToast.darkColorToast(
        context as Activity,
        title = messageTitle,
        message = messageText,
        MotionToastStyle.WARNING,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        ResourcesCompat.getFont(context, www.sanju.motiontoast.R.font.helvetica_regular)
    )
}
