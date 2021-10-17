package ab.appdev.easynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        replaceFragment(DashNoteFragment.newInstance(), true)
    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        if (isTransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frameLayout, fragment)
            .commit()

    }
}