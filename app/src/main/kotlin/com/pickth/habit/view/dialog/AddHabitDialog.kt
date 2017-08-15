/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.habit.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.pickth.habit.R
import com.pickth.habit.view.main.adapter.Habit
import kotlinx.android.synthetic.main.dialog_add_habit.*
import org.jetbrains.anko.alert
import java.util.*

/**
 * Created by yonghoon on 2017-08-14
 */

class AddHabitDialog(context: Context, val listener: View.OnClickListener): Dialog(context, R.style.AppTheme_NoTitle_TransLucent) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.run {
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
            setGravity(Gravity.CENTER)
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }

        setContentView(R.layout.dialog_add_habit)
        btn_add_habit_submit.setOnClickListener(listener)
    }

    fun addHabit(): Habit? {
        var title = et_add_habit_title.text.toString()
        if(title == "") {
            // 제목을 안지었을 때
            context.alert("제목을 입력하세요").show()
            return null
        }

        var havit = Habit(UUID.randomUUID().toString(),
                title,
                ContextCompat.getColor(context, R.color.colorAccent)
        )
        dismiss()
        return havit
    }
}