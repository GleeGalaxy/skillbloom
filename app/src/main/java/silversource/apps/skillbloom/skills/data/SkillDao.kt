package silversource.apps.skillbloom.skills.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SkillDao {
    //Inserting a skill into the Skill entity.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSkill(skill : Skill)

//    //Inserting a subskill into the Skill entity.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSubskill(subSkill : SubSkill)

    @Query("SELECT * FROM skill_table ORDER BY _SkillId ASC")
    fun readAllSkills() : LiveData<List<Skill>>

    @Query("SELECT * FROM subskill_table ORDER BY _SubSkillId ASC")
    fun readAllSubSkills() : LiveData<List<SubSkill>>


    //Getting all records relating to the subskill entity. Returns a list of sub skills.
//    @Transaction
//    @Query("SELECT * FROM SubSkill LEFT JOIN Skill ON Skill._SkillId = SubSkill._SkillIdParent")
//    fun getSkillWithSubSkill(): List<SkillWithSubskill>
}