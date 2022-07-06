package com.example.bs23exam.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.graphics.drawable.ColorDrawable
import java.util.*


class ImageUtil {

    companion object {
        fun loadImage(context: Context, imageUri: String?, target: ImageView){
            if (!imageUri.isNullOrEmpty()){
                Glide.with(context)
                    .load(imageUri)
                    .placeholder(getRandomDrawableColor())
                    .into(target)
            }
        }

        private fun getRandomDrawableColor(): ColorDrawable {
            val idx: Int = Random().nextInt(vibrantLightColorList.size)
            return vibrantLightColorList[idx]
        }

        private val vibrantLightColorList = arrayOf(
            ColorDrawable(Color.parseColor("#9ACCCD")), ColorDrawable(Color.parseColor("#8FD8A0")),
            ColorDrawable(Color.parseColor("#CBD890")), ColorDrawable(Color.parseColor("#DACC8F")),
            ColorDrawable(Color.parseColor("#D9A790")), ColorDrawable(Color.parseColor("#D18FD9")),
            ColorDrawable(Color.parseColor("#FF6772")), ColorDrawable(Color.parseColor("#DDFB5C"))
        )
    }

}