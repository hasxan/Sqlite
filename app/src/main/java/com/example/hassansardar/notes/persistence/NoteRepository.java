package com.example.hassansardar.notes.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.hassansardar.notes.Model.Note;
import com.example.hassansardar.notes.async.DeleteAsyncTask;
import com.example.hassansardar.notes.async.InsertAsyncTask;
import com.example.hassansardar.notes.async.UpdateAsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){
        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public void updateNoteTask(Note note){
        new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }

    public void deleteNoteTask(Note note){
        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }
}

