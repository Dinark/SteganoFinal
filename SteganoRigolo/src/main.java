import javax.swing.JOptionPane;

import views.Interface;

public class main{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		
		
		try {
			  Interface affiche = new Interface();
			  } catch (Throwable t) {
			    JOptionPane.showMessageDialog(
			        null, t.getClass().getSimpleName() + ": " + t.getMessage());
			    throw t;
			  }

		

	}


}