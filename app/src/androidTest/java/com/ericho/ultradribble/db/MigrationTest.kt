package com.ericho.ultradribble.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.support.test.InstrumentationRegistry
import com.ericho.ultradribble.database.AppDatabase
import org.junit.Rule
import org.junit.Test


/**
 * Created by steve_000 on 24/10/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.db
 */
@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val TEST_DB = "migration-test"

    @Rule
    @JvmField
    var helper: MigrationTestHelper = MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
            AppDatabase::class.qualifiedName,
            FrameworkSQLiteOpenHelperFactory())
    @Test
    public fun migrate1To2() {
        var db:SupportSQLiteDatabase = helper.createDatabase (TEST_DB, 1);

        // db has schema version 1. insert some data using SQL queries.
        // You cannot use DAO classes because they expect the latest schema.
        db.execSQL("")

        // Prepare for the next version.
        db.close();

        // Re-open the database with version 2 and provide
        // MIGRATION_1_2 as the migration process.
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true);

        // MigrationTestHelper automatically verifies the schema changes,
        // but you need to validate that the data was migrated properly.
    }
}