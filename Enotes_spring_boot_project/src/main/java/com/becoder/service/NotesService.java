package com.becoder.service;

import java.util.List;

import com.becoder.entity.Notes;
import com.becoder.entity.User;

public interface NotesService 
{
	public Notes saveNotes(Notes notes);
	
	public Notes getNotes(int id);
	
	public List<Notes> getNotesByUser(User user);
	
	public Notes updateNotes(Notes notes);
	
	public boolean deleteNotes(int id);

}
