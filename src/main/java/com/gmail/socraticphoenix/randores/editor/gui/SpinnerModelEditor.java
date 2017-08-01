/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 socraticphoenix@gmail.com
 * Copyright (c) 2017 contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.gmail.socraticphoenix.randores.editor.gui;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JSpinner;

import java.awt.Font;
import java.text.ParseException;

public class SpinnerModelEditor extends JSpinner.DefaultEditor {

    public SpinnerModelEditor(JSpinner spinner) {
        super(spinner);
        this.getTextField().setEditable(true);
        this.getTextField().setEnabled(true);
        this.getTextField().setFont(this.getTextField().getFont().deriveFont(Font.PLAIN));
        this.getTextField().setFormatterFactory(new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                return new AbstractFormatter() {
                    @Override
                    public Object stringToValue(String text) throws ParseException {
                        try {
                            return Integer.parseInt(text);
                        } catch (NumberFormatException e) {
                            throw new ParseException("Bad number: " + e.getMessage(), 0);
                        }
                    }

                    @Override
                    public String valueToString(Object value) throws ParseException {
                        return String.valueOf(value);
                    }
                };
            }
        });
    }

}
