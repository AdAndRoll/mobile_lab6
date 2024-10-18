package com.example.lab_6_1

import android.os.Bundle
import android.util.Log
import android.util.Xml
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

import org.xmlpull.v1.XmlPullParser
import java.io.StringReader

class Task4Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task4)

        recyclerView = findViewById(R.id.currencyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCurrencies()
    }

    private fun loadCurrencies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.cbr.ru/scripts/XML_daily.asp")
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                responseBody?.let {
                    val currencies = parseXml(it)
                    runOnUiThread {
                        adapter = CurrencyAdapter(currencies)
                        recyclerView.adapter = adapter
                    }
                }
            } catch (e: Exception) {
                Log.e("Task4Activity", "Ошибка: $e")
            }
        }
    }

    private fun parseXml(xml: String): List<Currency> {
        val currencies = mutableListOf<Currency>()
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setInput(StringReader(xml))

        var eventType = parser.eventType
        var currentCurrency: Currency? = null
        var currentTag: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    currentTag = parser.name
                    if (currentTag == "Valute") {
                        currentCurrency = Currency()
                    }
                }
                XmlPullParser.TEXT -> {
                    currentCurrency?.let {
                        when (currentTag) {
                            "CharCode" -> it.charCode = parser.text
                            "Nominal" -> it.nominal = parser.text
                            "Name" -> it.name = parser.text
                            "Value" -> it.value = parser.text
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == "Valute") {
                        currentCurrency?.let { currencies.add(it) }
                    }
                    currentTag = null
                }
            }
            eventType = parser.next()
        }
        return currencies
    }
}
