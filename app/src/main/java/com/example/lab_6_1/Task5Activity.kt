package com.example.lab_6_1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStream

class Task5Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CurrencyAdapterT5
    private lateinit var errorTextView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task5)

        recyclerView = findViewById(R.id.recyclerView)
        errorTextView = findViewById(R.id.errorTextView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchCurrencyData()
    }

    private fun fetchCurrencyData() {
        Thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.cbr.ru/scripts/XML_daily.asp")
                    .build()
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (responseBody != null) {
                    val currencyList = parseXml(responseBody.byteInputStream())
                    runOnUiThread {
                        adapter = CurrencyAdapterT5(currencyList)
                        recyclerView.adapter = adapter
                        errorTextView.visibility = View.GONE
                    }
                } else {
                    showError()
                }
            } catch (e: Exception) {
                Log.e("Task5Activity", "Error fetching currency data", e)
                showError()
            }
        }.start()
    }

    private fun parseXml(inputStream: InputStream): List<CurrencyT5> {
        val currencyList = mutableListOf<CurrencyT5>()
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.parse(inputStream)
        doc.documentElement.normalize()

        val nodeList = doc.getElementsByTagName("Valute")

        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val element = node as Element
                val charCode = element.getElementsByTagName("CharCode").item(0)?.textContent ?: "Unknown"
                //val name = element.getElementsByTagName("Name").item(0)?.textContent ?: "Unknown"
                val value = element.getElementsByTagName("Value").item(0)?.textContent ?: "Unknown"
                val previous = element.getElementsByTagName("Previous").item(0)?.textContent ?: "Unknown"

                currencyList.add(CurrencyT5(charCode, /*name,*/ value, previous))
            }
        }
        return currencyList
    }

    private fun showError() {
        runOnUiThread {
            errorTextView.visibility = View.VISIBLE
        }
    }
}

