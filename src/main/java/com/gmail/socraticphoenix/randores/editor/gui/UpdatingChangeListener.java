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

import com.gmail.socraticphoenix.randores.editor.model.DefinitionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class UpdatingChangeListener<T extends JComponent, K> implements ChangeListener, ActionListener {
    private T type;
    private Function<T, K> getter;
    private BiConsumer<DefinitionModel, K> setter;
    private ProjectScreen screen;

    public UpdatingChangeListener(T type, Function<T, K> getter, BiConsumer<DefinitionModel, K> setter, ProjectScreen screen) {
        this.type = type;
        this.getter = getter;
        this.setter = setter;
        this.screen = screen;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.doUpdate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.doUpdate();
    }

    private void doUpdate() {
        Optional<DefinitionModel> modelOptional = this.screen.getModel();
        if (modelOptional.isPresent()) {
            DefinitionModel model = modelOptional.get();
            this.setter.accept(model, this.getter.apply(this.type));
        } else {
            JOptionPane.showMessageDialog(null, "No definition is selected! Edits you are making will not be applied to a definition...", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

}
