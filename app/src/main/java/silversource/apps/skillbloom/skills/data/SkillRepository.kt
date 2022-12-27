package silversource.apps.skillbloom.skills.data

import androidx.lifecycle.LiveData

class SkillRepository(private val skillDao : SkillDao) {

    val readAllSkills : LiveData<List<Skill>> = skillDao.readAllSkills()

    val readAllSubskills : LiveData<List<SubSkill>> = skillDao.readAllSubSkills()

    suspend fun addSkill(skill: Skill){
        skillDao.addSkill(skill)
    }

    suspend fun  addSubskill(subSkill : SubSkill) {
        skillDao.addSubskill(subSkill)
    }

}