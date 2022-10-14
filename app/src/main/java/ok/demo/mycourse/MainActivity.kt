package ok.demo.mycourse

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        courseRV = findViewById(R.id.idRVCourses)


        courseList = ArrayList()


        courseRVAdapter = CourseRVAdapter(courseList)


        courseRV.adapter = courseRVAdapter


        courseList.add(CourseRVModal("Анжройд разработчик", R.drawable.a))
        courseList.add(CourseRVModal("C++ разработчик", R.drawable.c))
        courseList.add(CourseRVModal("Java разработчик", R.drawable.j))
        courseList.add(CourseRVModal("Python разработчик", R.drawable.p))
        courseList.add(CourseRVModal("JavaScript разработчик", R.drawable.js))


        courseRVAdapter.notifyDataSetChanged()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater


        inflater.inflate(R.menu.search_menu, menu)


        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)


        val searchView: SearchView = searchItem.getActionView() as SearchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {

                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<CourseRVModal> = ArrayList()


        for (item in courseList) {

            if (item.courseName.toLowerCase().contains(text.toLowerCase())) {

                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {

            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

            courseRVAdapter.filterList(filteredlist)
        }
    }
}