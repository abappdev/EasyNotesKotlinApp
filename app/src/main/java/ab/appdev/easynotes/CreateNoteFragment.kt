package ab.appdev.easynotes

import ab.appdev.easynotes.database.NotesDatabase
import ab.appdev.easynotes.entities.Notes
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun saveCurrentNote(title: String, subTitle: String, description: String, Date: String) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveNote.setOnClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a")
            val currentDate = sdf.format(Date())

            if (!noteTitle.text.isNullOrEmpty() && !noteDesc.text.isNullOrEmpty()) {
                launch {
                    val Note = Notes()
                    Note.title = noteTitle.text.toString()
                    Note.subTitle = noteSubTitle.text.toString()
                    Note.notetext = noteDesc.text.toString()
                    Note.dateTime = currentDate.toString()
                    context?.let {
                        NotesDatabase.getDatabase(it).noteDao().insertNotes(Note)
                        noteDesc.setText("")
                        noteSubTitle.setText("")
                        noteTitle.setText("")
                    }
                }
            }
            if (noteTitle.text.isNullOrEmpty()) {
                Toast.makeText(context, "Add Title", Toast.LENGTH_LONG).show()
            }
            if (noteDesc.text.isNullOrEmpty()) {
                Toast.makeText(context, "Description Missing", Toast.LENGTH_LONG).show()
            }
        }

        goBack.setOnClickListener() {
            replaceFragment(DashNoteFragment.newInstance(), false)
        }

    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition) {
            fragmentTransition.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransition.replace(R.id.frameLayout, fragment)
            .commit()

    }
}