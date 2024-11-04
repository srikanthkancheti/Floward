package com.floward.posts.ui.extensions

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
