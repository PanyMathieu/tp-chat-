package inf1010;

import javax.swing.*;

//class qui precise qui est connecté : Client or Server
public class serveur {

	
	public static void main(String [] args){
		
		Object[] selectioValues = { "Server","Client"};
		String initialSection = "Server";
		
		Object selection = JOptionPane.showInputDialog(null, "Connexion: ", "CHAT", JOptionPane.QUESTION_MESSAGE, null, selectioValues, initialSection);
		if(selection.equals("Server")){
                   String[] arguments = new String[] {};
			new multithread();
			multithread.main(arguments);
		}else if(selection.equals("Client")){
			String IPServer = JOptionPane.showInputDialog( "Ne rien saisir et cliquer sur OK POUR COMMENCER");
                        String[] arguments = new String[] {IPServer};
			new client();
			client.main(arguments);
		}
		
	}

}
