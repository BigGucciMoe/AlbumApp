package com.app.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerNewAdapter(
    private val values: List<DataEntity>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerNewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val thumbnailUrl: TextView = view.findViewById(R.id.thumbnailUrl)
        val viewBtn : Button = view.findViewById(R.id.viewBtn)
    }

    private var listener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_recycler_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = values[position]
        holder.title.text = data.title
        holder.thumbnailUrl.text = data.thumbnailUrl
        holder.viewBtn.setOnClickListener {
            val intent: Intent = Intent(context, DisplayActivity::class.java)
            intent.putExtra("title", data.title)
            intent.putExtra("url", data.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    fun getPosition(): Int {
        return 0
    }

}
