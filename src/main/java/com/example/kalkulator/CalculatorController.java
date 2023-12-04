package com.example.kalkulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField display;

    private StringBuilder trenutno = new StringBuilder();
    private String operator = "";
    private Double prviBr;
    private boolean noviOperation = true;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
            Button b = (Button) event.getSource();
            String unos = b.getText();

            if (unos.equals("=")) {
                rezultat();
            } else if (unos.equals("clr")){
                reset();
            } else if (unos.matches("[+\\-*/%]")) {
                operatori(unos);
            } else if (unos.equals(".")) {
                decimalno();
            } else if (unos.matches("[0-9]")) {
                brojevi(unos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void operatori(String operator) {
        if (!trenutno.toString().isEmpty()) {
            if (prviBr == null) {
                prviBr = Double.parseDouble(display.getText());
            } else {
                rezultat();
            }

            this.operator = operator;
            display.setText(display.getText() + " " + operator);
            trenutno = new StringBuilder();
            noviOperation = false;
        }
    }

    private void brojevi(String number) {
        if (noviOperation) {
            trenutno = new StringBuilder(number);
            noviOperation = false;
        } else {
            trenutno.append(number);
        }
        display.setText(display.getText() + number);
    }

    private void decimalno() {
        if (!trenutno.toString().contains(".")) {
            trenutno.append(".");
            display.setText(display.getText() + ".");
            noviOperation = false;
        }
    }

    private void rezultat() {
        if (!trenutno.toString().isEmpty() && prviBr != null) {
            double drugiBr = Double.parseDouble(trenutno.toString());
            double rez = 0.0;

            switch (operator) {
                case "+":
                    rez = prviBr + drugiBr;
                    break;
                case "-":
                    rez = prviBr - drugiBr;
                    break;
                case "*":
                    rez = prviBr * drugiBr;
                    break;
                case "/":
                    if (drugiBr != 0) {
                        rez = prviBr / drugiBr;
                    } else {
                        display.setText("Error");
                        reset();
                        return;
                    }
                    break;
                case "%":
                    rez = prviBr % drugiBr;
                    break;
            }

            display.setText(String.valueOf(rez));
            prviBr = null;
            trenutno = new StringBuilder();
            noviOperation = true;
        }
    }

    private void reset() {
        prviBr = null;
        trenutno = new StringBuilder();
        operator = "";
        noviOperation = true;
        display.clear();
    }
}
