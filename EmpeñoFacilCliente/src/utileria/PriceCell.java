/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utileria;

import javafx.scene.control.TableCell;

/**
 *
 * @author YZ
 */
public class PriceCell<S> extends TableCell<S, Integer> {

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            String precioString = item.toString();
            String pesos = precioString.substring(0, precioString.length() - 2);
            String centavos = precioString.substring(precioString.length() - 2, precioString.length());
            setText("$" + pesos + "." + centavos);
        } else {
            setText("");
        }

    }
}
