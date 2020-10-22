package sheridan.muhammal.assignment2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "envelopes")
data class Envelope(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "randomDie_0")
    val randomDie0: String,

    @ColumnInfo(name = "randomDie_1")
    val randomDie1: String,

    @ColumnInfo(name = "randomDie_2")
    val randomDie2: String,

    @ColumnInfo(name = "total_sum")
    val totalSum: String,

    @ColumnInfo(name = "time_stamp")
    val timeStamp: Date
)