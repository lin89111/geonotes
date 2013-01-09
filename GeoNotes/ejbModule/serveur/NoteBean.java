package serveur;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class NoteBean
 */
@Stateful
@LocalBean
public class NoteBean implements NoteRemote, NoteLocal {

    /**
     * Default constructor. 
     */
    public NoteBean() {
        // TODO Auto-generated constructor stub
    }

}
