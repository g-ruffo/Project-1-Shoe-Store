package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JetTag(var name: String = "Default", var size: Double = 00.00, var company: String, var description: String,
                  val images: List<String> = mutableListOf()) : Parcelable