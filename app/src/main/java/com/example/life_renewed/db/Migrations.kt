package com.example.life_renewed.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {


    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Step 1: Add the new column first (if it doesn't affect the rename logic directly)
            // Or, you can include it in the new table definition below.
            // For clarity, let's add it separately if it's an independent change.
            db.execSQL("ALTER TABLE notes_table ADD COLUMN sermonTitle TEXT")

            db.execSQL("ALTER TABLE notes_table RENAME COLUMN title TO seriesTitle")

            // --- Steps to Rename 'old_column_name' to 'new_column_name' ---

            // Step A: Create a new temporary table with the desired schema
            // Make sure to include ALL columns from the original table,
            // with 'old_column_name' changed to 'new_column_name',
            // and also include the newly added 'sermonTitle' column.
            // Replace 'column1_type', 'column2_type', etc., with actual types and names.
            db.execSQL("""
                CREATE TABLE notes_table_new (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    sermonTitle TEXT,
                    description TEXT,
                    seriesTitle TEXT,
                    date TEXT
                )
            """.trimIndent())

            // Step B: Copy data from the old table to the new table
            // Ensure the column order in INSERT INTO matches SELECT
            db.execSQL("""
                INSERT INTO notes_table_new (id, sermonTitle, description, seriesTitle, date)
                SELECT id, NULL, description, seriesTitle, date
                FROM notes_table
            """.trimIndent())
            // Note: We are inserting NULL for sermonTitle as it's a new column.
            // You might want to provide a default value if 'sermonTitle' is NOT NULL
            // and doesn't have a default value defined in the schema.

            // Step C: Drop the old table
            db.execSQL("DROP TABLE notes_table")
            // Step D: Rename the new table to the original table's name
            db.execSQL("ALTER TABLE notes_table_new RENAME TO notes_table")

        }
    }

    fun getMigrations(): Migration {
        return MIGRATION_1_2
    }
}