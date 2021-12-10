package ru.axcheb.motion

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

class PlayerScreenMotionLayout(
    context: Context,
    attributeSet: AttributeSet? = null
) : MotionLayout(context, attributeSet) {

    private val viewToDetectTouch by lazy {
        findViewById<View>(R.id.expended_player_layout)
    }
    private val viewRect = Rect()
    private var hasTouchStarted = false

    override fun onTouchEvent(event: MotionEvent): Boolean {

        // Сброс состояния hasTouchStarted в false.
        when (event.actionMasked) {
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                hasTouchStarted = false
                return super.onTouchEvent(event)
            }
        }

        // Если hasTouchStarted ещё в состоянии true,
        // проверяю коснулись ли именно viewToDetectTouch.
        if (!hasTouchStarted) {
            //This Checks if the touch is on the Player or the transaprent background
            viewToDetectTouch.getHitRect(viewRect)
            hasTouchStarted = viewRect.contains(event.x.toInt(), event.y.toInt())
        }
        return hasTouchStarted && super.onTouchEvent(event)
    }
}