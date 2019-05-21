package com.evolution.planner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.cell_me_auth.view.*
import kotlinx.android.synthetic.main.cell_me_regular.view.*
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment: Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        meListView.adapter = MeAdapter(context!!)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_me, container, false)
//        meListView = view.findViewById(R.id.meListView)
        return inflater.inflate(R.layout.fragment_me, container, false)

        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meListView.adapter = MeAdapter(context!!, true)
        meListView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.listViewBackground))
//        meListView.setBackgroundColor(context!!.resources.getColor(R.color.listViewBackground))
        meListView.setOnItemClickListener { parent, view, position, id ->
            when (position) {
                2->{
                    startActivity(Intent(view.context, MyCourseActivity::class.java))
                }
            }

//            startActivity(Intent)
            println(position)
        }
    }

    inner class MeAdapter(context: Context, isLoggedIn: Boolean): BaseAdapter() {
        private val mContext = context
        private val isLoggedIn = isLoggedIn

//        init {
//            this.mContext = context
//            this.isLoggedIn = isLoggedIn
//        }

        override fun getCount(): Int {
            if (isLoggedIn) {
                return 6
            }
            return 2
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            if (position == 0) {
                val cell = layoutInflater.inflate(R.layout.cell_me_user, parent, false)
                return cell
            }
            if (isLoggedIn && position < 5) {
                val cell = layoutInflater.inflate(R.layout.cell_me_regular, parent, false)
                when (position) {
                    1 -> {
                        cell.me_regular_cell_image.setImageResource(R.mipmap.follow)
                        cell.me_regular_cell_text.text = "following"
                    }
                    2 -> {
                        cell.me_regular_cell_image.setImageResource(R.mipmap.course)
                        cell.me_regular_cell_text.text = "Course"
                    }
                    3 -> {
                        cell.me_regular_cell_image.setImageResource(R.mipmap.task)
                        cell.me_regular_cell_text.text = "Task"
                    }
                    4 -> {
                        cell.me_regular_cell_image.setImageResource(R.mipmap.setting)
                        cell.me_regular_cell_text.text = "Account Settings"
                    }
                }
                return cell
            } else {
                val cell = layoutInflater.inflate(R.layout.cell_me_auth, parent, false)
                if (isLoggedIn) {
                    cell.me_auth_cell_text.text = "Log out"
                    cell.me_auth_cell_text.setTextColor(ContextCompat.getColor(mContext, R.color.colorRed))
//                    cell.me_auth_cell_text.setTextColor(mContext.resources.getColor(R.color.colorRed))
                } else {
                    cell.me_auth_cell_text.text = "Log in"
                    cell.me_auth_cell_text.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlue))
//                    cell.me_auth_cell_text.setTextColor(mContext.resources.getColor(R.color.colorBlue))
                }
                return cell
            }
        }
    }
}