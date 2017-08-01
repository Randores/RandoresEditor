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
package com.gmail.socraticphoenix.randores.editor.model.ability;

import com.gmail.socraticphoenix.jlsc.value.annotation.ConversionConstructor;
import com.gmail.socraticphoenix.jlsc.value.annotation.Convert;
import com.gmail.socraticphoenix.jlsc.value.annotation.Convertible;
import com.gmail.socraticphoenix.jlsc.value.annotation.Index;

import java.util.ArrayList;
import java.util.List;

@Convertible
public class PotionModel {
    public static final List<String> KNOWN_POTIONS = new ArrayList<>();

    static {
        List<String> potions = PotionModel.KNOWN_POTIONS;
        potions.add("minecraft:speed");
        potions.add("minecraft:slowness");
        potions.add("minecraft:haste");
        potions.add("minecraft:mining_fatigue");
        potions.add("minecraft:strength");
        potions.add("minecraft:instant_health");
        potions.add("minecraft:instant_damage");
        potions.add("minecraft:jump_boost");
        potions.add("minecraft:nausea");
        potions.add("minecraft:regeneration");
        potions.add("minecraft:resistance");
        potions.add("minecraft:fire_resistance");
        potions.add("minecraft:water_breathing");
        potions.add("minecraft:invisibility");
        potions.add("minecraft:blindness");
        potions.add("minecraft:night_vision");
        potions.add("minecraft:hunger");
        potions.add("minecraft:weakness");
        potions.add("minecraft:poison");
        potions.add("minecraft:wither");
        potions.add("minecraft:health_boost");
        potions.add("minecraft:absorption");
        potions.add("minecraft:saturation");
        potions.add("minecraft:glowing");
        potions.add("minecraft:levitation");
        potions.add("minecraft:luck");
        potions.add("minecraft:unluck");
    }

    @Convert(value = 0, reflect = false)
    private String id;

    @ConversionConstructor
    public PotionModel(@Index(0) String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public PotionModel setId(String id) {
        this.id = id;
        return this;
    }

}
