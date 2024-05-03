import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GadgetShop extends JFrame implements ActionListener {
    private ArrayList<Gadget> gadgets;
    private JTextField modelField, priceField, weightField, sizeField, creditField, memoryField, phoneNumberField, durationField, downloadField;
    private JButton addMobileButton, addMP3Button, clearButton, displayAllButton, makeCallButton, downloadMusicButton;

    public GadgetShop() {
        super("Gadget Shop");
        gadgets = new ArrayList<>();
        initializeComponents();
        layoutComponents();
        addActionListeners();
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
      modelField = new JTextField(10);
    priceField = new JTextField(10);
    weightField = new JTextField(10);
    sizeField = new JTextField(10);
    creditField = new JTextField(10);
    memoryField = new JTextField(10);
    phoneNumberField = new JTextField(10);
    durationField = new JTextField(10);
    downloadField = new JTextField(10);
    

    addMobileButton = new JButton("Add Mobile");
    addMP3Button = new JButton("Add MP3");
    clearButton = new JButton("Clear");
    displayAllButton = new JButton("Display All");
    makeCallButton = new JButton("Make A Call");
    downloadMusicButton = new JButton("Download Music");
    }
    
private void addComponent(Component component, GridBagConstraints gbc, int y, int x) {
    gbc.gridx = x;
    gbc.gridy = y;
    add(component, gbc);

    }
    
    private void layoutComponents() {
   setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(1, 1, 1, 1); 
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 1;

    int row = 0; 
    addComponent(new JLabel("Model:"), gbc, row, 0);
    addComponent(modelField, gbc, row++, 1);
    addComponent(new JLabel("Price:"), gbc, row, 0);
    addComponent(priceField, gbc, row++, 1);
    addComponent(new JLabel("Weight:"), gbc, row, 0);
    addComponent(weightField, gbc, row++, 1);
    addComponent(new JLabel("Size:"), gbc, row, 0);
    addComponent(sizeField, gbc, row++, 1);
    addComponent(new JLabel("Credit:"), gbc, row, 0);
    addComponent(creditField, gbc, row++, 1);
    addComponent(new JLabel("Memory:"), gbc, row, 0);
    addComponent(memoryField, gbc, row++, 1);
    addComponent(new JLabel("Phone No:"), gbc, row, 0);
    addComponent(phoneNumberField, gbc, row++, 1);
    addComponent(new JLabel("Duration:"), gbc, row, 0);
    addComponent(durationField, gbc, row++, 1);
    addComponent(new JLabel("Download (MB):"), gbc, row, 0);
    addComponent(downloadField, gbc, row++, 1);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(addMobileButton);
    buttonPanel.add(addMP3Button);
    buttonPanel.add(clearButton);
    buttonPanel.add(displayAllButton);
    buttonPanel.add(makeCallButton);
    buttonPanel.add(downloadMusicButton);

    gbc.gridy++;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    add(buttonPanel, gbc);
}

private void addActionListeners() {
    
        clearButton.addActionListener(this);
        displayAllButton.addActionListener(this);
        makeCallButton.addActionListener(this);
        downloadMusicButton.addActionListener(this);
        addMobileButton.addActionListener(this);
        addMP3Button.addActionListener(this);
    }


@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addMobileButton) {
            addMobile();
        } else if (source == addMP3Button) {
            addMP3();
        } else if (source == clearButton) {
             clearFields();
        } else if (source == displayAllButton) {
            displayAllGadgets();
        } else if (source == makeCallButton) {
            makeCall();
        } else if (source == downloadMusicButton) {
            downloadMusic();
         }
}

        private void clearFields() 
        {
    modelField.setText("");
    priceField.setText("");
    weightField.setText("");
    sizeField.setText("");
    creditField.setText("");
    memoryField.setText("");
    phoneNumberField.setText("");
    durationField.setText("");
    downloadField.setText("");

    System.out.println("All fields have been reset.");
}

private void makeCall() {   
    String phoneNumber = phoneNumberField.getText();
    int duration;
    boolean found = false;
    String model = modelField.getText();
    try {
        duration = Integer.parseInt(durationField.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Please enter a valid duration.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (duration < 0) {
        JOptionPane.showMessageDialog(null, "Error: Duration cannot be negative.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
    for (Gadget gadget : gadgets) {
        if (gadget instanceof Mobile && gadget.getModel().equals(model)) {
            ((Mobile) gadget).makeCall(phoneNumber, duration);
            System.out.println("Call made using " + model + " to " + phoneNumber + " for " + duration + " minutes.");
        
        }
            found = true;
            break;
        }
    if (!found) {
        System.out.println("No mobile found with model " + model + " to make the call.");
    }
    }


private void downloadMusic() {
    String model = modelField.getText();
    int downloadSize;
    boolean found = false;
    try {
        downloadSize = Integer.parseInt(downloadField.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Download size cannot be negative.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (downloadSize < 0) {
        JOptionPane.showMessageDialog(null, "Error: Download size cannot be negative.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }
    for (Gadget gadget : gadgets) {
        if (gadget instanceof MP3 && gadget.getModel().equals(model)) {
            ((MP3) gadget).downloadMusic(downloadSize);
            System.out.println("Music downloaded using " + model + ", " + downloadSize + " MB used.");
            found = true;
            break;
        }
    }
    if (!found) {
        System.out.println("No MP3 player found with model " + model + " to download music.");
    }
    }
    
    private void addMobile() {
    String model = modelField.getText();
    double price;
    int weight;
    String size = sizeField.getText();
    int credit;
    String phoneNumber = phoneNumberField.getText();  
    if (!phoneNumber.matches("[0-9]+")) {
        JOptionPane.showMessageDialog(null, "Error: Phone number cannot contain letters or symbols.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return; 
    }
    try {
        price = Double.parseDouble(priceField.getText());
        weight = Integer.parseInt(weightField.getText());
        credit = Integer.parseInt(creditField.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: Please enter valid numbers for price, weight, and credit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;  
    }
    Mobile mobile = new Mobile(model, price, weight, size, credit);
    gadgets.add(mobile);
    System.out.println("Mobile added: " + model + " with phone number: " + phoneNumber + "\n");
    }

    private void addMP3() 
    {
        String model = modelField.getText();
        double price = Double.parseDouble(priceField.getText());
        int weight = Integer.parseInt(weightField.getText());
        String size = sizeField.getText();
        int memory = Integer.parseInt(memoryField.getText());
        MP3 mp3 = new MP3(model, price, weight, size, memory);
        gadgets.add(mp3);
        System.out.println("MP3 added: " + model + "\n");
    }
    
    private void displayAllGadgets() 
    {
    for (Gadget gadget : gadgets) {
        if (gadget instanceof Mobile) {
            Mobile mobile = (Mobile) gadget;
            System.out.println("************************");
            System.out.println("PHONE:");
            System.out.println("Model: " + mobile.getModel());
            System.out.println("Price: £" + mobile.getPrice());
            System.out.println("Weight: " + mobile.getWeight() + " grams");
            System.out.println("Size: " + mobile.getSize());
            System.out.println("Calling Credit: " + mobile.getCallingCredit() + " minutes");
            System.out.println("************************");
            System.out.println();
        } else if (gadget instanceof MP3) {
            MP3 mp3 = (MP3) gadget;
            System.out.println("************************");
            System.out.println("MP3:");
            System.out.println("Model: " + mp3.getModel());
            System.out.println("Price: £" + mp3.getPrice());
            System.out.println("Weight: " + mp3.getWeight() + " grams");
            System.out.println("Size: " + mp3.getSize());
            System.out.println("Available Memory: " + mp3.getAvailableMemory() + " MB");
            System.out.println("************************");
            System.out.println();
        }
    }
}

    

    public static void main(String[] args) {
        new GadgetShop();
    }
}