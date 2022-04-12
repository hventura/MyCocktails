package pt.hventura.mycoktails.data.database.dao

import androidx.room.*
import pt.hventura.mycoktails.data.models.CompactDrink
import pt.hventura.mycoktails.data.models.Drink

@Dao
interface CocktailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinks(drinks: List<CompactDrink>)

    @Delete
    suspend fun deleteDrink(drink: Drink)

    @Update
    suspend fun updateDrink(drink: Drink)

    //Queries
    @Query("SELECT * FROM drinks_list")
    suspend fun getDrinks(): List<CompactDrink>

    @Query("SELECT * FROM detailed_drinks WHERE idDrink = :id")
    suspend fun getDetailedDrink(id: String): List<Drink>

    @Query("UPDATE drinks_list SET existsInDB = 'true' WHERE idDrink = :id")
    suspend fun setExistsInDB(id: String): Int

}