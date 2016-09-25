package be.vergauwen.simon.konductor.core.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.bluelinelabs.conductor.demo.R

class TwitterItem(twitter: String) : Item {

    override var name: String = twitter

    override val itemColorId: Int
        get() = R.color.twitter

    override fun getIcon(context: Context): Drawable = ContextCompat.getDrawable(context, R.drawable.ic_twitter)
}