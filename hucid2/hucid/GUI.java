package hucid2.hucid;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {

	static JFrame guiMain = new JFrame("Fake News Detector");// creating instance of JFrame
	static JButton guiMainStart = new JButton("Start Analysis");// creating instance of JButton
	static JButton guiMoreInfo = new JButton("More Info");// creating instance of JButton
	static JTextField urlEnter = new JTextField("Url Enter");
	static JProgressBar percentageReal = new JProgressBar(0, 2000);
	static JLabel percentageTrueLabel = new JLabel("");
	static JButton backMainGUI = new JButton("Back to results");

	static JFrame guiMoreInfoFrame = new JFrame("Fake News Detector - More Info");// creating instance of JFrame
	static JLabel percentageResultsTrueLabel = new JLabel("");
	static JProgressBar percentageRealResults = new JProgressBar(0, 2000);

	public static void setGUI() {

		urlEnter.setBounds(50, 100, 500, 60);
		urlEnter.setText("Please enter the URL here");

		guiMainStart.setBounds(600, 100, 200, 60);

		guiMoreInfo.setBounds(1350, 800, 100, 60);

		JLabel ProductName = new JLabel("Product Name");
		ProductName.setBounds(400, 50, 100, 60);

		JLabel Logo = new JLabel("Logo");
		Logo.setBounds(100, 50, 100, 60);

		percentageTrueLabel.setBounds(1300, 800, 100, 60);

		percentageReal.setBounds(40, 800, 1200, 60);
		percentageReal.setValue(0);
		percentageReal.setStringPainted(true);

		guiMain.add(guiMainStart);
		guiMain.add(urlEnter);
		guiMain.add(ProductName);
		guiMain.add(Logo);
		guiMain.add(guiMoreInfo);
		guiMain.add(percentageReal);
		guiMain.add(percentageTrueLabel);

		guiMain.setSize(1500, 1000);
		guiMain.setLayout(null);
		guiMain.setVisible(true);

		guiMainStart.addActionListener(new ActionListenerGUI());
		guiMoreInfo.addActionListener(new ActionListenerGUI());

	}

	public static void setResultsGUI() {

		guiMoreInfoFrame.setSize(1500, 1000);
		guiMoreInfoFrame.setLayout(null);
		guiMoreInfoFrame.setVisible(true);

		JLabel resultsTitle = new JLabel("Breakdown of Realibility");
		resultsTitle.setBounds(50, 80, 400, 60);
		resultsTitle.setFont(new Font("", 1, 18));

		JLabel urlEntered = new JLabel(urlEnter.getText());
		urlEntered.setBounds(500, 80, 900, 60);
		urlEntered.setFont(new Font("", 1, 16));

		percentageResultsTrueLabel.setBounds(1300, 800, 100, 60);

		percentageRealResults.setBounds(40, 800, 1200, 60);
		percentageRealResults.setStringPainted(true);

		backMainGUI.setBounds(50, 20, 200, 40);

		guiMoreInfoFrame.add(percentageRealResults);
		guiMoreInfoFrame.add(percentageResultsTrueLabel);
		guiMoreInfoFrame.add(resultsTitle);
		guiMoreInfoFrame.add(urlEntered);
		guiMoreInfoFrame.add(backMainGUI);

		backMainGUI.addActionListener(new ActionListenerGUI());

	}

	public static void urlError() {

		JOptionPane.showMessageDialog(guiMain, "Please enter a valid URL", "Inane error", JOptionPane.ERROR_MESSAGE);

	}

	public static void webConnectvityError() {

		JOptionPane.showMessageDialog(guiMain, "Please enter a valid URL", "Inane error", JOptionPane.ERROR_MESSAGE);

	}

	public static void updatePercentages(Website website, WebsiteRating websiteRating) {
		int x = ((2000 / 100) * websiteRating.getOverallRating());
		percentageReal.setValue(x);
		percentageReal.setBackground(Color.RED);
		percentageReal.setForeground(Color.GREEN);

		percentageRealResults.setValue(x);
		percentageRealResults.setBackground(Color.RED);
		percentageRealResults.setForeground(Color.GREEN);

		int y = websiteRating.getOverallRating();

		percentageTrueLabel.setText(y + "%");
		percentageResultsTrueLabel.setText(y + "%");

		createTable(website, websiteRating);

		System.out.println("End of update.");

	}

	public static void createTable(Website website, WebsiteRating websiteRating) {

		// headers for the table
		String[] columns = new String[] { "Attributes", "", "Results" };

		String hasAuthor = "NO";
		String hasTitle = "NO";
		String hasDate = "NO";

		if (website.getAuthor() != null && website.getAuthor() != "") {
			hasAuthor = "YES";
		}
		if (website.getTitle() != null && website.getTitle() != "") {
			hasTitle = "YES";
		}
		if (website.getDate() != null && website.getDate() != "") {
			hasDate = "YES";
		}

		// actual data for the table in a 2d array
		Object[][] data = new Object[][] { { "Host Reliabillity", "", websiteRating.getHostRealibility() + "%" },
				{ "hostEndingPercentage", "", websiteRating.getHostEndingPercentage() + "%" },
				{ "Contains Author", "", hasAuthor }, { "Contains Title", "", hasTitle },
				{ "Contains Date", "", hasDate },
				{ "Web Search Results ", "", websiteRating.getWebSearchResults() + "%" }, };

		final Class[] columnClass = new Class[] { String.class, String.class, String.class };
		// create table model with data
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnClass[columnIndex];
			}
		};

		JTable table = new JTable(model);

		table.setBounds(40, 150, 1400, 600);
		table.setVisible(true);
		table.sizeColumnsToFit(-1);
		table.setRowHeight(90);
		table.setShowGrid(false);
		table.setBackground(Color.lightGray);
		table.setFont(new Font("", 1, 28));

		guiMoreInfoFrame.add(table);

	}

}
