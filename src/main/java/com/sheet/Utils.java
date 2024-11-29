package com.sheet;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Utils {
    public static void SetPanePadding(Pane pane,int padding){
        pane.setPadding(new Insets(padding));
    }
    public static void SetFontStyle(Labeled label, String family, FontWeight fontWeight, double  size){
        label.setFont(Font.font(family, fontWeight, size));
    }
       public static void ClearFields(Node... fields) {
        for (Node field : fields) {
            if (field instanceof TextInputControl) {
                ((TextInputControl) field).clear();
            } else if (field instanceof DatePicker) {
                ((DatePicker) field).setValue(null);
            }
        }
    }
    public static FontWeight GetFontWeight(Weight weight) {
        switch (weight) {
            default:
            case Bold:
                return FontWeight.BOLD;
            case Light:
                return FontWeight.LIGHT;
            case Thin:
                return FontWeight.THIN;
            case Normal:
                return FontWeight.NORMAL;
            case Medium:
                return FontWeight.MEDIUM;
            case ExtraBold:
                return FontWeight.EXTRA_BOLD;
            case SemiBold:
                return FontWeight.SEMI_BOLD;
        }
    }
}
