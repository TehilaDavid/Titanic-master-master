import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private List<Passenger> passengerList;

    private JButton filterButton;
    private JButton statisticsButton;

    private JComboBox<String> pClassComboBox;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> embarkedComboBox;

    private JTextField idMinTextField;
    private JTextField idMaxTextField;
    private JTextField nameTextField;
    private JTextField sibSpTextField;
    private JTextField parchTextField;
    private JTextField ticketTextField;
    private JTextField fareMinTextField;
    private JTextField fareMaxTextField;
    private JTextField cabinTextField;

    private int classType;
    private String sexChoice;
    private char embarkedChoice;
    private int filterCounter;
    private String firstLine;
    private int x;


    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
        this.filterCounter = 0;

        createPassengerList(file);
        this.x = x + Constants.MARGIN_FROM_LEFT;

        createFilters(y);

        filtering ();
    }

    private void createPassengerList(File file) {
        String lineData;
        this.passengerList = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                this.firstLine = scanner.nextLine();
                while (scanner.hasNextLine()) {
                    lineData = scanner.nextLine();
                    Passenger passenger = new Passenger(lineData);
                    this.passengerList.add(passenger);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFilters(int y) {
        JLabel idMinLabel = new JLabel("Min passenger Id: ");
        idMinLabel.setBounds(this.x , y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(idMinLabel);

        this.idMinTextField = new JTextField();
        this.idMinTextField.setBounds(idMinLabel.getX() + idMinLabel.getWidth(), y, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.idMinTextField);

//        JLabel idMaxLabel = new JLabel("Max Passenger Id: ");
//        idMaxLabel.setBounds(this.idMinTextField.getX() + Constants.MARGIN_FROM_LEFT + Constants.LABEL_WIDTH + 20, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
//        this.add(idMaxLabel);
//
//        this.idMaxTextField = new JTextField();
//        this.idMaxTextField.setBounds(idMaxLabel.getX() + idMaxLabel.getWidth(), y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
//        this.add(this.idMaxTextField);

        JLabel idMaxLabel = new JLabel("Max passenger Id: ");
        idMaxLabel.setBounds(this.x , y + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(idMaxLabel);


        this.idMaxTextField = new JTextField();
        this.idMaxTextField.setBounds(idMaxLabel.getX() + Constants.LABEL_WIDTH, this.idMinTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.idMaxTextField);

        createPClassFilter();
        createNameFilter();
        createSexFilter();
        createSibSpFilter();
        createParchFilter ();
        createTicketFilter ();
        createFareFilter ();
        createCabinFilter();
        createEmbarkedFilter();

        createFilterButton();
        createStatisticsButton();
    }

    private void createPClassFilter() {
        JLabel pClassLabel = new JLabel("Passenger class: ");
        pClassLabel.setBounds(this.x, this.idMaxTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(pClassLabel);

        this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.pClassComboBox.setBounds(pClassLabel.getX() + pClassLabel.getWidth(), pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.pClassComboBox);
        this.pClassComboBox.addActionListener((e) -> {
            this.classType = this.pClassComboBox.getSelectedIndex();
        });
    }

    private void createNameFilter (){
        JLabel nameLabel = new JLabel("Passenger name: ");
        nameLabel.setBounds(this.x , this.pClassComboBox.getY() + Constants.COMBO_BOX_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(nameLabel);

        this.nameTextField = new JTextField();
        this.nameTextField.setBounds(nameLabel.getX() + nameLabel.getWidth() , nameLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.nameTextField);
    }

    private void createSexFilter (){
        JLabel sexLabel = new JLabel("Passenger sex: ");
        sexLabel.setBounds(this.x , this.nameTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(sexLabel);

        this.sexComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
        this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth() , sexLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.sexComboBox);
        this.sexChoice = this.sexComboBox.getItemAt(0);
        this.sexComboBox.addActionListener((e) -> {
            this.sexChoice = (String) this.sexComboBox.getSelectedItem();
//            System.out.println(this.sexChoice);
        });
    }

    private void createSibSpFilter (){
        JLabel sibSpLabel = new JLabel("Passenger number of siblings: ");
        sibSpLabel.setBounds(this.x,this.sexComboBox.getY() + Constants.COMBO_BOX_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(sibSpLabel);

        this.sibSpTextField = new JTextField();
        this.sibSpTextField.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth(), sibSpLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.sibSpTextField);
    }

    private void createParchFilter (){
        JLabel parchLabel = new JLabel("Passenger number of children / parents: ");
        parchLabel.setBounds(this.x,this.sibSpTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(parchLabel);

        this.parchTextField = new JTextField();
        this.parchTextField.setBounds(parchLabel.getX() + parchLabel.getWidth(), parchLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.parchTextField);
    }

    private void createTicketFilter (){
        JLabel ticketLabel = new JLabel("Passenger number of ticket: ");
        ticketLabel.setBounds(this.x,this.parchTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(ticketLabel);

        this.ticketTextField = new JTextField();
        this.ticketTextField.setBounds(ticketLabel.getX() + ticketLabel.getWidth(), ticketLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.ticketTextField);
    }

    private void createFareFilter (){
        JLabel fareMinLabel = new JLabel("Min passenger ticket fare: ");
        fareMinLabel.setBounds(this.x, this.ticketTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(fareMinLabel);

        this.fareMinTextField = new JTextField();
        this.fareMinTextField.setBounds(fareMinLabel.getX() + fareMinLabel.getWidth(), fareMinLabel.getY(),Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.fareMinTextField);

        JLabel fareMaxLabel = new JLabel("Max passenger ticket fare: ");
        fareMaxLabel.setBounds(this.x, this.fareMinTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(fareMaxLabel);

        this.fareMaxTextField = new JTextField();
        this.fareMaxTextField.setBounds(fareMaxLabel.getX() + fareMaxLabel.getWidth(), fareMaxLabel.getY(),Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.fareMaxTextField);
    }

    private void createCabinFilter (){
        JLabel cabinLabel = new JLabel("Passenger cabin number: ");
        cabinLabel.setBounds(this.x, this.fareMaxTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(cabinLabel);

        this.cabinTextField = new JTextField();
        this.cabinTextField.setBounds(cabinLabel.getX() + cabinLabel.getWidth(), cabinLabel.getY(),Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.add(this.cabinTextField);
    }

    private void createEmbarkedFilter (){
        JLabel embarkedLabel = new JLabel("Passenger embarked: ");
        embarkedLabel.setBounds(this.x, this.cabinTextField.getY() + Constants.LABEL_HEIGHT + Constants.SPACE, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(embarkedLabel);

        this.embarkedComboBox = new JComboBox<>(Constants.PASSENGER_EMBARKED_OPTIONS);
        this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth(), embarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.embarkedComboBox);
        this.embarkedChoice = this.embarkedComboBox.getItemAt(0).charAt(0);
        this.embarkedComboBox.addActionListener((e) -> {
            this.embarkedChoice =((String)this.embarkedComboBox.getSelectedItem()).charAt(0);
//            System.out.println(this.embarkedChoice);
        });
    }

    private void createFilterButton(){
        this.filterButton = new JButton("Filter");
        this.filterButton.setBounds(this.x, Constants.WINDOW_HEIGHT - 2*(Constants.MARGIN_FROM_TOP + Constants.BUTTON_HEIGHT) , Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.add(this.filterButton);
    }

    private void createStatisticsButton (){
        this.statisticsButton = new JButton("Statistics");
        this.statisticsButton.setBounds(Constants. WINDOW_WIDTH - (Constants.BUTTON_WIDTH + Constants.MARGIN_FROM_LEFT + 2*Constants.SPACE), this.filterButton.getY() , Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT );
        this.add(this.statisticsButton);
    }

    private void filtering (){
        this.filterButton.addActionListener((e) -> {
            int minId = 0;
            try {
                minId = Integer.parseInt(this.idMinTextField.getText());
            }catch (NumberFormatException exception) {
//                exception.printStackTrace();
            }

            int maxId = this.passengerList.size();
            try {
                maxId = Integer.parseInt(this.idMaxTextField.getText());
            }catch (NumberFormatException exception) {
//                exception.printStackTrace();
            }

            int finalMinId = minId;
            int finalMaxId = maxId;
            List<Passenger> filteredList = this.passengerList
                    .stream()
                    .filter(passenger -> passenger.isIdInRange(finalMinId, finalMaxId))
                    .filter(passenger -> passenger.isNameCorrect(this.nameTextField.getText()))
                    .filter(passenger -> passenger.isSameClass(this.classType))
                    .filter(passenger -> passenger.isSameSex(this.sexChoice))
                    .sorted(Comparator.comparing(Passenger::getFormattedName))
                    .collect(Collectors.toList());
            System.out.println(filteredList);

            this.filterCounter++;

            String filteredPassengers = this.firstLine + "\n";

            for (Passenger passenger: filteredList) {
                filteredPassengers += passenger.toString();

            }

            writeToFile( filteredPassengers,Constants.PATH_TO_FILTERED_FILE + this.filterCounter + ".csv");

        });

    }

    private void writeToFile (String text , String path){
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}