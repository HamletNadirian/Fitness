package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.Exercise

class ExercisePreviewAdapter(
    private val exercises: List<Exercise>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<ExercisePreviewAdapter.ExercisePreviewViewHolder>() {
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    inner class ExercisePreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseImage: ImageView = itemView.findViewById(R.id.ivExerciseImage)
        val exerciseName: TextView = itemView.findViewById(R.id.tvExerciseName)
        val exerciseDuration: TextView = itemView.findViewById(R.id.tvExerciseDuration)
        val exerciseNumber: TextView = itemView.findViewById(R.id.tvExerciseNumber)
        val checkIcon: ImageView = itemView.findViewById(R.id.ivCheckIcon)

        fun bind(exercise: Exercise) {
            itemView.setOnClickListener {
                listener?.onExerciseClick(exercise)

            }
        }
     /*   init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(exercises[position])

                }
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePreviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_preview, parent, false)
        return ExercisePreviewViewHolder(view)
    }

    override fun onViewRecycled(holder: ExercisePreviewViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.exerciseImage.context).clear(holder.exerciseImage)
    }

    override fun onBindViewHolder(holder: ExercisePreviewViewHolder, position: Int) {
        val exercise = exercises[position]
        // Обновление GIF анимации
        Glide.with(holder.exerciseImage.context)
            .asGif()
            .load(exercise.gifResId)
            .into(holder.exerciseImage)
        //holder.exerciseImage.setImageResource(exercise.gifResId)
        holder.exerciseName.text = exercise.name
        holder.exerciseDuration.text = formatDuration(exercise.durationSeconds)

        // Показываем номер упражнения или галочку (если выполнено)
        // Пока что просто показываем номер
        holder.exerciseNumber.text = (position + 1).toString()
        holder.exerciseNumber.visibility = View.VISIBLE
        holder.checkIcon.visibility = View.GONE
        holder.bind(exercise)
        // TODO: Здесь можно добавить логику для отображения прогресса выполнения
        // например, если упражнение выполнено, показывать галочку вместо номера
    }

    override fun getItemCount(): Int = exercises.size

    private fun formatDuration(seconds: Int): String {
        return if (seconds < 60) {
            "00:${seconds.toString().padStart(2, '0')}"
        } else {
            val minutes = seconds / 60
            val remainingSeconds = seconds % 60
            "${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}"
        }
    }
}
