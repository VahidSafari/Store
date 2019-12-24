package com.example.store.core.di.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.store.App
import com.example.store.core.db.StoreDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    private val databaseName = "store.db"

    @Provides
    @Singleton
    fun provideDB(app: App) =
        Room.databaseBuilder(
            app,
            StoreDatabase::class.java,
            databaseName
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    db.execSQL(
                        """
                        CREATE TRIGGER set_has_child_field
                        AFTER INSERT ON parent_child_entity
                        BEGIN
                        UPDATE list_entity
                        SET has_child = 1
                        WHERE id = NEW.parent_id;
                        END;
                    """.trimIndent()
                    )

                    db.execSQL(
                        """
                        INSERT INTO list_entity(name, has_child) VALUES
                        ('اصلی',0),
                        ('مواد غذایی', 0),
                        ('کالای دیجیتال', 0),
                        ('لوازم تحریر', 0),
                        ('مد و پوشاک', 0),
                        ('ورزش و سفر', 0),
                        ('کفش', 0),
                        ('شلوار', 0),
                        ('جوراب', 0),
                        ('فرآورده های لبنی', 0),
                        ('تنقلات',0);
                    """.trimIndent()
                    )

                    db.execSQL(
                        """
                        INSERT INTO parent_child_entity(parent_id, child_id) VALUES
                        (1,2),
                        (1,3),
                        (1,4),
                        (1,5),
                        (1,6),
                        (2,10),
                        (2,11),
                        (5,7),
                        (5,8),
                        (5,9);
                    """.trimIndent()
                    )

                    //TODO: SET hasChild field false when all the children are removed

                }
            }
            )
            .build()

}