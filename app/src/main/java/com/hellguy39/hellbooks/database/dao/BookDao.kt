package com.hellguy39.hellbooks.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hellguy39.hellbooks.database.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    suspend fun getAll(): List<BookEntity>

    @Query("SELECT * FROM book")
    fun getAllFlow(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): BookEntity?

    @Query("SELECT * FROM Book WHERE id LIKE :id LIMIT 1")
    fun findByIdFlow(id: Int): Flow<BookEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookEntity: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg bookEntities: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(bookEntity: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(vararg bookEntities: BookEntity)

    @Delete
    suspend fun delete(bookEntity: BookEntity)

    @Delete
    suspend fun deleteAll(vararg bookEntities: BookEntity)

    @Query("DELETE FROM Book WHERE id LIKE :id")
    suspend fun deleteById(id: Int)

}