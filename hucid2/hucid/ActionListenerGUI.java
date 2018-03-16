package hucid2.hucid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerGUI extends GUI implements ActionListener {

	GUI parent;

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Start Analysis")) {
			System.out.println("Start Analysis has been clicked");
			Main.startingProgram();
		} else if (e.getActionCommand().equals("More Info")) {
			System.out.println("More Info has been clicked");
			GUI.setResultsGUI();
		} else if (e.getActionCommand().equals("Back to results")) {
			GUI.guiMoreInfoFrame.dispose();
		}

	}

}
