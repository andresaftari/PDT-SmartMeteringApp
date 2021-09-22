package org.smartmetering.client.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.smartmetering.client.R
import org.smartmetering.client.TAG_UNIT_RESPONSE
import org.smartmetering.client.databinding.ActivityMainBinding
import org.smartmetering.client.utils.MainAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_refresh, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                fetchData()
                true
            }
            else -> false
        }
    }

    private fun fetchData() = with(viewModel) {
        getData()
        data.observe(this@MainActivity) {
            it.forEach { unit ->
                Log.d(TAG_UNIT_RESPONSE, "$unit")

                mainAdapter = MainAdapter(it)

                with(binding.rvUnit) {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = mainAdapter
                }
            }
        }
    }
}