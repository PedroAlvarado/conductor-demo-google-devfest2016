package be.vergauwen.simon.konductor

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import be.vergauwen.simon.konductor.core.ActionBarProvider
import be.vergauwen.simon.konductor.core.anko.actionBarSize
import be.vergauwen.simon.konductor.core.anko.changeHandlerFrameLayout
import be.vergauwen.simon.konductor.core.controllers.MasterDetailController
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import org.jetbrains.anko.UI
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent

class MainActivity : AppCompatActivity(), ActionBarProvider {

    internal var toolbar: Toolbar? = null
    internal var container: ViewGroup? = null

    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UI {
            coordinatorLayout {
                fitsSystemWindows = true
                appBarLayout {
                    toolbar = toolbar {

                    }.lparams(width = matchParent, height = actionBarSize())
                }.lparams(width = matchParent)

                container = changeHandlerFrameLayout {

                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }
        }

        setSupportActionBar(toolbar)

        container?.let { router = Conductor.attachRouter(this, it, savedInstanceState) }
        if (router?.hasRootController() == false) {
            router?.setRoot(RouterTransaction.with(MasterDetailController()))
        }
    }

    override fun onBackPressed() {
        if (router?.handleBack() == false) {
            super.onBackPressed()
        }
    }
}