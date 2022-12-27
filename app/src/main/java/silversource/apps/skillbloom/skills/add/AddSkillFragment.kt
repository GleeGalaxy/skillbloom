package silversource.apps.skillbloom.skills.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_skill.view.*
import silversource.apps.skillbloom.R
import silversource.apps.skillbloom.skills.data.Skill
import silversource.apps.skillbloom.skills.data.SkillViewModel

class AddSkillFragment : DialogFragment() {

    private lateinit var skillViewModel: SkillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_skill, container, false)

        skillViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        view.btn_cancel.setOnClickListener {
            dismiss()
        }

        view.btn_add.setOnClickListener {
            insertSkillInDatabase()
        }

        return view
    }

    private fun insertSkillInDatabase(){
        val skillName = view?.et_skill_name?.text

        //Check if name is empty
        if(TextUtils.isEmpty(skillName)) {
            Toast.makeText(requireContext(), "Enter a valid name for the skill", Toast.LENGTH_LONG).show()
            return
        }

        //Create skill object
        val skill = Skill(0, skillName.toString(), 10)
        //Add user
        skillViewModel.addSkill(skill)
        Toast.makeText(requireContext(), "Skill added successfully...", Toast.LENGTH_LONG).show()
        dismiss()
    }

}