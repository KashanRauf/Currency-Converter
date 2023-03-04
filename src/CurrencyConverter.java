import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyConverter extends Frame {

    static final int WIDTH = 450;
    static final int HEIGHT = 200;

    Map<String, Currency> currencyMap = Map.of(
        "CAD", new Currency("Canadian dollar", '$', 2, 1.0),
        "USD", new Currency("US dollar", '$', 2, 1.36),
        "AED", new Currency("UAE Dirham", 'د', 2, 0.37),
        "JPY", new Currency("Japanese Yen", '¥', 0, 0.01),
        "KRW", new Currency("Korean Won", '₩', 0, 0.0011)
    );

    public CurrencyConverter() {

        // Create the frame, will be visible after adding components
        setTitle("Currency Converter");
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(240, 240, 240));
        setLayout(null);

        addWindowListener(new WindowListener() {    
            @Override
            public void windowOpened(WindowEvent e) {
                return;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                return;
            }

            @Override
            public void windowIconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowActivated(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                return;
            }    
        });    

        // Field for input
        Label inputLabel = new Label("Currency to convert:");
        inputLabel.setBounds(50, 50, 120, 12);
        inputLabel.setFont(new Font("font", Font.PLAIN, 12));
        inputLabel.setAlignment(Label.LEFT);
        add(inputLabel);
        
        TextField input = new TextField();
        input.setBounds(50, 67, 120, 20);
        input.setEditable(true);
        add(input);

        Choice inputCurrency = new Choice();
        inputCurrency.add("CAD");
        inputCurrency.add("USD");
        inputCurrency.add("AED");
        inputCurrency.add("JPY");
        inputCurrency.add("KRW");
        inputCurrency.setBounds(50, 92, 120, 20);
        add(inputCurrency);

        // Field for output
        Label outputLabel = new Label("Converted value:");
        outputLabel.setBounds(WIDTH-170, 50, 120, 20);
        outputLabel.setAlignment(Label.LEFT);
        add(outputLabel);

        TextField converted = new TextField();
        converted.setBounds(WIDTH-170, 67, 120, 20);
        converted.setEditable(false);
        converted.setBackground(Color.WHITE);
        add(converted);

        Choice outputCurrency = new Choice();
        outputCurrency.add("CAD");
        outputCurrency.add("USD");
        outputCurrency.add("AED");
        outputCurrency.add("JPY");
        outputCurrency.add("KRW");
        outputCurrency.setBounds(WIDTH-170, 92, 120, 20);
        add(outputCurrency);

        // Convert button
        Button convert = new Button("Convert");
        convert.setBounds((WIDTH-80)/2, HEIGHT-75, 80, 65);
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = input.getText();
                if (inputText.isBlank() || inputText.startsWith("-")) {
                    return;
                }
                try {
                    Double.parseDouble(inputText);
                } catch (IllegalArgumentException err) {
                    System.out.println("Can not parse double.");
                    return;
                }

                Currency converting = currencyMap.get(inputCurrency.getSelectedItem());
                Currency convertTo = currencyMap.get(outputCurrency.getSelectedItem());

                BigDecimal bd = new BigDecimal(Double.parseDouble(inputText));
                bd = bd.setScale(converting.getDecimals(), RoundingMode.DOWN);

                double inputValue = bd.doubleValue();
                double outputValue = inputValue * (converting.getRate()/convertTo.getRate());

                bd = new BigDecimal(outputValue);
                bd = bd.setScale(convertTo.getDecimals(), RoundingMode.DOWN);

                converted.setText(convertTo.getSymbol() + bd.toString());
            }
        });
        add(convert);

        setVisible(true);
    }
}


// INSTEAD OF ARRAY OF CURRENCIES AND CURRENCIES AS COMBO OPTIONS
// MAKE A HASH OF CURRENCY NAMES AND VALUES
// NEED THE CURRENCY SIGN SOMEHOW  (string concat val/currency and use substring?)