package silversource.apps.skillbloom.skills.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SkillViewModel(application: Application) : AndroidViewModel(application){

    //Skills variable
    val readAllSkills: LiveData<List<Skill>>
    //Sub skill variable.
    val readAllSubskillData : LiveData<List<SubSkill>>
    private val repository: SkillRepository

    //List of sub skills.
//    val readAllSubskillData: LiveData<List<SubSkill>>

    init {
        val skillDao = SkillDatabase.getDatabase(application).skillDao()
        repository = SkillRepository(skillDao)
        readAllSkills = repository.readAllSkills
        readAllSubskillData = repository.readAllSubskills
    }

    //Adding a skill to the database.
    fun addSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addSkill(skill)
        }
    }

    //Adding a sub skill to the database.
    fun addSubskill(subSkill: SubSkill){
        viewModelScope.launch (Dispatchers.IO){
            repository.addSubskill(subSkill)
        }
    }

}