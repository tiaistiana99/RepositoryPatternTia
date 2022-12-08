package com.chintansoni.android.tiaistiana.model.local.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chintansoni.android.tiaistiana.model.local.DatabaseConstants
import com.chintansoni.android.tiaistiana.model.remote.response.Dob
import com.chintansoni.android.tiaistiana.model.remote.response.Location
import com.chintansoni.android.tiaistiana.model.remote.response.Name
import com.chintansoni.android.tiaistiana.model.remote.response.Picture
import kotlinx.android.parcel.Parcelize


@Entity(tableName = DatabaseConstants.mTableUser)
@Parcelize
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @Embedded
        val name: Name,
        val email: String,
        val gender: String,
        val cell: String,
        @Embedded
        val picture: Picture,
        @Embedded
        val location: Location,
        @Embedded
        val dob: Dob) : Parcelable