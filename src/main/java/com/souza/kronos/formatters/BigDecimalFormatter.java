package com.souza.kronos.formatters;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class BigDecimalFormatter implements Formatter<BigDecimal> {

    @Override
    public String print(BigDecimal object, Locale locale) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        return numberFormat.format(object);
        
    }

    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        if (text == "")
        {
            text = "0,00";
        }

        text = text.replaceAll("[^\\d.,]", "").replace(".", "").replace(",", ".");
        return new BigDecimal(text);
    }

}
