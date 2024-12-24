package com.example.magic_demo.TestActivities

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var processedBitmap: MutableState<Bitmap?> = mutableStateOf(null)
}
