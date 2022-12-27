package silversource.apps.skillbloom.skills.data

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "skill_table")
data class Skill(
    @PrimaryKey(autoGenerate = true)
    val _SkillId : Int,
    val _SkillName : String,
    val _SkillProgression : Int) {
}

@Entity(tableName = "subskill_table")
data class SubSkill(
    @PrimaryKey(autoGenerate = true)
    val _SubSkillId: Int,
    val _SubSkillName: String,
    val _SubSkillprogression: Float,

    val _SkillIdParent: Int
)

data class SkillWithSubskill(
    @Embedded val skill: Skill,
    @Relation(
        parentColumn = "_SkillId",
        entityColumn = "_SkillIdParent"
    )
    val subSkills: LiveData<List<SubSkill>>
)