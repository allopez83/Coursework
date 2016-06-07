import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class p2 extends JFrame {

	// Native Functions
	public native void setDirContent();
	public native String[] getFileName();
	public native String[] getDate();
	public native String[] getIsFile();
	public native String[] getFileSize();

	// Constants
	public static String NAME = "Name";
	public static String DATE = "Date";
	public static String TYPE = "Type";
	public static String SIZE = "Size";

	public static String ASCENDING = "^ ASC ^";
	public static String DESCENDING = "v DES v";

	public static boolean NAME_ASC = false;
	public static boolean DATE_ASC = false;
	public static boolean TYPE_ASC = false;
	public static boolean SIZE_ASC = false;

	public static int WIDTH = 800;
	public static int HEIGHT = 600;

	// Instance attributes used in this example
	private JPanel tablePanel;
	private JTable table;
	private JScrollPane scrollPane;

	private JPanel buttonPanel;
	private JButton name_button = new JButton(ASCENDING);
	private JButton date_button = new JButton(ASCENDING);
	private JButton type_button = new JButton(ASCENDING);
	private JButton size_button = new JButton(ASCENDING);

	private String columnNames[];
	private String dataValues[][];

	private String names[];
	private String dates[];

    private String typeStrings[];
	private boolean types[];

    private String sizeStrings[];
    private int sizes[];

	private Entry entries[];

	// Main entry point for this example
	public static void main(String args[]) {
		// Load the .lib
		System.loadLibrary("p2");

		// Create an instance of the test application
		p2 mainFrame = new p2();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Constructor of main frame
	public p2() {
		// Set the frame characteristics
		setTitle("Basic GUI");
		setSize(WIDTH, HEIGHT);
		setBackground(Color.gray);

		GetFileData();
		setupButtons(this.getContentPane());
		buildWindowPane(this.getContentPane());
	}

	@SuppressWarnings("deprecation")
	public void buildWindowPane(Container contentPane) {
		// Create a panel to hold all other components
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));

		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		contentPane.add(tablePanel);

		// Create columns
		CreateColumns();
		CreateData();

		// Create a new table instance
		table = new JTable(dataValues, columnNames);

		// Change the selection color
		table.setSelectionForeground(Color.white);
		table.setSelectionBackground(Color.red);

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane();
		scrollPane = JTable.createScrollPaneForTable(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		// Add buttons to buttonPanel
		buttonPanel.add(name_button);
		buttonPanel.add(date_button);
		buttonPanel.add(type_button);
		buttonPanel.add(size_button);
	}

	public void setupButtons(Container contentPane) {
		name_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!NAME_ASC) {
					NAME_ASC = true;
					name_button.setText(DESCENDING);
				} else {
					NAME_ASC = false;
					name_button.setText(ASCENDING);
				}

				sortNames(NAME_ASC);

				contentPane.removeAll();
				contentPane.revalidate();
				contentPane.repaint();
				buildWindowPane(contentPane);
			}
		});

		date_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!DATE_ASC) {
					DATE_ASC = true;
					date_button.setText(DESCENDING);
				} else {
					DATE_ASC = false;
					date_button.setText(ASCENDING);
				}

				sortDates(DATE_ASC);

				contentPane.removeAll();
				contentPane.revalidate();
				contentPane.repaint();
				buildWindowPane(contentPane);
			}
		});

		type_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!TYPE_ASC) {
					TYPE_ASC = true;
					type_button.setText(DESCENDING);
				} else {
					TYPE_ASC = false;
					type_button.setText(ASCENDING);
				}

				sortTypes(TYPE_ASC);

				contentPane.removeAll();
				contentPane.revalidate();
				contentPane.repaint();
				buildWindowPane(contentPane);
			}
		});

		size_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!SIZE_ASC) {
					SIZE_ASC = true;
					size_button.setText(DESCENDING);
				} else {
					SIZE_ASC = false;
					size_button.setText(ASCENDING);
				}

				sortSizes(SIZE_ASC);

				contentPane.removeAll();
				contentPane.revalidate();
				contentPane.repaint();
				buildWindowPane(contentPane);
			}
		});
	}

	public void CreateColumns() {
		// Create column string labels
		columnNames = new String[4];
		columnNames[0] = NAME;
		columnNames[1] = DATE;
		columnNames[2] = TYPE;
		columnNames[3] = SIZE;
	}

	// create the data for the table
	public void CreateData() {
		// Create data for each column
		// first column is File Name
		// second column is File Data
		// third column is File Type
		// fourth column is File Size
		// assume each array is of the same size

		boolToString();
		intToString();
		int row_Length = entries.length;

		dataValues = new String[row_Length][4];

		// first column
		for (int i = 0; i < row_Length; i++) {
			for (int j = 0; j < 1; j++) {
				dataValues[i][j] = entries[i].name;
			}
		}
		// second column
		for (int i = 0; i < row_Length; i++) {
			for (int j = 1; j < 2; j++) {
				dataValues[i][j] = entries[i].date;
			}
		}

		// third column
		// for boolean values; public native boolean[] getIsFile();
		for (int i = 0; i < row_Length; i++) {
			for (int j = 2; j < 3; j++) {
				dataValues[i][j] = entries[i].boolString;
			}
		}

		// fourth column
		// for int values; public native int[] getFileSize();
		for (int i = 0; i < row_Length; i++) {
			for (int j = 3; j < 4; j++) {
				dataValues[i][j] = entries[i].intString;
			}
		}
	}

	// Parse Bool -> String
	public void boolToString() {
		// FILE = True, DIRECTORY = False
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].type == true) {
				entries[i].boolString = "File";
			} else {
				entries[i].boolString = "Folder";
			}
		}
	}

	// Parse Int -> String
	public void intToString() {
		for (int i = 0; i < entries.length; i++) {
			entries[i].intString = "" + entries[i].size;
		}
	}

	public void GetFileData() {
		setDirContent();
		names = getFileName();
		dates = getDate();

        typeStrings = getIsFile();
        parseTypes();

        // types = javaGetIsFile();

        sizeStrings = getFileSize();
        parseSizes();

		// sizes = javaGetFileSize();

		entries = new Entry[names.length];

		for (int i = 0; i < entries.length; i++) {
			entries[i] = new Entry(names[i], dates[i], types[i], sizes[i]);
		}
	}

    public void parseTypes() {
        types = new boolean[typeStrings.length];
        for(int i = 0; i < typeStrings.length; i++) {
            if(typeStrings[i].equals("true")) {
                types[i] = true;
            } else {
                types[i] = false;
            }
        }
    }

    public void parseSizes() {
        sizes = new int[sizeStrings.length];
        for(int i = 0; i < sizeStrings.length; i++) {
            sizes[i] = Integer.parseInt(sizeStrings[i]);
        }
    }

	public void sortNames(boolean asc) {
		if (asc) {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].name.compareTo(entries[j].name) < 0) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		} else {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].name.compareTo(entries[j].name) > 0) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		}
	}

	public void sortDates(boolean asc) {
		if (asc) {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].date.compareTo(entries[j].date) < 0) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		} else {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].date.compareTo(entries[j].date) > 0) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		}
	}

	public void sortTypes(boolean asc) {
		if (asc) {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].type && !entries[j].type) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		} else {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (!entries[i].type && entries[j].type) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		}
	}

	public void sortSizes(boolean asc) {
		if (asc) {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].size < entries[j].size) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		} else {
			for (int j = 0; j < entries.length; j++) {
				for (int i = j + 1; i < entries.length; i++) {
					if (entries[i].size > entries[j].size) {
						Entry temp = entries[j];
						entries[j] = entries[i];
						entries[i] = temp;
					}
				}
			}
		}
	}

	/** FILLER DATA **/
	public static String[] javaGetFileName() {
		String[] temp = new String[1000];
		for (int i = 0; i < 1000; i++) {
			temp[i] = "File" + i;
		}
		return temp;
	}

	public static String[] javaGetDate() {
		String[] temp = new String[1000];
		for (int i = 0; i < 1000; i++) {
			temp[i] = "" + i;
		}
		return temp;
	}

	public boolean[] javaGetIsFile() {
		boolean[] temp = new boolean[1000];
		for (int i = 0; i < 500; i++) {
			temp[i] = false;
		}
		for (int i = 500; i < 1000; i++) {
			temp[i] = true;
		}
		return temp;
	}

	public int[] javaGetFileSize() {
		int[] temp = new int[1000];
		for (int i = 0; i < 1000; i++) {
			temp[i] = i;
		}
		return temp;
	}

	/** FILLER DATA **/

	class Entry {
		String name;
		String date;
		boolean type;
		int size;

		String boolString;
		String intString;

		Entry(String newName, String newDate, boolean newType, int newSize) {
			name = newName;
			date = newDate;
			type = newType;
			size = newSize;
		}
	}
}
