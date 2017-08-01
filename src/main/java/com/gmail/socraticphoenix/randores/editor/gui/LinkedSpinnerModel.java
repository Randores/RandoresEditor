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

import com.gmail.socraticphoenix.collect.coupling.Pair;
import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class LinkedSpinnerModel extends AbstractSpinnerModel {
    private Integer min;
    private Integer max;
    private SpinnerModel link;

    private int value;

    private int start;

    public LinkedSpinnerModel(Integer min, Integer max, SpinnerModel link, int start) {
        this.min = min;
        this.max = max;
        this.link = link;
        this.value = start;
        this.start = start;
    }

    public Integer getMin() {
        return this.min;
    }

    public Integer getMax() {
        return this.max;
    }

    public SpinnerModel getLink() {
        return this.link;
    }

    public int getStart() {
        return this.start;
    }

    public static LinkedSpinnerModel lessThan(int min, SpinnerModel lessThan, int start) {
        return new LinkedSpinnerModel(min, null, lessThan, start);
    }

    public static LinkedSpinnerModel greaterThan(int max, SpinnerModel greaterThan, int start) {
        return new LinkedSpinnerModel(null, max, greaterThan, start);
    }

    public static Pair<LinkedSpinnerModel, LinkedSpinnerModel> dualLinked(int min, int max, int start) {
        LinkedSpinnerModel small = new LinkedSpinnerModel(min, null, null, start);
        LinkedSpinnerModel large = new LinkedSpinnerModel(null, max, small, start);
        small.link = large;
        return Pair.of(small, large);
    }

    public static void dualLink(JSpinner minSpin, JSpinner maxSpin, int min, int max, int start) {
        Pair<LinkedSpinnerModel, LinkedSpinnerModel> link = LinkedSpinnerModel.dualLinked(min, max, start);
        minSpin.setModel(link.getA());
        maxSpin.setModel(link.getB());
    }

    public static void dualLinkZeroMax(JSpinner minSpin, JSpinner maxSpin) {
        LinkedSpinnerModel.dualLink(minSpin, maxSpin, 0, Integer.MAX_VALUE, 0);
    }

    public static void dualLinkOneMax(JSpinner minSpin, JSpinner maxSpin) {
        LinkedSpinnerModel.dualLink(minSpin, maxSpin, 1, Integer.MAX_VALUE, 1);
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        if(value != null && (int) value != this.value) {
            this.value = clamp(value);
            super.fireStateChanged();
        }
    }

    @Override
    public Object getNextValue() {
        return clamp(this.value + 1);
    }

    @Override
    public Object getPreviousValue() {
        return clamp(this.value - 1);
    }

    private int clamp(Object in) {
        int value = this.start;
        if(in instanceof Integer) {
            int val = (Integer) in;
            if(this.min != null && val < this.min) {
                value = this.min;
            } else if (this.max != null && val > this.max) {
                value = this.max;
            } else {
                int link = this.getBound();
                if(this.min == null && val < link) {
                    value = link;
                } else if (this.max == null && val > link) {
                    value = link;
                } else {
                    value = val;
                }
            }
        }

        return value;
    }

    private int getBound() {
        Object link = this.link.getValue();
        if(link instanceof Integer) {
            return (int) link;
        } else {
            return this.start;
        }
    }

}
