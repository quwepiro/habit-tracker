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

package com.pickth.habit.view.main

import com.pickth.habit.base.mvp.BaseView
import com.pickth.habit.util.HabitManagement
import com.pickth.habit.util.OnHabitClickListener
import com.pickth.habit.util.StringUtil
import com.pickth.habit.view.main.adapter.Habit
import com.pickth.habit.view.main.adapter.MainAdapterContract

/**
 * Created by yonghoon on 2017-08-09
 */

class MainPresenter: MainContract.Presenter, OnHabitClickListener {

    private lateinit var mView: MainContract.View
    private lateinit var mAdapterView: MainAdapterContract.View
    private lateinit var mAdapterModel: MainAdapterContract.Model

    override fun attachView(view: BaseView<*>) {
        mView = view as MainContract.View
    }

    override fun setAdapterView(view: MainAdapterContract.View) {
        mAdapterView = view
        mAdapterView.setOnHabitClickListener(this)
    }

    override fun setAdapterModel(model: MainAdapterContract.Model) {
        mAdapterModel = model
    }

    override fun getItemCount(): Int = mAdapterModel.getItemCount() - 1

    override fun addHabitItem(item: Habit) {
        mAdapterModel.addItem(item)
        HabitManagement.addHabit(mView.getContext(), item)
    }

    override fun addHabitItems(list: ArrayList<Habit>) {
        mAdapterModel.addItems(list)
    }

    override fun onItemCheck(position: Int) {
        mAdapterModel.notifyChanged(position)
        mAdapterModel.getItem(position).days.add(0, StringUtil.getCurrentDay())

        HabitManagement.notifyDataSetChanged(mView.getContext())
        mView.updateWidget()
    }

    override fun onItemUnCheck(position: Int) {
        mAdapterModel.notifyChanged(position)
        mAdapterModel.getItem(position).days.removeAt(0)

        HabitManagement.notifyDataSetChanged(mView.getContext())
        mView.updateWidget()
    }

    override fun onItemLongClick(position: Int) {
        mAdapterModel.removeItem(position)
        HabitManagement.removeHabit(mView.getContext(), position)
    }

    override fun onLastItemClick() {
        mView.showAddHabitDialog()
//        mView.scrollToLastItem()
    }
}