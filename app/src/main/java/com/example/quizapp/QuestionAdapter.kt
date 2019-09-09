package com.example.quizapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val questions: List<Question>) :
        RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //create a dynamic textView to put into the XML content of the recyclerView item thingy
        private val tvAnswer: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(question: Question) {
            tvAnswer.text = question.question
        }
    }


}
