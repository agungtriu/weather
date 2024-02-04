package com.agungtriu.weather.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import com.agungtriu.weather.R
import com.agungtriu.weather.data.remote.DailyItem
import com.agungtriu.weather.data.remote.HourlyItem
import com.agungtriu.weather.databinding.ActivityMainBinding
import com.agungtriu.weather.utils.Menu
import com.agungtriu.weather.utils.UIState
import com.agungtriu.weather.utils.Utils.millisToClock
import com.agungtriu.weather.utils.Utils.millisToDate
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLayout()
        setObserve()
        setOnListener()
    }

    private fun setLayout() {
        Menu.entries.forEach {
            addChip(binding.cgMain, it.string, this)
        }

        binding.cgMain.forEachIndexed { _, chip ->
            if ((chip as Chip).text == viewModel.selectedMenu) {
                chip.isChecked = true
            }
        }
    }

    private fun setObserve() {
        viewModel.resultLocation.observe(this) {
            when (it) {
                is UIState.Loading -> {
                    binding.toolbarDetail.title = "Loading..."
                    startShimmer()
                }

                is UIState.Error -> {
                    binding.toolbarDetail.title = it.error
                    stopShimmer()
                }

                is UIState.Success -> {
                    binding.toolbarDetail.title =
                        "${it.data.city}, ${it.data.region}, ${it.data.country}"
                }
            }
        }

        viewModel.resultWeather.observe(this) {
            when (it) {
                is UIState.Loading -> startShimmer()

                is UIState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                    stopShimmer()
                }

                is UIState.Success -> {
                    stopShimmer()
                    loadDataHourly(it.data.hourly!!, viewModel.selectedMenu)
                    loadDataDaily(it.data.daily!!, viewModel.selectedMenu)
                }
            }
        }
    }

    private fun loadDataHourly(list: List<HourlyItem>, selectedMenu: String) {
        val entries = mutableListOf<BarEntry>()
        val labels = mutableListOf<String>()
        for (i in 0..<12) {
            when (viewModel.selectedMenu) {
                Menu.TEMPERATURE.string -> entries.add(
                    BarEntry(
                        i.toFloat(),
                        list[i].temp!!
                    )
                )

                Menu.HUMIDITY.string -> entries.add(
                    BarEntry(
                        i.toFloat(),
                        list[i].humidity!!
                    )
                )

                Menu.BAROMETER.string -> entries.add(
                    BarEntry(
                        i.toFloat(),
                        list[i].pressure!!
                    )
                )
            }
            labels.add(millisToClock(list[i].dt!!.toLong()))
        }

        val dataSet = BarDataSet(entries, selectedMenu)
        displayBarChart(binding.barchartMainHourly, dataSet, labels)
    }

    private fun loadDataDaily(list: List<DailyItem>, selectedMenu: String) {
        val entries = mutableListOf<BarEntry>()
        val labels = mutableListOf<String>()
        list.forEachIndexed { index, item ->
            when (viewModel.selectedMenu) {
                Menu.TEMPERATURE.string -> entries.add(
                    BarEntry(
                        index.toFloat(),
                        (item.temp!!.max!!.plus(item.temp.min!!)) / 2
                    )
                )

                Menu.HUMIDITY.string -> entries.add(
                    BarEntry(
                        index.toFloat(),
                        item.humidity!!
                    )
                )

                Menu.BAROMETER.string -> entries.add(
                    BarEntry(
                        index.toFloat(),
                        item.pressure!!
                    )
                )
            }
            labels.add(millisToDate(item.dt!!))
        }

        val dataSet = BarDataSet(entries, selectedMenu)
        displayBarChart(binding.barchartMainDaily, dataSet, labels)
    }

    private fun displayBarChart(barChart: BarChart, dataSet: BarDataSet, labels: List<String>) {
        dataSet.color = getColor(R.color.colorBottom)
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return when (viewModel.selectedMenu) {
                    Menu.TEMPERATURE.string -> String.format("%.2f°C", value)
                    Menu.HUMIDITY.string -> String.format("%.0f", value).plus("%")
                    Menu.BAROMETER.string -> String.format("%.0fhPa", value)
                    else -> "$value"
                }
            }
        }
        val barData = BarData(dataSet)
        barData.setValueTextSize(8f)


        with(barChart) {
            this.data = barData
            this.animateY(500)
            this.description.isEnabled = false

            this.xAxis.position = XAxis.XAxisPosition.BOTTOM
            this.xAxis.labelCount = labels.size
            this.xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val index = value.toInt()
                    if (index >= 0 && index < labels.size) {
                        return labels[index]
                    }
                    return ""

                }
            }

            this.xAxis.setDrawGridLines(false)

            this.axisLeft.setDrawGridLines(false)
            this.axisLeft.setDrawAxisLine(false)
            this.axisLeft.isEnabled = false

            this.axisRight.setDrawGridLines(false)
            this.axisRight.setDrawAxisLine(false)
            this.axisRight.isEnabled = false

        }
    }

    private fun setOnListener() {
        binding.btnMainRefresh.setOnClickListener {
            viewModel.getLocation()
        }

        binding.btnMainExit.setOnClickListener {
            finish()
        }

        binding.cgMain.setOnCheckedStateChangeListener { group, _ ->
            if (group.checkedChipId != -1) {
                val selectedChip = group.findViewById<Chip>(group.checkedChipId)
                viewModel.selectedMenu = selectedChip.text.toString()
                viewModel.getWeather()
            }
        }
    }

    private fun addChip(
        chipGroup: ChipGroup,
        text: String?,
        context: Context,
    ) {
        val chip = Chip(context)
        chip.text = text
        chip.setChipBackgroundColorResource(R.color.colorChip)
        chip.isCheckable = true
        chipGroup.addView(chip)
    }


    private fun startShimmer() {
        binding.barchartMainHourly.visibility = View.INVISIBLE
        binding.barchartMainDaily.visibility = View.INVISIBLE
        binding.shimmerMainDaily.startShimmer()
        binding.shimmerMainHourly.startShimmer()
        binding.shimmerMainDaily.visibility = View.VISIBLE
        binding.shimmerMainHourly.visibility = View.VISIBLE
    }

    private fun stopShimmer() {
        binding.barchartMainHourly.visibility = View.VISIBLE
        binding.barchartMainDaily.visibility = View.VISIBLE
        binding.shimmerMainDaily.stopShimmer()
        binding.shimmerMainHourly.stopShimmer()
        binding.shimmerMainDaily.visibility = View.GONE
        binding.shimmerMainHourly.visibility = View.GONE
    }
}