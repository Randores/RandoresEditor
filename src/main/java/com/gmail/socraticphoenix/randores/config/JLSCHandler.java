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
package com.gmail.socraticphoenix.randores.config;

import com.gmail.socraticphoenix.jlsc.registry.JLSCRegistry;
import com.gmail.socraticphoenix.jlsc.serialization.JLSCCollectionSerializer;
import com.gmail.socraticphoenix.randores.editor.model.CraftableModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.AbilityModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.PotionEffectModel;
import com.gmail.socraticphoenix.randores.editor.model.ability.PotionModel;
import com.gmail.socraticphoenix.randores.editor.model.property.FlammablePropertyModel;
import com.gmail.socraticphoenix.randores.editor.model.property.PropertyModel;

import java.util.ArrayList;

public class JLSCHandler {

    public static void init() {
        JLSCRegistry.register(new JLSCColorProcessor());
        JLSCRegistry.register(new JLSCCollectionSerializer(ArrayList.class, PropertyModel.class, false, ArrayList::new, "arrayList"));
        JLSCRegistry.register(new JLSCCollectionSerializer(ArrayList.class, AbilityModel.class, false, ArrayList::new, "arrayList"));
        JLSCRegistry.register(new JLSCCollectionSerializer(ArrayList.class, CraftableModel.class, false, ArrayList::new, "arrayList"));

        JLSCRegistry.registerAnnotated(PotionEffectModel.class, false, "potionEffect");
        JLSCRegistry.registerAnnotated(PotionModel.class, false, "potion");
        JLSCRegistry.registerAnnotated(FlammablePropertyModel.class, false, "flammable");
        JLSCRegistry.registerAnnotated(CraftableModel.class);
    }

}
