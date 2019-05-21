package com.evolution.planner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_my_course.*
import  com.evolution.planner.utility.Course
import kotlinx.android.synthetic.main.cell_course.view.*

class MyCourseActivity: AppCompatActivity() {

    private val courses = ArrayList<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_course)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        val adapter = CourseAdapter(this.courses)
        list_view.adapter = adapter
        val db = FirebaseFirestore.getInstance()
        db.collection("courses").whereEqualTo("owner", "NLgDHaUhsyVJ5pVNrCYzRM8BJpR2").get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    courses.clear()
                    for (document in documents) {
                        courses.add(Course(document.id, document.data))
                    }
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                println("cannot load courses information")
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    inner class CourseAdapter(courses: ArrayList<Course>): BaseAdapter() {

        val courses = courses

        override fun getCount(): Int {
            return courses.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return this.courses.get(position)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cell = layoutInflater.inflate(R.layout.cell_course, parent, false)
            val course = this.courses.get(position)
            cell.course_name_label.text = course.getName()
            cell.semester_label.text = course.getSemester()
            cell.description_label.text = course.getDescription()
            cell.create_date_label.text = course.getCreateDateString()
            return cell
        }

    }
}