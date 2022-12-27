package silversource.apps.skillbloom.skills

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_skill_list.view.*
import kotlinx.android.synthetic.main.list_item_skill.view.*
import silversource.apps.skillbloom.R
import silversource.apps.skillbloom.skills.data.Skill
import silversource.apps.skillbloom.skills.data.SkillViewModel
import silversource.apps.skillbloom.skills.data.SubSkill

class SkillAdapter(
    storeOwner: Fragment,
    lifecycleOwner : LifecycleOwner,
    context : Context,
    fragment : Fragment) : RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    private lateinit var skillViewModel : SkillViewModel

    private var skillList = emptyList<Skill>()

    private val storeOwner = storeOwner
    private val lifecycleOwner = lifecycleOwner
    private val context = context
    private val fragment = fragment

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSkillName: TextView
        val tvSkillLevel : TextView
        val pbSkillProgress : ProgressBar

        ///Buttons
        val btnAddSubSkillTop : Button
        val btnAddSubSkillBottom : Button

        //Child recycler view i.e: sub skill recycler view.
        val rvSubSkill : RecyclerView

        init {
            // Define click listener for the ViewHolder's View
            tvSkillName = view.findViewById(R.id.tv_skill_name)
            tvSkillLevel = view.findViewById(R.id.tv_skill_level)
            pbSkillProgress = view.findViewById(R.id.pb_skill_progress)

            //Buttons
            btnAddSubSkillTop = view.btn_add_skill_top
            btnAddSubSkillBottom = view.btn_add_skill_bottom

            //Subskill recycler view.
            rvSubSkill = view.rv_subskill
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_skill, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvSkillName.text = skillList[position]._SkillName
        viewHolder.tvSkillLevel.text = skillList[position]._SkillProgression.toString()
        viewHolder.pbSkillProgress.progress = skillList[position]._SkillProgression

        //OnClick event listeners.
        viewHolder.btnAddSubSkillTop.setOnClickListener {
            findNavController(fragment).navigate(R.id.go_to_add_subskill_fragment)
        }
        viewHolder.btnAddSubSkillBottom.setOnClickListener {
            findNavController(fragment).navigate(R.id.go_to_add_subskill_fragment)
        }

        //Contents of child recycler view
        val subSkillAdapter = SubSkillAdapter()
        val rvSubSkills =  viewHolder.rvSubSkill
        rvSubSkills.adapter = subSkillAdapter
        rvSubSkills.layoutManager = LinearLayoutManager(context)

        //Initializing the skills view model.
        skillViewModel = ViewModelProvider(storeOwner).get(SkillViewModel::class.java)
        skillViewModel.readAllSubskillData.observe(lifecycleOwner, Observer{ skill ->
            subSkillAdapter.setData(skill)
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = skillList.size

    fun setData(skills: List<Skill>){
        this.skillList = skills
        notifyDataSetChanged()
    }

}

//Adapter for the child recycler view i.e: Subskill recylcer view
class SubSkillAdapter() : RecyclerView.Adapter<SubSkillAdapter.ViewHolder>() {

    private var subSkillList = emptyList<SubSkill>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSubSkillName: TextView

        init {
            // Define click listener for the ViewHolder's View
            tvSubSkillName = view.findViewById(R.id.tv_subskill_name)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_subskill, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvSubSkillName.text = subSkillList[position]._SubSkillName
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = subSkillList.size

    fun setData(subSkills: List<SubSkill>){
        this.subSkillList = subSkills
        notifyDataSetChanged()
    }

}
