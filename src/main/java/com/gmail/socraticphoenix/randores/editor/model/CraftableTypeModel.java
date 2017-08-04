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
package com.gmail.socraticphoenix.randores.editor.model;

import com.gmail.socraticphoenix.jlsc.value.annotation.ConversionConstructor;
import com.gmail.socraticphoenix.jlsc.value.annotation.Convert;
import com.gmail.socraticphoenix.jlsc.value.annotation.Convertible;
import com.gmail.socraticphoenix.jlsc.value.annotation.Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Convertible
public class CraftableTypeModel {
    public static final List<String> KNOWN_CRAFTABLE_TYPES = new ArrayList<>();
    public static final Map<String, Integer> KNOWN_SIZES = new HashMap<>();

    static {
        List<String> list = KNOWN_CRAFTABLE_TYPES;
        list.add("randores.items.axe");
        list.add("randores.items.hoe");
        list.add("randores.items.pickaxe");
        list.add("randores.items.shovel");
        list.add("randores.items.helmet");
        list.add("randores.items.chestplate");
        list.add("randores.items.leggings");
        list.add("randores.items.boots");
        list.add("randores.items.sword");
        list.add("randores.items.battleaxe");
        list.add("randores.items.sledgehammer");
        list.add("randores.items.bow");
        list.add("randores.items.stick");
        list.add("randores.items.bricks");
        list.add("randores.items.torch");

        Map<String, Integer> map = KNOWN_SIZES;
        map.put("randores.items.bricks", 4);
        map.put("randores.items.torch", 4);
        map.put("randores.items.stick", 2);
    }

    @Convert(value = 0, reflect = false)
    private String value;

    @ConversionConstructor
    public CraftableTypeModel(@Index(0) String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}