package silversource.apps.skillbloom.skills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import silversource.apps.skillbloom.R

class SkillListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_skill_list, container, false)

        val rv_skills =  view.findViewById<RecyclerView>(R.id.rv_skills)
        rv_skills.adapter

        return view
    }

}