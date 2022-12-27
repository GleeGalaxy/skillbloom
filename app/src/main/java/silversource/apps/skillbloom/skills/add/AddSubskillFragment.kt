package silversource.apps.skillbloom.skills.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_subskill.view.*
import kotlinx.android.synthetic.main.fragment_add_subskill.view.btn_add
import kotlinx.android.synthetic.main.fragment_add_subskill.view.btn_cancel
import silversource.apps.skillbloom.R
import silversource.apps.skillbloom.skills.data.SkillViewModel
import silversource.apps.skillbloom.skills.data.SubSkill

class AddSubskillFragment : DialogFragment() {

    private lateinit var skillViewModel : SkillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_subskill, container, false)

        skillViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        view.btn_cancel.setOnClickListener {
            dismiss()
        }

        view.btn_add.setOnClickListener {
            insertSubskillInDatabase()
        }

        return view
    }

    private fun insertSubskillInDatabase(){
        val subSkillName = view?.et_subskill_name?.text

        //Check if name is empty
        if(TextUtils.isEmpty(subSkillName)) {
            Toast.makeText(requireContext(), "Enter a valid name for the Subskill", Toast.LENGTH_LONG).show()
            return
        }

        //Create skill object
        val subSkill = SubSkill(0, subSkillName.toString(), 47.0F, 3)
        //Add user
        skillViewModel.addSubskill(subSkill)
        Toast.makeText(requireContext(), "Suskill added successfully...", Toast.LENGTH_LONG).show()
        dismiss()
    }

}