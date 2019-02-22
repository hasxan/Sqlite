package com.example.hassansardar.notes.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hassansardar.notes.Model.Note;
import com.example.hassansardar.notes.R;
import com.example.hassansardar.notes.Utils.Utility;

import java.util.ArrayList;



public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder>  {

    public static final String TAG = "NotesRecyclerAdapter";

    ArrayList<Note> mNotes = new ArrayList<>();
    private OnNoteListener mOnNoteListener;
    public NotesRecyclerAdapter(ArrayList<Note> notes, OnNoteListener OnNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener =OnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_note_list_item, viewGroup, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        try{
            String month = mNotes.get(position).getTimestamp().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mNotes.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            viewHolder.timestamp.setText(timestamp);
            viewHolder.title.setText(mNotes.get(position).getTitle());
        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

         TextView title;
         TextView timestamp;

        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            mOnNoteListener= onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }
    }


    public interface  OnNoteListener
    {

        void onNoteClick(int position);
    }
}
