package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    /**
     * Initializes the view and all its components.
     */
    private fun initView(){
        //Bind the adapter and layout manger to the recyclerView.
        rvAnswers.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvAnswers.adapter = questionAdapter

        //add a vertical divider (line/ border) at the bottom of the items in the collection
        rvAnswers.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        //attach the item touch helper to the recycler view (for swiping support)
        createItemTouchHelper().attachToRecyclerView(rvAnswers)

        //TODO temp add 3 questions to the collection and tell the adapter the dataSource has changed
        questions.add(Question("My name is Michael", true))
        questions.add(Question("I have pink hair", false))
        questions.add(Question("1 + 2 = 12", false))
        questions.add(Question("12 + 12 = 24", true))
        questionAdapter.notifyDataSetChanged()
    }

    /***
     * this method is used to bind to the onSwipe and onMove method to the recycler view.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        //create / configure the callback method on swipe left
        val callback = object :  ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            //on move just return false since we dont need a implementation of this method for this app
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                var answer = questions.elementAt(position)

                if(direction == ItemTouchHelper.LEFT && answer.isCorrect){
                    Snackbar.make(tvQuestion, "That is correct!", Snackbar.LENGTH_SHORT).show()
                }
                else if(direction == ItemTouchHelper.RIGHT && !answer.isCorrect){
                    Snackbar.make(tvQuestion, "That is correct!", Snackbar.LENGTH_SHORT).show()
                }
                else{
                    Snackbar.make(tvQuestion, "Sorry that is not the correct answer, try again.", Snackbar.LENGTH_SHORT).show()
                }

                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        return ItemTouchHelper(callback)
    }
}
