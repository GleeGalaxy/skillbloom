package silversource.apps.skillbloom.skills.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Skill::class, SubSkill::class], version = 2, exportSchema = false)
abstract class SkillDatabase : RoomDatabase() {

    abstract fun skillDao() : SkillDao

    companion object{
        private var INSTANCE: SkillDatabase? = null

        fun getDatabase(context: Context):SkillDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SkillDatabase::class.java,
                    "skill_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }
}