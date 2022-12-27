package silversource.apps.skillbloom.skills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_skill_list.view.*
import silversource.apps.skillbloom.R
import silversource.apps.skillbloom.skills.data.SkillViewModel

class SkillListFragment : Fragment() {

    private lateinit var skillViewModel: SkillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_skill_list, container, false)

        //Recycler view
        val adapter = SkillAdapter(this, viewLifecycleOwner, requireContext(), this)
        val rvSkills =  view.rv_skills
        rvSkills.adapter = adapter
        rvSkills.layoutManager = LinearLayoutManager(requireContext())
        //User view model
        skillViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)
        skillViewModel.readAllSkills.observe(viewLifecycleOwner, Observer{ skill ->
            adapter.setData(skill)
        })

        view.fab_add_skill.setOnClickListener {
            findNavController().navigate(R.id.go_to_add_skill_fragment)
        }

        return view
    }

}